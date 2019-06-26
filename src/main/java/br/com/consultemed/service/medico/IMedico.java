package br.com.consultemed.service.medico;

import br.com.consultemed.model.Medico;

public interface IMedico {

	public void salvar(Medico medico);
	public Medico buscarMedicoPorCrm(String crm);
	
}
