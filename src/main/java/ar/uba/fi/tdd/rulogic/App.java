package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

/**
 * Console application.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("I shall answer all your questions!");
        KnowledgeBase KB = new KnowledgeBase("./src/main/resources/rules.db");
//        System.out.println("respuesta: " + KB.answer("padre(juan, pepa)."));
//        System.out.println("respuesta: " + KB.answer("mujer(maria) ."));
//        System.out.println("respuesta: " + KB.answer("padre(ju, pepa)."));
//        System.out.println("respuesta: " + KB.answer("padre(roberto, cecilia)."));
//        System.out.println("respuesta: " + KB.answer("varon ( nicolas ) ."));
//        System.out.println("respuesta: " + KB.answer("varon(nicolas) ."));
        //---- todo corregir los espacios
        System.out.println("I shall answer all your questions!");
        System.out.println("respuesta: " + KB.answer("hijo( pepe,juan)."));
        System.out.println("respuesta: " + KB.answer("hijo(juan,juan)."));
        System.out.println("respuesta: " + KB.answer("tio(nicolas,alejandro,roberto)"));
        System.out.println("respuesta: " + KB.answer("tio(roberto,pepe,nicolas)"));
        System.out.println("respuesta: " + KB.answer("tia(nicolas,cecilia,roberto)"));


        System.out.println("I shall answer all your questions!");
        System.out.println("respuesta: " + KB.answer("nieto( pepe,hector,juan)."));
    }
}
