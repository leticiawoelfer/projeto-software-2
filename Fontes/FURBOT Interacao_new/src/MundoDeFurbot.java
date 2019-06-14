
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;

public class MundoDeFurbot extends Furbot {

	public void inteligencia() throws Exception {
		// escreva o seu código aqui
		diga("ola mundo!!!!");
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();

		diga("cheguei ao fim do algoritmo... e estou vivo!!!");
	}

	public static void main(String[] args) {
		MundoVisual.iniciar("MundoDeFurbot.xml"); // inicia o mundo do furbot
	}
}
