package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick 
 */
public class Projetil extends ObjetoDoMundoAdapter
{

    public Projetil(Direcao direcao)
    {
        this.direcao = direcao;
        new ObjetoMundoImpl(this);
    }

    public ImageIcon buildImage()
    {
        return LoadImage.getInstance().getIcon("imagens/shell.png");
    }

    public void executar()
        throws Exception
    {
        do
            try
            {
                switch(direcao)
                {
                case ABAIXO: // '\002'
                    andarAbaixo();
                    break;

                case ACIMA: // '\004'
                    andarAcima();
                    break;

                case DIREITA: // '\003'
                    andarDireita();
                    break;

                case ESQUERDA: // '\001'
                    andarEsquerda();
                    break;
                }
            }
            catch(MundoException mundoexception)
            {
                return;
            }
        while(true);
    }

    private Direcao direcao;

}