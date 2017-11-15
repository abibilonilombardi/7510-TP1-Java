package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseTest {

    //	@InjectMocks
    private KnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
//		initMocks(this);
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

}
