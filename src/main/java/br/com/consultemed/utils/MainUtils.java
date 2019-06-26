package br.com.consultemed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.GeneralUtils;
import br.com.consultemed.service.agendamento.AgendamentoSuporte;
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
				System.out.println("Exibe menu do médico"); // TODO
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
				exibeMenuParaVoltar();
				break;
			case 2:
				Paciente paciente = PacienteUtils.buscarPaciente();
				if (paciente != null) {
					PacienteUtils.exibePaciente(paciente);
					exibeMenuParaVoltar();
						
				} else {
					System.out.println("\n\n====================================");
					System.out.println("| Paciente não localizado.         |");
					System.out.println("====================================");
					exibeMenuParaVoltar();
				}
				break;
			case 3:
				MedicoUtils.cadastrarMedico();
				exibeMenuParaVoltar();
				break;
			case 4:
				Medico medico = MedicoUtils.buscarMedico();
				if (medico != null) {
					MedicoUtils.exibeMedico(medico);
					exibeMenuParaVoltar();
				} else {
					System.out.println("\n\n====================================");
					System.out.println("| Médico não localizado.           |");
					System.out.println("====================================");
					exibeMenuParaVoltar();
				}
				break;
			case 5:
				AgendamentoUtils.iniciaAgendamentos();
//				AgendamentoUtils.cadastrarAgendamento();
//				exibeMenuParaVoltar();
				break;
			case 6:	
				AgendamentoUtils.listarTodos();
				exibeMenuParaVoltar();
				break;
			case 7:
				AgendamentoUtils.listarAgendamentosPorPeriodo();
				exibeMenuParaVoltar();
				break;
			case 8:
				System.out.println("Cancelar consulta agendada"); // TODO
				break;
			case 9:
				System.out.println("Listar consultas por período"); // TODO
				break;
			}

		} else {
			System.out.println("Opção inválida.\n");
			exibeMenuParaVoltar();
		}

	}


	private static boolean isOpcaoSecretariaValida(String valor) {
		return valor.matches("[1-6]");
	}

	private static boolean isOpcaoUsuarioValida(String valor) {
		return valor.matches("[1-2]");
	}
	
	public static void iniciaSecretaria() throws IOException {
		GeneralUtils.exibeMenuSecretaria();
		selecionarOpcaoSecretaria();
	}

	public static void exibeMenuParaVoltar() throws IOException {		
		System.out.println("Pressione 1 para voltar ao menu principal");
		String opcao = GeneralUtils.lerLinha();
		if(opcao.equals("1")){
			iniciaSecretaria();
		} else {
			System.out.println("\nOpção inválida.\n");
			exibeMenuParaVoltar();
		}
	}
	
	
}
