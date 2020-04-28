package resources;

import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;

public class ChatServer {
	Vector<String> users = new Vector<String>();
	Vector<HandleClient> clients = new Vector<HandleClient>();
	public String port;

	public void process() throws Exception {
		ServerSocket server = new ServerSocket(Integer.valueOf(port), 100);
		out.println("Server Started...");
		while (true) {
			Socket client = server.accept();
			HandleClient c = new HandleClient(client);
			clients.add(c);
		}
	}

	public static void main(String... args) throws Exception {
		new ChatServer().process();
	}

	public void boradcast(String user, String message) {
		if (user == null) {
			for (HandleClient c : clients)

				c.sendMessages(message);
		} else if (user == "sdbjkbcsdab" && message.equals("update")) {
			for (HandleClient c : clients)

				c.sendMessagesx(message);
		} else {
			for (HandleClient c : clients)

				c.sendMessage(user, message);
		}
	}

	class HandleClient extends Thread {
		String name = "";
		BufferedReader input;
		PrintWriter output;

		public HandleClient(Socket client) throws Exception {

			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(), true);

			name = input.readLine();
			users.add(name);

			start();
		}

		public void sendMessages(String msg) {
			output.println(" " + msg);
			
		}

		public void sendMessagesx(String msg) {
			output.println(msg);
			getOnlineUsers();
		}

		public void sendMessage(String uname, String msg) {
			output.println(uname + " " + msg);
		}

		public void getOnlineUsers() {
			boradcast(null, "CleareAll");
			for (HandleClient c : clients) {
				for (int i = 0; i < users.size(); i++) {
					boradcast(null, users.get(i));
					System.out.println(i + " : " + users.get(i));
				}
			}
		}

		public void updateUI() {

			boradcast("sdbjkbcsdab", "update");

		}

		public String getUserName() {
			return name;
		}

		public void run() {
			String line;
			try {
				while (true) {
					line = input.readLine();
					if (line.equals("end")) {
						clients.remove(name);
						users.remove(name);
						break;

					} else if (line.equals("pslvmark42")) {
						getOnlineUsers();
						// break;
					} else if (line.equals("updateUI")) {
						updateUI();

					} else {
						boradcast(name, line);
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

}