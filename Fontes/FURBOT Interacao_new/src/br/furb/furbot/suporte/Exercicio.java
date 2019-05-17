package br.furb.furbot.suporte;

import br.furb.furbot.MundoVisual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import br.furb.furbot.ObjetoDoMundo;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class Exercicio extends GrupoObjetos {

	public FurbotRandom getRandom() {
		return random;
	}

	public void setRandom(FurbotRandom random) {
		this.random = random;
	}

	public void setClassRobo(String classRobo) {
		for (ElementoExercicio elem : getElementos()) {
			if (elem.getClazz().equals(this.classRobo))
				elem.setClazz(classRobo);
		}

		this.classRobo = classRobo;
	}

	public Exercicio(String nomeArquivoXML) {

		String[] pedacos = nomeArquivoXML.split("[.]");

		this.classRobo = "";
		for (int x = 0; x < pedacos.length - 1; x++) {
			this.classRobo += pedacos[x];
			if (x < pedacos.length - 2)
				this.classRobo += ".";
		}

	}

	public int getQtdadeLin() {
		return qtdadeLin;
	}

	public void setQtdadeLin(int qtdadeLin) {
		this.qtdadeLin = qtdadeLin;
	}

	public int getQtdadeCol() {
		return qtdadeCol;
	}

	public void setQtdadeCol(int qtdadeCol) {
		this.qtdadeCol = qtdadeCol;
	}

	public int getQtdMaxLinCol() {
		return qtdMaxLinCol;
	}

	public void setQtdMaxLinCol(int qtdMaxLinCol) {
		this.qtdMaxLinCol = qtdMaxLinCol;
	}

	public int getQtdMinLinCol() {
		return qtdMinLinCol;
	}

	public void setQtdMinLinCol(int qtdMinLinCol) {
		this.qtdMinLinCol = qtdMinLinCol;
	}

	public boolean isExplodir() {
		return explodir;
	}

	public void setExplodir(boolean explodir) {
		this.explodir = explodir;
	}

	public String getEnunciado() {
		return (new StringBuilder("<html>")).append(enunciado).append("</html>").toString();
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public void addRobo(ElementoExercicio robo) {
		addElemento(robo);
		if (contaRobo == 0)
			robo.setId("robo");
		else
			robo.setId((new StringBuilder("robo")).append(contaRobo++).toString());
		robo.setClazz(classRobo);
		robo.setBloqueado(false);
	}

	public Mundo criarMundo() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		hashObjsMundo.clear();
		ArrayList<String> posUsadas = new ArrayList<String>();
		calcularQtdadeLinCol();
		Mundo m = new Mundo(qtdadeLin, qtdadeCol);
		m.setExplodir(explodir);
		m.setUsarLinhasNaGrade(usarLinhasNaGrade);
		m.setTamCell(TamanhoCelula.getEnumBySigla(getTamanhoCel()));
		List<ElementoExercicio> elemsAUsar = new ArrayList<ElementoExercicio>();
		elemsAUsar.addAll(getElementos());
		List<GrupoObjetos> grupos = getGruposObjetos();
		if (grupos.size() > 0) {

			addElementsOfGroups(elemsAUsar, grupos);
		}
		List<ElementoExercicio> novosElems = new ArrayList<ElementoExercicio>();
		for (ElementoExercicio elemExerc : elemsAUsar) {
			int qtosObjs = sorteio.nextInt(elemExerc.getQtdade());
			for (int x = 0; x < qtosObjs; x++) {
				novosElems.add(elemExerc.clonar());
			}
		}

		elemsAUsar.addAll(novosElems);
		adicionarObjetosMundo(false, false, m, posUsadas, elemsAUsar);
		adicionarObjetosMundo(true, false, m, posUsadas, elemsAUsar);
		adicionarObjetosMundo(false, true, m, posUsadas, elemsAUsar);
		adicionarObjetosMundo(true, true, m, posUsadas, elemsAUsar);
		return m;
	}

	private void addElementsOfGroups(List<ElementoExercicio> elemsAUsar, List<GrupoObjetos> grupos) {
		GrupoObjetos g = ((GrupoObjetos) grupos.get(sorteio.nextInt(grupos.size())));
		elemsAUsar.addAll(g.getElementos());
		if (g.getGruposObjetos().size() > 0) {
			addElementsOfGroups(elemsAUsar, g.getGruposObjetos());
		}
	}

	private void calcularQtdadeLinCol() {
		// aqui identifica o tamanho da tela
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		int tamanhoTela = d.height <= d.width ? d.height : d.width;

		TamanhoCelula tamanhoCelula = TamanhoCelula.getEnumBySigla(getTamanhoCel());
		switch (tamanhoCelula) {
		case GRANDE:
			tamanhoTela = tamanhoTela - 250;
			break;
		case MEDIA:
			tamanhoTela = tamanhoTela - 250;
			break;
		case PEQUENA:
			tamanhoTela = tamanhoTela - 250;
			break;
		}

		int tamanhoCel = tamanhoCelula.getTamanho();
		Float divisao = (float) (tamanhoTela / tamanhoCel);
		int qntLinCol = divisao.intValue();
		if (qntLinCol > getQtdMaxLinCol()) {
			qntLinCol = getQtdMaxLinCol();
		}
		if (qntLinCol < getQtdMinLinCol()) {
			qntLinCol = getQtdMinLinCol();
		}
		setQtdadeLin(qntLinCol);
		setQtdadeCol(qntLinCol);
		/*
		 * System.out.println("Screen width = " + d.width);
		 * System.out.println("Screen height = " + d.height);
		 * System.out.println("aqui"); if (random.isRandom()) { int limiteSupCol =
		 * random.getLimiteSupRandomX(); int limiteSupLin =
		 * random.getLimiteSupRandomY(); int limiteInfCol =
		 * random.getLimiteInfRandomX(); int limiteInfLin =
		 * random.getLimiteInfRandomY(); if (limiteSupCol <= 0) limiteSupCol = 10; if
		 * (limiteSupLin <= 0) limiteSupLin = 10; if (random.isRandomX()) qtdadeCol =
		 * sorteio.nextInt(limiteSupCol); else if (random.isRandomY()) { qtdadeLin =
		 * sorteio.nextInt(limiteSupLin); } else { qtdadeLin =
		 * sorteio.nextInt(limiteSupLin); qtdadeCol = sorteio.nextInt(limiteSupCol); }
		 * if (qtdadeCol < limiteInfCol) qtdadeCol = limiteInfCol; if (qtdadeLin <
		 * limiteInfLin) qtdadeLin = limiteInfLin; }
		 */
	}

	private void adicionarObjetosMundo(boolean random, boolean dependentes, Mundo m, List<String> posUsadas,
			List<ElementoExercicio> elems)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ArrayList<ElementoExercicio> elementosExtraidos = extrairElementosPosicao(random, dependentes, elems);
		for (ElementoExercicio elem : elementosExtraidos) {
			m.addObjetoMundoImpl(criarObjetoMundoImpl(m, posUsadas, elem, elem.getClazz()));
		}

	}

	private ArrayList<ElementoExercicio> extrairElementosPosicao(boolean random, boolean dependentes,
			List<ElementoExercicio> elemsOrigem) {
		ArrayList<ElementoExercicio> elems = new ArrayList<ElementoExercicio>();
		for (ElementoExercicio elem : elemsOrigem) {
			if (elem.getRandom().isRandom() == random && elem.isDependente() == dependentes)
				elems.add(elem);
		}

		return elems;
	}

	private ObjetoMundoImpl criarObjetoMundoImpl(Mundo mundo, List<String> posUsadas, ElementoExercicio elemento,
			String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ObjetoDoMundo objMundo = (ObjetoDoMundo) Class.forName(clazz).newInstance();
		ObjetoMundoImpl obj = objMundo.getObjetoMundoImpl();
		obj.setMundo(mundo);
		obj.setBloqueado(elemento.isBloqueado());
		int x = getX(mundo, elemento);
		int y = getY(mundo, elemento);
		if (elemento.getRandom().isRandom()) {
			FurbotRandom random = elemento.getRandom();
			int limiteSupCol = random.getLimiteSupRandomX() + 1;
			int limiteSupLin = random.getLimiteSupRandomY() + 1;
			int limiteInfCol = random.getLimiteInfRandomX();
			int limiteInfLin = random.getLimiteInfRandomY();
			if (limiteSupCol == 0 || limiteSupCol > qtdadeCol)
				limiteSupCol = qtdadeCol;
			if (limiteSupLin == 0 || limiteSupLin > qtdadeLin)
				limiteSupLin = qtdadeLin;
			limiteSupLin -= limiteInfLin;
			limiteSupCol -= limiteInfCol;
			do {
				if (random.isRandomX())
					x = sorteio.nextInt(limiteSupCol);
				else if (random.isRandomY()) {
					y = sorteio.nextInt(limiteSupLin);
				} else {
					x = sorteio.nextInt(limiteSupCol);
					y = sorteio.nextInt(limiteSupLin);
				}
				x += limiteInfCol;
				y += limiteInfLin;
				java.awt.Point p = new java.awt.Point();
				p.x = x;
				p.y = y;
				refazerPosicaoDependente(p, mundo, elemento);
				x = p.x;
				y = p.y;
			} while (posUsadas.indexOf((new StringBuilder(String.valueOf(x))).append("=").append(y).toString()) != -1);
		} else {
			java.awt.Point p = new java.awt.Point();
			p.x = x;
			p.y = y;
			refazerPosicaoDependente(p, mundo, elemento);
			x = p.x;
			y = p.y;
		}
		obj.setX(x);
		obj.setY(y);
		if (elemento.isUsarEnergia())
			obj.setMaxEnergia(elemento.getEnergia());
		hashObjsMundo.put(elemento.getId(), obj);
		elemento.outrasAtribuicoes(obj, objMundo);
		posUsadas.add((new StringBuilder(String.valueOf(x))).append("=").append(y).toString());
		return obj;
	}

	private void refazerPosicaoDependente(java.awt.Point p, Mundo mundo, ElementoExercicio elemento) {
		if (elemento.isDependente()) {
			if (elemento.getIdDependeX() != null) {
				p.x = getObjetoMundoImpl(mundo, elemento.getIdDependeX()).getX() + elemento.getX();
				if (p.x > qtdadeCol - 1)
					p.x = qtdadeCol - 2;
				else if (p.x < 0)
					p.x = 0;
			}
			if (elemento.getIdDependeY() != null) {
				p.y = ((ObjetoMundoImpl) hashObjsMundo.get(elemento.getIdDependeY())).getY() + elemento.getY();
				if (p.y > qtdadeLin)
					p.y = qtdadeLin - 2;
				else if (p.y < 0)
					p.y = 0;
			}
		}
	}

	private int getY(Mundo mundo, ElementoExercicio elemento) {
		int y = elemento.getY();
		if (y == -1)
			y = mundo.getQtdadeLin() - 1;
		return y;
	}

	private int getX(Mundo mundo, ElementoExercicio elemento) {
		int x = elemento.getX();
		if (x == -1)
			x = mundo.getQtdadeCol() - 1;
		return x;
	}

	private ObjetoMundoImpl getObjetoMundoImpl(Mundo mundo, String id) {
		return (ObjetoMundoImpl) hashObjsMundo.get(id);
	}

	public void finalizar() {
		if (contaRobo > 0) {
			((ObjetoMundoImpl) hashObjsMundo.get("robo")).setBloqueado(true);
			for (int x = 1; x <= contaRobo; x++)
				((ObjetoMundoImpl) hashObjsMundo.get("robo")).setBloqueado(true);

		}
	}

	public boolean isUsarLinhasNaGrade() {
		return usarLinhasNaGrade;
	}

	public void setUsarLinhasNaGrade(boolean usarLinhasNaGrade) {
		this.usarLinhasNaGrade = usarLinhasNaGrade;
	}

	private String enunciado;
	private int qtdadeLin;
	private int qtdadeCol;
	private int qtdMaxLinCol;
	private int qtdMinLinCol;
	private boolean explodir;
	private String classRobo;
	private boolean usarLinhasNaGrade = true;
	private String tamanhoCel = "G";
	private FurbotRandom random = new FurbotRandom();
	private HashMap<String, ObjetoMundoImpl> hashObjsMundo = new HashMap<String, ObjetoMundoImpl>();
	private int contaRobo;
	private Random sorteio = new Random();

	public String getTamanhoCel() {
		return tamanhoCel;
	}

	public void setTamanhoCel(String tamanhoCel) {
		this.tamanhoCel = tamanhoCel;
	}
}