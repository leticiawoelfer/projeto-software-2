package br.furb.furbot;

import javax.swing.ImageIcon;

import br.furb.furbot.suporte.LoadImage;

public class Alien extends ObjetoDoMundoAdapter {

	@Override
	public String toString() {
		return "Alien";
	}

	@Override
	public ImageIcon buildImage() {
		
		return LoadImage.getInstance().getIcon("imagens/alien_0.png");
		
	}

	@Override
	public void executar() throws Exception {
	}

}
