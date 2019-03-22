package br.furb.furbot;

import br.furb.furbot.suporte.LoadImage;

public class Alien extends ObjetoDoMundoAdapter
{
  public Alien() {}
  
  public String toString()
  {
    return "Alien";
  }
  

  public javax.swing.ImageIcon buildImage()
  {
    return LoadImage.getInstance().getIcon("imagens/alien_0.png");
  }
  
  public void executar()
    throws Exception
  {}
}
