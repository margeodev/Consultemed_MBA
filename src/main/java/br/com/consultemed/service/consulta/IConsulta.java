package br.com.consultemed.service.consulta;

import java.time.LocalDate;
import java.util.List;

import br.com.consultemed.model.Consulta;

public interface IConsulta {

	public void salvar(Consulta consulta);
	public List<Consulta> buscarConsultasPorMedico(String crm);
	public List<Consulta> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal, String crm);	
}
