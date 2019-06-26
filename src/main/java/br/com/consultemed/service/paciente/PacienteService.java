package br.com.consultemed.service.paciente;

import br.com.consultemed.model.Paciente;
import dao.PacienteDAO;

public class PacienteService implements IPaciente {
	
	private PacienteDAO dao;
	
	public PacienteService() {
		this.dao = new PacienteDAO();
	}

	@Override
	public void salvar(Paciente paciente) {
		this.dao.salvar(paciente);		
	}

	@Override
	public Paciente buscarPacientePorId(Long id) {
		try {
			return this.dao.buscaPorId(id);			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	

}
