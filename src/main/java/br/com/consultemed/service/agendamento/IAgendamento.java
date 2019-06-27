package br.com.consultemed.service.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.consultemed.model.Agendamento;

public interface IAgendamento {

	public void salvar(Agendamento agendamento);
	public List<Agendamento> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal);
	public List<Agendamento> listarPorMedico(String crm);
	public List<Agendamento> listarTodos();
	public void excluir(Long id);
	public void atualizar(Agendamento agendamento);
	public void desativar(Agendamento agendamento);
	public Agendamento buscarAgendamentoPorId(Long id);
	public List<Agendamento> buscarPorDataHora(LocalDate data, LocalDateTime hora);
	public List<Agendamento> exibeAgendamentosCancelados();
	
}
