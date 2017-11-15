package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

public class Regla extends Definicion {
    private ContenedorDeDefiniciones Contexto;
    private List<Definicion> Facts;

    public Regla(String Nombre, String[] Parametros) {
        super(Nombre, Parametros);
        this.Facts = new ArrayList<Definicion>();
    }

    public void EstablecerContexto(ContenedorDeDefiniciones Contexto) {
        this.Contexto = Contexto;
    }

    public void AgregarDefinicion(Definicion definicion){
        Facts.add(definicion);
    }

    public boolean Evaluar(Definicion consultaAEvaluar) {
        if (this.Nombre.equals(consultaAEvaluar.Nombre) && this.Parametros.length == consultaAEvaluar.Parametros.length) {
            for(Definicion fact: this.Facts) {
                Hecho copia = new Hecho(fact.Nombre, fact.Parametros.clone());
                for (int i = 0; i < this.Parametros.length; i++) {
                    copia.Reemplazar(this.Parametros[i], consultaAEvaluar.Parametros[i]);
                }
                if(! this.Contexto.Contiene(copia)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
