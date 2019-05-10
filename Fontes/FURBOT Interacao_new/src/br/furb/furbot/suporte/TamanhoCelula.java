package br.furb.furbot.suporte;

public enum TamanhoCelula {

	Pequena, Media, Grande;

	public static TamanhoCelula pixelsToTamanhoCelula(int tamanho) {
		switch (tamanho) {
		default:
		case 50:
			return Grande;
		case 30:
			return Pequena;
		case 40:
			return Media;
		}
	}

}
