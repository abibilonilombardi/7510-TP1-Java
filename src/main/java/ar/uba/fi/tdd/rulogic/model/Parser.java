package ar.uba.fi.tdd.rulogic.model;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Toma una linea y la convierte en una Definicion si esta matchea con la sintaxis
 * con la que se instancio.
 */
public class Parser {
    private Pattern Sintaxis;
    private ContenedorDeDefiniciones ContenedorDeDefiniciones;

    public Parser(String sintaxis) {
        this.Sintaxis = Pattern.compile(sintaxis);
    }

    public void EstablecerContexto(ContenedorDeDefiniciones contenedorDeDefiniciones){this.ContenedorDeDefiniciones = contenedorDeDefiniciones;}

    public Definicion Parsear(String sentencia) throws SentenciaInvalidaException {
        Matcher matcher = this.Sintaxis.matcher(sentencia);

        if (matcher.find()) {
            String nombre = matcher.group(1);
            String[] parametros = matcher.group(2).split(",");

            List<String> definicionesDeRegla = new ArrayList<>();

            while (matcher.find()) {
                definicionesDeRegla.add(matcher.group(0));
            }

            if (definicionesDeRegla.isEmpty()) {
                return new Hecho(nombre, parametros);
            } else {
                Regla regla = new Regla(nombre, parametros);
                for (String str : definicionesDeRegla) {
                    regla.AgregarDefinicion(Parsear(str));
                }
                regla.EstablecerContexto(this.ContenedorDeDefiniciones);
                return regla;
            }
        }
        throw new SentenciaInvalidaException();
    }
}
