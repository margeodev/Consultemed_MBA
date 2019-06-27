package br.com.consultemed.service.paciente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.consultemed.model.Contato;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.GeneralUtils;

public class PacienteUtils {
	
	private static List<Contato> contatos = new ArrayList<>();

	public static void exibePaciente(Paciente paciente) {
		System.out.println("===============================");
		System.out.println("|      Ficha do paciente      |");
		System.out.println("===============================");
		System.out.println("| Nome: " + paciente.getNome());
		System.out.println("| CPF: " + paciente.getCpf());
		System.out.println("| Telefone: " + paciente.getTelefone());
		exibeContatosDoPaciente(paciente);
		System.out.println("===============================\n\n");
	}
	
	public static void exibeContatosDoPaciente(Paciente paciente) {
		System.out.println("| ----------------------");
		System.out.println("| Lista de contatos:");
		System.out.println("| ----------------------");
		for(Contato c: paciente.getContatos()) {
			System.out.println("| Nome: " + c.getNome());
			System.out.println("| Email: " + c.getEmail());
			System.out.println("| Telefone: " + c.getTelefone());
			System.out.println("| ----------------------");			
		}
	}
	
	public static Paciente buscarPaciente() throws IOException {
		System.out.println("Informe o código do paciente: ");	
		String digitado = GeneralUtils.lerLinha();
		Long id = 0L;
		
		if(isDigit(digitado)) {
			id  = Long.parseLong(digitado);	
		} else {
			System.out.println("Para pesquisar um paciente a entrada deve ser numérica.\n");
			buscarPaciente();
		}
		
		PacienteService service = new PacienteService();
		Paciente paciente = new Paciente();
		paciente = service.buscarPacientePorId(id);
		return paciente;
	}
	
	public static void cadastrarPaciente() throws IOException {
		System.out.println("Informe o nome do paciente:");
		String nome = GeneralUtils.lerLinha();
		
		System.out.println("Informe o cpf do paciente:");
		String cpf = GeneralUtils.lerLinha();
		
		System.out.println("Informe o telefone do paciente:");
		String telefone = GeneralUtils.lerLinha();
		
		Paciente paciente = new Paciente();
		paciente.setNome(nome);
		paciente.setCpf(cpf);
		paciente.setTelefone(telefone);
		
		System.out.println("\n-------------------------------------------");
		System.out.println("Você deve cadastrar pelo menos 1 contato.\n");
		contatos.clear();
		adicionarContato();
		paciente.setContatos(contatos);
		
		PacienteService service = new PacienteService();
		service.salvar(paciente);		
		
		System.out.println("\n=====================================");
		System.out.println("| Paciente adicionado com sucesso!  |");
		System.out.println("=====================================\n");
		
	}
	
	static boolean isDigit(String s) {
	    return s.matches("[0-9]*");
	}
	
	private static void adicionarContato() throws IOException {		
		String nome = "";
		String email = "";
		String telefone = "";
		
		System.out.println("Informe o nome do contato:");
		nome = GeneralUtils.lerLinha();
		
		System.out.println("Informe o email do contato:");
		email = GeneralUtils.lerLinha();
		
		System.out.println("Informe o telefone do contato:");
		telefone = GeneralUtils.lerLinha();
		
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		
		contatos.add(contato);
		cadastrarOutroContato();
		
	} 
	
	private static void cadastrarOutroContato() throws IOException {
		System.out.println("Deseja cadastrar outro contato? S/N");
		String resposta = GeneralUtils.lerLinha();	
		
		if(resposta.equalsIgnoreCase("s")) {
			adicionarContato();
		} else if (resposta.equalsIgnoreCase("n")) {
			return;
		} else {
			System.out.println("\n----------------");
			System.out.println("Opção inválida.\n");
			cadastrarOutroContato();
		}
	}
}
