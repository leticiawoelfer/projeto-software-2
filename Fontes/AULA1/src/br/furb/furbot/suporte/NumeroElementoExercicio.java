package br.furb.furbot.suporte;

import br.furb.furbot.Numero;
import br.furb.furbot.ObjetoDoMundo;
import java.util.Random;


public class NumeroElementoExercicio
  extends ElementoExercicio
{
  private int valor;
  private int randomInf;
  private int randomSup;
  
  public NumeroElementoExercicio()
  {
    valor = -1;
    randomInf = -1;
    randomSup = 20;
    setClazz("br.furb.furbot.Numero");
    setBloqueado(false);
  }
  
  public int getValor()
  {
    if (randomInf > -1)
    {
      Random sorteio = new Random();
      valor = (sorteio.nextInt(randomSup - randomInf + 1) + randomInf);
    }
    return valor;
  }
  
  public void setValor(int valor)
  {
    this.valor = valor;
  }
  
  public void outrasAtribuicoes(ObjetoMundoImpl obj, ObjetoDoMundo objMundo)
  {
    Numero num = (Numero)objMundo;
    num.setValorInicial(getValor());
  }
  
  public int getRandomInf()
  {
    return randomInf;
  }
  
  public void setRandomInf(String randomInf)
  {
    this.randomInf = Integer.valueOf(randomInf).intValue();
  }
  
  public int getRandomSup()
  {
    return randomSup;
  }
  
  public void setRandomSup(String randomSup)
  {
    this.randomSup = Integer.valueOf(randomSup).intValue();
  }
  
  public ElementoExercicio clonar()
    throws InstantiationException, IllegalAccessException
  {
    NumeroElementoExercicio e = (NumeroElementoExercicio)super.clonar();
    e.setValor(valor);
    e.setRandomInf(randomInf);
    e.setRandomSup(randomSup);
    return e;
  }
}
