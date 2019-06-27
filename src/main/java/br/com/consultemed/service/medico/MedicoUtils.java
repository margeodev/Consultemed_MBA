package br.com.consultemed.service.medico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.consultemed.enums.Dias;
import br.com.consultemed.model.Medico;
import br.com.consultemed.service.GeneralUtils;

public class MedicoUtils {
			
	private static List<String> diasDisponiveis = new ArrayList<>();
	private static List<Dias> diasDeAtendimento = new ArrayList<>();

	public static void exibeMedico(Medico medico) {
		System.out.println("===============================");
		System.out.println("|      Ficha do médico        |");
		System.out.println("===============================");
		System.out.println("| Nome: " + medico.getNome());
		System.out.println("| CRM: " + medico.getCrm());
		System.out.println("| Dias de atendimento: " + medico.getDiasAtendimento());
		System.out.println("===============================\n\n");
	}
	
	public static Medico buscarMedico() throws IOException {
		System.out.println("Informe o CRM do médico: ");	
		String crm = GeneralUtils.lerLinha();
						
		MedicoService service = new MedicoService();
		Medico medico = new Medico();
		medico = service.buscarMedicoPorCrm(crm);
		return medico;
	}
	
	public static void cadastrarMedico() throws IOException {
		limparDias();
		preencheDiasDisponiveis();
		
		System.out.println("Informe o nome do médico:");
		String nome = GeneralUtils.lerLinha();
		
		System.out.println("Informe o CRM do médico:");
		String crm = GeneralUtils.lerLinha();
		
		escolheDias();
		
		Medico medico = new Medico();
		medico.setDiasAtendimento(diasDeAtendimento);		
		medico.setNome(nome);
		medico.setCrm(crm);
		
		MedicoService service = new MedicoService();
		service.salvar(medico);
		
		System.out.println("\n\n=====================================");
		System.out.println("Medico adicionado com sucesso!");
		System.out.println("=====================================\n\n");

	}
	
	private static void escolheDias() throws IOException {
		System.out.println("--------------------------------");
		System.out.println(" Escolha os dias de atendimento");
		System.out.println("--------------------------------");
		System.out.println(" Digite o número referente ao dia, pressione 0 ao concluir.\n");
		System.out.println(" Dias disponíveis:\n");
		exibeDiasDisponiveis();
		
		String dia = GeneralUtils.lerLinha();
				
		boolean opcaoValida = isOpcaoDiaValida(dia);
		
		if(opcaoValida) {
			int op = Integer.parseInt(dia);
			switch(op) {
				case 1:
					diasDisponiveis.remove(Dias.DOMINGO.toString());
					diasDeAtendimento.add(Dias.DOMINGO);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 2:
					diasDisponiveis.remove(Dias.SEGUNDA.toString());
					diasDeAtendimento.add(Dias.SEGUNDA);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 3:
					diasDisponiveis.remove(Dias.TERÇA.toString());
					diasDeAtendimento.add(Dias.TERÇA);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 4:
					diasDisponiveis.remove(Dias.QUARTA.toString());
					diasDeAtendimento.add(Dias.QUARTA);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 5:
					diasDisponiveis.remove(Dias.QUINTA.toString());
					diasDeAtendimento.add(Dias.QUINTA);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 6:
					diasDisponiveis.remove(Dias.SEXTA.toString());
					diasDeAtendimento.add(Dias.SEXTA);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 7:
					diasDisponiveis.remove(Dias.SABADO.toString());
					diasDeAtendimento.add(Dias.SABADO);
					exibeDiasDisponiveis();
					escolheDias();
					break;
					
				case 0:					
					return;
					
			}
		} else {
			System.out.println("\nOpção inválida\n");
			exibeMenuParaVoltar();
		}
		
	}
	
	private static void limparDias() {
		diasDisponiveis.clear();
		diasDeAtendimento.clear();
	}
	
	private static void exibeDiasDisponiveis() {
		if(diasDisponiveis.size()>0) {
			System.out.println("------------");
			for(String dia: diasDisponiveis) {	
				adicionarNumeroAoDia(dia);
			}			
			System.out.println("------------");
		} else {
			System.out.println("-------------------------------------------");
			System.out.println(" O médico já está atendendo todos os dias.");
			System.out.println(" Pressione 0 para adicionar o médico.");
			return;
		}
	}
	
	public static String adicionarNumeroAoDia(String dia) {
		if(dia.equals(Dias.DOMINGO.toString())) {
			System.out.println("[1] " + Dias.DOMINGO);
			return ("[1] " + Dias.DOMINGO);
		}
		if(dia.equals(Dias.SEGUNDA.toString())) {
			System.out.println("[2] " + Dias.SEGUNDA);
			return ("[2] " + Dias.SEGUNDA);
		}
		if(dia.equals(Dias.TERÇA.toString())) {
			System.out.println("[3] " + Dias.TERÇA);
			return ("[3] " + Dias.TERÇA);
		}
		if(dia.equals(Dias.QUARTA.toString())) {
			System.out.println("[4] " + Dias.QUARTA);
			return ("[4] " + Dias.QUARTA);
		}
		if(dia.equals(Dias.QUINTA.toString())) {
			System.out.println("[5] " + Dias.QUINTA);
			return ("[5] " + Dias.QUINTA);			
		}
		if(dia.equals(Dias.SEXTA.toString())) {
			System.out.println("[6] " + Dias.SEXTA);
			return ("[6] " + Dias.SEXTA);
		}
		if(dia.equals(Dias.SABADO.toString())) {
			System.out.println("[7] " + Dias.SABADO);
			return ("[7] " + Dias.SABADO);
		}
		return "";		
	}	
	
	private static void preencheDiasDisponiveis() {
		diasDisponiveis.add(Dias.DOMINGO.toString());
		diasDisponiveis.add(Dias.SEGUNDA.toString());
		diasDisponiveis.add(Dias.TERÇA.toString());
		diasDisponiveis.add(Dias.QUARTA.toString());
		diasDisponiveis.add(Dias.QUINTA.toString());
		diasDisponiveis.add(Dias.SEXTA.toString());
		diasDisponiveis.add(Dias.SABADO.toString());
	}
	
	public static void exibeMenuParaVoltar() throws IOException {		
		System.out.println("Pressione 1 para continuar cadastrando o médico");
		String opcao = GeneralUtils.lerLinha();
		
		if(opcao.equals("1")){
			exibeDiasDisponiveis();
			escolheDias();
		} else {
			System.out.println("\nOpção inválida.\n");
			exibeMenuParaVoltar();
		}
	}
	
	private static boolean isOpcaoDiaValida(String valor) {
		return valor.matches("[0-7]");
	}
	

}
