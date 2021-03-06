package br.com.pickshow.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe Servidor para tratar as mensagens dos clientes.
 * 
 * @author Cicero Romão
 * 
 */

public class Servidor extends Thread {

	private Socket cliente;
	private static ListUsers<User> users = new ListUsers<>();

	public Servidor(Socket novoCliente) {
		this.cliente = novoCliente;
	}

	@Override
	public void run() {

		BufferedReader veioDoCliente;
		DataOutputStream vaiPraCliente;
		String nomeUser;

		try {

			String msgCliente;
			veioDoCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			vaiPraCliente = new DataOutputStream(cliente.getOutputStream());

			// Captura o nome do cliente, pois é a primeira informação que é enviada para o
			// servidor.
			nomeUser = veioDoCliente.readLine();

			// Cria um novo usuário com o nome, o Input e Output dele.
			User newUser = new User(nomeUser, veioDoCliente, vaiPraCliente);

			// Adiciona o novo usuário na lista de usuários.
			users.addNewUser(newUser);

			// Controle das mensagens recebidas e para encaminhar para os outros clientes.
			while (true) {
				msgCliente = veioDoCliente.readLine();
				mandarPraTodos(msgCliente, nomeUser);
			}

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	// Função para mandar as mensagens para todos os clientes.
	public void mandarPraTodos(String txt, String nomeUser) {

		// verificador para saber se o usuário saiu.
		boolean saiu = false;
		int user = -1;

		// Se o usuário digitar "sair", é preparado uma mensagem.
		if (txt.equalsIgnoreCase("sair")) {
			txt = "*** " + nomeUser + " saiu do chat";
			saiu = true;
		} else {
			txt = nomeUser + ": " + txt;
		}

		// Try para mandar a mensagem para todos os clientes.
		try {
			for (int i = 0; i < users.listUsers.size(); i++) {

				// Manda a mensagem para todos, menos para o que digitou.
				if (!(nomeUser == users.listUsers.get(i).getName())) {
					users.listUsers.get(i).getOutputClient().writeBytes(txt + '\n');
				}

				// IF para saber se o cliente saiu.
				if (nomeUser == users.listUsers.get(i).getName() && saiu == true) {
					users.listUsers.get(i).getOutputClient().writeBytes("sair" + '\n');
					user = i;
				}
			}

			// Se o cliete saiu, remove da lista e fecha o socket dele.
			if (saiu) {
				users.listUsers.remove(user);
				cliente.close();
			}

		} catch (IOException ex) {
			System.out.println("Erro ao enviar mensagem para todos...");
		}
	}

	public static void main(String[] args) throws IOException {

		// Cria-se o servidor na porta informada.
		@SuppressWarnings("resource")
		ServerSocket servidor = new ServerSocket(8001);

		// Controle para poder aceitar vários clientes.
		while (true) {
			System.out.print("Esperando conexão...");
			Socket cliente = servidor.accept();
			System.out.println("conectou!");
			new Servidor(cliente).start();
		}

	}
}
