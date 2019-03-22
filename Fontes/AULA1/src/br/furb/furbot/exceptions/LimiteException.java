package br.furb.furbot.exceptions;







public class LimiteException
  extends MundoException
{
  public LimiteException()
  {
    super("Alcançou o limite do mundo.");
  }
}
