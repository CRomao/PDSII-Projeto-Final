package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.model.VisualizarLocaisModel;
import br.com.pickshow.view.LocalSelecionadoClienteView;
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

/**
 * Classe controller para poder ter o controle da listagem de todos os locais
 * cadastrados no sistema, implementando a interface Initializable para poder
 * alterar alguns campos ao iniciar a tela.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
public class VisualizarLocaisController implements Initializable {

	public static Local localSelecionado;
	public static int doubleClicked = 0;
	public Local primeiroClick, segundoClick;

	@SuppressWarnings("exports")
	@FXML
	public Button btnVisualizar;
	@SuppressWarnings("exports")
	@FXML
	public Button btnVoltar;
	@FXML
	public ComboBox<String> comboBoxPesquisa;
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

	/**
	 * Método para poder verificar quando o usuário clicou em voltar, para poder
	 * capturar a tela atual e fechar ela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnVoltar() {
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}

	/**
	 * Método para popular o comboBox de pesquisar algum local.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actioncomboBoxPesquisa() {
		String[] areas = { "ID", "Nome local", "Rua local" };
		comboBoxPesquisa.getItems().removeAll(areas);
		comboBoxPesquisa.getItems().addAll(areas);

	}

	/**
	 * Método para poder iniciarlizar a tabela com as colunas referentes a classe
	 * interna Local, e para também caregar todos os locais cadastrados no sistema
	 * na tabela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
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

	/**
	 * Método para retornar todos os locais cadastrados.
	 * 
	 * @author Cicero Romão
	 * 
	 * @return ObservableList - Lista dos locais cadastrados no sistema.
	 */
	private ObservableList<Local> listaDeLocais() {

		return FXCollections.observableArrayList(VisualizarLocaisModel.mostrarLocaisCadastrados());
	}
	
	

	public static Local getLocalSelecionado() {
		return localSelecionado;
	}

	public static void setLocalSelecionado(Local localSelecionado) {
		VisualizarLocaisController.localSelecionado = localSelecionado;
	}

	/**
	 * Método para verificar se o usuário clicou duas vezes em um registro da
	 * tabela, onde a cada clique na tabela é incrementado na variável
	 * doubleClicked, e é feito a verificação abaixo. Essa verificação basicamente
	 * verifica se o usuário clicou duas vezes no mesmo registro - e não uma vez no
	 * registro A e outra no B. Caso for no mesmo registro, é capturado o registro
	 * clicado e atribuído na variável localSelecionado e aberto a tela de
	 * LocalSelecionadoClienteView.
	 * 
	 * @author Cicero Romão
	 * @see LocalSelecionadoClienteView
	 */
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

	/**
	 * Classe interna para servir como molde para a tabela dos locais.
	 * 
	 * @author Cicero Romão
	 * 
	 */
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

		@SuppressWarnings("exports")
		public SimpleStringProperty codProperty() {
			return nomeProprietario;
		}

		public void setNomeProprietario(String nomeProprietario) {
			this.nomeProprietario.set(nomeProprietario);
		}

		public String getNomeLocal() {
			return nomeLocal.get();
		}

		@SuppressWarnings("exports")
		public SimpleStringProperty nomeLocalProperty() {
			return nomeLocal;
		}

		public void setNomeLocal(String nomeLocal) {
			this.nomeLocal.set(nomeLocal);
		}

		public String getRuaLocal() {
			return ruaLocal.get();
		}

		@SuppressWarnings("exports")
		public SimpleStringProperty ruaLocalProperty() {
			return ruaLocal;
		}

		public void setRuaLocal(String ruaLocal) {
			this.ruaLocal.set(ruaLocal);
		}

		public String getTelefoneLocal() {
			return telefoneLocal.get();
		}

		@SuppressWarnings("exports")
		public SimpleStringProperty telefoneLocalProperty() {
			return telefoneLocal;
		}

		public void setTelefoneLocal(String telefoneLocal) {
			this.telefoneLocal.set(telefoneLocal);
		}

		public String getTipoLocal() {
			return tipoLocal.get();
		}

		@SuppressWarnings("exports")
		public SimpleStringProperty tipoLocalProperty() {
			return tipoLocal;
		}

		public void setTipoLocal(String tipoLocal) {
			this.tipoLocal.set(tipoLocal);
		}
	}

}
