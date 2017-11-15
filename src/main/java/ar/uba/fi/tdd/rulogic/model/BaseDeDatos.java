package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Contiene todas las Definiciones generadas por el Parser a partir del archivo.
 * Si el archivo tiene errores de sintaxis se imprime por pantalla y todas las
 * consultas daran false.
 */
public class BaseDeDatos implements ContenedorDeDefiniciones {
    private List<Definicion> Definiciones;
    private Parser Parser;

    public BaseDeDatos( Parser parser) {
        this.Definiciones = new ArrayList<>();
        this.Parser = parser;
        this.Parser.EstablecerContexto(this);
    }

    private List<String> ObtenerLineas(String archivo) {
        List<String> lineas = new ArrayList<>();
        try (Stream<String> lineasArchivo = Files.lines(Paths.get(archivo))) {
            lineasArchivo.forEach(lineas::add);
        } catch (IOException e) {
            System.out.println("ERROR: no se encuentra el archivo");
            lineas.clear();
        }
        return lineas;
    }

    public void CargarDesdeArchivo(String archivo) {
        for (String linea : this.ObtenerLineas(archivo)) {
            Definicion definicion;
            try {
                definicion = this.Parser.Parsear(linea);
                this.Definiciones.add(definicion);
            } catch (SentenciaInvalidaException e) {
                System.out.println("ERROR: el archivo contiene un error en la linea: [" + (this.Definiciones.size() + 1) + "] " + linea);
                this.Definiciones.clear();
            }
        }
    }

    @Override
    public boolean Contiene(Definicion definicion) {
        for (Definicion def : this.Definiciones) {
            if (def.Evaluar(definicion)) {
                return true;
            }
        }
        return false;
    }
}
