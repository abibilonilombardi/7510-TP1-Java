package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("I shall answer all your questions!");

        Scanner scanner = new Scanner(System.in);

//        System.out.print("Ingrese el nombre de la base en la que desea buscar: ");
//        KnowledgeBase Interprete = new KnowledgeBase("./src/main/resources/" + in.nextLine());

        KnowledgeBase Interprete = new KnowledgeBase("./src/main/resources/rules.db");
        System.out.println("Para terminar escriba 'salir' ");

        String consulta;
        while(true){
            System.out.println("Consulta: ");
            consulta = scanner.nextLine();
            if (consulta.equals("salir")){break;}
            System.out.println(">" + Interprete.answer(consulta));
        }
        System.out.print("Vuelva pronto :) ");
    }
}
