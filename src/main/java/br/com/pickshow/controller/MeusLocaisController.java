package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.com.pickshow.model.LoginModel;
import br.com.pickshow.model.MeusLocaisModel;
import br.com.pickshow.view.CadastroMeusLocais;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MeusLocaisController implements Initializable {

	public Button btnCadastrar;
	@FXML
	public Button btnAlterar;
	@FXML
	public Button btnDeletar;
	@FXML
	public Button btnVoltar;
	@FXML
	public ComboBox comboBoxPesquisa;
	@FXML
	public TableView<Local> tabela;
	@FXML
	public TableColumn<Local, Integer> codCol;
	@FXML
	public TableColumn<Local, String> nomeLocalCol;
	@FXML
	public TableColumn<Local, String> ruaLocalCol;
	@FXML
	public TableColumn<Local, String> telefoneLocalCol;
	@FXML
	public TableColumn<Local, String> tipoLocalCol;

	@FXML
	public void actionBtnVoltar() {
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionBtnCadastrar() {
		try {
			new CadastroMeusLocais().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void actioncomboBoxPesquisa() {
		String[] areas = { "Código", "Nome local", "Rua local" };
		comboBoxPesquisa.getItems().removeAll(areas);
		comboBoxPesquisa.getItems().addAll(areas);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codCol.setCellValueFactory(new PropertyValueFactory<>("cod"));
		nomeLocalCol.setCellValueFactory(new PropertyValueFactory<>("nomeLocal"));
		ruaLocalCol.setCellValueFactory(new PropertyValueFactory<>("ruaLocal"));
		telefoneLocalCol.setCellValueFactory(new PropertyValueFactory<>("telefoneLocal"));
		tipoLocalCol.setCellValueFactory(new PropertyValueFactory<>("tipoLocal"));

		ObservableList<Local> locais = listaDeLocais();
		tabela.getItems().removeAll(locais);
		tabela.setItems(locais);

	}

	private ObservableList<Local> listaDeLocais() {

		return FXCollections.observableArrayList(MeusLocaisModel.conectar(LoginModel.pegarIdusuario()));
	}

	public static class Local {
		private final SimpleIntegerProperty cod;
		private final SimpleStringProperty nomeLocal;
		private final SimpleStringProperty ruaLocal;
		private final SimpleStringProperty telefoneLocal;
		private final SimpleStringProperty tipoLocal;

		public Local(Integer cod, String nomeLocal, String ruaLocal, String telefoneLocal, String tipoLocal) {
			this.cod = new SimpleIntegerProperty(cod);
			this.nomeLocal = new SimpleStringProperty(nomeLocal);
			this.ruaLocal = new SimpleStringProperty(ruaLocal);
			this.telefoneLocal = new SimpleStringProperty(telefoneLocal);
			this.tipoLocal = new SimpleStringProperty(tipoLocal);
		}

		public Integer isSelected() {
			return cod.get();
		}

		public SimpleIntegerProperty codProperty() {
			return cod;
		}

		public void setCod(Integer cod) {
			this.cod.set(cod);
		}

		public String getNomeLocal() {
			return nomeLocal.get();
		}

		public SimpleStringProperty nomeLocalProperty() {
			return nomeLocal;
		}

		public void setNomeLocal(String nomeLocal) {
			this.nomeLocal.set(nomeLocal);
		}

		public String getRuaLocal() {
			return ruaLocal.get();
		}

		public SimpleStringProperty ruaLocalProperty() {
			return ruaLocal;
		}

		public void setRuaLocal(String ruaLocal) {
			this.ruaLocal.set(ruaLocal);
		}

		public String getTelefoneLocal() {
			return telefoneLocal.get();
		}

		public SimpleStringProperty telefoneLocalProperty() {
			return telefoneLocal;
		}

		public void setTelefoneLocal(String telefoneLocal) {
			this.telefoneLocal.set(telefoneLocal);
		}

		public String getTipoLocal() {
			return tipoLocal.get();
		}

		public SimpleStringProperty tipoLocalProperty() {
			return tipoLocal;
		}

		public void setTipoLocal(String tipoLocal) {
			this.tipoLocal.set(tipoLocal);
		}
	}

}
