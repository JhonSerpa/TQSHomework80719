/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tqshomework;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author joaos
 */
public class DataGatherer {

    public String getData() {
        Document data = null;
        try {
            data = Jsoup.connect("http://www.apilayer.net/api/live?access_key=39a9a0a36fc1110d93f136e44dacea88&format=1").ignoreContentType(true).get();
        } catch (IOException e) {
            return "EMPTY";
        }
        return data.body().text();
    }

    //{ "success":false, "error":{ "code":105, "info":"Access Restricted - Your current Subscription Plan does not support Source Currency Switching." } }
    public String getDataAS(String as) {
        Document data = null;
        try {
            data = Jsoup.connect("http://www.apilayer.net/api/live?access_key=39a9a0a36fc1110d93f136e44dacea88&source=" + as + "&format=1").ignoreContentType(true).get();
        } catch (IOException e) {
            return "EMPTY";
        }
        return data.body().text();
    }
}
