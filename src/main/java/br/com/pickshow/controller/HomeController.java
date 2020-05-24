package br.com.pickshow.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.view.ConversasProfissionalView;
import br.com.pickshow.view.LocaisProfissionalView;
import br.com.pickshow.view.VisualizarLocaisView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe controller para a Home, onde terá algumas opções que o usuário poderá
 * fazer. Implementa a interface Initializable, para que altere alguns
 * componentes ao iniciar a tela.
 * 
 * @author Cicero Romão
 * 
 */
public class HomeController implements Initializable {

	@FXML
	public Menu menuNomeUser;
	@FXML
	public MenuItem menuLocal;
	@FXML
	public MenuItem menuItemMeusDados;
	@FXML
	public MenuItem menuItemChat;
	@FXML
	public MenuItem menuItemMeusLocais;
	@FXML
	public MenuItem menuItemMeusServicos;
	@FXML
	public MenuItem menuItemMeusProdutos;
	@FXML
	public MenuItem menuItemSair;
	@FXML
	public Button sair;
	@FXML
	public ImageView img1;
	@FXML
	public ImageView img2;
	@FXML
	public ImageView star1;
	@FXML
	public ImageView star2;
	@FXML
	public ImageView star3;
	@FXML
	public ImageView star4;
	@FXML
	public ImageView star5;
	@FXML
	public ImageView star6;
	@FXML
	public ImageView star7;
	@FXML
	public ImageView star8;
	@FXML
	public ImageView star9;
	@FXML
	public ImageView star10;
	@FXML
	public ImageView eventos;
	@FXML
	public ImageView colab1;
	@FXML
	public ImageView colab2;
	@FXML
	public ImageView colab3;
	boolean fechar;

	/**
	 * Método para poder verificar quando o usuário clicou no item de 'sair', caso
	 * ele confirme a saída, será atribuído true a variável fechar, em seguida
	 * fazendo a chamada ao método actionSair().
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionMenuItemSair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText("Realmente deseja sair?");
		ButtonType sim = new ButtonType("Sim");
		ButtonType nao = new ButtonType("Não");
		alert.getButtonTypes().setAll(sim, nao);
		alert.showAndWait().ifPresent(b -> {
			if (b.getText() == "Sim") {
				fechar = true;
			}
		});
		actionSair();
	}

	/**
	 * Método para poder verificar quando o usuário clicou em sair, para poder
	 * capturar a tela atual e fechar ela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionSair() {
		if (fechar) {
			Stage stage = (Stage) sair.getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * Método para chamar a tela dos locais do profissional, quando ele clicar em
	 * 'Meus Locais'.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionMenuItemMeusLocais() {
		try {
			new LocaisProfissionalView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para chamar a tela que mostrará todos os locais cadastrados no sistema
	 * em uma tabela, para que o cliente possa ver.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionMenuLocal() {
		try {
			new VisualizarLocaisView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para chamar a tela que mostrará os clientes que entraram em contato
	 * com o profissional, quando o profissional clicar em 'chat', mostrando em
	 * detalhes qual o cliente que entrou em contato.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionMenuItemChat() {
		try {
			new ConversasProfissionalView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para poder preencher o nome do usuário no menu da direita, acessando a
	 * classe LoginController e pegando nome dele, fazendo isso ao iniciar a tela da
	 * Home.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarImagensHome();
		removerItensCliente();
		

	}

	public void carregarImagensHome() {
		menuNomeUser.setText(LoginController.pegarNomeUsuario());

		img1.setImage(new Image(new File("img/home-corp.jpg").toURI().toString()));
		img2.setImage(new Image(new File("img/home-rec.jpg").toURI().toString()));
		star1.setImage(new Image(new File("img/star.png").toURI().toString()));
		star2.setImage(new Image(new File("img/star.png").toURI().toString()));
		star3.setImage(new Image(new File("img/star.png").toURI().toString()));
		star4.setImage(new Image(new File("img/star.png").toURI().toString()));
		star5.setImage(new Image(new File("img/star.png").toURI().toString()));
		star6.setImage(new Image(new File("img/star.png").toURI().toString()));
		star7.setImage(new Image(new File("img/star.png").toURI().toString()));
		star8.setImage(new Image(new File("img/star.png").toURI().toString()));
		star9.setImage(new Image(new File("img/star.png").toURI().toString()));
		star10.setImage(new Image(new File("img/star.png").toURI().toString()));
		eventos.setImage(new Image(new File("img/eventos.jpg").toURI().toString()));
		colab1.setImage(new Image(new File("img/mulher.png").toURI().toString()));
		colab2.setImage(new Image(new File("img/cara.png").toURI().toString()));
		colab3.setImage(new Image(new File("img/cara2.png").toURI().toString()));

	}
	
	public void removerItensCliente() {
		if(LoginController.tipoUsuario == 1) {
			System.out.println(menuNomeUser.getItems());
			menuNomeUser.getItems().removeAll(menuItemMeusProdutos, menuItemMeusLocais, menuItemMeusServicos);
			System.out.println(menuNomeUser.getItems());
		}
		
	}
}
