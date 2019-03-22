package br.furb.furbot;

import br.furb.furbot.suporte.ObjetoMundoImpl;

public abstract interface ListenerMundo
{
  public abstract void disse(ObjetoMundoImpl paramObjetoMundoImpl, String paramString);
  
  public abstract void andou(ObjetoMundoImpl paramObjetoMundoImpl, Direcao paramDirecao, int paramInt1, int paramInt2);
  
  public abstract void fimExecucao();
  
  public abstract void repintar();
  
  public abstract void limparConsole();
}
