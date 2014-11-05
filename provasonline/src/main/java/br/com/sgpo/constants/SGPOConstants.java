package br.com.sgpo.constants;

/**
 * Classe com atributos constants do sistema
 * 
 * @author Roseli
 * 
 */
public class SGPOConstants {

	public static final String LOGGED_FUNCIONARIO = "funcionario";
	public static final String LOGGED_ROLE_ADMIN = "ROLE_ADMIN";
	public static final String LOGGED_ROLE_INSTRUTOR = "ROLE_INSTRUTOR";
	public static final String LOGGED_ROLE_GERENTE = "ROLE_GERENTE";
	public static final String LOGGED_ROLE_COLABORADOR = "ROLE_COLABORADOR";
	public static final String SERVER_PATH = "D:\\provasonline\\anexos";
	public static final long TEMPO_DE_PROVA = 1000 * 60 * 60 * 2; // padrão será 2 horas

	public enum Agenda {
		AGENDAR, ATUALIZAR, CANCELAR
	}
}
