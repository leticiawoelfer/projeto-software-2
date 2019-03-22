package br.furb.furbot.suporte;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;




public class ListaObjetosMundoImpl
  implements PosicaoMundo
{
  private List<ObjetoMundoImpl> objetos;
  
  public ListaObjetosMundoImpl()
  {
    objetos = new ArrayList();
  }
  
  public void add(ObjetoMundoImpl obj)
  {
    objetos.add(obj);
  }
  
  public void insert(int index, ObjetoMundoImpl obj)
  {
    objetos.add(index, obj);
  }
  
  public ImageIcon getImage()
  {
    return ((ObjetoMundoImpl)objetos.get(objetos.size() - 1)).getImage();
  }
  
  public List<ObjetoMundoImpl> getObjetos()
  {
    return objetos;
  }
  
  public boolean isBloqueado()
  {
    for (ObjetoMundoImpl obj : objetos)
    {
      if (obj.isBloqueado()) {
        return true;
      }
    }
    return false;
  }
  

  public void run()
  {
    for (int x = 0; x < objetos.size(); x++) {
      ObjetoMundoImpl obj = (ObjetoMundoImpl)objetos.get(x);
      obj.run();
    }
  }
  
  public void parar()
  {
    for (ObjetoMundoImpl obj : objetos) {
      obj.parar();
    }
  }
  
  public boolean equals(Object obj)
  {
    for (ObjetoMundoImpl objMundo : objetos)
    {
      if (objMundo == obj) {
        return true;
      }
    }
    return false;
  }
}
