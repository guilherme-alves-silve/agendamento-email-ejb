package br.com.guilhermealvessilve.agendamentoemail.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BusinessException extends Exception{
	
	private final List<String> mensagens;
	
	public BusinessException() {
		super();
		mensagens = new ArrayList<>();
	}
	
	public BusinessException(String mensagem) {
		super(mensagem);
		mensagens = new ArrayList<>();
		mensagens.add(mensagem);
	}

	public List<String> getMensagens() {
		return Collections.unmodifiableList(mensagens);
	}

	public void addMensagem(String mensagem) {
		this.mensagens.add(Objects.requireNonNull(mensagem, "mensagem não pode ser nula!"));
	}
}
