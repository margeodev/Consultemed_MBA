package br.com.consultemed.service.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.consultemed.model.Agendamento;
import dao.AgendamentoDAO;

public class AgendamentoService implements IAgendamento {
	
	private AgendamentoDAO dao;

	public AgendamentoService() {
		this.dao = new AgendamentoDAO();
	}
	
	@Override
	public void salvar(Agendamento agendamento) {
		this.dao.salvar(agendamento);		
	}

	@Override
	public List<Agendamento> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal) {
		return this.dao.listarPorIntervaloDeDatas(dataInicial, dataFinal);
	}
	
	@Override
	public List<Agendamento> listarTodos() {
		return this.dao.listarTodos();
	}

	@Override
	public List<Agendamento> listarPorMedico(String crm) {
		return this.dao.listarPorMedico(crm);
	}

	@Override
	public void excluir(Long id) {
		this.dao.excluir(id);		
	}

	@Override
	public void editar(Agendamento agendamento) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Agendamento buscarAgendamentoPorId(Long id) {
		return this.dao.getById(id);
	}

	@Override
	public List<Agendamento> buscarPorDataHora(LocalDate data, LocalDateTime hora) {
		return this.dao.buscarPorDataHora(data, hora);
	}
	

}
