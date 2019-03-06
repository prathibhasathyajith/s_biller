package com.biller.webapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Prathibha Sathyajith on 12/5/2018.
 */
public class tes {
    public static void main(String[] args) {
        try {
            new tes().getAddressByGpsCoordinates("6.85059434143502","79.89765672556084");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private String getAddressByGpsCoordinates(String lng, String lat)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {

        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&sensor=true");
        System.out.println(url.toString());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";

        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);

            if (rsp.containsKey("results")) {
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address");
            }

            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }
    }
}
