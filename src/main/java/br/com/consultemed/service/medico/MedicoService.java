package br.com.consultemed.service.medico;

import br.com.consultemed.model.Medico;
import dao.MedicoDAO;

public class MedicoService implements IMedico {

	private MedicoDAO dao;
	
	public MedicoService() {
		this.dao = new MedicoDAO();
	}
	
	@Override
	public void salvar(Medico medico) {
		this.dao.salvar(medico);		
	}

	@Override
	public Medico buscarMedicoPorCrm(String crm) {
		try {
			return this.dao.buscaPorCrm(crm);			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
