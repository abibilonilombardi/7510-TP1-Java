package ar.uba.fi.tdd.rulogic.model;


public abstract class Definicion {
    protected String Nombre;
    protected String[] Parametros;

    public Definicion(String Nombre, String[] Parametros) {
        this.Nombre = Nombre.trim();
        this.Parametros = AplicarFormatoSinEspacio(Parametros);
    }

    private String[] AplicarFormatoSinEspacio(String[] parametros) {
        String[] parametrosSinEspacios = new String[parametros.length];
        for (int i = 0; i < parametros.length; i++) {
            parametrosSinEspacios[i] = parametros[i].trim();
        }
        return parametrosSinEspacios;
    }

    public abstract boolean Evaluar(Definicion queryAEvaluar);
}