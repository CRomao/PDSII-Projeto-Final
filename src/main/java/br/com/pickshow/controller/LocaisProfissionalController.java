package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.com.pickshow.controller.VisualizarLocaisController.Local;
import br.com.pickshow.model.LoginModel;
import br.com.pickshow.model.LocaisProfissionalModel;
import br.com.pickshow.view.CadastrarLocaisView;
import br.com.pickshow.view.LocalSelecionadoClienteView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LocaisProfissionalController implements Initializable {

	public static int verifyButton = 0;
	public static Local localSelecionado;
	public Local primeiroClick, segundoClick;
	public static int doubleClicked = 0;
	boolean deletar;

	public Button btnCadastrar;
	@FXML
	public Button btnDeletar;
	@FXML
	public Button btnAtualizar;
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
		verifyButton = 1;
		try {
			new CadastrarLocaisView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionBtnAtualizar() {
		locaisCarregados();
	}

	@FXML
	public void onMouseClickedTabela() {
		doubleClicked++;
		if (doubleClicked == 1) {
			primeiroClick = tabela.getSelectionModel().getSelectedItem();
		}

		if (doubleClicked == 2) {
			segundoClick = tabela.getSelectionModel().getSelectedItem();
		}

		if (doubleClicked == 2) {
			if (primeiroClick == segundoClick) {
				localSelecionado = tabela.getSelectionModel().getSelectedItem();
				chamarTela();
				doubleClicked = 0;
			} else {
				doubleClicked = 0;
			}

		}
	}

	@FXML
	public void actionBtnDeletar() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");

		if (doubleClicked < 1) {
			alert.setContentText("Escolha um registro para deletar.");
			alert.showAndWait();

		} else if (doubleClicked == 1) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setHeaderText(null);
			alert2.setTitle("Informação");
			alert2.setContentText("Deseja realmente excluir este registro?");
			ButtonType sim = new ButtonType("Sim");
			ButtonType nao = new ButtonType("Não");
			alert2.getButtonTypes().setAll(sim, nao);
			alert2.showAndWait().ifPresent(b -> {
				if (b.getText() == "Sim") {
					deletar = true;
				}
			});
			if (deletar) {
				localSelecionado = tabela.getSelectionModel().getSelectedItem();
				alert.setContentText(LocaisProfissionalModel.deletarRegistroLocal(localSelecionado.getCod()));
				doubleClicked = 0;
				locaisCarregados();
				alert.showAndWait();
			}
			doubleClicked = 0;

		}
	}

	public void chamarTela() {
		try {
			new CadastrarLocaisView().start(new Stage());
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

		locaisCarregados();

	}

	private ObservableList<Local> listaDeLocais() {

		return FXCollections.observableArrayList(LocaisProfissionalModel.selecionarLocais(LoginModel.pegarIdusuario()));
	}

	public void locaisCarregados() {
		ObservableList<Local> locais = listaDeLocais();
		tabela.getItems().removeAll(locais);
		tabela.setItems(locais);
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

		public Integer getCod() {
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
