
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;

public class MundoDeFurbot extends Furbot {

	public static void main(String[] args) {
		MundoVisual.iniciar("MundoDeFurbot.xml"); // inicia o mundo do furbot
	}
	
	public void inteligencia() throws Exception {
		// escreva o seu código aqui
		diga("ola mundo!!!!");
		andarDireita();
		andarAcima();
		andarDireita();
		andarDireita();
		andarAbaixo();
		andarAbaixo();
		andarAbaixo();
		andarAcima();
		andarAcima();
		andarAcima();
		andarEsquerda();
		
		diga("cheguei ao fim do algoritmo... e estou vivo!!!");
	}
}
