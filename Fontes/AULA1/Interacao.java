
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.ObjetoDoMundo;
import java.awt.event.KeyEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author interacaodsc
 */
public class Interacao extends Furbot {

    private boolean jogar;
    private int qtdAlien = 26;

    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        int contadorAlien = 0;
        ObjetoDoMundo obj = null;
        this.jogar = true;
        adicionarAlienR();
        while (jogar) {
            andar();
            obj = getObjetoXY(getX(), getY());
            if(obj != null){
                removerObjetoDoMundo(obj);
                contadorAlien++;
                limparConsole();
                diga("Peguei o alien "+contadorAlien);
            }//fim if
            if(contadorAlien == this.qtdAlien){
                jogar = false;
                diga("PARABENS PEGAMOS TODOS OS ALIENS");
            }//fim if
        }//fim while
        diga("FIM DE JOGO");
    }//fim inteligencia

    public void adicionarAlienR(){
        AlienR r1 = new AlienR(true);
        r1.desbloquear();
        adicionarObjetoNoMundoXY(r1, 0, 0);

    }

    public void andar() {
        switch (getUltimaTeclaPress()) {
            case 37:
                if (!ehFim(ESQUERDA)) {
                    andarEsquerda();
                }//fim if
                break;
            case 38:
                if (!ehFim(ACIMA)) {
                    andarAcima();
                }//fim if
                break;
            case 39:
                if (!ehFim(DIREITA)) {
                    andarDireita();
                }//fim if
                break;
            case 40:
                if (!ehFim(ABAIXO)) {
                    andarAbaixo();
                }//fim if
                break;
            case KeyEvent.VK_ESCAPE:
                jogar = false;
                break;
            default:
                break;
        }//fim switch
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Interacao.xml");
    }//fim main

}//fim class

