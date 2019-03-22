package br.furb.furbot;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.Exercicio;
import br.furb.furbot.suporte.ExercicioFactory;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;

public class MundoVisual extends javax.swing.JFrame
{
  private MundoFurbot mundoFurbot;
  private JTextArea console;
  private boolean executou;
  private String[] nomesArquivosXML;
  private int mundoAtual;
  private boolean executarProxMundo;
  private Class<?> furbotClass;
  private static MundoVisual euMesmo;
  
  public static void proxMundo(int indiceMundo)
  {
    if (indiceMundo >= euMesmonomesArquivosXML.length) {
      throw new MundoException("Índice do mundo fora da faixa.");
    }
    euMesmomundoAtual = indiceMundo;
    euMesmoexecutarProxMundo = true;
  }
  

  public static int getMundo()
  {
    return euMesmomundoAtual;
  }
  
  public static <T> void setAtributo(String nome, T valor) {
    atributos.put(nome, valor);
  }
  
  public static <T> T getAtributo(String nome)
  {
    return atributos.get(nome);
  }
  
  public static boolean temAtributo(String nome) {
    return atributos.containsKey(nome);
  }
  
  private MundoVisual(Exercicio exercicio, String autor)
    throws Exception
  {
    executou = false;
    euMesmo = this;
    
    mundoFurbot = new MundoFurbot(exercicio);
    mundoFurbot.addDisseramListener(new DisseramListener()
    {
      public void disse(String texto)
      {
        console.setText(
          console.getText() + texto + "\n");
      }
      

      public void limpar()
      {
        console.setText("");
      }
    });
    mundoFurbot.addFinalizouExecucaoListener(new FinalizouExecucaoListener()
    {
      public void finalizouExecucao()
      {
        if (executarProxMundo) {
          executarProxMundo = false;
          try {
            MundoVisual.this.executar(ExercicioFactory.create(nomesArquivosXML[mundoAtual], furbotClass));
          } catch (Exception e) {
            throw new MundoException(e.getMessage());
          }
        } else {
          jbExecutar.setEnabled(true);
          jbParar.setEnabled(false);
          jbRenovar.setEnabled(true);
        }
      }
    });
    initComponents(exercicio, autor);
  }
  
  public MundoVisual(String autor, Exercicio exercicio, Class<?> furbotClass, String[] nomesArquivosXML) throws Exception
  {
    this(exercicio, autor);
    this.nomesArquivosXML = nomesArquivosXML;
    this.furbotClass = furbotClass;
  }
  
