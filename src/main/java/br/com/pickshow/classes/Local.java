package br.com.pickshow.classes;

import br.com.pickshow.model.CadastrarClienteModel;
import br.com.pickshow.model.CadastrarLocaisModel;

public class Local {

	private String nomeLocal;
	private String ruaLocal;
	private String telefoneContato;
	private String areaInformacoes;
	private int tipoAreaLocal;
	private int idProfissional;

	/*
	 * public Local(String nomeLocal, String ruaLocal, String telefoneContato,
	 * String areaInformacoes, int tipoAreaLocal, int idProfissional) {
	 * this.nomeLocal = nomeLocal; this.ruaLocal = ruaLocal; this.telefoneContato =
	 * telefoneContato; this.areaInformacoes = areaInformacoes; this.tipoAreaLocal =
	 * tipoAreaLocal; this.idProfissional = idProfissional; }
	 */

	public int verificarCamposNulos(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		if (nomeLocal == null) {
			return 1;
		} else if (ruaLocal == null) {
			return 1;
		} else if (telefoneContato == null) {
			return 1;
		} else if (areaInformacoes == null) {
			return 1;
		} else if (tipoAreaLocal < 0) {
			return 1;
		} else if (idProfissional < 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public int verificarCamposVazios(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		if (nomeLocal.equals("")) {
			return 1;
		} else if (ruaLocal.equals("")) {
			return 1;
		} else if (telefoneContato.equals("")) {
			return 1;
		} else if (areaInformacoes.equals("")) {
			return 1;
		} else if (tipoAreaLocal < 0) {
			return 1;
		} else if (idProfissional < 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public int camposInvalidos(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		if (nomeLocal.length() <= 3) {
			return 1;
		} else if (telefoneContato.length() <= 3) {
			return 1;
		} else if (areaInformacoes.length() <= 3) {
			return 1;
		} else if (ruaLocal.length() <= 3) {
			return 1;
		} else if (tipoAreaLocal <= 3) {
			return 1;
		} else if (idProfissional <= 3) {
			return 1;
		} else {
			return 0;
		}
	}

	public String cadastroLocal(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		String resultado = CadastrarLocaisModel.insert(nomeLocal, ruaLocal, telefoneContato, areaInformacoes,
				tipoAreaLocal, idProfissional);
		return resultado;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getRuaLocal() {
		return ruaLocal;
	}

	public void setRuaLocal(String ruaLocal) {
		this.ruaLocal = ruaLocal;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getAreaInformacoes() {
		return areaInformacoes;
	}

	public void setAreaInformacoes(String areaInformacoes) {
		this.areaInformacoes = areaInformacoes;
	}

	public int getTipoAreaLocal() {
		return tipoAreaLocal;
	}

	public void setTipoAreaLocal(int tipoAreaLocal) {
		this.tipoAreaLocal = tipoAreaLocal;
	}

	public int getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(int idProfissional) {
		this.idProfissional = idProfissional;
	}

}
