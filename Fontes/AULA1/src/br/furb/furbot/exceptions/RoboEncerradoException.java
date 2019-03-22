package br.furb.furbot.exceptions;







public class RoboEncerradoException
  extends MundoException
{
  public RoboEncerradoException()
  {
    super("Mundo encerrado.");
  }
  
  public void printStackTrace() {}
}
