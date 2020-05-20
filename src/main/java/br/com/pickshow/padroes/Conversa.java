package br.com.pickshow.padroes;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Conversa{

	public Pane pane;
	public Label lblNome, lblSobrenome, lblLocal;
	public Button btnVisualizar, btnApagar;
	
	public Pane initComponents() {//função para inicializar os componentes
		pane = new Pane();
		pane.setPrefSize(596, 80);
		
		btnVisualizar = new Button("Visualizar");
		btnVisualizar.setPrefSize(77, 50);
		btnVisualizar.setLayoutX(388);
		btnVisualizar.setLayoutY(60);
		pane.getChildren().add(btnVisualizar);
		return this.pane;
		
	}
		
}
