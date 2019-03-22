package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import javax.swing.ImageIcon;



public class Projetil
  extends ObjetoDoMundoAdapter
{
  private Direcao direcao;
  
  public Projetil(Direcao direcao)
  {
    this.direcao = direcao;
    new ObjetoMundoImpl(this);
  }
  
  public ImageIcon buildImage()
  {
    return LoadImage.getInstance().getIcon("imagens/shell.png");
  }
  
  public void executar() throws Exception
  {
    try
    {
      for (;;)
      {
        switch (direcao)
        {
        case ACIMA: 
          andarAbaixo();
          break;
        
        case DIREITA: 
          andarAcima();
          break;
        
        case AQUIMESMO: 
          andarDireita();
          break;
        
        case ABAIXO: 
          andarEsquerda();
        }
        
      }
      

      return;
    }
    catch (MundoException mundoexception) {}
  }
}
