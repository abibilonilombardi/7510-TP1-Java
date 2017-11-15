package ar.uba.fi.tdd.rulogic.model;


public class KnowledgeBase {
	private BaseDeDatos BDD;

	public KnowledgeBase(String archivoDeEntrada){
	    this.BDD = new BaseDeDatos(archivoDeEntrada);
	}

	public boolean answer(String query) {
		return BDD.EstaContenida(this.BDD.ParsearString(query));
	}

}
