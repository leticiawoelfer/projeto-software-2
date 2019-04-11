package br.furb.furbot.suporte;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class FurbotRandom
{

    public FurbotRandom()
    {
        tipoRandom = TipoRandom.NENHUM;
        limiteInfRandomX = 0;
        limiteInfRandomY = 0;
        limiteSupRandomX = -1;
        limiteSupRandomY = -1;
    }

    public int getLimiteSupRandomX()
    {
        return limiteSupRandomX;
    }

    public void setLimiteSupRandomX(String limiteSupRandomX)
    {
        this.limiteSupRandomX = Integer.valueOf(limiteSupRandomX).intValue();
    }

    public int getLimiteSupRandomY()
    {
        return limiteSupRandomY;
    }

    public void setLimiteSupRandomY(String limiteSupRandomY)
    {
        this.limiteSupRandomY = Integer.valueOf(limiteSupRandomY).intValue();
    }

    public int getLimiteInfRandomY()
    {
        return limiteInfRandomY;
    }

    public void setLimiteInfRandomY(String limiteInfRandomY)
    {
        this.limiteInfRandomY = Integer.valueOf(limiteInfRandomY).intValue();
    }

    public int getLimiteInfRandomX()
    {
        return limiteInfRandomX;
    }

    public void setLimiteInfRandomX(String limiteInfRandomX)
    {
        this.limiteInfRandomX = Integer.valueOf(limiteInfRandomX).intValue();
    }

    public boolean isRandom()
    {
        return tipoRandom != TipoRandom.NENHUM;
    }

    public boolean isRandomXY()
    {
        return tipoRandom == TipoRandom.XY;
    }

    public void setRandomXY()
    {
        tipoRandom = TipoRandom.XY;
    }

    public boolean isRandomX()
    {
        return tipoRandom == TipoRandom.X;
    }

    public void setRandomX()
    {
        tipoRandom = TipoRandom.X;
    }

    public boolean isRandomY()
    {
        return tipoRandom == TipoRandom.Y;
    }

    public void setRandomY()
    {
        tipoRandom = TipoRandom.Y;
    }

    private TipoRandom tipoRandom;
    private int limiteInfRandomX;
    private int limiteInfRandomY;
    private int limiteSupRandomX;
    private int limiteSupRandomY;
}