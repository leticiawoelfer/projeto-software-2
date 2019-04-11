package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick
 */
public class Numero extends ObjetoDoMundoAdapter {

	public Numero() {
	}

	public int getValor() {
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

	public ImageIcon buildImage() {
		/*
		 * BufferedImage img = new BufferedImage(MundoVisual.TAMCELL,
		 * MundoVisual.TAMCELL, BufferedImage.TYPE_INT_RGB);
		 * 
		 * int x = (int) (MundoVisual.TAMCELL * 0.1); int y = (int)
		 * (MundoVisual.TAMCELL * 0.5); int fontSize = (int)
		 * (MundoVisual.TAMCELL * 0.36); fonte = new Font("Arial", Font.PLAIN,
		 * fontSize);
		 * 
		 * Graphics2D g = img.createGraphics();
		 * 
		 * g.setColor(Color.white); g.fillRect(0, 0, MundoVisual.TAMCELL,
		 * MundoVisual.TAMCELL);
		 * 
		 * g.setColor(Color.black); g.setFont(fonte);
		 * g.drawString(String.valueOf(valor), x, y);
		 * 
		 * ImageIcon imagem = new ImageIcon(img); return imagem;
		 */

		BufferedImage img = new BufferedImage(50, 50,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = img.createGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, 50, 50);

		g.setColor(Color.black);
		g.setFont(fonte);
		g.drawString(String.valueOf(valor), 10, 25);

		ImageIcon imagem = new ImageIcon(img);
		return imagem;
	}

	public void executar() throws Exception {
	}

	public String toString() {
		return (new StringBuilder(String.valueOf(valor))).toString();
	}

	private int valor;
	private static Font fonte = new Font("Arial", 0, 18);
    

}