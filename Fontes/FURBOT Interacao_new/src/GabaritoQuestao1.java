import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;

public class GabaritoQuestao1 extends Furbot {

	public static void main(String[] args) {
		MundoVisual.iniciar("GabaritoQuestao1.xml"); // inicia o mundo do furbot
	}

	@Override
	public void inteligencia() throws Exception {
		diga("GabaritoQuestao1\nOlá mundo!!!!");
		andarDireita();
		andarAbaixo();
		andarAbaixo();
		andarAbaixo();
		andarAbaixo();
		andarEsquerda();
		andarDireita();
		andarDireita();
		andarDireita();
		andarDireita();
		
		diga("Cheguei ao fim do algoritmo... E estou vivo!!!");
		
	}

}
