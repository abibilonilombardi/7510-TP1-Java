package ar.uba.fi.tdd.rulogic.model;

public class Fact extends Definicion {

    public Fact(String Nombre, String[] Parametros) {
        super(Nombre, Parametros);
    }


    public void Reemplazar(String parametro, String reemplazo) {
        String[] parametrosReemplazados = this.Parametros.clone(); // lo copia o refencia?

        for (int i = 0; i < this.Parametros.length; i++) {
            parametrosReemplazados[i] = parametrosReemplazados[i].replace(parametro, reemplazo);
        }
        this.Parametros = parametrosReemplazados;
    }

    public boolean Evaluar(Definicion queryAEvaluar) {
        if (this.Nombre.equals(queryAEvaluar.Nombre) && this.Parametros.length == queryAEvaluar.Parametros.length) {
            for (int i = 0; i < this.Parametros.length; i++) {
                if (!this.Parametros[i].equals(queryAEvaluar.Parametros[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}

