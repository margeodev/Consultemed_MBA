package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.utils.JPAUtils;

public class AgendamentoDAO {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public Agendamento getById(final Long id) {
		return manager.find(Agendamento.class, id);
	}

	public void salvar(Agendamento agendamento) {

		this.manager.getTransaction().begin();
		this.manager.persist(agendamento);
		this.manager.getTransaction().commit();
		this.manager.close();
	};

	public List<Agendamento> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal) {
		manager = emf.createEntityManager();
		List<Agendamento> agendamentosPorData = manager
				.createQuery("from Agendamento a where a.data between :dataInicial and :dataFinal", Agendamento.class)
				.setParameter("dataInicial", dataInicial).setParameter("dataFinal", dataFinal).getResultList();
		manager.close();
		return agendamentosPorData;
	}
	
	public List<Agendamento> buscarPorDataHora(LocalDate data, LocalDateTime hora) {
		manager = emf.createEntityManager();
		List<Agendamento> agendamentosPorDataHora = manager
				.createQuery("FROM Agendamento a WHERE a.data = :data and a.hora = :hora", Agendamento.class)
				.setParameter("data", data).
				setParameter("hora", hora).
				getResultList();		
		return agendamentosPorDataHora;
	}

	public List<Agendamento> listarPorMedico(String crm) {
		manager = emf.createEntityManager();
		List<Agendamento> agendamentosPorData = manager
				.createQuery("from Agendamento a where a.medico.crm = :crm", Agendamento.class).setParameter("crm", crm)
				.getResultList();
		manager.close();
		return agendamentosPorData;
	}

	public void excluir(Long id) {
		try {
			Agendamento agendamento = getById(id);
			remove(agendamento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void remove(Agendamento agendamento) {
		try {
			this.manager.getTransaction().begin();
			agendamento = manager.find(Agendamento.class, agendamento.getId());
			manager.remove(agendamento);
			this.manager.getTransaction().commit();			
		} catch (Exception ex) {
			ex.printStackTrace();
			this.manager.getTransaction().rollback();
		}
	}

	public List<Agendamento> listarTodos() {
		manager = emf.createEntityManager();
		List<Agendamento> agendamentos = manager.createQuery("from Agendamento", Agendamento.class).getResultList();
		manager.close();
		return agendamentos;
	}

}
