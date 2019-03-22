package br.furb.furbot;

import br.furb.furbot.suporte.LoadImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;






public class AlienMovel
  extends ObjetoDoMundoAdapter
{
  public AlienMovel() {}
  
  public String toString()
  {
    return "AlienMovel";
  }
  
  public ImageIcon buildImage()
  {
    return LoadImage.getInstance().getIcon("imagens/alienmovel.png");
  }
  
  public void executar()
    throws Exception
  {
    List<Direcao> direcoes = new ArrayList();
    if ((ehVazio(ACIMA)) && (!ehFim(ACIMA)))
      direcoes.add(ACIMA);
    if ((ehVazio(ABAIXO)) && (!ehFim(ABAIXO)))
      direcoes.add(ABAIXO);
    if ((ehVazio(ESQUERDA)) && (!ehFim(ESQUERDA)))
      direcoes.add(ESQUERDA);
    if ((ehVazio(DIREITA)) && (!ehFim(DIREITA)))
      direcoes.add(DIREITA);
    if (direcoes.size() == 0)
      return;
    Random sorteio = new Random();
    int destino = sorteio.nextInt(direcoes.size());
    for (;;) {
      switch ((Direcao)direcoes.get(destino))
      {
      case ACIMA: 
        andarAbaixo();
        andarAcima();
        break;
      
      case DIREITA: 
        andarAcima();
        andarAbaixo();
        break;
      
      case AQUIMESMO: 
        andarDireita();
        andarEsquerda();
        break;
      
      case ABAIXO: 
        andarEsquerda();
        andarDireita();
      }
    }
  }
}
