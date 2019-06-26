package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Medico;
import br.com.consultemed.utils.JPAUtils;

public class MedicoDAO {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public void salvar(Medico medico) {

		this.manager.getTransaction().begin();
		this.manager.persist(medico);
		this.manager.getTransaction().commit();
		this.manager.close();		
	};
	
	
	public Medico buscaPorCrm(String crm) {
		this.manager.getTransaction().begin();
		Query query = 
				this.manager.createQuery("SELECT m FROM Medico m WHERE m.crm = :crm");
		query.setParameter("crm", crm);
		Medico medico = (Medico) query.getSingleResult();
		return medico;
	}

}
