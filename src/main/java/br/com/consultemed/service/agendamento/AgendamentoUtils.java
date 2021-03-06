package br.com.consultemed.service.agendamento;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.GeneralUtils;
import br.com.consultemed.service.medico.MedicoService;
import br.com.consultemed.service.medico.MedicoUtils;
import br.com.consultemed.service.paciente.PacienteUtils;

public class AgendamentoUtils {	
	
	public static void iniciaAgendamentos() throws IOException {
		AgendamentoSuporte.exibeMenuAgendamentos();
		AgendamentoSuporte.selecionarOpcaoAgendamento();
	}
	
	public static void cadastrarAgendamento() throws IOException {
		Paciente paciente = new Paciente();
		paciente = PacienteUtils.buscarPaciente();

		while (paciente == null) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("N�o foi localizado nenhum paciente com o c�digo informado.\n");
			paciente = PacienteUtils.buscarPaciente();
		}
		
		PacienteUtils.exibePaciente(paciente);

		
		Medico medico = new Medico();
		medico = MedicoUtils.buscarMedico();

		while (medico == null) {
			System.out.println("\n-------------------------------------------------------");
			System.out.println("N�o foi localizado nenhum m�dico com o CRM informado.\n");
			medico = MedicoUtils.buscarMedico();
		}
		
		MedicoUtils.exibeMedico(medico);		

		AgendamentoSuporte.exibirDiasDeAtendimentoDisponiveis(medico);
		
		System.out.println("\n===================================================================================");
		System.out.println(" VOC� PODE AGENDAR A SUA CONSULTA PARA AT� 30 DIAS A PARTIR DA DATA ATUAL");
		System.out.println(" NESSE INTERVALO SER�O EXIBIDOS APENAS OS DIAS DE ATENDIMENTO DO M�DICO ESCOLHIDO.");
		System.out.println("===================================================================================");

		LocalDate dataAtendimento = AgendamentoSuporte.definirDiaAtendimento();

		LocalDateTime horaAtendimento = AgendamentoSuporte.defineHoraAtendimento(dataAtendimento);				

		Agendamento agendamento = new Agendamento();		
	
		AgendamentoService service = new AgendamentoService();
		List<Agendamento> agend = service.buscarPorDataHora(dataAtendimento, horaAtendimento);
		
		while (agend.size() > 0) {
			System.out.println("\n------------------------------------------------------------");
			System.out.println("J� EXISTE UMA CONSULTA AGENDADA PARA ESTE HOR�RIO NESTA DATA.");
			System.out.println("------------------------------------------------------------\n");
			horaAtendimento = AgendamentoSuporte.defineHoraAtendimento(dataAtendimento);
			agend = service.buscarPorDataHora(dataAtendimento, horaAtendimento);
		}
		
		agendamento.setPaciente(paciente);
		agendamento.setMedico(medico);
		agendamento.setData(dataAtendimento);
		agendamento.setHora(horaAtendimento);
		agendamento.setAtivo(true);
		
