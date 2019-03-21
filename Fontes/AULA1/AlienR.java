
import br.furb.furbot.Alien;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author interacaodsc
 */
public class AlienR extends Alien {

    private boolean sentido;

    public AlienR(boolean sentido) {
        this.sentido = sentido;
    }

    public ImageIcon buildImage(){
        ImageIcon i = new ImageIcon("alien_original.jpg");
        return i;
    }

    public void executar() throws Exception {
        while (!jahExplodiu()) {
            if (sentido) {
                while (!ehFim(DIREITA)) {

                    esperar(1);
                    andarDireita();
                }//fim ehile
                while (!ehFim(ESQUERDA)) {
                    esperar(1);
                    andarEsquerda();
                }//fim while
            } else {
                while (!ehFim(ACIMA)) {

                    esperar(1);
                    andarAcima();
                }//fim ehile
                while (!ehFim(ABAIXO)) {
                    esperar(1);
                    andarAbaixo();
                }//fim while
            }//fim else
        }//fim while

    }//fim executar
}//fim class

