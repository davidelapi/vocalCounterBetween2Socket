package clientSocketVerifica;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		
		/*
		 * 	Si inseriscono i dati richiesti per la connesione 
		 *	(server, porta) e si crea il socket "connesione".
		 */
		Socket connesione;
		String server= "localhost";
		int porta = 3360;
		
		/*
		 * 	Vengono creati gli input stream e il bufferedReader che permetteranno la
		 * 	lettura di possibili invii del server al client.
		 */
		InputStream in;
		InputStreamReader input;
		BufferedReader sIN;
		
		/*
		 * Vengono creati l'OutputStream e il PrintWriter per permettere
		 * l'invio di dati al server da parte del client.
		 */
		OutputStream out;
		PrintWriter sOut;
		
		//	Viene instaziato lo scanner per permettere la lettura di input dell'utente.
		Scanner s = new Scanner(System.in);
		
		/*
		 * Viene chiesto all'utente di inserire una parola che verrà inviata al server
		 * a cui saranno conteggiate il numero di vocali
		 */
			System.out.println("Inserisci la parola a cui vuoi conteggiare il numero delle vocali:");
				String parola = s.nextLine();
				s.close();
				try {
					connesione = new Socket(server, porta);
					System.out.println("connesione aperta");
					out = connesione.getOutputStream();
					sOut = new PrintWriter(out,true);
				
					//	Instanze di output per inviare dei dati al server.
					sOut.println(parola);
					
					//	Instanze di input per leggere gli invii del client.
					in= connesione.getInputStream();
					input = new InputStreamReader(in);
					sIN = new BufferedReader(input);
					String risultato = sIN.readLine();
					System.out.println("Il server ha contato il numero di vocali presenti nella tua parola.\n"
							+ "Il risultato è: " + risultato);
					sIN.close();
					sOut.close();

					//	La connesione tra server e client è stata chiusa.
					connesione.close();
					System.out.println("connesione chiusa");
					
					//	Gestione delle eccezioni ed eventuale scrittura su console di errore
				}catch(IOException e) {
					System.err.println(e);
					}
				}
}