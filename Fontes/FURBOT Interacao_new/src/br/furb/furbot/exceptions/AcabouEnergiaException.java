package br.furb.furbot.exceptions;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class AcabouEnergiaException extends MundoException
{

    public AcabouEnergiaException(String nomeObjeto)
    {
        super((new StringBuilder("Acabou energia do ")).append(nomeObjeto).toString());
    }
}