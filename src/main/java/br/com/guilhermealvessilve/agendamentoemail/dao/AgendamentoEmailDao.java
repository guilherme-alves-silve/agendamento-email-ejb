package br.com.guilhermealvessilve.agendamentoemail.dao;

import br.com.guilhermealvessilve.agendamentoemail.entity.AgendamentoEmail;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AgendamentoEmailDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> lista() {
		return entityManager
				.createQuery("SELECT a FROM AgendamentoEmail a", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void salva(final AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public void atualiza(final AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}

	public List<AgendamentoEmail> listaPorEmail(final String email) {
		
		TypedQuery<AgendamentoEmail> query = entityManager.createQuery(
				"SELECT a FROM AgendamentoEmail a WHERE a.email =:email AND a.enviado = false",
				AgendamentoEmail.class);
		query.setParameter("email", email);
		
		return query.getResultList();
	}
	
    public List<AgendamentoEmail> listaNaoEnviados() {

		TypedQuery<AgendamentoEmail> query = entityManager.createQuery(
				"SELECT a FROM AgendamentoEmail a WHERE a.enviado = false",
				AgendamentoEmail.class);
		return query.getResultList();
	}
}
