package br.furb.furbot.suporte;

import br.furb.furbot.ObjetoDoMundo;

public class ElementoExercicio
{
  private int x;
  private int y;
  private boolean bloqueado;
  private String clazz;
  private String id;
  
  public ElementoExercicio()
  {
    bloqueado = true;
    random = new FurbotRandom();
    energia = -1;
    qtdade = 1;
  }
  
  public int getQtdade()
  {
    return qtdade;
  }
  
  public void setQtdade(int qtdade)
  {
    this.qtdade = qtdade;
  }
  
  public int getEnergia()
  {
    return energia;
  }
  
  public void setEnergia(int energia)
  {
    this.energia = energia;
  }
  
  public boolean isUsarEnergia()
  {
    return energia > 0;
  }
  
  public void setRandom(FurbotRandom random)
  {
    this.random = random;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getIdDependeX()
  {
    return idDependeX;
  }
  
  public void setIdDependeX(String idDependeX)
  {
    this.idDependeX = idDependeX;
  }
  
  public String getIdDependeY()
  {
    return idDependeY;
  }
  
  public void setIdDependeY(String idDependeY)
  {
    this.idDependeY = idDependeY;
  }
  
  public int getX()
  {
    return x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }
  
  public int getY()
  {
    return y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }
  
  public String getClazz()
  {
    return clazz;
  }
  
  public void setClazz(String clazz)
  {
    this.clazz = clazz;
  }
  
  public boolean isBloqueado()
  {
    return bloqueado;
  }
  


  public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }
  
  private String idDependeX;
  private String idDependeY;
  private FurbotRandom random;
  private int energia;
  private int qtdade;
  public void outrasAtribuicoes(ObjetoMundoImpl objetomundoimpl, ObjetoDoMundo objetodomundo) {}
  
  public boolean isDependente() { return (idDependeX != null) || (idDependeY != null); }
  

  public FurbotRandom getRandom()
  {
    return random;
  }
  
  public ElementoExercicio clonar()
    throws InstantiationException, IllegalAccessException
  {
    ElementoExercicio e = (ElementoExercicio)getClass().newInstance();
    e.setBloqueado(bloqueado);
    e.setClazz(clazz);
    e.setEnergia(energia);
    e.setId(id);
    e.setIdDependeX(idDependeX);
    e.setIdDependeY(idDependeY);
    e.setQtdade(qtdade);
    e.setRandom(random);
    e.setX(x);
    e.setY(y);
    return e;
  }
}
