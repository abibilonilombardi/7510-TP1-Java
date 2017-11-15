package ar.uba.fi.tdd.rulogic.model;


public class KnowledgeBase {
    private BaseDeDatos BDD;

    public KnowledgeBase(String archivoDeEntrada) {
        this.BDD = new BaseDeDatos(archivoDeEntrada);
    }

    public boolean answer(String query) {
        try {
            return BDD.EstaContenida(this.BDD.ParsearString(query));
        } catch (SentenciaInvalidaException e) {
            System.out.println("ERROR: la query tiene una sintaxis invalida");
            return false;
        }
    }
}
