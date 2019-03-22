package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import javax.swing.ImageIcon;

public abstract interface ObjetoDoMundo
{
  public abstract void executar()
    throws Exception;
  
  public abstract void diga(String paramString)
    throws MundoException;
  
  public abstract void diga(Object paramObject)
    throws MundoException;
  
  public abstract void limparConsole()
    throws MundoException;
  
  public abstract void andarEsquerda()
    throws MundoException;
  
  public abstract void andarDireita()
    throws MundoException;
  
  public abstract void andarAcima()
    throws MundoException;
  
  public abstract void andarAbaixo()
    throws MundoException;
  
  public abstract boolean ehVazio(Direcao paramDirecao)
    throws MundoException;
  
  public abstract boolean ehFim(Direcao paramDirecao)
    throws MundoException;
  
  public abstract <T extends ObjetoDoMundo> T getObjeto(Direcao paramDirecao)
    throws MundoException;
  
  public abstract <T extends ObjetoDoMundo> T getObjetoXY(int paramInt1, int paramInt2)
    throws MundoException;
  
  public abstract int getX();
  
  public abstract int getY();
  
  public abstract ImageIcon buildImage();
  
  public abstract void setObjetoMundoImpl(ObjetoMundoImpl paramObjetoMundoImpl);
  
  public abstract void repintar()
    throws MundoException;
  
  public abstract int getUltimaTeclaPress()
    throws MundoException;
  
  public abstract void chegouUmObjetoNaPosicao(ObjetoDoMundo paramObjetoDoMundo);
  
  public abstract ObjetoMundoImpl getObjetoMundoImpl();
  
  public abstract void removerObjetoDoMundo(ObjetoDoMundo paramObjetoDoMundo)
    throws MundoException;
  
  public abstract void adicionarObjetoNoMundoXY(ObjetoDoMundo paramObjetoDoMundo, int paramInt1, int paramInt2)
    throws MundoException;
  
  public abstract void adicionarObjetoNoMundo(ObjetoDoMundo paramObjetoDoMundo, Direcao paramDirecao)
    throws MundoException;
  
  public abstract void adicionarObjetoNoMundoEmbaixo(ObjetoDoMundo paramObjetoDoMundo, Direcao paramDirecao)
    throws MundoException;
  
  public abstract void removerMe()
    throws MundoException;
  
  public abstract boolean jahExplodiu()
    throws MundoException;
  
  public abstract int getTempoEspera();
  
  public abstract void setTempoEspera(int paramInt);
  
  public abstract boolean ehBloqueado();
  
  public abstract void bloquear();
  
  public abstract void desbloquear();
  
  public abstract String getSouDoTipo();
}
