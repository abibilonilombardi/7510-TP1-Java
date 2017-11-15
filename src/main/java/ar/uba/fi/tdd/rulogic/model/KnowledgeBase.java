package ar.uba.fi.tdd.rulogic.model;


public class KnowledgeBase {
    private static final String sintaxis = "([a-zA-Z\\s]*)\\(([a-zA-Z\\s,]*)\\)\\s*[.|:]?";

    private BaseDeDatos BaseDeDatos;
    private Parser Parser;

    public KnowledgeBase(String archivoDeEntrada) {
        this.Parser = new Parser(sintaxis);
        this.BaseDeDatos = new BaseDeDatos(this.Parser);
        this.BaseDeDatos.CargarDesdeArchivo(archivoDeEntrada);
    }

    public boolean answer(String query) {
        try {
            return BaseDeDatos.Contiene(this.Parser.Parsear(query));
        } catch (SentenciaInvalidaException e) {
            System.out.println("ERROR: la query tiene una sintaxis invalida");
            return false;
        }
    }
}
