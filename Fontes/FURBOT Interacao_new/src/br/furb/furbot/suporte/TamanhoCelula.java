package br.furb.furbot.suporte;

public enum TamanhoCelula {

	PEQUENA("P", 30),
	MEDIA("M", 40),
	GRANDE("G", 50);

	String sigla;
	int tamanho;
	
	private TamanhoCelula(String sigla, int tamanho) {
		this.sigla = sigla;
		this.tamanho = tamanho;
	}
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public static TamanhoCelula getEnumByPixels(int pixels) {
		for(TamanhoCelula EnumVal: values()) {
            if (EnumVal.getTamanho() == pixels) {
                return EnumVal;
            }
        }
		return GRANDE;
	}
	
	public static TamanhoCelula getEnumBySigla(String sigla) {
		for(TamanhoCelula EnumVal: values()) {
            if (EnumVal.getSigla().equalsIgnoreCase(sigla)) {
                return EnumVal;
            }
        }
		return GRANDE;
		
	}

}
