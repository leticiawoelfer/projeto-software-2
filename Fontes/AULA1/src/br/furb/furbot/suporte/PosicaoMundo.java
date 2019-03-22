package br.furb.furbot.suporte;

import java.util.List;
import javax.swing.ImageIcon;

public abstract interface PosicaoMundo
  extends Runnable
{
  public abstract ImageIcon getImage();
  
  public abstract boolean isBloqueado();
  
  public abstract List<ObjetoMundoImpl> getObjetos();
  
  public abstract void parar();
}
