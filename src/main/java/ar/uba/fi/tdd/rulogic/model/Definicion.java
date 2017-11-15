package ar.uba.fi.tdd.rulogic.model;

/**
 * Generalizacion de Hechos y Reglas
 * Composite: Las Hechos son las hojas y las Reglas son los contenedores de Definiciones
 * (Las reglas pueden estar compuestas de Hechos y de otras Reglas)
 */
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

    public abstract boolean Evaluar(Definicion consultaAEvaluar);
}