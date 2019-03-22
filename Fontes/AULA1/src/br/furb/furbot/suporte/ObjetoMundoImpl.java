package br.furb.furbot.suporte;

import br.furb.furbot.Direcao;
import br.furb.furbot.Furbot;
import br.furb.furbot.ObjetoDoMundo;
import br.furb.furbot.exceptions.AcabouEnergiaException;
import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.exceptions.RoboEncerradoException;
import java.awt.Image;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;





public class ObjetoMundoImpl
  implements PosicaoMundo
{
  public ObjetoMundoImpl(ObjetoDoMundo objetoMundo)
  {
    podeParar = false;
    executando = false;
    meuTempoEspera = -1;
    bloqueado = true;
    umaListaComigo = new ArrayList();
    umaListaComigo.add(this);
    this.objetoMundo = objetoMundo;
    objetoMundo.setObjetoMundoImpl(this);
  }
  
  public <T extends ObjetoDoMundo> T getObjetoMundo()
  {
    return objetoMundo;
  }
  
  public void run() {
    podeParar = false;
    try {
      executando = true;
      inteligencia();
    } catch (Exception e) {
      e.printStackTrace();
    }
    executando = false;
    podeParar = false;
  }
  
  public void parar() {
    if (executando)
      podeParar = true;
  }
  
  public boolean isParar() {
    return podeParar;
  }
  
  public int getMaxEnergia() {
    return maxEnergia;
  }
  
  public void setMaxEnergia(int maxEnergia) {
    this.maxEnergia = maxEnergia;
    setEnergia(maxEnergia);
  }
  
  public boolean isBloqueado() {
    return bloqueado;
  }
  
  public boolean isUsarEnergia() {
    return usarEnergia;
  }
  
  public void setUsarEnergia(boolean usarEnergia) {
    this.usarEnergia = usarEnergia;
  }
  
  public int getEnergia() {
    return energia;
  }
  
  public void setEnergia(int energia) {
    setUsarEnergia(true);
    this.energia = energia;
  }
  
  private void gastarEnergia(int qtde) {
    if (!isUsarEnergia())
      return;
    if (qtde > energia) {
      throw new AcabouEnergiaException(toString());
    }
    energia -= qtde;
    setImage(null);
    mundo.repintar();
  }
  

  public void setBloqueado(boolean bloqueado)
  {
    this.bloqueado = bloqueado;
  }
  
  public Mundo getMundo() {
    return mundo;
  }
  
  public void setMundo(Mundo mundo) {
    this.mundo = mundo;
  }
  
  public void inteligencia() throws Exception {
    objetoMundo.executar();
  }
  
  public final void diga(Object object) throws MundoException {
    if (object == null) {
      diga("Vazio");
    } else
      diga(object.toString());
  }
  
  public int getTempoEspera() {
    if (meuTempoEspera == -1) {
      return tempoEspera;
    }
    return meuTempoEspera;
  }
  
  public void setTempoEspera(int milisegundos) {
    meuTempoEspera = milisegundos;
  }
  
  public final void diga(String texto) throws MundoException {
    if (isParar())
      throw new RoboEncerradoException();
    List<MundoException> ex = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    try {
      gastarEnergia(10);
      mundo.disse(euMesmo, texto);
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public void limparConsole() throws MundoException
  {
    if (isParar())
      throw new RoboEncerradoException();
    List<MundoException> ex = new ArrayList();
    try {
      mundo.limparConsole();
      











      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public final void andarEsquerda() throws MundoException
  {
    doAndar(Direcao.ESQUERDA);
  }
  
  public final void andarDireita() throws MundoException {
    doAndar(Direcao.DIREITA);
  }
  
  public final void andarAcima() throws MundoException {
    doAndar(Direcao.ACIMA);
  }
  
  public final void andarAbaixo() throws MundoException {
    doAndar(Direcao.ABAIXO);
  }
  
  private void doAndar(Direcao direcao) throws MundoException {
    if (isParar())
      throw new RoboEncerradoException();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try
    {
      gastarEnergia(20);
      mundo.andar(euMesmo, direcao);
      












      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public boolean ehVazio(Direcao direcao) throws MundoException
  {
    if (isParar())
      throw new RoboEncerradoException();
    List<Boolean> bool = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(5);
      bool.add(Boolean.valueOf(
        mundo.ehVazio(euMesmo, direcao)));
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0)
      throw ((MundoException)ex.get(0));
    return (bool.size() > 0) && (((Boolean)bool.get(0)).booleanValue());
  }
  
  public boolean ehFim(Direcao direcao) throws MundoException {
    if (isParar())
      throw new RoboEncerradoException();
    List<Boolean> bool = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(5);
      bool
        .add(Boolean.valueOf(mundo.ehFim(euMesmo, 
        direcao)));
      














      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0)
      throw ((MundoException)ex.get(0));
    return (bool.size() > 0) && (((Boolean)bool.get(0)).booleanValue());
  }
  
  public ObjetoMundoImpl getObjeto(Direcao direcao) throws MundoException
  {
    if (isParar())
      throw new RoboEncerradoException();
    List<ObjetoMundoImpl> objRet = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(15);
      objRet.add(mundo.getObjeto(euMesmo, direcao));
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      objRet.add(null);
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
    return (ObjetoMundoImpl)objRet.get(0);
  }
  
  public ObjetoMundoImpl getObjeto(int x, int y) {
    if (isParar())
      throw new RoboEncerradoException();
    List<ObjetoMundoImpl> objRet = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(30);
      objRet.add(mundo.getObjeto(euMesmo, x, y));
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      objRet.add(null);
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
    return (ObjetoMundoImpl)objRet.get(0);
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public final ImageIcon getImage() {
    try {
      if (imagem == null) {
        ImageIcon imageBuilded = objetoMundo.buildImage();
        if (imageBuilded != null)
          imagem = new ImageIcon(imageBuilded.getImage()
            .getScaledInstance(mundo.getTamCell(), mundo.getTamCell(), 4));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return imagem;
  }
  
  public final void setImage(ImageIcon imagem) {
    this.imagem = imagem;
  }
  
  public void repintar() {
    if (isParar())
      throw new RoboEncerradoException();
    try {
      if (mundo != null) {
        mundo.repintar();

      }
      


    }
    catch (Exception e)
    {


      e.printStackTrace();
    }
  }
  
  public boolean ehVazio(int x, int y) {
    if (isParar())
      throw new RoboEncerradoException();
    List<Boolean> bool = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(5);
      bool.add(Boolean.valueOf(mundo.ehVazio(euMesmo, x, y)));
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0)
      throw ((MundoException)ex.get(0));
    return (bool.size() > 0) && (((Boolean)bool.get(0)).booleanValue());
  }
  
  public int getQtdadeLin() {
    return mundo.getQtdadeLin();
  }
  
  public int getQtdadeCol() {
    return mundo.getQtdadeCol();
  }
  
  public <T extends ObjetoDoMundo> List<T> getObjetos(Class<T> clazz)
  {
    if (isParar())
      throw new RoboEncerradoException();
    List<List<ObjetoMundoImpl>> objetos = new ArrayList();
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(5 * objetos.size());
      objetos.add(mundo.getObjetos());
      













      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      objetos.add(new ArrayList());
      e.printStackTrace();
    }
    if (ex.size() > 0)
      throw ((MundoException)ex.get(0));
    List<T> objsMundo = new ArrayList();
    for (ObjetoMundoImpl obj : (List)objetos.get(0)) {
      if ((!Furbot.class.isInstance(obj.getObjetoMundo())) && (
        (clazz == null) || (obj.getObjetoMundo().getClass() == clazz))) {
        objsMundo.add(obj.getObjetoMundo());
      }
    }
    return objsMundo;
  }
  
  public List<ObjetoMundoImpl> getObjetos() {
    return umaListaComigo;
  }
  
  public void pararMundo() throws MundoException {
    try {
      mundo.parar();
      








      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public int getUltimaTeclaPress() {
    if (isParar())
      throw new RoboEncerradoException();
    List<Integer> ultTeclaPress = new ArrayList();
    try {
      ultTeclaPress.add(Integer.valueOf(
        mundo.getUltimaTeclaPress()));
      









      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      ultTeclaPress.clear();
      ultTeclaPress.add(Integer.valueOf(0));
      e.printStackTrace();
    }
    return ((Integer)ultTeclaPress.get(0)).intValue();
  }
  
  public void esperarAlguem() {
    if (isParar())
      throw new RoboEncerradoException();
    ObjetoMundoImpl euMesmo = this;
    try {
      mundo.addListenerCelula(euMesmo);
      








      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void doAdicionarObjetoNoMundo(ObjetoMundoImpl objetoMundoImpl, boolean embaixo)
  {
    if (isParar())
      throw new RoboEncerradoException();
    List<MundoException> ex = new ArrayList();
    try {
      mundo.addObjetoMundoImpl(objetoMundoImpl, embaixo);
      










      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public void inserirObjetoNoMundo(ObjetoMundoImpl obj)
  {
    doAdicionarObjetoNoMundo(obj, true);
  }
  
  public void adicionarObjetoNoMundo(ObjetoMundoImpl obj) {
    doAdicionarObjetoNoMundo(obj, false);
  }
  
  public void removerMe() {
    if (isParar())
      throw new RoboEncerradoException();
    List<MundoException> ex = new ArrayList();
    ObjetoMundoImpl euMesmo = this;
    try {
      mundo.removerObjeto(euMesmo);
      











      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public void setExplodiu(boolean explodiu)
  {
    this.explodiu = explodiu;
  }
  
  public boolean isExplodiu() {
    return explodiu;
  }
  
  public void mudarPosicao(int x, int y) {
    if (isParar())
      throw new RoboEncerradoException();
    ObjetoMundoImpl euMesmo = this;
    List<MundoException> ex = new ArrayList();
    try {
      gastarEnergia(100);
      mundo.mudarPosicao(euMesmo, x, y);
      












      Thread.sleep(getTempoEspera());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ex.size() > 0) {
      throw ((MundoException)ex.get(0));
    }
  }
  
  public void setRemovido(boolean removido)
  {
    this.removido = removido;
  }
  
  public boolean isRemovido() {
    return removido;
  }
  
  public static int tempoEspera = 500;
  private boolean podeParar;
  private int meuTempoEspera;
  private int x;
  private int y;
  private Mundo mundo;
  private boolean bloqueado;
  private ObjetoDoMundo objetoMundo;
  private boolean usarEnergia;
  private int energia;
  private int maxEnergia;
  private ImageIcon imagem;
  private List<ObjetoMundoImpl> umaListaComigo;
  private boolean explodiu;
  private boolean removido;
  private boolean executando;
}
