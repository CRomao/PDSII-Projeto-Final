package br.com.pickshow.padroes;

import java.io.IOException;

import br.com.pickshow.view.CadastroCliente;
import br.com.pickshow.view.CadastroProfissional;
import javafx.stage.Stage;

public class CadastroFactory {

	public static final int PROFISSIONAL = 1;
	public static final int CLIENTE = 2;

	public void criarCadastro(int tipoCadastro) throws IOException {

		switch (tipoCadastro) {
		case CadastroFactory.PROFISSIONAL:
			new CadastroProfissional().start(new Stage());
			break;
		case CadastroFactory.CLIENTE:
			new CadastroCliente().start(new Stage());
			break;
		}

	}

}
