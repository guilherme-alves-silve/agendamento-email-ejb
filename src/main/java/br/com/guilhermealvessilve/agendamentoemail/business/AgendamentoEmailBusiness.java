package br.com.guilhermealvessilve.agendamentoemail.business;

import br.com.guilhermealvessilve.agendamentoemail.dao.AgendamentoEmailDao;
import br.com.guilhermealvessilve.agendamentoemail.dto.AgendamentoEmailDto;
import br.com.guilhermealvessilve.agendamentoemail.entity.AgendamentoEmail;
import br.com.guilhermealvessilve.agendamentoemail.exception.BusinessException;
import br.com.guilhermealvessilve.agendamentoemail.interceptor.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Logger
@Stateless
public class AgendamentoEmailBusiness {
	
	@Inject
	private AgendamentoEmailDao dao;
	
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session sessaoEmail;

	private static String EMAIL_FROM = "mail.address";
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";
	
	public List<AgendamentoEmail> lista() {
		return dao.lista();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salva(@Valid AgendamentoEmailDto dto) throws BusinessException {

		final AgendamentoEmail agendamentoEmail = AgendamentoEmailDto.build(dto);

		final boolean emailJaAgendado = !dao
				.listaPorEmail(agendamentoEmail.getEmail())
				.isEmpty();
		if(emailJaAgendado) {
			throw new BusinessException("Email já está agendado.");
		}
		
		agendamentoEmail.setEnviado(false);
		dao.salva(agendamentoEmail);
	}
	
	 public List<AgendamentoEmail> listaNaoEnviados() {
	 
		 return dao.listaNaoEnviados();
	 }
	 
	 public void marcaEnviadas(final AgendamentoEmail agendamentoEmail) {
		 agendamentoEmail.setEnviado(true);
		 dao.atualiza(agendamentoEmail);
	 }
	 
	 public void enviaEmail(final AgendamentoEmail agendamentoEmail) {
		 try {
	       MimeMessage mensagem = new MimeMessage(sessaoEmail);
	       mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
	       mensagem.setRecipients(Message.RecipientType.TO, agendamentoEmail.getEmail());
	       mensagem.setSubject(agendamentoEmail.getAssunto());
	       mensagem.setText(Optional.ofNullable(agendamentoEmail.getMensagem()).orElse(""));

	       Transport.send(mensagem,
	                    sessaoEmail.getProperty(EMAIL_USER),
	                    sessaoEmail.getProperty(EMAIL_PASSWORD));
	     } catch (MessagingException ex) {
	        throw new RuntimeException(ex);
	     }
	 }
}
