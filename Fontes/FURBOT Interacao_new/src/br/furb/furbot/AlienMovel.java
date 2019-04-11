package br.furb.furbot;

import br.furb.furbot.suporte.LoadImage;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class AlienMovel extends ObjetoDoMundoAdapter
{

    public AlienMovel()
    {
    }

    public String toString()
    {
        return "AlienMovel";
    }

    public ImageIcon buildImage()
    {
        return LoadImage.getInstance().getIcon("imagens/alienmovel.png");
    }

    public void executar()
        throws Exception
    {
        List<Direcao> direcoes = new ArrayList<Direcao>();
        if(ehVazio(ACIMA) && !ehFim(ACIMA))
            direcoes.add(ACIMA);
        if(ehVazio(ABAIXO) && !ehFim(ABAIXO))
            direcoes.add(ABAIXO);
        if(ehVazio(ESQUERDA) && !ehFim(ESQUERDA))
            direcoes.add(ESQUERDA);
        if(ehVazio(DIREITA) && !ehFim(DIREITA))
            direcoes.add(DIREITA);
        if(direcoes.size() == 0)
            return;
        Random sorteio = new Random();
        int destino = sorteio.nextInt(direcoes.size());
        do
            switch(direcoes.get(destino))
            {
            case ABAIXO: // '\002'
                andarAbaixo();
                andarAcima();
                break;

            case ACIMA: // '\004'
                andarAcima();
                andarAbaixo();
                break;

            case DIREITA: // '\003'
                andarDireita();
                andarEsquerda();
                break;

            case ESQUERDA: // '\001'
                andarEsquerda();
                andarDireita();
                break;
            }
        while(true);
    }

 }