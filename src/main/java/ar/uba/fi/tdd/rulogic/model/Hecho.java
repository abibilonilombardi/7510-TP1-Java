package ar.uba.fi.tdd.rulogic.model;

public class Hecho extends Definicion {

    public Hecho(String Nombre, String[] Parametros) {
        super(Nombre, Parametros);
    }


    public void Reemplazar(String parametro, String reemplazo) {
        String[] parametrosReemplazados = this.Parametros.clone(); // lo copia o refencia?

        for (int i = 0; i < this.Parametros.length; i++) {
            parametrosReemplazados[i] = parametrosReemplazados[i].replace(parametro, reemplazo);
        }
        this.Parametros = parametrosReemplazados;
    }

    public boolean Evaluar(Definicion consultaAEvaluar) {
        if (this.Nombre.equals(consultaAEvaluar.Nombre) && this.Parametros.length == consultaAEvaluar.Parametros.length) {
            for (int i = 0; i < this.Parametros.length; i++) {
                if (!this.Parametros[i].equals(consultaAEvaluar.Parametros[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}

