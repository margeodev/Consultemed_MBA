package br.com.consultemed.service.consulta;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Consulta;
import br.com.consultemed.model.Medico;
import br.com.consultemed.service.GeneralUtils;
import br.com.consultemed.service.agendamento.AgendamentoService;
import br.com.consultemed.service.medico.MedicoUtils;

public class ConsultaUtils {

	public static void cadastrarConsulta() throws IOException {
		
		Medico medico = buscarMedico();
		while(medico == null) {
			System.out.println("\nNão foi localizado nenhum médico com o CRM informado.\n");
			medico = buscarMedico();
		}
		
		AgendamentoService service = new AgendamentoService();
		List<Agendamento> agendamentos = service.listarPorMedico(medico.getCrm());
		
		exibeListaConsultasAgendadas(agendamentos);
		selecionaConsulta();
	}
	
	private static void selecionaConsulta() throws IOException {
		System.out.println("\nINFORME O CÓDIGO DA CONSULTA QUE DESEJA ATENDER:");
		String codigo = GeneralUtils.lerLinha();
		
		if(!StringUtils.isNumeric(codigo)) {
			System.out.println("O código da consulta deve ser numérico.\n");
			selecionaConsulta();		
		} else {
			Long id = Long.parseLong(codigo);
			AgendamentoService agendamentoService = new AgendamentoService();
			Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
			
			if(agendamento != null) {
				System.out.println("DESCREVA O QUE FOI REALIZADO NA CONSULTA:");
				String descricaoConsulta = GeneralUtils.lerLinha();
				
				Consulta consulta = new Consulta();
				consulta.setAgendamento(agendamento);
				consulta.setDescricao(descricaoConsulta);
				
				ConsultaService consultaService = new ConsultaService();
				consultaService.salvar(consulta);
				agendamento.setAtivo(false);
				agendamentoService.desativar(agendamento);
				System.out.println("Consulta finalizada.");
				
				
			} else {
				System.out.println("Código inválido.");
				selecionaConsulta();	
			}
			
		}
		
	}

	private static Medico buscarMedico() throws IOException {
		return MedicoUtils.buscarMedico();
	}
	
	private static void exibeListaConsultasAgendadas(List<Agendamento> agendamentos) {		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("=================================");
		for(Agendamento a: agendamentos) {
			String hora = a.getHora().format(formatterTime);
			String data = a.getData().format(formatterDate);
			System.out.println("Código: " + a.getId());
			System.out.println("Paciente: " + a.getPaciente().getNome());
			System.out.println("Data: " + data);
			System.out.println("Hora: " + hora);			
			System.out.println("------------------------------");
		}
		System.out.println("EXISTEM " + agendamentos.size() + " CONSULTAS AGENDADAS PARA O MÉDICO." );
	}

	public static void listarTodasConsultas() throws IOException {
		Medico medico = buscarMedico();
		while(medico == null) {
			System.out.println("\nNão foi localizado nenhum médico com o CRM informado.\n");
			medico = buscarMedico();
		}
		
		ConsultaService service = new ConsultaService();
		List<Consulta> consultas = service.buscarConsultasPorMedico(medico.getCrm());
		
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("=================================");
		for(Consulta c: consultas) {
			String hora = c.getAgendamento().getHora().format(formatterTime);
			String data = c.getAgendamento().getData().format(formatterDate);
			System.out.println("Paciente: " + c.getAgendamento().getPaciente().getNome());
			System.out.println("Descrição: \n" + c.getDescricao());
			System.out.println("Data: " + data);
			System.out.println("Hora: " + hora);
			System.out.println("--------------------------------\n");
		}
	}
	
	public static void listarPorIntervaloDeDatas() throws IOException {
		Medico medico = buscarMedico();
		while(medico == null) {
			System.out.println("\nNão foi localizado nenhum médico com o CRM informado.\n");
			medico = buscarMedico();
		}
					
		LocalDate datas[] = GeneralUtils.geraDataInicialEFinal();
		LocalDate dataInicial = datas[0];
		LocalDate dataFinal = datas[1];
		
		ConsultaService service = new ConsultaService();
		
		List<Consulta> consultas = service.listarPorIntervaloDeDatas(dataInicial, dataFinal, medico.getCrm());
		
		if(consultas.size() > 0) {
			DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.println("=================================");
			for(Consulta c: consultas) {
				String hora = c.getAgendamento().getHora().format(formatterTime);
				String data = c.getAgendamento().getData().format(formatterDate);
				System.out.println("Paciente: " + c.getAgendamento().getPaciente().getNome());
				System.out.println("Descrição: \n" + c.getDescricao());
				System.out.println("Data: " + data);
				System.out.println("Hora: " + hora);
				System.out.println("--------------------------------");
			}
			
		} else {
			System.out.println("Não foram encontradas consultas no período informado.");
		}
	}
	
}
