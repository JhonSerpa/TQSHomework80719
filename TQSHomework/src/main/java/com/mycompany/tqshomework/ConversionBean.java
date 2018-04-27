/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tqshomework;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.google.gson.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author joaos
 */
@ManagedBean
@ApplicationScoped
public class ConversionBean {

    private String baseCurrency;
    private String destinCurrency;

    private List<String> currenciList;
    private Map<String,Double> rates = new HashMap<>();
    
    
    private DataGatherer gather;

    private JsonObject result;

    private double amount;
    private double res;

    public ConversionBean(DataGatherer gather) {

        this.gather = gather;
        currenciList = new ArrayList<>();
        res = 0;
        populate();
    }

    public ConversionBean() {

        this.gather = new DataGatherer();
        currenciList = new ArrayList<>();
        res = 0;
        populate();
    }

    private void populate() {

        JsonElement ele = new JsonParser().parse(gather.getData());
        JsonElement quotes = ele.getAsJsonObject().get("quotes");
        result = quotes.getAsJsonObject();
        Set<String> resset;
        resset = result.keySet();
        for (String curr : resset) {
            String c = curr.replaceFirst("USD", "");
            rates.put(c, Double.parseDouble(result.get(curr).toString()));
            currenciList.add(c);
        }

        baseCurrency = currenciList.get(0);
        destinCurrency = currenciList.get(0);

    }

    /**
     * Função para ser usada no Converter.
     */
    public void calculate() {
        calcConversion(this.baseCurrency, this.destinCurrency, this.amount);
    }

    /**
     * Used in my own API
     *
     * @param initial
     * @param finalCurr
     * @return
     */
    public Double grabFactor(String initial, String finalCurr) {
        try {
            Double rtn;
            double factor;

            if (initial == finalCurr) {
                return 1.0;
            } else if (initial == "USD") {
                rtn = rates.get(finalCurr);
            } else if (finalCurr == "USD") {
                rtn = 1.0 / (rates.get(initial));
            } else {
                factor = (1.0 / (rates.get(initial))) * rates.get(finalCurr);
                rtn = factor;
            }

            return rtn;

        } catch (Exception e) {
            return null;
        }
    }

    public Double calcConversion(String inicial, String finalCurr, double valu) {
        try {
            double fac = 0;
            //Se for USDEUR o valor é o que se recebe
            //Se for EURUSD o valor é inverso
            //Se for EURAES o valor é o inverso do inicial (USDEUR) a multiplicar com o (USDAES)
            if (valu <= 0.0) {
                return 0.0;
            } else if (inicial == finalCurr) {
                return valu;
            } else if (finalCurr == "USD") {
                fac = 1.0 / (rates.get(inicial));
            } else if (inicial == "USD") {
                fac = rates.get(finalCurr);
            } else {
                /*JsonElement ele = new JsonParser().parse(gather.getDataAS(inicial));
                JsonElement quotes = ele.getAsJsonObject().get("quotes");
                result = quotes.getAsJsonObject();
                System.out.println(result.toString());
                fac = result.get(concatInitFin).getAsDouble();*/
                fac = (1.0 / (rates.get(inicial))) * rates.get(finalCurr);
            }

            res = valu * fac;
            return res;

        } catch (NullPointerException e) {
            return 0.0;
        }
    }

    //Getter and setter
    public String resString() {
        return String.format("%.3f", res);
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getDestinCurrency() {
        return destinCurrency;
    }

    public void setDestinCurrency(String destinCurrency) {
        this.destinCurrency = destinCurrency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public double getRes() {
        return res;
    }

    public List<String> getCurrenciList() {
        return currenciList;
    }

    public void setCurrenciList(List<String> currenciList) {
        this.currenciList = currenciList;
    }

    public DataGatherer getGather() {
        return gather;
    }

    public void setGather(DataGatherer gather) {
        this.gather = gather;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }

}
