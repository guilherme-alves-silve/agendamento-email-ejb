package br.com.guilhermealvessilve.agendamentoemail.dto;

import br.com.guilhermealvessilve.agendamentoemail.entity.AgendamentoEmail;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class AgendamentoEmailDto {

	@NotBlank(message="{agendamentoEmail.email.vazio}")
	@Email(message="#{agendamentoEmail.email.invalido}")
	private String email;

	@NotBlank(message="{agendamentoEmail.assunto.vazio}")
	private String assunto;

	@NotBlank(message="{agendamentoEmail.mensagem.vazio}")
	private String mensagem;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static AgendamentoEmail build(AgendamentoEmailDto dto) {

		AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
		agendamentoEmail.setEmail(dto.getEmail());
		agendamentoEmail.setAssunto(dto.getAssunto());
		agendamentoEmail.setMensagem(dto.getMensagem());

		return agendamentoEmail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AgendamentoEmailDto)) return false;
		AgendamentoEmailDto that = (AgendamentoEmailDto) o;
		return email.equals(that.email) &&
				assunto.equals(that.assunto) &&
				mensagem.equals(that.mensagem);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, assunto, mensagem);
	}
}
