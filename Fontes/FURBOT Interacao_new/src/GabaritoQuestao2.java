import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;

public class GabaritoQuestao2 extends Furbot {

	public static void main(String[] args) {
		MundoVisual.iniciar("GabaritoQuestao2.xml"); // inicia o mundo do furbot
	}

	@Override
	public void inteligencia() throws Exception {
		this.diga("Olá, eu sou o robô furbot");
		for(int i=0;i<5;i++) {
		   this.andarDireita();
		}
		for(int i=0;i<2;i++) {
		   this.andarAbaixo();
		}
		for(int i=0;i<3;i++) {
		   this.andarDireita();
		}
		for(int i=0;i<2;i++) {
		   this.andarAbaixo();
		}
		for(int i=0;i<3;i++) {
		   this.andarEsquerda();
		}
		for(int i=0;i<2;i++) {
		   this.andarAbaixo();
		}
		for(int i=0;i<3;i++) {
		   this.andarDireita();
		}
		for(int i=0;i<2;i++) {
		   this.andarAbaixo();
		}
		for(int i=0;i<6;i++) {
		   this.andarEsquerda();
		}
		for(int i=0;i<3;i++) {
		   this.andarAcima();
		}
		this.andarDireita();
		for(int i=0;i<2;i++) {
		   this.andarAcima();
		}
		for(int i=0;i<3;i++) {
		   this.andarEsquerda();
		}
		for(int i=0;i<3;i++) {
		   this.andarAcima();
		}
		this.diga("Conseguiiii");

		
	}

}
