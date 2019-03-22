package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;



public abstract class Furbot
  extends ObjetoDoMundoAdapter
{
  public static final double VERSAO = 1.800000001D;
  
  public Furbot() {}
  
  public ImageIcon buildImage()
  {
    ImageIcon image = LoadImage.getInstance().getIcon("imagens/r2d2-icon.gif");
    
    if (ehDependenteEnergia()) {
      BufferedImage img = new BufferedImage(50, 50, 1);
      Graphics2D g = img.createGraphics();
      g.setColor(Color.white);
      g.fillRect(0, 0, 50, 50);
      g.drawImage(image.getImage(), 2, 2, null);
      float fator = getEnergia() / (
        getMaxEnergia() * 1.0F);
      int altura = Math.round(40.0F * fator);
      g.setColor(Color.red);
      g.fillRoundRect(40, 45 - altura, 5, altura, 5, 5);
      g.setColor(Color.black);
      g.drawRoundRect(40, 5, 5, 40, 5, 5);
      image = new ImageIcon(img);
    }
    
    return image;
  }
  
  public void executar() throws Exception {
    MundoException ex = null;
    try {
      esperar(1);
      inteligencia();
    } catch (MundoException e) {
      ex = e;
    }
    objetoMundoImpl.pararMundo();
    if (ex != null) {
      throw ex;
    }
  }
  
  public abstract void inteligencia()
    throws Exception;
}
