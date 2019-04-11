package br.furb.furbot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import br.furb.furbot.exceptions.MundoException;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.suporte.ObjetoMundoImpl;
import br.furb.furbot.suporte.Mundo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * 
 * @author Adilson Vahldick
 */
public abstract class Furbot extends ObjetoDoMundoAdapter {

    public static List<String> le = new ArrayList<String>();
    public static Boolean ajuda = false;
    private static HashMap valorContTipo = new HashMap();
    private static ArrayList contTipo = new ArrayList<String>();
    private static HashMap valorSomaTipo = new HashMap();
    private static ArrayList somaTipo = new ArrayList<String>();
    private static int QtdLinhas = 0;
    private static int QtdColunas = 0;

    public Furbot() {
        this.QtdLinhas = MundoVisual.getAtributo("lll");
        this.QtdColunas = MundoVisual.getAtributo("ccc");
    }

    public ImageIcon buildImage() {
        ImageIcon image = LoadImage.getInstance().getIcon("imagens/r2d2-icon.gif");

        if (ehDependenteEnergia()) {
            BufferedImage img = new BufferedImage(50, 50, 1);
            Graphics2D g = img.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, 50, 50);
            g.drawImage(image.getImage(), 2, 2, null);
            float fator = (float) getEnergia()
                    / ((float) getMaxEnergia() * 1.0F);
            int altura = Math.round(40F * fator);
            g.setColor(Color.red);
            g.fillRoundRect(40, 45 - altura, 5, altura, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect(40, 5, 5, 40, 5, 5);
            image = new ImageIcon(img);
        }

        return image;
    }

    public void executar() throws Exception {
        //MundoException ex = null;
        try {
            esperar(1);
            if (!ajuda) {
                inteligencia();
            } else {
                int tecla = getUltimaTeclaPress();
                while (true) {
                    getTeclas(tecla);
                    tecla = getUltimaTeclaPress();
                }
            }
        } catch (MundoException e) {
        }
        objetoMundoImpl.pararMundo();
        return;
    }

    public final void getTeclas(int keyCode) {
        switch (keyCode) {
            case TECLAESQUERDA: {
                verificaDirecao(ESQUERDA);
            }
            break;
            case TECLACIMA: {
                verificaDirecao(ACIMA);
            }
            break;
            case TECLADIREITA: {
                verificaDirecao(DIREITA);
            }
            break;
            case TECLABAIXO: {
                verificaDirecao(ABAIXO);
            }
            break;
            case TECLA_D: {
                String frase =
                        JOptionPane.showInputDialog(
                        null,
                        "O que devo dizer?: ",
                        "Escreva algo", 1);
                if (frase != null) {
                    le.add("9," + frase);
                    this.diga("Aqui sera dito: " + frase);
                }
            }
            break;
            case TECLA_P: {
                le.add("10");
                this.diga("Aqui sera informada a posição");
            }
            break;
        }
    }

