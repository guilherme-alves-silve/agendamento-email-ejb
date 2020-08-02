package br.com.guilhermealvessilve.agendamentoemail.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.guilhermealvessilve.agendamentoemail.business.AgendamentoEmailBusiness;
import br.com.guilhermealvessilve.agendamentoemail.entity.AgendamentoEmail;
import br.com.guilhermealvessilve.agendamentoemail.interceptor.Logger;

@Logger
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class EmailConsumerMDB implements MessageListener {

	@Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;

    @Override
    public void onMessage(final Message message) {
       try {
           final var agendamentoEmail = message.getBody(AgendamentoEmail.class);
		   agendamentoEmailBusiness.enviaEmail(agendamentoEmail);
       } catch (JMSException e) {
    	   throw new RuntimeException(e);
       }
    }
}
