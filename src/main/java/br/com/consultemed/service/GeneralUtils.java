package br.com.consultemed.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GeneralUtils {

	public static void exibeMenuSecretaria() {

		System.out.println("\nSELECIONE A OPÇÃO DESEJADA:\n");
		System.out.println("[1] CADASTRAR PACIENTE");
		System.out.println("[2] BUSCAR PACIENTE");
		System.out.println("[3] CADASTRAR MÉDICO");
		System.out.println("[4] BUSCAR MÉDICO");
		System.out.println("[5] AGENDAMENTOS");	
		System.out.println("[6] VOLTAR AO MENU DE USUÁRIO");
		System.out.println();
	}

	public static void exibeMenuMedico() {
		
		System.out.println("\nSELECIONE A OPÇÃO DESEJADA:\n");
		System.out.println("[1] LISTAR CONSULTAS AGENDADAS");
		System.out.println("[2] CONSULTAR PACIENTE");
		System.out.println("[3] AGENDAR NOVA CONSULTA");		
		System.out.println("[4] CANCELAR CONSULTA");
		System.out.println("[5] LISTAR TODAS AS CONSULTAS ATENDIDAS");
		System.out.println("[6] LISTAR CONSULTAS ATENDIDAS POR PERÍODO ");
		System.out.println("[7] VOLTAR AO MENU DE USUÁRIO");
		System.out.println();
	}
	
	public static void exibeMenuParaDefinirUsuario() {
		System.out.println("\n===============================================");
		System.out.println("||           CLÍNICA CONSULTEMED             ||");
		System.out.println("===============================================");
		System.out.println("");
		System.out.println("INFORME O TIPO DE USUÁRIO: ");
		System.out.println("---------------------------");
		System.out.println("[1] Secretária");
		System.out.println("[2] Médico\n");
	}
			
	public static String lerLinha() throws IOException {
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
	
	public static boolean validaFormatoDeData(String data) {
		String padrao = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)";
		return data.matches(padrao);
	}
	
	public static LocalDate[] geraDataInicialEFinal() throws IOException {
		System.out.println("\nInforme a data inicial: ('dd-MM-yyyy')");
		String data1 = lerLinha();		
		if(!validaFormatoDeData(data1)) {
			System.out.println("Formato de data inválido.");
			geraDataInicialEFinal();
		} 
		
		System.out.println("Informe a data final: ('dd-MM-yyyy')");
		String data2 = lerLinha();		
		if(!validaFormatoDeData(data2)) {
			System.out.println("Formato de data inválido.\n");
			geraDataInicialEFinal();
		}		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dataInicial = LocalDate.parse(data1, formatter);
		LocalDate dataFinal = LocalDate.parse(data2, formatter);
		
		int n = 2;
		LocalDate datas[] = new LocalDate[n];
		datas[0] = dataInicial;
		datas[1] = dataFinal;
		
		return datas;
	}
	
}
