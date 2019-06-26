package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.utils.JPAUtils;

public class PacienteDAO {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();

	public void salvar(Paciente paciente) {

		this.factory.getTransaction().begin();
		this.factory.persist(paciente);
		this.factory.getTransaction().commit();
		this.factory.close();		
	};
	
	
	public Paciente buscaPorId(Long id) {
		this.factory.getTransaction().begin();
		Query query = 
				this.factory.createQuery("SELECT p FROM Paciente p WHERE p.id = :id");
		query.setParameter("id", id);
		Paciente paciente = (Paciente) query.getSingleResult();
		return paciente;
	}
}
