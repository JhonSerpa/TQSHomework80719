/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tqshomework;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

/**
 * API to give the values
 *
 * @author joaos
 */
@Path("/convertion")
public class ConversionAPI {

    private final ConversionBean conv = new ConversionBean();

    @Path("/get")
    @GET
    @Produces("application/json")
    public Response convertion(@QueryParam("de") String baseCurrency, @QueryParam("para") String destinCurrency, @QueryParam("val") String vl) {

        double amount = 0, val, rate;

        if (baseCurrency == null || destinCurrency == null || vl == null) {
            return Response.status(204).build();
        }

        amount = Double.parseDouble(vl);

        val = conv.calcConversion(baseCurrency, destinCurrency, amount);
        rate = conv.grabFactor(baseCurrency, destinCurrency);

        String finalAmmount = String.format("%.3f", amount);
        String finalDestinCurr = String.format("%.3f", val);
        String finalRate = String.format("%.3f", rate);

        StringBuilder sb = new StringBuilder();
        sb.append("{\"de\" : \"").append(baseCurrency).append("\" ,");
        sb.append("\"para\" : \"").append(destinCurrency).append("\" ,");
        sb.append("\"val\" : \"").append(finalAmmount).append("\" ,");
        sb.append("\"result\" : \"").append(finalDestinCurr).append("\" ,");
        sb.append("\"rate\" : \"").append(finalRate).append("\"}");

        return Response.status(200).entity(sb.toString()).build();
    }
}
