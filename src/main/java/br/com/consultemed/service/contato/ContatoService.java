/**
 * 
 */
package br.com.consultemed.service.contato;

import java.util.List;

import br.com.consultemed.model.Contato;
import dao.ContatoDAO;

/**
 * @author carlosbarbosagomesfilho
 * Classe responável pelas regras de negócio da entidade contato.
 */

public class ContatoService implements IContato {
	
	
	 private ContatoDAO dao;
	 
	 public ContatoService() {
		 this.dao = new ContatoDAO();
	}

	@Override
	public void salvar(Contato contato) {
		this.dao.salvar(contato);
	}

	@Override
	public void remover(Long idContato) {
		
	}

	@Override
	public List<Contato> listarContatos() {
		return this.dao.contatos();
	}

	@Override
	public void editar(Contato contato) {
		this.dao.editar(contato);
	}

	@Override
	public Contato buscaContatoNome(String nome) {
		return this.dao.buscaPorNome(nome);
	}

	@Override
	public Contato buscaContatoEmail(String email) {
		return this.dao.buscaPorEmail(email);
	}

	@Override
	public Contato buscaContatoId(Long idContato) {
		return this.dao.buscaPorId(idContato);
	}
	
	
}
