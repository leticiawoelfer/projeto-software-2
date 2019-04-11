package br.furb.furbot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import br.furb.furbot.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick
 */
public class Tesouro extends Numero {

	public Tesouro() {
	}

	public ImageIcon buildImage() {
		/*
		ImageIcon image = LoadImage.getInstance().getIcon("imagens/tesouro.jpg");
		BufferedImage img = new BufferedImage(MundoVisual.TAMCELL, MundoVisual.TAMCELL, BufferedImage.TYPE_INT_RGB);
		
		int imageTopLeft = (int) (MundoVisual.TAMCELL * 0.04f);
		int rectTopLeft = (int) (MundoVisual.TAMCELL * 0.6f);
		int rectSize = (int) (MundoVisual.TAMCELL * 0.4f);
		int stringX = (int) (MundoVisual.TAMCELL * 0.64f);
		int stringY = (int) (MundoVisual.TAMCELL * 0.9f);
		
		Graphics2D g = img.createGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, MundoVisual.TAMCELL, MundoVisual.TAMCELL);
		g.drawImage(image.getImage(), imageTopLeft, imageTopLeft, null);
		
		g.fillRect(rectTopLeft, rectTopLeft, rectSize, rectSize);
		g.setColor(Color.black);
		g.drawRect(rectTopLeft, rectTopLeft, rectSize-1, rectSize-1);
		
		int fontSize = (int) (MundoVisual.TAMCELL * 0.36);
		Font fonte = new Font("Arial", Font.PLAIN, fontSize);
		g.setFont(fonte);
		g.setColor(Color.red);
		g.drawString(String.valueOf(getValor()), stringX, stringY);
		
		return new ImageIcon(img);
		*/

		ImageIcon image = LoadImage.getInstance().getIcon("imagens/tesouro.jpg");
		BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = img.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 50, 50);
		g.drawImage(image.getImage(), 2, 2, null);
		
		g.fillRect(30, 30, 20, 20);
		g.setColor(Color.black);
		g.drawRect(30, 30, 19, 19);
		
		g.setFont(fonte);
		g.setColor(Color.red);
		g.drawString(String.valueOf(getValor()), 32, 45);
		
		return new ImageIcon(img);
	}

	private static Font fonte = new Font("Arial", Font.PLAIN, 14);
	
}