  private void initComponents(Exercicio exercicio, String autor) {
    setResizable(false);
    setDefaultCloseOperation(3);
    String title = "Mundo do Furbot v 1.800000001";
    if (autor != null) {
      title = title + " [" + autor + "]";
    }
    setTitle(title);
    JPanel jp = getEnunciado(exercicio.getEnunciado());
    getContentPane().add(jp, "North");
    JScrollPane scrollmundo = new JScrollPane();
    scrollmundo.setViewportView(mundoFurbot);
    getContentPane().add(mundoFurbot, "Center");
    jp.add(getControle(exercicio), "South");
    console = new JTextArea();
    JScrollPane scroll = new JScrollPane(console);
    console.setEditable(false);
    console.setRows(5);
    getContentPane().add(scroll, "South");
    addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(WindowEvent arg0) {
        mundoFurbot.parar();
      }
      

    });
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private JPanel getEnunciado(String enunciado) {
    JPanel jp = new JPanel();
    jlEnunciado = new JLabel(enunciado);
    jp.add(jlEnunciado);
    return jp;
  }
  
  private JPanel getControle(final Exercicio exercicio) {
    JPanel jconsole = new JPanel(new java.awt.BorderLayout());
    JPanel jp = new JPanel(new GridLayout(2, 1));
    JPanel jpBotoes = new JPanel();
    jp.add(jpBotoes);
    
    jbExecutar = new JButton("Run");
    jpBotoes.add(jbExecutar);
    jconsole.add(jp, "West");
    ActionListener action = new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        mundoAtual = 0;
        executarProxMundo = false;
        MundoVisual.this.novaSequencia(exercicio);
        MundoVisual.this.executar(exercicio);
      }
      
    };
    jbExecutar.addActionListener(action);
    
    jbRenovar = new JButton("New");
    jpBotoes.add(jbRenovar);
    jbRenovar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        mundoFurbot.parar();
        MundoVisual.this.novaSequencia(exercicio);
        try {
          mundoFurbot.setExercicio(exercicio);
          mundoFurbot.reiniciar();
          pack();
        } catch (Exception e) {
          e.printStackTrace();
        }
        executou = false;
        mundoFurbot.requestFocus();
      }
      
    });
    jbParar = new JButton("Stop");
    jbParar.setEnabled(false);
    jpBotoes.add(jbParar);
    jbParar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        mundoFurbot.parar();
        jbExecutar.setEnabled(true);
        jbParar.setEnabled(false);
      }
      
    });
    JPanel jpSlider = new JPanel(new GridLayout(2, 1));
    slider = new JSlider();
    slider.setMaximum(2000);
    slider.setInverted(true);
    slider.setValue(mundoFurbot.getTempoEspera());
    slider.addChangeListener(new javax.swing.event.ChangeListener()
    {
      public void stateChanged(ChangeEvent ev) {
        mundoFurbot.setTempoEspera(slider.getValue());
        mundoFurbot.requestFocus();
      }
      
    });
    jpSlider.add(new JLabel("   Velocidade :"));
    jpSlider.add(slider);
    jp.add(jpSlider);
    return jconsole;
  }
  
  private void novaSequencia(Exercicio exercicio) {
    jlEnunciado.setText(exercicio.getEnunciado());
    atributos.clear();
  }
  
  private void executar(Exercicio exercicio) {
    if (executou)
      try {
        jlEnunciado.setText(exercicio.getEnunciado());
        mundoFurbot.setExercicio(exercicio);
        mundoFurbot.reiniciar();
        pack();
      } catch (Exception e) {
        e.printStackTrace();
      }
    habilitarBotoesExecucao();
    mundoFurbot.executar();
    executou = true;
    mundoFurbot.requestFocus();
  }
  
  private void habilitarBotoesExecucao() {
    jbExecutar.setEnabled(false);
    jbParar.setEnabled(true);
    jbRenovar.setEnabled(false);
  }
  
  public static void iniciar(String nomeArquivoXML) {
    iniciar(nomeArquivoXML, null, null);
  }
  
  public static void iniciar(String nomeArquivoXML, String autor) {
    iniciar(nomeArquivoXML, null, autor);
  }
  
  public static void iniciar(String nomeArquivoXML, Class<?> furbotClass, final String autor) {
    try {
      Exercicio exercicio = ExercicioFactory.create(nomeArquivoXML, furbotClass);
      java.awt.EventQueue.invokeLater(new Runnable()
      {
        public void run() {
          try {
            new MundoVisual(MundoVisual.this, autor, null);
          } catch (ClassNotFoundException classE) {
            javax.swing.JOptionPane.showMessageDialog(null, 
              "Você precisa criar uma classe de nome " + classE.getLocalizedMessage());
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      });
    }
    catch (Exception e) {
      System.out.println("Problemas no carregamento do Exercício");
      e.printStackTrace();
    }
  }
  
  public static void iniciar(String nomeArquivoXML, Class<?> furbotClass)
  {
    iniciar(nomeArquivoXML, furbotClass, null);
  }
  
  public static void iniciarSequencia(Class<?> furbotClass, String... nomesArquivosXML)
  {
    iniciarSequencia(null, furbotClass, nomesArquivosXML);
  }
  
  public static void iniciarSequencia(String autor, Class<?> furbotClass, String... nomesArquivosXML) {
    try {
      final Exercicio exercicio = ExercicioFactory.create(
        nomesArquivosXML[0], furbotClass);
      final String[] arquivosXML = nomesArquivosXML;
      final Class<?> classe = furbotClass;
      java.awt.EventQueue.invokeLater(new Runnable()
      {
        public void run() {
          try {
            new MundoVisual(MundoVisual.this, exercicio, classe, arquivosXML);
          } catch (ClassNotFoundException classE) {
            javax.swing.JOptionPane.showMessageDialog(null, 
              "Você precisa criar uma classe de nome " + classE.getLocalizedMessage());
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      });
    }
    catch (Exception e) {
      System.out.println("Problemas no carregamento do Exercício");
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) throws Exception {
    if (args.length > 0)
      iniciar(args[0]);
    throw new Exception("Faltou informar o nome do arquivo do enunciado.");
  }
  







  private static HashMap<String, Object> atributos = new HashMap();
  private JLabel jlEnunciado;
  private JButton jbExecutar;
  private JButton jbParar;
  private JSlider slider;
  private JButton jbRenovar;
}
