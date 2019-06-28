package br.com.consultemed.service.consulta;

import java.time.LocalDate;
import java.util.List;

import br.com.consultemed.model.Consulta;
import dao.ConsultaDAO;

public class ConsultaService implements IConsulta {
	
	private ConsultaDAO dao;
	
	public ConsultaService() {
		this.dao = new ConsultaDAO();
	}

	@Override
	public void salvar(Consulta consulta) {
		this.dao.salvar(consulta);		
	}

	@Override
	public List<Consulta> buscarConsultasPorMedico(String crm) {
		return this.dao.listarPorMedico(crm);
	}

	@Override
	public List<Consulta> listarPorIntervaloDeDatas(LocalDate dataInicial, LocalDate dataFinal, String crm) {
		return this.dao.listarPorIntervaloDeDatas(dataInicial, dataFinal, crm);
	}

}
