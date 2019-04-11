package br.furb.furbot;

import java.util.List;

import br.furb.furbot.exceptions.MundoException;

/**
 * 
 * @author Adilson Vahldick
 */
public abstract class FurbotAvancado extends Furbot {

	public FurbotAvancado() {
	}

	public boolean ehVazio(int x, int y) {
		return objetoMundoImpl.ehVazio(x, y);
	}

	public int getQtdadeLinMundo() {
		return objetoMundoImpl.getQtdadeLin();
	}

	public int getQtdadeColMundo() {
		return objetoMundoImpl.getQtdadeCol();
	}

	public void pararMundo() throws MundoException {
		objetoMundoImpl.pararMundo();
	}

	public <T extends ObjetoDoMundo> List<T> getObjetos(Class<T> clazz) {
		return this.objetoMundoImpl.getObjetos(clazz);
	}

	public void executar() throws Exception {
		inteligencia();
	}
}