package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.model.LoginModel;
import br.com.pickshow.view.Locais;
import br.com.pickshow.view.MeusLocais;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class HomeController implements Initializable {

	@FXML
	public Menu menuNomeUser;
	@FXML
	public MenuItem menuLocal;
	@FXML
	public MenuItem menuItemMeusDados;
	@FXML
	public MenuItem menuItemMeusLocais;
	@FXML
	public MenuItem menuItemSair;
	@FXML
	public Button sair;
	boolean fechar;
	
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

	@FXML
	public void actionSair() {
		if (fechar) {
			Stage stage = (Stage) sair.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	public void actionMenuItemMeusLocais() {
		try {
			new MeusLocais().start(new Stage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionMenuLocal() {
		try {
			new Locais().start(new Stage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuNomeUser.setText(LoginController.pegarNomeUsuario());
	}
}
