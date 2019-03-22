package br.furb.furbot.suporte;

import br.furb.furbot.Booleano;
import br.furb.furbot.ObjetoDoMundo;
import java.util.Random;



public class BooleanoElementoExercicio
  extends ElementoExercicio
{
  private boolean valor;
  private boolean random;
  
  public BooleanoElementoExercicio()
  {
    valor = true;
    random = false;
    setClazz("br.furb.furbot.Booleano");
    setBloqueado(false);
  }
  
  public boolean getValor()
  {
    if (random)
    {
      Random sorteio = new Random();
      valor = (sorteio.nextInt(2) == 1);
    }
    return valor;
  }
  
  public void setValor(boolean valor)
  {
    this.valor = valor;
  }
  
  public void outrasAtribuicoes(ObjetoMundoImpl obj, ObjetoDoMundo objMundo)
  {
    Booleano bool = (Booleano)objMundo;
    bool.setValorInicial(getValor());
  }
  
  public boolean isRandom()
  {
    return random;
  }
  
  public void setRandom(String random)
  {
    this.random = Boolean.valueOf(random).booleanValue();
  }
  
  public ElementoExercicio clonar()
    throws InstantiationException, IllegalAccessException
  {
    BooleanoElementoExercicio e = (BooleanoElementoExercicio)super.clonar();
    e.setValor(valor);
    e.setRandom(random);
    return e;
  }
}
