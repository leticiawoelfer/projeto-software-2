package br.furb.furbot.suporte;

import java.io.File;
import java.io.IOException;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;









public class ExercicioFactory
{
  public ExercicioFactory() {}
  
  public static Exercicio create(String nomeArquivoXML, Class<?> furbotClass)
    throws IOException, SAXException
  {
    Exercicio exercicio = create(nomeArquivoXML);
    if (furbotClass != null) {
      exercicio.setClassRobo(furbotClass.getName());
    }
    return exercicio;
  }
  
  public static Exercicio create(String nomeArquivoXML)
    throws IOException, SAXException
  {
    Exercicio exercicio = null;
    Digester d = new Digester();
    exercicio = new Exercicio(nomeArquivoXML);
    d.push(exercicio);
    
    d.addSetProperties("furbot");
    d.addBeanPropertySetter("*/enunciado", "enunciado");
    d.addBeanPropertySetter("*/mundo/qtdadeLin");
    d.addBeanPropertySetter("*/mundo/qtdadeCol");
    d.addBeanPropertySetter("*/mundo/explodir");
    d.addBeanPropertySetter("*/mundo/usarLinhasNaGrade");
    d.addBeanPropertySetter("*/mundo/tamanhoCel");
    
    addRulesRandom(d, "*/mundo");
    d.addObjectCreate("*/grupo", GrupoObjetos.class);
    d.addSetNext("*/grupo", "addGrupo");
    addRuleObjeto(d, "*/robo");
    d.addSetNext("*/robo", "addRobo");
    addRuleObjeto(d, "*/objeto");
    d.addSetNext("*/objeto", "addElemento");
    addRuleObjetoClass(d, "*/numero", NumeroElementoExercicio.class);
    d.addSetNext("*/numero", "addElemento");
    d.addCallMethod("*/numero/valor", "setRandomInf", 1);
    d.addCallParam("*/numero/valor", 0, "randomInf");
    d.addBeanPropertySetter("*/numero/valor");
    d.addCallMethod("*/numero/valor", "setRandomSup", 1);
    d.addCallParam("*/numero/valor", 0, "randomSup");
    addRuleObjetoClass(d, "*/booleano", BooleanoElementoExercicio.class);
    d.addSetNext("*/booleano", "addElemento");
    d.addCallMethod("*/booleano/valor", "setRandom", 1);
    d.addCallParam("*/booleano/valor", 0, "random");
    d.addBeanPropertySetter("*/booleano/valor");
    File srcfile = new File(nomeArquivoXML);
    d.parse(srcfile);
    exercicio.finalizar();
    return exercicio;
  }
  
  private static void addRuleObjeto(Digester d, String pattern)
  {
    addRuleObjetoClass(d, pattern, ElementoExercicio.class);
  }
  
  private static void addRuleObjetoClass(Digester d, String pattern, Class<?> clazz)
  {
    d.addObjectCreate(pattern, clazz);
    d.addSetProperties(pattern, new String[] {
      "class", "qtdade" }, 
      new String[] {
      "clazz", "qtdade" });
    
    d.addBeanPropertySetter(pattern + "/x");
    d.addBeanPropertySetter(pattern + "/y");
    d.addBeanPropertySetter(pattern + "/id");
    d.addCallMethod(pattern + "/x", "setIdDependeX", 1);
    d.addCallParam(pattern + "/x", 0, "depende");
    d.addCallMethod(pattern + "/y", "setIdDependeY", 1);
    d.addCallParam(pattern + "/y", 0, "depende");
    addRulesRandom(d, pattern);
    d.addBeanPropertySetter(pattern + "/energia");
    d.addBeanPropertySetter(pattern + "/bloqueado");
  }
  
  private static void addRulesRandom(Digester d, String pattern)
  {
    d.addObjectCreate(pattern + "/random", FurbotRandom.class);
    d.addObjectCreate(pattern + "/randomX", FurbotRandom.class);
    d.addObjectCreate(pattern + "/randomY", FurbotRandom.class);
    definirRandom(d, pattern + "/random", "setRandomXY", "limiteInfX", "limiteSupX", "limiteInfY", "limiteSupY");
    definirRandomXY(d, pattern + "/randomX", "setRandomX", "X", "limiteInf", "limiteSup");
    definirRandomXY(d, pattern + "/randomY", "setRandomY", "Y", "limiteInf", "limiteSup");
    d.addSetNext(pattern + "/random", "setRandom");
    d.addSetNext(pattern + "/randomX", "setRandom");
    d.addSetNext(pattern + "/randomY", "setRandom");
  }
  
  private static void definirRandomXY(Digester d, String pattern, String methodName, String chave, String limiteInf, String limiteSup)
  {
    d.addCallMethod(pattern, "setLimiteSupRandom" + chave, 1);
    d.addCallParam(pattern, 0, limiteSup);
    d.addCallMethod(pattern, "setLimiteInfRandom" + chave, 1);
    d.addCallParam(pattern, 0, limiteInf);
    d.addCallMethod(pattern, methodName);
  }
  
  private static void definirRandom(Digester d, String pattern, String methodName, String limiteInfX, String limiteSupX, String limiteInfY, String limiteSupY)
  {
    d.addCallMethod(pattern, "setLimiteSupRandomY", 1);
    d.addCallParam(pattern, 0, limiteSupY);
    d.addCallMethod(pattern, "setLimiteInfRandomY", 1);
    d.addCallParam(pattern, 0, limiteInfY);
    d.addCallMethod(pattern, "setLimiteSupRandomX", 1);
    d.addCallParam(pattern, 0, limiteSupX);
    d.addCallMethod(pattern, "setLimiteInfRandomX", 1);
    d.addCallParam(pattern, 0, limiteInfX);
    d.addCallMethod(pattern, methodName);
  }
}
