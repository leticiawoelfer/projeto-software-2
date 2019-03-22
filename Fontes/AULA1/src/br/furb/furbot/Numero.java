package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Numero extends ObjetoDoMundoAdapter
{
  private int valor;
  
  public Numero() {}
  
  public int getValor()
  {
    return valor;
  }
  
  public void setValorInicial(int valor) {
    this.valor = valor;
  }
  
  public void setValor(int numero) {
    valor = numero;
    try {
      repintar();
    } catch (MundoException e) {
      e.printStackTrace();
    }
  }
  



















  public ImageIcon buildImage()
  {
    BufferedImage img = new BufferedImage(50, 50, 
      1);
    
    Graphics2D g = img.createGraphics();
    
    g.setColor(Color.white);
    g.fillRect(0, 0, 50, 50);
    
    g.setColor(Color.black);
    g.setFont(fonte);
    g.drawString(String.valueOf(valor), 10, 25);
    
    ImageIcon imagem = new ImageIcon(img);
    return imagem;
  }
  
  public void executar() throws Exception
  {}
  
  public String toString() {
    return valor;
  }
  

  private static Font fonte = new Font("Arial", 0, 18);
}
