package br.furb.furbot.exceptions;







public class BateuException
  extends MundoException
{
  public BateuException()
  {
    super("O objeto tentou invadir o espaço de outro objeto");
  }
}
