package br.com.consultemed.service.paciente;

import br.com.consultemed.model.Paciente;

public interface IPaciente {

	public void salvar(Paciente paciente);
	public Paciente buscarPacientePorId(Long id);
	
}
