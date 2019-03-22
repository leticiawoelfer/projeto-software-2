package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import java.util.List;




public abstract class FurbotAvancado
  extends Furbot
{
  public FurbotAvancado() {}
  
  public boolean ehVazio(int x, int y)
  {
    return objetoMundoImpl.ehVazio(x, y);
  }
  
  public int getQtdadeLinMundo() {
    return objetoMundoImpl.getQtdadeLin();
  }
  
  public int getQtdadeColMundo() {
    return objetoMundoImpl.getQtdadeCol();
  }
  
  public void pararMundo() throws MundoException {
    objetoMundoImpl.pararMundo();
  }
  
  public <T extends ObjetoDoMundo> List<T> getObjetos(Class<T> clazz) {
    return objetoMundoImpl.getObjetos(clazz);
  }
  
  public void executar() throws Exception {
    inteligencia();
  }
}
