package br.furb.furbot.suporte;

public enum TamanhoCelula
{
  Pequena,  Media,  Grande;
  
  public static TamanhoCelula pixelsToTamanhoCelula(int tamanho) {
    switch (tamanho) {
    default:  return Grande;
    case 30:  return Pequena; }
    return Media;
  }
}
