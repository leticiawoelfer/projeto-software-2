package br.furb.furbot;

import br.furb.furbot.suporte.ObjetoMundoImpl;

/**
 * 
 * @author Adilson Vahldick 
 */
public interface ListenerMundo
{

    public abstract void disse(ObjetoMundoImpl objetomundoimpl, String s);

    public abstract void andou(ObjetoMundoImpl objetomundoimpl, Direcao direcao, int i, int j);

    public abstract void fimExecucao();

    public abstract void repintar();

    public abstract void limparConsole();
}