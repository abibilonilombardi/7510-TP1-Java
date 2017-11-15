package ar.uba.fi.tdd.rulogic.model;


public abstract class Definicion {
    protected String Nombre;
    protected String[] Parametros;
    protected BaseDeDatos Contexto;

    public Definicion(String Nombre, String[] Parametros) {
        this.Nombre = Nombre.trim();
        this.Parametros = AplicarFormatoSinEspacio(Parametros);
    }

    public void SetContexto(BaseDeDatos contexto) {
        this.Contexto = contexto;
    }

    public abstract boolean Evaluar(Definicion queryAEvaluar);

    private String[] AplicarFormatoSinEspacio(String[] parametros) {
        String[] str = new String[parametros.length];
        for (int i = 0; i < parametros.length; i++) {
            str[i] = parametros[i].trim();
        }
        return str;
    }
}