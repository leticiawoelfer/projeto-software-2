package br.furb.furbot.suporte;


/**
 * 
 * @author Adilson Vahldick
 *
 */
public class ListenerMudouPosicao
{

    public ListenerMudouPosicao(ObjetoMundoImpl objetoMundo)
    {
        this.objetoMundo = objetoMundo;
        x = objetoMundo.getX();
        y = objetoMundo.getY();
    }

    public void chegou(ObjetoMundoImpl objeto)
    {
        objetoMundo.getObjetoMundo().chegouUmObjetoNaPosicao(objeto.getObjetoMundo());
    }

    public Object getObjeto()
    {
        return objetoMundo;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    private ObjetoMundoImpl objetoMundo;
    private int y;
    private int x;
}