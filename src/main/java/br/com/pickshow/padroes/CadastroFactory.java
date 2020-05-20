package br.com.pickshow.padroes;

import java.io.IOException;

import br.com.pickshow.view.CadastrarClienteView;
import br.com.pickshow.view.CadastrarProfissionalView;
import javafx.stage.Stage;

public class CadastroFactory {

	public static final int PROFISSIONAL = 1;
	public static final int CLIENTE = 2;

	public void criarCadastro(int tipoCadastro) throws IOException {

		switch (tipoCadastro) {
		case CadastroFactory.PROFISSIONAL:
			new CadastrarProfissionalView().start(new Stage());
			break;
		case CadastroFactory.CLIENTE:
			new CadastrarClienteView().start(new Stage());
			break;
		}

	}

}