    public final void contarObjetosDoTipo(String tipo, int quais) {
        int cont = 0;
        le.add("18," + tipo + "," + quais);
        this.diga("Contando objetos do tipo " + tipo + "...");
        for (int mY = 0; mY <= Furbot.QtdLinhas; mY++) {
            for (int mX = 0; mX <= Furbot.QtdColunas; mX++) {
                ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);
                if (objeto != null) {
                    if (objeto.getSouDoTipo().equals(tipo)) {
                        switch (quais) {
                            case 0: { // Todos
                                cont++;
                            }
                            break;
                            case 1: { // Colunas pares
                                if (mX % 2 == 0) {
                                    cont++;
                                }
                            }
                            break;
                            case 2: { // colunas impares
                                if (mX % 2 != 0) {
                                    cont++;
                                }
                            }
                            break;
                            case 3: { // Linhas pares
                                if (mY % 2 == 0) {
                                    cont++;
                                }
                            }
                            break;
                            case 4: { // Linhas impares
                                if (mY % 2 != 0) {
                                    cont++;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        switch (quais) {
            case 0: { // Todos
                this.diga("Foram encontrados " + cont + " objetos do tipo " + tipo);
            }
            break;
            case 1: { // Colunas pares
                this.diga("Foram encontrados " + cont + " objetos do tipo " + tipo + " em colunas pares");
            }
            break;
            case 2: { // Colunas impares
                this.diga("Foram encontrados " + cont + " objetos do tipo " + tipo + " em colunas impares");
            }
            break;
            case 3: { // Linhas pares
                this.diga("Foram encontrados " + cont + " objetos do tipo " + tipo + " em linhas pares");
            }
            break;
            case 4: { // Linhas impares
                this.diga("Foram encontrados " + cont + " objetos do tipo " + tipo + " em linhas impares");
            }
            break;
        }
    }

    public final void somarObjetosDoTipo(String tipo, int quais) {
        int soma = 0;
        le.add("19," + tipo + "," + quais);
        this.diga("Somaando objetos do tipo " + tipo + "...");
        for (int mY = 0; mY <= Furbot.QtdLinhas; mY++) {
            for (int mX = 0; mX <= Furbot.QtdColunas; mX++) {
                ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);
                if (objeto != null) {
                    if (objeto.getSouDoTipo().equals(tipo)) {
                        switch (quais) {
                            case 0: { // Todos
                                soma += Integer.parseInt(objeto.toString());
                            }
                            break;
                            case 1: { // Colunas pares
                                if (mX % 2 == 0) {
                                    soma += Integer.parseInt(objeto.toString());
                                }
                            }
                            break;
                            case 2: { // colunas impares
                                if (mX % 2 != 0) {
                                    soma += Integer.parseInt(objeto.toString());
                                }
                            }
                            break;
                            case 3: { // Linhas pares
                                if (mY % 2 == 0) {
                                    soma += Integer.parseInt(objeto.toString());
                                }
                            }
                            break;
                            case 4: { // Linhas impares
                                if (mY % 2 != 0) {
                                    soma += Integer.parseInt(objeto.toString());
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        switch (quais) {
            case 0: { // Todos
                this.diga("A soma dos objetos do tipo " + tipo + " é de " + soma);
            }
            break;
            case 1: { // Colunas pares
                this.diga("A soma dos objetos do tipo " + tipo + " em colunas pares é de " + soma);
            }
            break;
            case 2: { // Colunas impares
                this.diga("A soma dos objetos do tipo " + tipo + " em colunas impares é de " + soma);
            }
            break;
            case 3: { // Linhas pares
                this.diga("A soma dos objetos do tipo " + tipo + " em linhas pares é de " + soma);
            }
            break;
            case 4: { // Linhas impares
                this.diga("A soma dos objetos do tipo " + tipo + " em linhas impares é de " + soma);
            }
            break;
        }
    }

    public final Boolean ehPossivelEmpurrar(Direcao direcao) {
        // Não pode ser empurrado para o fim        
        if (this.getObjeto(direcao).ehFim(direcao)) {
            return false;
        }
        //Uma parede não pode ser empurrada
        if (this.getObjeto(direcao).getSouDoTipo().equals("Parede")) {
            return false;
        }
        //Se não houver nada pode empurrar
        ObjetoDoMundoAdapter objeto = this.getObjeto(direcao).getObjeto(direcao);
        if (objeto == null) {
            return true;
        }
        // Não pode ser empurrado para uma parede
        if (objeto.getSouDoTipo().equals("Parede")) {
            return false;
        }

        return true;
    }

    public final void verificaDirecao(Direcao direcao) {
        if (ehFim(direcao)) {
            addmovimentoVerificado(direcao);
        } else if (ehVazio(direcao)) {
            switch (direcao) {
                case ESQUERDA: {
                    le.add("1");
                    andarEsquerda();
                    this.diga("Movimento para esquerda");
                }
                break;
                case ACIMA: {
                    le.add("2");
                    andarAcima();
                    this.diga("Movimento para cima");
                }
                break;
                case DIREITA: {
                    le.add("3");
                    andarDireita();
                    this.diga("Movimento para direita");
                }
                break;
                case ABAIXO: {
                    le.add("4");
                    andarAbaixo();
                    this.diga("Movimento para baixo");
                }
                break;
            }
        } else {
            String[] opcoes = {"Empurrar", "Remover", "Contar", "Acumular", "Nada"};
            int opcao = JOptionPane.showOptionDialog(
                    null, "O que deve ser feito com este objeto?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, "Furbot");

            switch (opcao) {
                case 0: { //Empurrar
                    if (ehPossivelEmpurrar(direcao)) {
                        switch (direcao) {
                            case ESQUERDA: {
                                le.add("11");
                                this.getObjeto(direcao).andarEsquerda();
                            }
                            break;
                            case ACIMA: {
                                le.add("12");
                                this.getObjeto(direcao).andarAcima();
                            }
                            break;
                            case DIREITA: {
                                le.add("13");
                                this.getObjeto(direcao).andarDireita();
                            }
                            break;
                            case ABAIXO: {
                                le.add("14");
                                this.getObjeto(direcao).andarAbaixo();
                            }
                            break;
                        }
                        diga("Objeto empurrado");
                        movimenta(direcao);
                    } else {
                        this.diga("Não é possível empurrar este objeto");
                    }
                }
                break;
                case 1: { //Remover
                    if (!(this.getObjeto(direcao).getSouDoTipo().equals("Parede"))) {
                        le.add("15," + direcao);
                        removerObjetoDoMundo(this.getObjeto(direcao));
                        diga("Objeto removido");
                        movimenta(direcao);
                    } else {
                        this.diga("Não é possível remover este objeto");
                    }
                }
                break;
                case 2: { // Contar
                    String tipo = this.getObjeto(direcao).getSouDoTipo();
                    String[] opcont = {"Todos", "Colunas pares", "Colunas Impares", "Linhas pares", "Linhas impares", "Somente este"};
                    int opscont = JOptionPane.showOptionDialog(
                            null, "Quais objetos deste tipo devem ser contados?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcont, "Furbot");
                    switch (opscont) {
                        case 0: {
                            this.contarObjetosDoTipo(tipo, 0);
                        }
                        break;
                        case 1: {
                            this.contarObjetosDoTipo(tipo, 1);
                        }
                        break;
                        case 2: {
                            this.contarObjetosDoTipo(tipo, 2);
                        }
                        break;
                        case 3: {
                            this.contarObjetosDoTipo(tipo, 3);
                        }
                        break;
                        case 4: {
                            this.contarObjetosDoTipo(tipo, 4);
                        }
                        break;
                        case 5: {
                            contIndividual(tipo);
                        }
                        break;
                    }
                    if (!ehFim(direcao)) {
                        if (!(this.getObjeto(direcao).getSouDoTipo().equals("Parede"))) {
                            movimenta(direcao);
                            addmovimentoVerificado(direcao);
                        }
                    }
                }
                break;
                case 3: {
                    try {
                        String tipo = this.getObjeto(direcao).getSouDoTipo();
                        String[] opsoma = {"Todos", "Colunas pares", "Colunas Impares", "Linhas pares", "Linhas impares", "Somente este"};
                        int opssoma = JOptionPane.showOptionDialog(
                                null, "Quais objetos deste tipo devem ser contados?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opsoma, "Furbot");
                        switch (opssoma) {
                            case 0: {
                                this.somarObjetosDoTipo(tipo, 0);
                            }
                            break;
                            case 1: {
                                this.somarObjetosDoTipo(tipo, 1);
                            }
                            break;
                            case 2: {
                                this.somarObjetosDoTipo(tipo, 2);
                            }
                            break;
                            case 3: {
                                this.somarObjetosDoTipo(tipo, 3);
                            }
                            break;
                            case 4: {
                                this.somarObjetosDoTipo(tipo, 4);
                            }
                            break;
                            case 5: {
                                this.somaIndividual(direcao);
                            }
                            break;
                        }
                    } catch (Exception e) {
                        this.diga("Este objeto não possui valor para ser somado");
                    }
                    if (!ehFim(direcao)) {
                        if (!(this.getObjeto(direcao).getSouDoTipo().equals("Parede"))) {
                            movimenta(direcao);
                            addmovimentoVerificado(direcao);
                        }
                    }
                }
                break;
                case 4: {
                    if (!ehFim(direcao)) {
                        if (!(this.getObjeto(direcao).getSouDoTipo().equals("Parede"))) {
                            movimenta(direcao);
                            addmovimentoVerificado(direcao);
                        }
                    }
                }
                break;
            }
        }
    }

    public final void somaIndividual(Direcao direcao) {
        int valor = Integer.parseInt(this.getObjeto(direcao).toString());
        String tipo = this.getObjeto(direcao).getSouDoTipo();
        le.add("17," + tipo + "," + direcao);
        if (somaTipo.contains(tipo)) {
            valorSomaTipo.put(tipo, ((Integer.parseInt(valorSomaTipo.get(tipo).toString()) + valor)));
        } else {
            somaTipo.add(tipo);
            valorSomaTipo.put(tipo, valor);
        }
        this.diga("--- Soma de valores de objetos ---");
        for (int i = 0; i < somaTipo.size(); i++) {
            diga(somaTipo.get(i) + ": " + valorSomaTipo.get(somaTipo.get(i)));
        }
    }

    public final void contIndividual(String tipo) {
        le.add("16," + tipo);
        if (valorContTipo.containsKey(tipo)) {
            valorContTipo.put(
                    tipo,
                    (Integer.parseInt(
                    valorContTipo.get(tipo).toString()) + 1));
        } else {
            valorContTipo.put(tipo, 1);
            contTipo.add(tipo);
        }
        diga("Contar objeto do tipo " + tipo);
        diga("--- Contagens Individuais---");
        for (int i = 0; i < contTipo.size(); i++) {
            diga(contTipo.get(i) + ": " + valorContTipo.get(contTipo.get(i)));
        }
    }

    public final void movimenta(Direcao direcao) {
        switch (direcao) {
            case ESQUERDA: {
                this.andarEsquerda();
                this.diga("Movimento para esquerda");
            }
            break;
            case ACIMA: {
                this.andarAcima();
                this.diga("Movimento para cima");
            }
            break;
            case DIREITA: {
                this.andarDireita();
                this.diga("Movimento para direita");
            }
            break;
            case ABAIXO: {
                this.andarAbaixo();
                this.diga("Movimento para baixo");
            }
            break;
        }
    }

    public final void addmovimentoVerificado(Direcao direcao) {
        switch (direcao) {
            case ESQUERDA:
                le.add("5");
                break;
            case ACIMA:
                le.add("6");
                break;
            case DIREITA:
                le.add("7");
                break;
            case ABAIXO:
                le.add("8");
                break;
        }
    }

    public static void gerarCodigo() {
        int cont = 1, prox = 0;
        String codigo = "";
        String frase = "";
        Integer valor = 0;
        String dir = "";
        String quais = "";
        ArrayList contHelper = new ArrayList<String>();
        ArrayList somaHelper = new ArrayList<String>();
        for (int i = 0; i < le.size(); i++) {
            try {
                valor = Integer.parseInt(le.get(i));
            } catch (Exception e) {
                String[] dado = le.get(i).split(",");
                valor = Integer.parseInt(dado[0]);
                frase = dado[1];
                if (valor == 17) {
                    dir = dado[2];
                }
                if ((valor == 18) || (valor == 19)) {
                    quais = dado[2];
                }
            }
            prox = i + 1;
            switch (valor) {
                case 1: {
                    if ((prox < le.size()) && (igualProx(prox, valor) == true)) {
                        cont++;
                    } else {
                        if (cont > 1) {
                            codigo += "for(int i=0;i<" + String.valueOf(cont) + ";i++) {\n";
                            codigo += "   this.andarEsquerda();\n}\n";
                            cont = 1;
                        } else {
                            codigo += "this.andarEsquerda();\n";
                        }
                    }
                }
                ;
                break;
                case 2: {
                    if ((prox < le.size()) && (igualProx(prox, valor) == true)) {
                        cont++;
                    } else {
                        if (cont > 1) {
                            codigo += "for(int i=0;i<" + String.valueOf(cont) + ";i++) {\n";
                            codigo += "   this.andarAcima();\n";
                            codigo += "}" + "\n";
                            cont = 1;
                        } else {
                            codigo += "this.andarAcima();\n";
                        }
                    }
                }
                ;
                break;
                case 3: {
                    if ((prox < le.size()) && (igualProx(prox, valor) == true)) {
                        cont++;
                    } else {
                        if (cont > 1) {
                            codigo += "for(int i=0;i<" + String.valueOf(cont) + ";i++) {\n";
                            codigo += "   this.andarDireita();\n}\n";
                            cont = 1;
                        } else {
                            codigo += "this.andarDireita();\n";
                        }
                    }
                }
                ;
                break;
                case 4: {
                    if ((prox < le.size()) && (igualProx(prox, valor) == true)) {
                        cont++;
                    } else {
                        if (cont > 1) {
                            codigo += "for(int i=0;i<" + String.valueOf(cont) + ";i++) {\n";
                            codigo += "   this.andarAbaixo();\n}\n";
                            cont = 1;
                        } else {
                            codigo += "this.andarAbaixo();" + "\n";
                        }
                    }
                }
                ;
                break;
                case 5: {
                    codigo += "if(!ehFim(ESQUERDA)) {\n"
                            + "    ObjetoDoMundoAdapter objeto = this.getObjeto(ESQUERDA);\n"
                            + "   if (objeto == null) {"
                            + "        this.andarEsquerda();\n"
                            + "    } else {\n"
                            + "        if (!objeto.getSouDoTipo().equals(\"Parede\")) {\n"
                            + "            this.andarEsquerda();\n"
                            + "        }\n    }\n}\n";

                }
                ;
                break;
                case 6: {
                    codigo += "if(!ehFim(ACIMA)) {\n"
                            + "    ObjetoDoMundoAdapter objeto = this.getObjeto(ACIMA);\n"
                            + "   if (objeto == null) {"
                            + "        this.andarAcima();\n"
                            + "    } else {\n"
                            + "        if (!objeto.getSouDoTipo().equals(\"Parede\")) {\n"
                            + "            this.andarAcima();\n"
                            + "        }\n    }\n}\n";
                }
                ;
                break;
                case 7: {
                    codigo += "if(!ehFim(DIREITA)) {\n"
                            + "    ObjetoDoMundoAdapter objeto = this.getObjeto(DIREITA);\n"
                            + "   if (objeto == null) {"
                            + "        this.andarDireita();\n"
                            + "    } else {\n"
                            + "        if (!objeto.getSouDoTipo().equals(\"Parede\")) {\n"
                            + "            this.andarDireita();\n"
                            + "        }\n    }\n}\n";
                }
                ;
                break;
                case 8: {
                    codigo += "if(!ehFim(ABAIXO)) {\n"
                            + "    ObjetoDoMundoAdapter objeto = this.getObjeto(ABAIXO);\n"
                            + "   if (objeto == null) {"
                            + "        this.andarAbaixo();\n"
                            + "    } else {\n"
                            + "        if (!objeto.getSouDoTipo().equals(\"Parede\")) {\n"
                            + "            this.andarAbaixo();\n"
                            + "        }\n    }\n}\n";
                }
                ;
                break;
                case 9: {
                    codigo += "this.diga(\"" + frase + "\");\n";
                }
                ;
                break;
                case 10: {
                    codigo += "this.diga(\"Coluna: \"+getX()+\" | Linha: \" + getY());\n";
                }
                ;
                break;
                case 11: {
                    codigo += "this.getObjeto(ESQUERDA).andarEsquerda();\n";
                }
                ;
                break;
                case 12: {
                    codigo += "this.getObjeto(ACIMA).andarAcima();\n";
                }
                ;
                break;
                case 13: {
                    codigo += "this.getObjeto(DIREITA).andarDireita();\n";
                }
                ;
                break;
                case 14: {
                    codigo += "this.getObjeto(ABAIXO).andarAbaixo();\n";
                }
                ;
                break;
                case 15: {
                    codigo += "removerObjetoDoMundo(this.getObjeto(" + frase.toUpperCase() + "));\n";
                }
                ;
                break;
                case 16: {
                    if (contHelper.contains(frase)) {
                        codigo += "cont" + frase + "++;\n";
                    } else {
                        codigo += "int cont" + frase + " = 1;\n";
                        contHelper.add(frase);
                    }
                }
                ;
                break;
                case 17: {
                    if (somaHelper.contains(frase)) {
                        codigo += "soma" + frase + " += Integer.parseInt(this.getObjeto(" + dir + ").toString());\n";
                    } else {
                        codigo += "int soma" + frase + " = Integer.parseInt(this.getObjeto(" + dir + ").toString());\n";
                        somaHelper.add(frase);
                    }
                }
                break;
                case 18: {
                    switch (Integer.parseInt(quais)) {
                        case 0: {
                            codigo +=
                                    "this.diga(\"Contando todos os objetos do tipo " + frase + "...\");\n"
                                    + "int contTotal" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "        if (objeto != null) {\n"
                                    + "            if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                contTotal" + frase + "++;\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"Foram encontrados \"+contTotal" + frase + "+\" objetos do tipo " + frase + "\");\n";
                        }
                        break;
                        case 1: {
                            codigo +=
                                    "this.diga(\"Contando objetos do tipo " + frase + " em colunas pares...\");\n"
                                    + "int contColPar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mX % 2 == 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    contColPar" + frase + "++;\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"Foram encontrados \"+contColPar" + frase + "+\" objetos do tipo " + frase + " em colunas pares\");\n";
                        }
                        break;
                        case 2: { // Colunas impares
                            codigo +=
                                    "this.diga(\"Contando objetos do tipo " + frase + " em colunas impares...\");\n"
                                    + "int contColImpar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mX % 2 != 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    contColImpar" + frase + "++;\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"Foram encontrados \"+contColImpar" + frase + "+\" objetos do tipo " + frase + " em colunas impares\");\n";
                        }
                        break;
                        case 3: { // Linhas pares
                            codigo +=
                                    "this.diga(\"Contando objetos do tipo " + frase + " em linhas pares...\");\n"
                                    + "int contLinPar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mY % 2 == 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    contLinPar" + frase + "++;\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"Foram encontrados \"+contLinPar" + frase + "+\" objetos do tipo " + frase + " em colunas pares\");\n";
                        }
                        break;
                        case 4: { //linhas impares
                            codigo +=
                                    "this.diga(\"Contando objetos do tipo " + frase + " em linhas impares...\");\n"
                                    + "int contLinImpar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mY % 2 != 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    contLinImpar" + frase + "++;\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"Foram encontrados \"+contLinImpar" + frase + "+\" do tipo " + frase + " em linhas impares\");\n";
                        }

                    }
                }
                break;
                case 19: {
                    switch (Integer.parseInt(quais)) {
                        case 0: {
                            codigo +=
                                    "this.diga(\"Somando todos os objetos do tipo " + frase + "...\");\n"
                                    + "int somaTotal" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "        if (objeto != null) {\n"
                                    + "            if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                somaTotal" + frase + "+= Integer.parseInt(objeto.toString());\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"A soma de todos os objetos do tipo " + frase + " é de \"+somaTotal" + frase + ");\n";
                        }
                        break;
                        case 1: { //Colunas pares
                            codigo +=
                                    "this.diga(\"Somando objetos do tipo " + frase + " em colunas pares...\");\n"
                                    + "int somaColPar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mX % 2 == 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    somaColPar" + frase + "+= Integer.parseInt(objeto.toString());\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"A soma dos objetos do tipo " + frase + " em colunas pares é de \"+somaColPar" + frase + ");\n";
                        }
                        break;
                        case 2: { // Colunas impares
                            codigo +=
                                    "this.diga(\"Somando objetos do tipo " + frase + " em colunas impares...\");\n"
                                    + "int somaColImpar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mX % 2 != 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    somaColImpar" + frase + "+= Integer.parseInt(objeto.toString());\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"A soma dos objetos do tipo " + frase + " em colunas impares é de \"+somaColImpar" + frase + ");\n";
                        }
                        break;
                        case 3: { // Linhas pares
                            codigo +=
                                    "this.diga(\"Somando objetos do tipo " + frase + " em linhas pares...\");\n"
                                    + "int somaLinPar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mY % 2 == 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    somaLinPar" + frase + "+= Integer.parseInt(objeto.toString());\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"A soma dos objetos do tipo " + frase + " em linhas pares é de \"+somaLinPar" + frase + ");\n";
                        }
                        break;
                        case 4: { //linhas impares
                            codigo +=
                                    "this.diga(\"Somando objetos do tipo " + frase + " em linhas impares...\");\n"
                                    + "int somaLinImpar" + frase + " = 0;"
                                    + "for (int mY = 0; mY <= " + QtdLinhas + "; mY++) {\n"
                                    + "    for (int mX = 0; mX <= " + QtdColunas + "; mX++) {\n"
                                    + "        if(mY % 2 != 0) {\n"
                                    + "            ObjetoDoMundoAdapter objeto = this.getObjetoXY(mX, mY);\n"
                                    + "            if (objeto != null) {\n"
                                    + "                if(objeto.getSouDoTipo().equals(\"" + frase + "\")) {\n"
                                    + "                    somaLinImpar" + frase + "+= Integer.parseInt(objeto.toString());\n"
                                    + "                }\n"
                                    + "            }\n"
                                    + "        }\n"
                                    + "    }\n"
                                    + "}\n"
                                    + "this.diga(\"A soma dos objetos do tipo " + frase + " em linhas impares é de \"+somaLinImpar" + frase + ");\n";
                        }

                    }
                }
            }
        }

        if (contTipo.size() > 0) {
            for (int it = 0; it < contTipo.size(); it++) {
                codigo += "this.diga(\"Foram encontrados \"+cont"
                        + contTipo.get(it) + "+\" objetos do tipo "
                        + contTipo.get(it) + "\");\n";
            }
        }
        if (somaTipo.size() > 0) {
            for (int it = 0; it < somaTipo.size(); it++) {
                codigo += "this.diga(\"A soma dos objetos do tipo "
                        + somaTipo.get(it) + " é de \"+soma" + somaTipo.get(it) + ");\n";

            }
        }
        FurbotCDialog a = new FurbotCDialog(null, true);
        a.add(codigo);
        a.setVisible(true);
    }

    public static Boolean igualProx(Integer prox, Integer comp) {
        try {
            int valor = Integer.parseInt(le.get(prox));
            if (valor == comp) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public abstract void inteligencia() throws Exception;
    public static final double VERSAO = 1.6;
}
