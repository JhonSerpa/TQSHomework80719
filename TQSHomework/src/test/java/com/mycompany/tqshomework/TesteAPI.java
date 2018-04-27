package com.mycompany.tqshomework;

import com.google.gson.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author joaos
 */
public class TesteAPI {

    Client clt;
    WebTarget trgt;

    @Before
    public void init() {
        String url = "http://localhost:8080/TQSHomework/convapi/convertion/get?de=ARS&para=USD&val=60.22";
        this.clt = ClientBuilder.newClient();
        this.trgt = clt.target(url);

    }

    @Test
    public void testGetResponse() {

        Response response = trgt.path("").request(MediaType.APPLICATION_JSON).get();
        String resp = response.readEntity(String.class);
        JsonElement elem = new JsonParser().parse(resp);
        JsonObject json = elem.getAsJsonObject();
        System.out.println(json.toString());
        assertTrue(json.has("de"));
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
