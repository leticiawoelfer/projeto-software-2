package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick
 */
public interface ObjetoDoMundo {

	public void executar() throws Exception;
        
	public  void diga(String s) throws MundoException;

	public  void diga(Object obj) throws MundoException;

	public  void limparConsole() throws MundoException;

	public  void andarEsquerda() throws MundoException;

	public  void andarDireita() throws MundoException;

	public  void andarAcima() throws MundoException;

	public  void andarAbaixo() throws MundoException;

	public  boolean ehVazio(Direcao direcao) throws MundoException;

	public  boolean ehFim(Direcao direcao) throws MundoException;

	public  <T extends ObjetoDoMundo> T getObjeto(Direcao direcao)
			throws MundoException;

	public  <T extends ObjetoDoMundo> T getObjetoXY(int i, int j)
			throws MundoException;

	public  int getX();

	public  int getY();

	public  ImageIcon buildImage();

	public  void setObjetoMundoImpl(ObjetoMundoImpl objetomundoimpl);

	public  void repintar() throws MundoException;

	public  int getUltimaTeclaPress() throws MundoException;

	public  void chegouUmObjetoNaPosicao(ObjetoDoMundo objetodomundo);

	public  ObjetoMundoImpl getObjetoMundoImpl();

	public  void removerObjetoDoMundo(ObjetoDoMundo objetodomundo)
			throws MundoException;

	public  void adicionarObjetoNoMundoXY(ObjetoDoMundo objetodomundo,
			int i, int j) throws MundoException;

	public  void adicionarObjetoNoMundo(ObjetoDoMundo objetodomundo,
			Direcao direcao) throws MundoException;

	public  void adicionarObjetoNoMundoEmbaixo(
			ObjetoDoMundo objetodomundo, Direcao direcao) throws MundoException;

	public  void removerMe() throws MundoException;

	public  boolean jahExplodiu() throws MundoException;

	public  int getTempoEspera();

	public  void setTempoEspera(int i);

	public  boolean ehBloqueado();

	public  void bloquear();

	public  void desbloquear();

	public  String getSouDoTipo();
}