package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseTest {

    private KnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        knowledgeBase = new KnowledgeBase("./src/main/resources/rules.db");
    }

    @Test
    public void test_facts_ok() {
        Assert.assertTrue(this.knowledgeBase.answer("varon(pepe)."));
        Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepa)."));
        Assert.assertTrue(this.knowledgeBase.answer("hermano(nicolas, roberto)."));
    }

    @Test
    public void test_facts_nok() {
        Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
    }

    @Test
    public void test_rules_ok() {
        Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas,alejandro,roberto)"));
        Assert.assertTrue(this.knowledgeBase.answer("hijo( pepe,juan)."));
    }

    @Test
    public void test_rules_nok() {
        Assert.assertFalse(this.knowledgeBase.answer("hijo(juan,juan)."));
    }

    @Test
    public void test_rulesConRules_ok() {
        this.knowledgeBase =  new KnowledgeBase("./src/main/resources/rulesConRules.db");

        Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe,juan)"));
        Assert.assertTrue(this.knowledgeBase.answer("nieto(pepe,hector,juan)"));
        Assert.assertTrue(this.knowledgeBase.answer("hija(maria ,pepe)"));
        Assert.assertTrue(this.knowledgeBase.answer("bisnieta(maria,hector,pepe,juan)"));
    }

    @Test
    public void test_rulesConRules_nok() {
        this.knowledgeBase =  new KnowledgeBase("./src/main/resources/rulesConRules.db");

        Assert.assertFalse(this.knowledgeBase.answer("nieto(pepe,juan, pepe)"));
        Assert.assertFalse(this.knowledgeBase.answer("bisnieta(maria,pepe,hector,juan)"));
    }

    @Test
    public void test_baseConErrorSintaxis_ok() {
        this.knowledgeBase =  new KnowledgeBase("./src/main/resources/conError.db");

        Assert.assertFalse(this.knowledgeBase.answer("nieto(pepe,juan, pepe)"));
    }

    @Test
    public void test_queryConErrorSintaxis_ok() {
        Assert.assertFalse(this.knowledgeBase.answer("padrejuan, pepe)"));
    }

    @Test
    public void test_noEncuentraArchivo_ok() {
        this.knowledgeBase =  new KnowledgeBase("./src/main/resources/archivoNoExistente.db");
        Assert.assertFalse(this.knowledgeBase.answer("padre(juan, pepe)"));
    }

}
