package br.furb.furbot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.furb.furbot.suporte.Exercicio;
import br.furb.furbot.suporte.Mundo;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import br.furb.furbot.suporte.TamanhoCelula;

public class MundoFurbot extends JPanel {

	private class MapaModel extends AbstractTableModel implements ListenerMundo {

		public int getColumnCount() {
			return mundo.getQtdadeCol();
		}

		public int getRowCount() {
			return mundo.getQtdadeLin();
		}

		public Object getValueAt(int lin, int col) {
			return mundo.getImagem(lin, col);
		}

		public void andou(ObjetoMundoImpl objMundo, Direcao direcao, int xAnt, int yAnt) {
			fireTableCellUpdated(yAnt, xAnt);
			fireTableCellUpdated(objMundo.getY(), objMundo.getX());
		}

		public void disse(ObjetoMundoImpl objetoMundo, String texto) {

			for (DisseramListener dl : disseramListeners) {
				dl.disse(texto);
			}
			if (console != null) {
				console.setText(console.getText() + "\n" + texto);
			}
		}

		public void limparConsole() {

			for (DisseramListener dl : disseramListeners) {
				dl.limpar();
			}
			if (console != null) {
				console.setText("");
			}
		}

		public void runRobo() {
			mundo.run();
		}

		public void geraCodigo() {

		}

		public void stopRobo() {
			mundo.parar();
		}

		public void finalizar() {
			mundo.removeListener(this);
		}

		public void fimExecucao() {

			if (botaoExecutar != null)
				botaoExecutar.setEnabled(true);

			if (botaoParar != null)
				botaoParar.setEnabled(false);

			for (FinalizouExecucaoListener f : finalizouListeners) {
				f.finalizouExecucao();
			}
		}

		public void repintar() {
			fireTableDataChanged();
		}

		private Mundo mundo;

		public MapaModel(int qtasColunas, int qtasLinhas, TamanhoCelula tamanhoCelula) {
			this.mundo = new Mundo(qtasLinhas, qtasColunas);
			this.mundo.setTamCell(tamanhoCelula);
			this.mundo.addListener(this);
		}

		public MapaModel(Exercicio exercicio) throws Exception {

			this.mundo = exercicio.criarMundo();
			this.mundo.addListener(this);

		}

	}

	private class MapaRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			setIcon((ImageIcon) value);
			setToolTipText(" Col " + column + "Lin " + row);

			return this;
		}

		public MapaRenderer() {
			this.setHorizontalAlignment(0);
		}
	}

	private JTable mapa;
	private MapaModel mapaModel;
	private JComponent botaoExecutar;
	private List<DisseramListener> disseramListeners = new ArrayList<DisseramListener>();
	private List<FinalizouExecucaoListener> finalizouListeners = new ArrayList<FinalizouExecucaoListener>();
	private JComponent botaoParar;
	private Exercicio exercicio;
	private JTextArea console;

	public void addDisseramListener(DisseramListener disseramListener) {
		this.disseramListeners.add(disseramListener);
	}

	public void removeDisseramListener(DisseramListener disseramListener) {
		this.disseramListeners.remove(disseramListener);
	}

	public void addFinalizouExecucaoListener(FinalizouExecucaoListener finalizouExecucaoListener) {
		this.finalizouListeners.add(finalizouExecucaoListener);
	}

	public void removeFinalizouExecucaoListener(FinalizouExecucaoListener finalizouExecucaoListener) {
		this.finalizouListeners.remove(finalizouExecucaoListener);
	}

	public MundoFurbot(int qtasColunas, int qtasLinhas, TamanhoCelula tamanhoCelula) {
		constroiGrade(new MapaModel(qtasColunas, qtasLinhas, tamanhoCelula));

		configKeyListener();

	}

	public MundoFurbot(Exercicio exercicio) throws Exception {
		this.exercicio = exercicio;
		constroiGrade(new MapaModel(exercicio));
		configKeyListener();
	}

	private void configKeyListener() {
		KeyAdapter keyAdapter = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				pressionadaTecla(e.getKeyCode());
			}

		};
		addKeyListener(keyAdapter);
		mapa.addKeyListener(keyAdapter);

	}

	private void constroiGrade(MapaModel mapaModel) {
		this.mapaModel = mapaModel;
		if (mapa == null)
			mapa = new JTable();
		mapa.setModel(mapaModel);
		mapa.setShowGrid(mapaModel.mundo.isUsarLinhasNaGrade());
		mapa.setRowHeight(mapaModel.mundo.getTamCell());
		mapa.setAutoResizeMode(0);
		mapa.setDefaultRenderer(Object.class, new MapaRenderer());
		for (int x = 0; x < mapa.getColumnCount(); x++) {
			mapa.getColumnModel().getColumn(x).setMaxWidth(mapaModel.mundo.getTamCell());
			mapa.getColumnModel().getColumn(x).setMinWidth(mapaModel.mundo.getTamCell());
		}

		setLayout(new BorderLayout());
		add(mapa, BorderLayout.CENTER);

	}

	public void reiniciar() throws Exception {
		if (this.exercicio == null) {
			constroiGrade(new MapaModel(mapaModel.mundo.getQtdadeCol(), mapaModel.mundo.getQtdadeLin(),
					TamanhoCelula.getEnumByPixels(mapaModel.mundo.getTamCell())));
		} else {
			constroiGrade(new MapaModel(exercicio));
		}
	}

	public void addObjeto(ObjetoDoMundo objetoDoMundo, int x, int y) {

		objetoDoMundo.getObjetoMundoImpl().setX(x);
		objetoDoMundo.getObjetoMundoImpl().setY(y);
		objetoDoMundo.getObjetoMundoImpl().setMundo(mapaModel.mundo);

		mapaModel.mundo.addObjetoMundoImpl(objetoDoMundo.getObjetoMundoImpl());

	}

	public void addObjeto(ObjetoDoMundo objetoDoMundo) {

		Random random = new Random();

		int x = 0, y = 0;
		while (true) {

			x = random.nextInt(mapaModel.mundo.getQtdadeCol());
			y = random.nextInt(mapaModel.mundo.getQtdadeLin());

			if (mapaModel.mundo.getObjeto(null, x, y) == null)
				break;

		}

		addObjeto(objetoDoMundo, x, y);
	}

	public void removerObjeto(int x, int y) {
		mapaModel.mundo.removerObjeto(mapaModel.mundo.getObjeto(null, x, y));
	}

	public void setBotaoExecutar(JComponent botaoExecutar) {
		this.botaoExecutar = botaoExecutar;
	}

	public void executar() {

		if (botaoExecutar != null)
			botaoExecutar.setEnabled(false);

		if (botaoParar != null)
			botaoParar.setEnabled(true);

		mapaModel.runRobo();
	}

	public void gerarCodigo() {
		System.out.println();
	}

	public void setBotaoParar(JComponent botaoParar) {
		this.botaoParar = botaoParar;

		if (botaoParar != null)
			botaoParar.setEnabled(false);
	}

	public void parar() {

		mapaModel.stopRobo();
	}

	public void setTempoEspera(int milisegundos) {
		ObjetoMundoImpl.tempoEspera = milisegundos;
	}

	public int getTempoEspera() {
		return ObjetoMundoImpl.tempoEspera;
	}

	public void pressionadaTecla(int keyCode) {
		mapaModel.mundo.pressionadaTecla(keyCode);

	}

	public JTextArea getConsole() {
		return console;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public void executar(final ObjetoDoMundo objetoDoMundo) {
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					objetoDoMundo.executar();
				} catch (Exception e) {
				}
			}
		};
		t.start();
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

}
