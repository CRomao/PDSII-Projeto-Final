package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.controller.LocaisProfissionalController.Local;
import br.com.pickshow.model.VisualizarLocaisModel;
import br.com.pickshow.view.LocalSelecionadoClienteView;
import br.com.pickshow.view.LocaisProfissionalView;
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

//Classe para o controle dos Cadastros.
public class VisualizarLocaisController implements Initializable {

	
	public static Local localSelecionado;
	public static int doubleClicked = 0;
	public Local primeiroClick, segundoClick;
	
	@FXML
	public Button btnVisualizar;
	@FXML
	public Button btnVoltar;
	@FXML
	public ComboBox comboBoxPesquisa;
	@FXML
	public TableView<Local> tabela;
	@FXML
	public TableColumn<Local, String> nomeProprietario;
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
	public void actioncomboBoxPesquisa() {
		String[] areas = { "ID", "Nome local", "Rua local" };
		comboBoxPesquisa.getItems().removeAll(areas);
		comboBoxPesquisa.getItems().addAll(areas);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeProprietario.setCellValueFactory(new PropertyValueFactory<>("nomeProprietario"));
		nomeLocalCol.setCellValueFactory(new PropertyValueFactory<>("nomeLocal"));
		ruaLocalCol.setCellValueFactory(new PropertyValueFactory<>("ruaLocal"));
		telefoneLocalCol.setCellValueFactory(new PropertyValueFactory<>("telefoneLocal"));
		tipoLocalCol.setCellValueFactory(new PropertyValueFactory<>("tipoLocal"));

		ObservableList<Local> locais = listaDeLocais();
		tabela.getItems().removeAll(locais);
		tabela.setItems(locais);

	}

	private ObservableList<Local> listaDeLocais() {

		return FXCollections.observableArrayList(VisualizarLocaisModel.mostrarLocaisCadastrados());
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
		
		if(doubleClicked == 2) {
			if (primeiroClick == segundoClick) {
				localSelecionado = tabela.getSelectionModel().getSelectedItem();
				System.out.println("CLIENTE: ");
				System.out.print(localSelecionado.hashCode());
				try {
					new LocalSelecionadoClienteView().start(new Stage());
				} catch (IOException e) {
					e.printStackTrace();
				}
				doubleClicked = 0;
			} else {
				doubleClicked = 0;
			}

		}
	}
	
	public static Local getLocalSelecionado() {
		return localSelecionado;
	}
	
	

	public static class Local {
		private final SimpleStringProperty nomeProprietario;
		private final SimpleStringProperty nomeLocal;
		private final SimpleStringProperty ruaLocal;
		private final SimpleStringProperty telefoneLocal;
		private final SimpleStringProperty tipoLocal;

		public Local(String nomeProprietario, String nomeLocal, String ruaLocal, String telefoneLocal,
				String tipoLocal) {
			this.nomeProprietario = new SimpleStringProperty(nomeProprietario);
			this.nomeLocal = new SimpleStringProperty(nomeLocal);
			this.ruaLocal = new SimpleStringProperty(ruaLocal);
			this.telefoneLocal = new SimpleStringProperty(telefoneLocal);
			this.tipoLocal = new SimpleStringProperty(tipoLocal);
		}

		public String getNomeProprietario() {
			return nomeProprietario.get();
		}

		public SimpleStringProperty codProperty() {
			return nomeProprietario;
		}

		public void setNomeProprietario(String nomeProprietario) {
			this.nomeProprietario.set(nomeProprietario);
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
