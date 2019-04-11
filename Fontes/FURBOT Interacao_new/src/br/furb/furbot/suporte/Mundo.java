package br.furb.furbot.suporte;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import br.furb.furbot.Direcao;
import br.furb.furbot.Furbot;
import br.furb.furbot.ListenerMundo;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.exceptions.BateuException;
import br.furb.furbot.exceptions.LimiteException;
import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.exceptions.NaoEraParaEstarAquiException;
import br.furb.furbot.exceptions.ObjRemovidoException;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class Mundo
{

    public synchronized void addListener(ListenerMundo listener)
    {
        listeners.add(listener);
    }

    public synchronized void removeListener(ListenerMundo listener)
    {
        listeners.remove(listener);
    }

    public Mundo(int qtdadeLin, int qtdadeCol)
    {
        imgExplosao = LoadImage.getInstance().getIcon("imagens/explosao.png");
        listeners = new ArrayList<ListenerMundo>();
        listenersMudouPosicao = new ArrayList<ListenerMudouPosicao>();
        modelo = new PosicaoMundo[qtdadeLin][qtdadeCol];
        this.qtdadeLin = qtdadeLin;
        this.qtdadeCol = qtdadeCol;
        MundoVisual.setAtributo("lll", qtdadeLin);        
        MundoVisual.setAtributo("ccc", qtdadeCol);                
    }

    public int getQtdadeLin()
    {
        return qtdadeLin;
    }

    public int getQtdadeCol()
    {
        return qtdadeCol;
    }

    public Object getImagem(int lin, int col)
    {
        if(modelo[lin][col] == null)
            return null;
        else
            return modelo[lin][col].getImage();
    }

    private void doAndar(ObjetoMundoImpl objMundo, Direcao direcao, int novoX, int novoY)
        throws MundoException
    {
        int xAnt;
        int yAnt;
        MundoException me;
        xAnt = objMundo.getX();
        yAnt = objMundo.getY();
        me = null;
        if(!movimentar(objMundo, novoX, novoY))
            return;
        try
        {
            addObjetoMundoImpl(objMundo);
            removerObjeto(objMundo, yAnt, xAnt);
        }
        catch(MundoException e)
        {
            objMundo.setExplodiu(true);
            objMundo.setImage(imgExplosao);
            me = e;
        }
        for(ListenerMundo listener:listeners)
        	listener.andou(objMundo, direcao, xAnt, yAnt);

        if(me != null)
        {
            if(Furbot.class.isAssignableFrom(objMundo.getObjetoMundo().getClass()))
            {
                throw me;
            } else
            {
                removerObjeto(objMundo);
                throw me;
            }
        } else
        {
            return;
        }
    }

    public synchronized void andar(ObjetoMundoImpl objMundo, Direcao direcao)
        throws MundoException
    {
        int novoX = objMundo.getX();
        int novoY = objMundo.getY();
        switch(direcao)
        {
        case ABAIXO: // '\002'
            novoY++;
            break;

        case ACIMA: // '\004'
            novoY--;
            break;

        case DIREITA: // '\003'
            novoX++;
            break;

        case ESQUERDA: // '\001'
            novoX--;
            break;
        }
        doAndar(objMundo, direcao, novoX, novoY);
    }

    private void removerObjeto(ObjetoMundoImpl objMundo, int yAnt, int xAnt)
    {
        if(modelo[yAnt][xAnt] == null)
            if(objMundo.isRemovido())
                throw new ObjRemovidoException();
            else
                throw new NaoEraParaEstarAquiException();
        if(modelo[yAnt][xAnt].getObjetos().size() > 1)
        {
            PosicaoMundo objAlvo = null;
            List<ObjetoMundoImpl> objs = modelo[yAnt][xAnt].getObjetos();
            objs.remove(objMundo);
            if(objs.size() > 1)
                objAlvo = modelo[yAnt][xAnt];
            else
                objAlvo = (PosicaoMundo)objs.get(0);
            modelo[yAnt][xAnt] = objAlvo;
        } else
        {
            modelo[yAnt][xAnt] = null;
        }
    }

    public synchronized void removerObjeto(ObjetoMundoImpl objMundo)
    {
        if (objMundo == null)
        	return;
        
    	removerObjeto(objMundo, objMundo.getY(), objMundo.getX());
        objMundo.setRemovido(true);
        repintar();
    }

    private void atribuirModelo(int y, int x, PosicaoMundo posicaoMundo)
    {
        List<ListenerMudouPosicao> encontrados = new ArrayList<ListenerMudouPosicao>();
        for(ListenerMudouPosicao listener:listenersMudouPosicao)
        {
            if(listener.getX() == x && listener.getY() == y)
            {
                List<ObjetoMundoImpl> listObjs = posicaoMundo.getObjetos();
                ObjetoMundoImpl temDiferente = null;
                for(ObjetoMundoImpl omi:listObjs)
                {
                    if(omi != listener.getObjeto())
                    {
                        temDiferente = omi;
                        break;
                    }
                }

                if(temDiferente != null)
                {
                    listener.chegou(temDiferente);
                    encontrados.add(listener);
                }
            }
        }

        listenersMudouPosicao.removeAll(encontrados);
        modelo[y][x] = posicaoMundo;
    }

    private boolean movimentar(ObjetoMundoImpl objMundo, int x, int y)
        throws MundoException
    {
        if(x < 0 || y < 0 || x >= getQtdadeCol() || y >= getQtdadeLin())
            if(isExplodir())
                throw new LimiteException();
            else
                return false;
        if(modelo[y][x] != null && (modelo[y][x].isBloqueado() || objMundo.isBloqueado()))
        {
            if(isExplodir())
                throw new BateuException();
            else
                return false;
        } else
        {
            objMundo.setX(x);
            objMundo.setY(y);
            return true;
        }
    }

    public synchronized void disse(ObjetoMundoImpl objetoMundo, String texto)
    {
        for(ListenerMundo listener:listeners)
        	listener.disse(objetoMundo, texto);

    }

    public synchronized void limparConsole()
    {
        for(ListenerMundo listener:listeners)
        	listener.limparConsole();

    }

    public synchronized ObjetoMundoImpl getObjeto(ObjetoMundoImpl objetoMundo, int x, int y)
    {
        if(x >= qtdadeCol || y >= qtdadeLin)
            return null;
        if(modelo[y][x] == null)
            return null;
        for(ObjetoMundoImpl objRetorno:modelo[y][x].getObjetos())
        {
            if(objRetorno != objetoMundo)
                return objRetorno;
        }

        return null;
    }

    public synchronized ObjetoMundoImpl getObjeto(ObjetoMundoImpl objetoMundo, Direcao direcao)
    {
        if(ehFim(objetoMundo, direcao))
            return null;
        int x = objetoMundo.getX();
        int y = objetoMundo.getY();
        switch(direcao)
        {
        case ABAIXO: // '\002'
            y = objetoMundo.getY() + 1;
            x = objetoMundo.getX();
            break;

        case ACIMA: // '\004'
            y = objetoMundo.getY() - 1;
            x = objetoMundo.getX();
            break;

        case DIREITA: // '\003'
            y = objetoMundo.getY();
            x = objetoMundo.getX() + 1;
            break;

        case ESQUERDA: // '\001'
            y = objetoMundo.getY();
            x = objetoMundo.getX() - 1;
            break;
        }
        return getObjeto(objetoMundo, x, y);
    }

    public synchronized boolean ehVazio(ObjetoMundoImpl objetoMundo, Direcao direcao)
    {
        return getObjeto(objetoMundo, direcao) == null;
    }

    public synchronized boolean ehVazio(ObjetoMundoImpl objetoMundoImpl, int x, int y)
    {
        return modelo[y][x] == null;
    }

    public synchronized void run()
    {
        for(int y = 0; y < qtdadeLin; y++)
        {
            for(int x = 0; x < qtdadeCol; x++)
                if(modelo[y][x] != null)
                    (new Thread(modelo[y][x])).start();

        }

    }

    public synchronized void parar()
    {
        for(int y = 0; y < qtdadeLin; y++)
        {
            for(int x = 0; x < qtdadeCol; x++)
                if(modelo[y][x] != null)
                    modelo[y][x].parar();

        }

        fimExecucao();
    }

    public synchronized boolean ehFim(ObjetoMundoImpl objetoMundo, Direcao direcao)
    {
        switch(direcao)
        {
        case ABAIXO: // '\002'
            return objetoMundo.getY() == getQtdadeLin() - 1;

        case ACIMA: // '\004'
            return objetoMundo.getY() == 0;

        case DIREITA: // '\003'
            return objetoMundo.getX() == getQtdadeCol() - 1;

        case ESQUERDA: // '\001'
            return objetoMundo.getX() == 0;
        }
        return false;
    }

    public synchronized void fimExecucao()
    {
        for(ListenerMundo listener:listeners)
        	listener.fimExecucao();

    }

    public boolean isExplodir()
    {
        return explodir;
    }

    public void setExplodir(boolean explodir)
    {
        this.explodir = explodir;
    }

    protected synchronized void repintar()
    {
        for(ListenerMundo listener:listeners)
        	listener.repintar();

    }

    public synchronized void addObjetoMundoImpl(ObjetoMundoImpl objMundo)
    {
        addObjetoMundoImpl(objMundo, false);
    }

    public synchronized void addObjetoMundoImpl(ObjetoMundoImpl objMundo, boolean embaixo)
    {
        if(modelo[objMundo.getY()][objMundo.getX()] != null)
        {
            ListaObjetosMundoImpl lista = null;
            if(modelo[objMundo.getY()][objMundo.getX()].getObjetos().size() > 1)
            {
                lista = (ListaObjetosMundoImpl)modelo[objMundo.getY()][objMundo.getX()];
            } else
            {
                lista = new ListaObjetosMundoImpl();
                lista.add((ObjetoMundoImpl)modelo[objMundo.getY()][objMundo.getX()]);
            }
            if(embaixo)
                lista.insert(0, objMundo);
            else
                lista.add(objMundo);
            atribuirModelo(objMundo.getY(), objMundo.getX(), lista);
        } else
        {
            atribuirModelo(objMundo.getY(), objMundo.getX(), objMundo);
        }
        repintar();
    }

    public synchronized List<ObjetoMundoImpl> getObjetos()
    {
        List<ObjetoMundoImpl> objs = new ArrayList<ObjetoMundoImpl>();
        for(int y = 0; y < qtdadeLin; y++)
        {
            for(int x = 0; x < qtdadeCol; x++)
                if(modelo[y][x] != null)
                    objs.addAll(modelo[y][x].getObjetos());

        }

        return objs;
    }

    public synchronized void pressionadaTecla(int keyCode)
    {
        ultTeclaPress = keyCode;
        
    }

    public synchronized int getUltimaTeclaPress()
        throws MundoException
    {
        int tecla = ultTeclaPress;
        ultTeclaPress = 0;
        return tecla;
    }
    
    public synchronized void geraCodigo(){
        //listar();
    }

    public synchronized void addListenerCelula(ObjetoMundoImpl euMesmo)
    {
        listenersMudouPosicao.add(new ListenerMudouPosicao(euMesmo));
    }

    public synchronized void mudarPosicao(ObjetoMundoImpl objMundo, int x, int y)
    {
        doAndar(objMundo, Direcao.AQUIMESMO, x, y);
    }

    public void setUsarLinhasNaGrade(boolean usarLinhasNaGrade)
    {
        this.usarLinhasNaGrade = usarLinhasNaGrade;
    }

    public boolean isUsarLinhasNaGrade()
    {
        return usarLinhasNaGrade;
    }

	public void setTamCell(String tamanhoCel) {
		
		switch (tamanhoCel.charAt(0)) {
			default : this.tamCell = 50; break;
			case 'M': this.tamCell = 40; break;
			case 'P': this.tamCell = 30; break;
		}
	}
	
	public int getTamCell() {
		return tamCell;
	}
	
    private PosicaoMundo modelo[][];
    private ImageIcon imgExplosao;
    private List<ListenerMundo> listeners;
    private int qtdadeCol;
    private int qtdadeLin;
    private boolean explodir;
    private int ultTeclaPress;
    private List<ListenerMudouPosicao> listenersMudouPosicao;
    private boolean usarLinhasNaGrade = true;
    private int tamCell = 50;
}