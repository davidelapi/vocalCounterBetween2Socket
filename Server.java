package serverSocketVerifica;

import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		
		/*
		 * 	Si inserisce il dato richiesto per la connesione 
		 *	(porta) e si crea il ServerSocket server.
		 */
		ServerSocket server;
		Socket connesione;
		int porta = 3360;
		
		/*
		 * Vengono creati l'OutputStream e il PrintWriter per permettere
		 * l'invio di dati al client da parte del server.
		 */
		OutputStream out;
		PrintWriter sOut;
		
		/*
		 * 	Vengono creati gli input stream e il bufferedReader che permetteranno la
		 * 	lettura di possibili invii del client al server.
		 */
		InputStream in;
		InputStreamReader input;
		BufferedReader sIN;
		
	try {
		//	Viene instanziato e avviato il server e gli si passa la porta.
		server= new ServerSocket(porta);
		while(true) {
			System.out.println("Server avviato.");
			System.out.println("In attesa di un client per la connesione...");
			// Un client si connette al server e il server accetta la connesione.
				connesione = server.accept();
			System.out.println("Connesso ad un client!");
			
			//	Instanze di input per leggere gli invii del client.
				in= connesione.getInputStream();
				input = new InputStreamReader(in);
				sIN = new BufferedReader(input);
			String parolaInEntrata = sIN.readLine();
			System.out.println(parolaInEntrata);
			/*
			 *	Prendere le informazioni ed elaborarle in modo tale che vengano lette solo
			 *	le vocali della stringa e conteggiarle.
			 */
				parolaInEntrata.toLowerCase();
				int nLettere = parolaInEntrata.length();
				int numVocaliParola = 0;
				int i=0;
				for(i=0; i<nLettere; i++) {
					if(parolaInEntrata.charAt(i)== 'a' || parolaInEntrata.charAt(i)== 'e' || parolaInEntrata.charAt(i)== 'i' || parolaInEntrata.charAt(i)== 'o' || parolaInEntrata.charAt(i)== 'u'  ) {
						numVocaliParola++; 
					}
				}
				
				//	Instanze di output per inviare dei dati al client.
				out = connesione.getOutputStream();
				sOut = new PrintWriter(out,true);
				String strNumVocaliParola = "" + numVocaliParola;
				sOut.println(strNumVocaliParola);
				sOut.close();
				sIN.close();

				
				//	La connesione tra server e client è stata chiusa.
				connesione.close();
			System.out.println("Connesione chiusa.");
		}
		//	Gestione delle eccezioni ed eventuale scrittura su console di errore
	}catch(IOException e) {
		System.err.println(e);
			}
		}
	}