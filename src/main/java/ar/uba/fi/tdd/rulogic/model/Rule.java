package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

public class Rule extends Definicion {
    private BaseDeDatos Contexto;
    private List<Definicion> Facts;

    public Rule(String Nombre, String[] Parametros) {
        super(Nombre, Parametros);
        this.Facts = new ArrayList<Definicion>();
    }

    public void SetContexto(BaseDeDatos contexto) {
        this.Contexto = contexto;
    }

    public void AgregarDefinicion(Definicion definicion){
        Facts.add(definicion);
    }

    public boolean Evaluar(Definicion queryAEvaluar) {
        if (this.Nombre.equals(queryAEvaluar.Nombre) && this.Parametros.length == queryAEvaluar.Parametros.length) {
            for(Definicion fact: this.Facts) {
                Fact copia = new Fact(fact.Nombre, fact.Parametros.clone());
                for (int i = 0; i < this.Parametros.length; i++) {
                    copia.Reemplazar(this.Parametros[i], queryAEvaluar.Parametros[i]);
                }
                if(! this.Contexto.EstaContenida(copia)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