		service.salvar(agendamento);	
		AgendamentoSuporte.exibeMensagemDeSucesoNoAgendamento();		

	}
	
	public static void listarAgendamentosPorPeriodo() throws IOException {
		LocalDate datas[] = GeneralUtils.geraDataInicialEFinal();
		LocalDate dataInicial = datas[0];
		LocalDate dataFinal = datas[1];
		
		AgendamentoService service = new AgendamentoService();
		List<Agendamento> agendamentosPorData = service.listarPorIntervaloDeDatas(dataInicial, dataFinal);

		if(agendamentosPorData.size() > 0) {
			AgendamentoSuporte.listarAgendamentos(agendamentosPorData);			
		} else {
			System.out.println("\nN�o foram localizados agendamentos neste per�odo.\n");
		}
		
	}	
	
	public static void listarTodos() {		
		AgendamentoService service = new AgendamentoService();
		List<Agendamento> agendamentos = service.listarTodos();
		if(agendamentos.size() > 0) {
			AgendamentoSuporte.listarAgendamentos(agendamentos);			
		} else {
			System.out.println("\nN�o existem agendamentos cadastradas no sistema.\n");
		}
		
	}	

	public static void exibeAgendamentosCancelados() {		
		AgendamentoService service = new AgendamentoService();
		List<Agendamento> agendamentos = service.exibeAgendamentosCancelados();
		if(agendamentos.size() > 0) {
			AgendamentoSuporte.listarAgendamentosCancelados(agendamentos);			
		} else {
			System.out.println("\nNenhum agendamento foi cancelado.\n");
		}
		
	}	
	
	public static void listarAgendamentosPorMedico() throws IOException {			
		System.out.println("Informe o CRM do M�dico: ");
		String crm = GeneralUtils.lerLinha();
		MedicoService medicoService = new MedicoService();
		Medico medico = medicoService.buscarMedicoPorCrm(crm);
		
		if(medico != null) {
			AgendamentoService service = new AgendamentoService();
			List<Agendamento> agendamentos = service.listarPorMedico(crm);
			if(agendamentos.size() > 0) {
				AgendamentoSuporte.listarAgendamentos(agendamentos);				
			} else {
				System.out.println("\nN�o existem agendamentos para o m�dico informado\n");
				AgendamentoSuporte.exibeMenuParaVoltar();
			}
		} else {
			System.out.println("\nN�o foi encontrado nenhum m�dico com o CRM informado.\n");
			listarAgendamentosPorMedico();
		}
		
	}	
	
	public static void reagendarConsulta() throws IOException {	
		System.out.println("Informe o c�digo do agendamento que voc� quer reagendar: ");
		String codigo = GeneralUtils.lerLinha();
		
		if(!StringUtils.isNumeric(codigo)) {
			System.out.println("O c�digo do agendamento deve ser num�rico.\n");
			reagendarConsulta();					
			
		} else {
			Long id = Long.parseLong(codigo);
			AgendamentoService service = new AgendamentoService();
			Agendamento agendamento = service.buscarAgendamentoPorId(id);
			if(agendamento != null) {
				AgendamentoSuporte.exibirDiasDeAtendimentoDisponiveis(agendamento.getMedico());
				
				LocalDate dataAtendimento = AgendamentoSuporte.definirDiaAtendimento();
				LocalDateTime horaAtendimento = AgendamentoSuporte.defineHoraAtendimento(dataAtendimento);
				
				agendamento.setData(dataAtendimento);
				agendamento.setHora(horaAtendimento);
				agendamento.setAtivo(true);
				
				service.atualizar(agendamento);	
				
				AgendamentoSuporte.exibeMensagemDeSucesoNaAtualizacaoAgendamento();
			} else {
				System.out.println("\nN�o foi encontrado nenhum agendamento com o c�digo informado.\n");
				cancelarAgendamento();
			}
		}		
		
	}	

	public static void cancelarAgendamento() throws IOException {
		System.out.println("\nInforme o c�digo do agendamento que voc� quer cancelar: \n");
		String codigo = GeneralUtils.lerLinha();
		
		if(!StringUtils.isNumeric(codigo)) {
			System.out.println("\nO c�digo do agendamento deve ser num�rico.\n");
			cancelarAgendamento();					
			
		} else {
			Long id = Long.parseLong(codigo);
			AgendamentoService service = new AgendamentoService();
			Agendamento agendamento = service.buscarAgendamentoPorId(id);
			if(agendamento != null) {
				agendamento.setAtivo(false);
				System.out.println("\nInforme o motivo do cancelamento: \n");
				String descricaoMotivo = GeneralUtils.lerLinha();
				agendamento.setMotivoCancelamento(descricaoMotivo);
				service.desativar(agendamento);
				System.out.println("\nAgendamento cancelado.\n");
			} else {
				System.out.println("\nN�o foi encontrado nenhum agendamento com o c�digo informado.\n");
				cancelarAgendamento();
			}
		}		
	}	
	
}
