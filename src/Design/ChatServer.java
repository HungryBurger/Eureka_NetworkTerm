package Design;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.HashMap;
import java.lang.String;

/*
 * This is the multithreaded chat room server.
 * 1. Make the Socket
 * 2. Make the hashMap of all the print writers for all the clients.
 * 3. Print the Entrance state to all clients
 * 4. Read the Input
 * 5. Check for whisper
 *		whisper
 *		1->split the input and get name 
 *		2->Match name from hashMap and find the client
 *		3->Print to appropriate client.
 * 		Else
 *		1->Print all client
 * 6. Print the Exit state to all client.
 * 
 */
public class ChatServer 
{

	/*
	 * The port that the server listens on.
	 */
	private static final int PORT = 8001;

	/*
	 * The set of all names of clients in the chat room.  Maintained
	 * so that we can check that new clients are not registering name
	 * already in use.
	 */
	private static HashSet<String> names = new HashSet<String>();

	/*
	 * The set of all the print writers for all the clients.  This
	 * set is kept so we can easily broadcast messages.
	 */
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	/*
	 * 
	 * The appplication main method, which just listens on a port and
	 * spawns handler threads.
	 */
	private static HashMap<String,PrintWriter> map = new HashMap<String,PrintWriter>();
	public static void main(String[] args) throws Exception {
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	/*
	 * A handler thread class.  Handlers are spawned from the listening
	 * loop and are responsible for a dealing with a single client
	 * and broadcasting its messages.
	 */
	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		/*
		 * Constructs a handler thread, squirreling away the socket.
		 * All the interesting work is done in the run method.
		 */
		public Handler(Socket socket) {
			this.socket = socket;
		}

		/*
		 * Services this thread's client by repeatedly requesting a
		 * screen name until a unique one has been submitted, then
		 * acknowledges the name and registers the output stream for
		 * the client in a global set, then repeatedly gets inputs and
		 * broadcasts them.
		 */
		public void run() {
			try {
				// Create character streams for the socket.
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				// Request a name from this client.  Keep requesting until
				// a name is submitted that is not already used.  Note that
				// checking for the existence of a name and adding the name
				// must be done while locking the set of names.

				while (true) {
					System.out.println("sub");
					out.println("SUBMITNAME");
					System.out.println("sub2");
					name = in.readLine();
					System.out.println("sub3");
					System.out.println(name);
					if (name == null) {
						return;
					}
					synchronized (names) {
						if (!names.contains(name)) {
							names.add(name);
							break;
						}
					}
				}

				// Now that a successful name has been chosen, add the
				// socket's print writer to the set of all writers so
				// this client can receive broadcast messages.
				out.println("NAMEACCEPTED");
				writers.add(out);
				//Input the HasMap
				map.put(name,out);
				//Print the message box that print the whisper format
				out.println("FIRST "+"귓속말 형식-> /r 또는 /ㄱ 이름 대화내용");
				//Print the 
				for (PrintWriter writer : writers) {
					writer.println("ENTRANCE " + name + "님이 입장하셨습니다 ");
				}
				// Accept messages from this client and broadcast them.
				// Ignore other clients that cannot be broadcasted to.
				while (true) {
					System.out.println("채팅이닷1");
					String input = in.readLine();
					System.out.println(input);
					if (input == null) {
						return;
					}
					//If client type the "/r" "/ㄱ", Apply a whisper
					if(input.startsWith("/r")||input.startsWith("/ㄱ")) {
						String[] temp_name;
						temp_name=input.split(" ");
						map.get(temp_name[1]).println("WHISPER "+name +"님으로 부터 귓속말이 왔습니다. : "+input.substring(input.indexOf(" ",4)));
						out.println("WHISPER "+"<whisper to "+temp_name[1]+">"+name +":"+input.substring(input.indexOf(" ",4)));
					}
					else
					{
						//Print all client
						for (PrintWriter writer : writers) {
							writer.println("MESSAGE " + name + ": " + input);
						}
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			} finally {
				// This client is going down!  Remove its name and its print
				// writer from the sets, and close its socket.
				//Get out from chat room and print the 
				for (PrintWriter writer : writers) {
					writer.println("EXIT " + name + "님이 퇴장하셨습니다 ");
				}
				if (name != null) {
					names.remove(name);
				}
				if (out != null) {
					writers.remove(out);
				}
			
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}