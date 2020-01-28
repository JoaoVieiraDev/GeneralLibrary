
/**
 *  @author Joao Vieira, 53332
 *  @author Tiago Mendes, 52964
 * **/

import java.io.*;

import java.util.Scanner;
import FCTBoleia.*;

public class Main {


	/**
	 * Input for commands
	 */
	private static final String REGISTA = "REGISTA";
	private static final String ENTRADA = "ENTRADA";
	private static final String SAI = "SAI";
	private static final String TERMINA = "TERMINA";
	private static final String AJUDA = "AJUDA";
	private static final String NOVA = "NOVA";
	private static final String REMOVE = "REMOVE";
	private static final String BOLEIA = "BOLEIA";
	private static final String RETIRA = "RETIRA";
	private static final String CONSULTA = "CONSULTA";
	private static final String LISTA = "LISTA";

	/**
	 * Outputs for exceptions, failure cases and success cases
	 */
	private static final String USER_EXISTS = "Utilizador ja existente.";
	private static final String REG_INV = "Registo nao realizado.";
	private static final String USER_NONEXISTS = "Utilizador nao existente.";
	private static final String USER_INNEXISTS = "Utilizador inexistente.";
	private static final String USER_GIVEN_NONEXISTS = "Nao existe o utilizador dado.";
	private static final String LOGIN_INV = "Entrada nao realizada.";
	private static final String INV_DADOS = "Dados invalidos.";
	private static final String INV_DATA = "Data invalida.";
	private static final String INV_DESLOC = "Deslocacao nao existe.";
	private static final String DESLOC_NONEXIST = "Sem deslocacoes.";
	private static final String UNKNOWN = "Comando invalido.";

	private static final String REMOVE_SUCC = "Deslocacao removida.";
	private static final String BOL_REG = "Boleia registada.";
	private static final String EXIT = "Obrigado. Ate a proxima.";


	private static final String DATA_FILE = "fctboleias.dat";


	public static void main(String[] args) {
		FCTBoleia fctBoleia = load();
		Scanner in = new Scanner(System.in);
		System.out.print("> ");
		String cmd = in.next().toUpperCase();
		while (!cmd.equals(TERMINA)) {
			switch (cmd) {
				case (REGISTA):
					regista(in ,fctBoleia);
					break;
				case (ENTRADA):
					login(in, fctBoleia);
					break;
				case (SAI):
					sai(fctBoleia);
					break;
				case (AJUDA):
					ajuda(fctBoleia);
					break;
				case (NOVA):
					nova(in, fctBoleia);
					break;
				case (REMOVE):
					remove(in, fctBoleia);
					break;
				case (BOLEIA):
					boleia(in, fctBoleia);
					break;
				case (RETIRA):
					retira(in, fctBoleia);
					break;
				case (CONSULTA):
					consulta(in, fctBoleia);
					break;
				case (LISTA):
					lista(in, fctBoleia);
					break;
				default:
					System.out.println(UNKNOWN);
					cmd = in.nextLine();
					break;
			}
			if(fctBoleia.initialMode()) System.out.print("> ");
			else System.out.print(fctBoleia.getCurrentUser() + " > ");
			if(in.hasNext()) cmd = in.next().toUpperCase();
			if(cmd.equals(TERMINA) && !fctBoleia.initialMode()) {
				System.out.println(UNKNOWN);
				System.out.print(fctBoleia.getCurrentUser() + " > ");
				cmd = in.next().toUpperCase();
			}

		}
		in.close();
		System.out.println(EXIT);
		save(fctBoleia);
	}


