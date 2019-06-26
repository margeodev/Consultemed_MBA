package br.com.consultemed.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GeneralUtils {

	public static void exibeMenuSecretaria() {

		System.out.println("\nSELECIONE A OP��O DESEJADA:\n");
		System.out.println("[1] CADASTRAR PACIENTE");
		System.out.println("[2] BUSCAR PACIENTE");
		System.out.println("[3] CADASTRAR M�DICO");
		System.out.println("[4] BUSCAR M�DICO");
		System.out.println("[5] AGENDAMENTOS");		
		System.out.println("[6] CONSULTAS");
		System.out.println();
	}
	
	public static void exibeMenuParaDefinirUsuario() {
		System.out.println("\n===============================================");
		System.out.println("||           CL�NICA CONSULTEMED             ||");
		System.out.println("===============================================");
		System.out.println("");
		System.out.println("INFORME O TIPO DE USU�RIO: ");
		System.out.println("---------------------------");
		System.out.println("[1] Secret�ria");
		System.out.println("[2] M�dico");
	}
			
	public static String lerLinha() throws IOException {
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
	
	
}
