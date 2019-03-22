package br.furb.furbot.exceptions;






public class AcabouEnergiaException
  extends MundoException
{
  public AcabouEnergiaException(String nomeObjeto)
  {
    super("Acabou energia do " + nomeObjeto);
  }
}
