package br.com.consultemed.service.agendamento;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.consultemed.enums.Dias;
import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Medico;
import br.com.consultemed.service.GeneralUtils;
import br.com.consultemed.utils.MainUtils;

public class AgendamentoSuporte {

	private static List<LocalDate> dias = new ArrayList<>();
	private static List<Dias> diasDisponiveis = new ArrayList<>();

	protected static boolean isHoraValida(String hora) {
		if (!StringUtils.isNumeric(hora)) {
			return false;
		}

		int horaNum = Integer.parseInt(hora);
		if (horaNum > 23 || horaNum < 0) {
			return false;
		}

		return true;
	}

	protected static boolean isDiaValido(String numeroDia) {
		if (!StringUtils.isNumeric(numeroDia)) {
			return false;
		}

		int dia = Integer.parseInt(numeroDia);
		if (dia > dias.size() || dia < 1) {
			return false;
		}

		return true;
	}

	protected static String traduzNomeDia(LocalDate dia) {
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return Dias.DOMINGO.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.MONDAY) {
			return Dias.SEGUNDA.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.TUESDAY) {
			return Dias.TERÇA.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
			return Dias.QUARTA.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.THURSDAY) {
			return Dias.QUINTA.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.FRIDAY) {
			return Dias.SEXTA.toString();
		}
		dia.getDayOfWeek();
		if (dia.getDayOfWeek() == DayOfWeek.SATURDAY) {
			return Dias.SABADO.toString();
		}
		return "";
	}

	protected static void exibirDiasDeAtendimentoDisponiveis(Medico medico) {
		System.out.println("\n-------------------------------------");
		System.out.println("O MÉDICO ATENDE NOS SEGUINTES DIAS:");
		System.out.println("-------------------------------------");
		System.out.println("(Selecione o dia a partir do número ao lado da data)\n");

		dias.clear();
		diasDisponiveis = medico.getDiasAtendimento();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataAtual = LocalDate.now();
		int contadorDia = 1;

		for (int i = 1; i <= 30; i++) {
			for (Dias dia : diasDisponiveis) {

				if (dia.toString().equals(traduzNomeDia(dataAtual))) {
					System.out.println("[" + contadorDia + "] Dia: " + dataAtual.format(formatador) + " - "
							+ traduzNomeDia(dataAtual));
					System.out.println("-----------------------------");
					dias.add(dataAtual);
					contadorDia++;
				}
			}

			dataAtual = dataAtual.plusDays(1);

		}
	}

	protected static LocalDate definirDiaAtendimento() throws IOException {
		System.out.println("\n---------------------------------");
		System.out.println("Informe um dos dias disponíveis.");
		System.out.println("---------------------------------\n");

		String dia = GeneralUtils.lerLinha();

		if (isDiaValido(dia)) {
			int numeroDia = Integer.parseInt(dia) - 1;
			return dias.get(numeroDia);
		} else {
			System.out.println("Dia inválido");
			definirDiaAtendimento();
		}
		return null;
	}

	protected static LocalDateTime defineHoraAtendimento(LocalDate data) throws IOException {
		System.out.println("\n---------------------------------");
		System.out.println("Informe o horário do atendimento.");
		System.out.println("---------------------------------");
		System.out.println("Padrão: HH (Deve ser entre 00 e 23)");

		LocalDate diaConsulta = data;

		String hora = GeneralUtils.lerLinha();

		if (isHoraValida(hora)) {
			LocalDateTime novaHora = LocalDateTime.now();
			int numeroHora = Integer.parseInt(hora);
			novaHora = diaConsulta.atTime(numeroHora, 0);
			return novaHora;
		} else {
			System.out.println("Hora inválida");
			defineHoraAtendimento(data);
		}

		return null;

	}

	protected static void exibeMensagemDeSucesoNoAgendamento() {
		System.out.println("\n========================================");
		System.out.println("| Agendamento adicionado com sucesso!  |");
		System.out.println("========================================\n");
	}
	
	protected static void exibeMensagemDeSucesoNaAtualizacaoAgendamento() {
		System.out.println("\n========================================");
		System.out.println("| Agendamento atualizado com sucesso!  |");
		System.out.println("========================================\n");
	}

	protected static void listarAgendamentos(List<Agendamento> agendamentos) {		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("=================================");
		for(Agendamento a: agendamentos) {
			String hora = a.getHora().format(formatterTime);
			String data = a.getData().format(formatterDate);
			System.out.println("CÓDIGO: " + a.getId());
			System.out.println("PACIENTE: " + a.getPaciente().getNome());
			System.out.println("DATA: " + data);
			System.out.println("HORA: " + hora);
			System.out.println("--------------------------------");
		}
		
	}

	protected static void listarAgendamentosCancelados(List<Agendamento> agendamentos) {		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("=================================");
		for(Agendamento a: agendamentos) {
			String data = a.getData().format(formatterDate);
			System.out.println("CÓDIGO: " + a.getId());
			System.out.println("PACIENTE: " + a.getPaciente().getNome());
			System.out.println("DATA: " + data);
			System.out.println("MOTIVO: " + a.getMotivoCancelamento());
			System.out.println("--------------------------------");
		}
	}

	protected static void exibeMenuAgendamentos() {
		System.out.println("\nOPÇÕES DE AGENDAMENTO");
		System.out.println("-----------------------\n");
		System.out.println("[1] AGENDAR CONSULTA");
		System.out.println("[2] LISTAR TODAS CONSULTAS AGENDADAS");
		System.out.println("[3] LISTAR AGENDAMENTOS POR PERÍODO");
		System.out.println("[4] LISTAR AGENDAMENTOS POR MÉDICO");
		System.out.println("[5] REAGENDAR CONSULTA");
		System.out.println("[6] CANCELAR AGENDAMENTO DE CONSULTA");
		System.out.println("[7] LISTAR AGENDAMENTOS CANCELADOS");
		System.out.println("[8] VOLTAR AO MENU PRINCIPAL\n");
	}

	protected static void selecionarOpcaoAgendamento() throws IOException {
		String opcao = GeneralUtils.lerLinha();
		boolean opcaoValida = isOpcaoAgendamentoValida(opcao);

		if (opcaoValida) {
			int op = Integer.parseInt(opcao);
			switch (op) {
			case 1:
				AgendamentoUtils.cadastrarAgendamento();
				exibeMenuParaVoltar();
				break;
				
			case 2:
				AgendamentoUtils.listarTodos();
				exibeMenuParaVoltar();
				break;
				
			case 3:
				AgendamentoUtils.listarAgendamentosPorPeriodo();
				exibeMenuParaVoltar();
				break;
				
			case 4:
				AgendamentoUtils.listarAgendamentosPorMedico();
				exibeMenuParaVoltar();
				break;
				
			case 5:
				AgendamentoUtils.reagendarConsulta();
				exibeMenuParaVoltar();
				break;
				
			case 6:
				AgendamentoUtils.cancelarAgendamento();
				exibeMenuParaVoltar();
				break;
				
			case 7:
				AgendamentoUtils.exibeAgendamentosCancelados();
				exibeMenuParaVoltar();
				break;
				
			case 8:
				MainUtils.iniciaSecretaria();
				break;
				
			}
		} else {
			System.out.println("Opção inválida.\n");
			exibeMenuParaVoltar();
		}
	}

	public static void exibeMenuParaVoltar() throws IOException {
		System.out.println("Pressione 1 para voltar ao menu de agendamento");
		String opcao = GeneralUtils.lerLinha();
		if (opcao.equals("1")) {
			AgendamentoUtils.iniciaAgendamentos();
		} else {
			System.out.println("\nOpção inválida.\n");
			exibeMenuParaVoltar();
		}
	}

	private static boolean isOpcaoAgendamentoValida(String valor) {
		return valor.matches("[1-8]");
	}	
}