	private static void lista(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String modo = in.next().trim();
			if(modo.toUpperCase().equals("MINHAS")) {
				if(fctBoleia.hasDesloc()) {
					//System.out.println("--  MINHAS --");
					System.out.print(fctBoleia.listMinhasDesloc());
				} else System.out.println(DESLOC_NONEXIST);
			} else if(modo.toUpperCase().equals("BOLEIAS")) {
				if(fctBoleia.hasBoleia()) {
					//System.out.println("--  BOLEIAS --");
					System.out.print(fctBoleia.listaMinhasBoleias());
				} else System.out.println(DESLOC_NONEXIST);
			} else if(modo.contains("email")) {
				if(fctBoleia.hasUser(modo)) {
					//System.out.println("--  EMAIL --");
					System.out.println(fctBoleia.listaUserDesloc(modo));
				} else System.out.println(USER_GIVEN_NONEXISTS);
			} else if(modo.matches("(.*)-(.*)-(.*)")) {
				String aux[] = modo.split("-");
				int day = Integer.parseInt(aux[0]);
				int month = Integer.parseInt(aux[1]);
				int year = Integer.parseInt(aux[2]);
				if(day<1 || day>31 || month<1 || month>12 || year < 2019) System.out.println(INV_DATA);
				else {
					if(fctBoleia.hasAnyDeslocInData(modo)) {
						//System.out.println("--  DATA --");
						System.out.print(fctBoleia.listaDeslocFromData(modo));
					}
					else System.out.println(DESLOC_NONEXIST);
				}
			} else if(modo.toUpperCase().equals("TODAS")) {
				if(fctBoleia.hasAnyDesloc()) {
					//System.out.println("--  TODAS --");
					System.out.print(fctBoleia.listaAllDesloc());
				}
				else System.out.println(DESLOC_NONEXIST);
			}
		} else System.out.println(UNKNOWN);
	}


	private static void consulta(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String email = in.next().trim();
			String data = in.next().trim();
			if(fctBoleia.hasUser(email)) {
				String aux[] = data.split("-");
				int day = Integer.parseInt(aux[0]);
				int month = Integer.parseInt(aux[1]);
				int year = Integer.parseInt(aux[2]);
				if(day<1 || day>31 || month<1 || month>12 || year < 2019) System.out.println(INV_DATA);
				else {
					if(fctBoleia.otherUserHasDesloc(email, data)) {
						System.out.println(fctBoleia.consultaDesloc(email, data));
					} else System.out.println(INV_DESLOC);
				}
			} else System.out.println(USER_INNEXISTS);
		} else System.out.println(UNKNOWN);
	}


	private static void retira(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String data = in.next().trim();
			String aux[] = data.split("-");
			int day = Integer.parseInt(aux[0]);
			int month = Integer.parseInt(aux[1]);
			int year = Integer.parseInt(aux[2]);
			if(day<1 || day>31 || month<1 || month>12 || year < 2019) System.out.println(INV_DATA);
			else {
				if(fctBoleia.userHasBoleia(data)) {
					fctBoleia.removeBoleia(data);
					System.out.println(fctBoleia.getCurrentUserName()+" boleia retirada.");
				} else System.out.println(fctBoleia.getCurrentUserName() + " nesta data nao tem registo de boleia.");
			}
		} else System.out.println(UNKNOWN);
	}


	private static void boleia(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String email = in.next().trim();
			String data = in.next().trim();
			if(fctBoleia.hasUser(email)) {
				String aux[] = data.split("-");
				int day = Integer.parseInt(aux[0]);
				int month = Integer.parseInt(aux[1]);
				int year = Integer.parseInt(aux[2]);
				if(day<1 || day>31 || month<1 || month>12 || year < 2019) System.out.println(INV_DATA);
				else {
					if(fctBoleia.otherUserHasDesloc(email, data)) {
						if(!fctBoleia.getCurrentUser().equals(email)) {
							if(!fctBoleia.userHasDeslocOuBoleia(data)) {
								int positionInWaitinglist = fctBoleia.addBoleia(email, data);
								if(positionInWaitinglist == -1) System.out.println(BOL_REG);
								else System.out.println("Ficou na fila de espera (posicao "+ positionInWaitinglist + ").");
							} else System.out.println(fctBoleia.getCurrentUserName()+" ja registou uma boleia ou deslocacao nesta data.");
						} else System.out.println(fctBoleia.getCurrentUserName()+" nao pode dar boleia a si proprio.");
					} else System.out.println(INV_DESLOC);
				}
			} else System.out.println(USER_INNEXISTS);
		} else System.out.println(UNKNOWN);
	}


	private static void remove(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String data = in.next().trim();
			String aux[] = data.split("-");
			int day = Integer.parseInt(aux[0]);
			int month = Integer.parseInt(aux[1]);
			int year = Integer.parseInt(aux[2]);
			if(day<1 || day>31 || month<1 || month>12 || year < 2019) System.out.println(INV_DATA);
			else {
				if(fctBoleia.userHasDesloc(data)) {
					if(!fctBoleia.deslocHasBoleias(data)) {
						fctBoleia.removeDesloc(data);
						System.out.println(REMOVE_SUCC);
					} else System.out.println(fctBoleia.getCurrentUserName()+" ja nao pode eliminar esta deslocacao.");
				} else System.out.println(fctBoleia.getCurrentUserName()+" nesta data nao tem registo de deslocacao.");
			}
		} else System.out.println(UNKNOWN);
	}


	private static void nova(Scanner in, FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			String origem = in.next().trim();
			String destino = in.next().trim(); in.nextLine();
			String data = in.next().trim();
			String aux[] = data.split("-");
			int day = Integer.parseInt(aux[0]);
			int month = Integer.parseInt(aux[1]);
			int year = Integer.parseInt(aux[2]);
			String hora = in.next().trim();
			String aux2[] = hora.split(":");
			int hour = Integer.parseInt(aux2[0]);
			int minute = Integer.parseInt(aux2[1]);
			int duration = Integer.parseInt(in.next().trim());
			int lugares = Integer.parseInt(in.next().trim());
			
			if(day<1 || day>31 || month<1 || month>12 || year < 2019 || hour<0 || hour>23 || minute<0 || minute>59
					 || duration<0 || lugares>10 || lugares<0) System.out.println(INV_DADOS);
			else {
				if(!fctBoleia.userHasDeslocOuBoleia(data)) {
					fctBoleia.addDesloc(origem, destino, data, hora, duration, lugares);
					System.out.println("Deslocacao "+fctBoleia.getNumberOfUserDesloc()+" registada. Obrigado "+fctBoleia.getCurrentUserName()+".");
				} else System.out.println(fctBoleia.getCurrentUserName()+" ja tem uma deslocacao ou boleia registada nesta data.");
			}
			
		} else System.out.println(UNKNOWN);
	}


	private static void ajuda(FCTBoleia fctBoleia) {
		if(fctBoleia.initialMode()) System.out.println("ajuda - Mostra os comandos existentes\n" + 
														"termina - Termina a execucao do programa\n" + 
														"regista - Regista um novo utilizador no programa\n" + 
														"entrada - Permite a entrada (\"login\") dum utilizador no programa");
		else System.out.println("ajuda - Mostra os comandos existentes\n" + 
								"sai - Termina a sessao deste utilizador no programa\n" + 
								"nova - Regista uma nova deslocacao\n" + 
								"lista - Lista todas ou algumas deslocacoes registadas\n" + 
								"boleia - Regista uma boleia para uma dada deslocacao\n" + 
								"consulta - Lista a informacao de uma dada deslocacao\n" + 
								"retira - Retira uma dada boleia\n" + 
								"remove - Retira uma dada deslocacao");
	}


	private static void sai(FCTBoleia fctBoleia) {
		if(!fctBoleia.initialMode()) {
			System.out.println("Ate a proxima " + fctBoleia.getCurrentUserName()+".");
			fctBoleia.logout();
		} else System.out.println(UNKNOWN);
	}



	private static void login(Scanner in, FCTBoleia fctBoleia) {
		String email = in.next().trim();
		if(fctBoleia.initialMode()) {
			if(fctBoleia.hasUser(email)) {
				for(int count = 1; count <= 3; count++) {
					System.out.print("password: ");
					String password = in.next().trim();
					if(password.equals(fctBoleia.getUserPassword(email))) {
						fctBoleia.login(email);
						System.out.println("Visita "+fctBoleia.getNumberOfUserVisits()+" efetuada.");
						return;
					}
				} System.out.println(LOGIN_INV);
			} else System.out.println(USER_NONEXISTS);
		} else System.out.println(UNKNOWN);
	}

	
	private static void regista(Scanner in, FCTBoleia fctBoleia) {
		String email = in.nextLine().trim();
		if(fctBoleia.initialMode()) {
			if(!fctBoleia.hasUser(email)) {
				System.out.print("nome (maximo 50 caracteres): ");
				String nome = in.next().trim();
				for(int count = 1; count <= 3; count++) {
					System.out.print("password (entre 4 e 6 caracteres - digitos e letras): ");
					String password = in.next().trim();
					if(password.length() <= 6 && password.length() >= 4 && password.chars().allMatch(Character::isLetterOrDigit)) {
						fctBoleia.registerUser(email, nome, password);
						System.out.println("Registo "+fctBoleia.getNumberOfRegistos()+" efetuado.");
						return;
					}
				} System.out.println(REG_INV);
			} else System.out.println(USER_EXISTS);
		} else System.out.println(UNKNOWN);
	}


	private static void save(FCTBoleia fctBoleia) {
		try{
			ObjectOutputStream file = new ObjectOutputStream(
					new FileOutputStream(DATA_FILE) );
			file.writeObject(fctBoleia);
			file.flush();
			file.close();
		}
		catch ( IOException e )
		{}
	}

	private static FCTBoleia load() {
		FCTBoleia fctBoleia = new FCTBoleiaClass();
		try{
			ObjectInputStream file = new ObjectInputStream(
					new FileInputStream(DATA_FILE) );
			// Compiler gives a warning.
			fctBoleia = (FCTBoleia) file.readObject();
			file.close();
		}
		catch ( IOException e )
		{}
		catch ( ClassNotFoundException e )
		{}
		return fctBoleia;
	}
}


