package br.com.consultemed.utils;

import java.io.IOException;

import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.GeneralUtils;
import br.com.consultemed.service.agendamento.AgendamentoUtils;
import br.com.consultemed.service.medico.MedicoUtils;
import br.com.consultemed.service.paciente.PacienteUtils;

public class MainUtils {
	
	
	public static void iniciaSistema() throws IOException {
		GeneralUtils.exibeMenuParaDefinirUsuario();
		selecionarOpcaoUsuario();
	}
	

	private static void selecionarOpcaoUsuario() throws IOException {
		String opcao = GeneralUtils.lerLinha();		
		
		boolean opcaoValida = isOpcaoUsuarioValida(opcao);
		if (opcaoValida) {
			int op = Integer.parseInt(opcao);
			switch (op) {
			case 1:
				GeneralUtils.exibeMenuSecretaria();
				selecionarOpcaoSecretaria();
				break;
			case 2:
				GeneralUtils.exibeMenuMedico();
				selecionarOpcaoMedico();
				break;
			}
		} else {
			System.out.println("Opção inválida.");
			iniciaSistema();
		}
		
	}

	public static void selecionarOpcaoSecretaria() throws IOException {
		String opcao = GeneralUtils.lerLinha();		
		boolean opcaoValida = isOpcaoSecretariaValida(opcao);

		if (opcaoValida) {
			int op = Integer.parseInt(opcao);
			switch (op) {
			case 1:
				PacienteUtils.cadastrarPaciente();
				exibeMenuParaVoltar(1);
				break;
			case 2:
				Paciente paciente = PacienteUtils.buscarPaciente();
				if (paciente != null) {
					PacienteUtils.exibePaciente(paciente);
					exibeMenuParaVoltar(1);
						
				} else {
					System.out.println("\n\n====================================");
					System.out.println("| Paciente não localizado.         |");
					System.out.println("====================================");
					exibeMenuParaVoltar(1);
				}
				break;
			case 3:
				MedicoUtils.cadastrarMedico();
				exibeMenuParaVoltar(1);
				break;
			case 4:
				Medico medico = MedicoUtils.buscarMedico();
				if (medico != null) {
					MedicoUtils.exibeMedico(medico);
					exibeMenuParaVoltar(1);
				} else {
					System.out.println("\n\n====================================");
					System.out.println("| Médico não localizado.           |");
					System.out.println("====================================");
					exibeMenuParaVoltar(1);
				}
				break;
			case 5:
				AgendamentoUtils.iniciaAgendamentos();
				break;
			case 6:	
				iniciaSistema();
				break;
			}

		} else {
			System.out.println("Opção inválida.\n");
			exibeMenuParaVoltar(1);
		}

	}

	public static void selecionarOpcaoMedico() throws IOException {
		String opcao = GeneralUtils.lerLinha();		
		boolean opcaoValida = isOpcaoMedicoValida(opcao);
		
		if (opcaoValida) {
			int op = Integer.parseInt(opcao);
			switch (op) {
			case 1:
				AgendamentoUtils.listarTodos();
				exibeMenuParaVoltar(2);
				break;
			case 2:
				Paciente paciente = PacienteUtils.buscarPaciente();
				if (paciente != null) {
					PacienteUtils.exibePaciente(paciente);
					exibeMenuParaVoltar(2);
					
				} else {
					System.out.println("\n\n====================================");
					System.out.println("| Paciente não localizado.         |");
					System.out.println("====================================");
					exibeMenuParaVoltar(2);
				}
				break;
			case 3:
				AgendamentoUtils.cadastrarAgendamento();
				exibeMenuParaVoltar(2);
				break;
			case 4:
				AgendamentoUtils.cancelarAgendamento();
				exibeMenuParaVoltar(2);
				break;
			case 5:
				iniciaSistema();
				break;
			}
			
		} else {
			System.out.println("\nOpção inválida.\n");
			exibeMenuParaVoltar(2);
		}
		
	}


	private static boolean isOpcaoSecretariaValida(String valor) {
		return valor.matches("[1-6]");
	}

	private static boolean isOpcaoMedicoValida(String valor) {
		return valor.matches("[1-5]");
	}

	private static boolean isOpcaoUsuarioValida(String valor) {
		return valor.matches("[1-2]");
	}
	
	public static void iniciaSecretaria() throws IOException {
		GeneralUtils.exibeMenuSecretaria();
		selecionarOpcaoSecretaria();
	}

	public static void iniciaMedico() throws IOException {
		GeneralUtils.exibeMenuMedico();
		selecionarOpcaoMedico();
	}

	public static void exibeMenuParaVoltar(int usuario) throws IOException {		
		System.out.println("\nPressione 1 para voltar ao menu principal\n");
		String opcao = GeneralUtils.lerLinha();
		if(opcao.equals("1")){
			if(usuario == 1) {
				iniciaSecretaria();				
			} else {
				iniciaMedico();
			}
		} else {
			System.out.println("\nOpção inválida.\n");
			exibeMenuParaVoltar(usuario);
		}
	}
	
	
}
