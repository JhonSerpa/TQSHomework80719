/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tqshomework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaos
 */
public class ConversionTest {

    private DataGatherer gather;

    @Before
    public void setUp() {

        gather = new DataGatherer();

    }

    @Test
    public void testGatherData() {
        assertNotEquals("EMPTY", gather.getData());
        assertNotEquals("EMPTY", gather.getDataAS("EUR"));
    }

    public ConversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

}
