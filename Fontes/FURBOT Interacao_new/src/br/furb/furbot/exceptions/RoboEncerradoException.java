package br.furb.furbot.exceptions;


/**
 * 
 * @author Adilson Vahldick
 *
 */
public class RoboEncerradoException extends MundoException
{

    public RoboEncerradoException()
    {
        super("Mundo encerrado.");
    }

    public void printStackTrace()
    {
    }
}