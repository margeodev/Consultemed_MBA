package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.model.Consulta;
import br.com.consultemed.utils.JPAUtils;

public class ConsultaDAO {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public void salvar(Consulta consulta) {

		this.manager.getTransaction().begin();
		this.manager.persist(consulta);
		this.manager.getTransaction().commit();
		this.manager.close();		
	};
	
	
	public List<Consulta> listarPorMedico(String crm) {
		manager = emf.createEntityManager();
		List<Consulta> consultas = manager
				.createQuery("FROM Consulta c WHERE c.agendamento.medico.crm = :crm", Consulta.class)
				.setParameter("crm", crm)
				.getResultList();
		manager.close();
		return consultas;
	}
	
	public List<Consulta> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal, String crm) {
		manager = emf.createEntityManager();
		List<Consulta> consultasPorData = manager
				.createQuery("FROM Consulta c WHERE c.agendamento.medico.crm = :crm AND c.agendamento.data "
						+ "BETWEEN :dataInicial AND :dataFinal", Consulta.class)
				.setParameter("crm", crm)
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.getResultList();
		manager.close();
		return consultasPorData;
	}
	
}
