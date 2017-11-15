package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class BaseDeDatos {
    private List<Definicion> BDD;

    public BaseDeDatos(String archivo) {
        this.BDD = new ArrayList<Definicion>();
        this.ParsearLista(archivo);
    }

    private List<String> ParsearArchivo(String archivo) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            System.out.println("ERROR: no se encuentra el archivo");
            lines.clear();
        }
        return lines;
    }

    private void ParsearLista(String archivo) {
        List<String> lineasArchivo = this.ParsearArchivo(archivo);

        for (String linea : lineasArchivo) {
            Definicion definicion;
            try {
                definicion = ParsearString(linea);
                this.BDD.add(definicion);
            } catch (SentenciaInvalidaException e) {
                System.out.println("ERROR: el archivo contiene un error en la linea: [" + (this.BDD.size()+1) + "] " + linea);
                this.BDD.clear();
            }
        }
    }

    public Definicion ParsearString(String sentencia) throws SentenciaInvalidaException {
        Pattern sintaxis = Pattern.compile("([a-zA-Z\\s]*)\\(([a-zA-Z\\s,]*)\\)\\s*[.|:]?");

        Matcher matcher = sintaxis.matcher(sentencia);

        if (matcher.find()) {
            String nombre = matcher.group(1);
            String parametros = matcher.group(2);

            List<String> facts = new ArrayList<>();

            while (matcher.find()) {
                facts.add(matcher.group(0));
            }

            if (facts.isEmpty()) {
                return new Fact(nombre, parametros.split(","));
            } else {
                Rule rule = new Rule(nombre, parametros.split(","));
                for (String str : facts) {
                    rule.AgregarDefinicion(ParsearString(str));
                }
                rule.SetContexto(this);
                return rule;
            }
        }
        throw new SentenciaInvalidaException();
    }

    public boolean EstaContenida(Definicion definicion) {
        for (Definicion def : this.BDD) {
            if (def.Evaluar(definicion)) {
                return true;
            }
        }
        return false;
    }
}
