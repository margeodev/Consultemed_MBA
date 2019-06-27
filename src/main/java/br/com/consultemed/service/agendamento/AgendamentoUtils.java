package br.com.consultemed.service.agendamento;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		LocalDate dataAtendimento = AgendamentoSuporte.definirDiaAtendimento();

		LocalDateTime horaAtendimento = AgendamentoSuporte.defineHoraAtendimento(dataAtendimento);				

		Agendamento agendamento = new Agendamento();
		
		agendamento.setPaciente(paciente);
		agendamento.setMedico(medico);
		agendamento.setData(dataAtendimento);
		agendamento.setHora(horaAtendimento);
		agendamento.setAtivo(true);
	
		AgendamentoService service = new AgendamentoService();
		service.salvar(agendamento);	
		
		AgendamentoSuporte.exibeMensagemDeSucesoNoAgendamento();		
		
	}

	public static void listarAgendamentosPorPeriodo() throws IOException {
		System.out.println("Informe a data inicial: ('dd-MM-yyyy')");
		String data1 = GeneralUtils.lerLinha();		
		if(!AgendamentoSuporte.validaFormatoDeData(data1)) {
			System.out.println("Formato de data inv�lido.");
			listarAgendamentosPorPeriodo();
		} 
		
		System.out.println("Informe a data final: ('dd-MM-yyyy')");
		String data2 = GeneralUtils.lerLinha();		
		if(!AgendamentoSuporte.validaFormatoDeData(data2)) {
			System.out.println("Formato de data inv�lido.\n");
			listarAgendamentosPorPeriodo();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dataInicial = LocalDate.parse(data1,formatter);
		LocalDate dataFinal = LocalDate.parse(data2,formatter);
		
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
	
	public static void reagendarConsulta() {	
		
	}	
	
	public static void cancelarAgendamento() throws IOException {
		System.out.println("Informe o c�digo do agendamento que voc� quer cancelar: ");
		String codigo = GeneralUtils.lerLinha();
		
		if(!StringUtils.isNumeric(codigo)) {
			System.out.println("O c�digo do agendamento deve ser num�rico.\n");
			cancelarAgendamento();					
			
		} else {
			Long id = Long.parseLong(codigo);
			AgendamentoService service = new AgendamentoService();
			Agendamento agendamento = service.buscarAgendamentoPorId(id);
			if(agendamento != null) {
				service.excluir(id);
				System.out.println("Agendamento cancelado.");
			} else {
				System.out.println("\nN�o foi encontrado nenhum agendamento com o c�digo informado.\n");
				cancelarAgendamento();
			}
		}		
	}	
	
}
