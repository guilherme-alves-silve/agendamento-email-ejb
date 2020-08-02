package br.com.guilhermealvessilve.agendamentoemail.timer;

import br.com.guilhermealvessilve.agendamentoemail.business.AgendamentoEmailBusiness;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Singleton
public class EmailTimerProducerMDB {
	
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Inject
	@JMSConnectionFactory(value = "java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Schedule(hour="*",minute="*")
	public void enviaEmailsAgendamentoAFila() {

		agendamentoEmailBusiness.listaNaoEnviados()
			.forEach(agendamentoEmail-> {
				context.createProducer().send(queue, agendamentoEmail);
				agendamentoEmailBusiness.marcaEnviadas(agendamentoEmail);
			});
	}
}
