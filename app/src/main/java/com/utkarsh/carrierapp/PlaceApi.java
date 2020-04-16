package com.utkarsh.carrierapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class PlaceApi {

    public ArrayList<String> autoComplete(String input) {
        ArrayList<String> list = new ArrayList<>();
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        try {
            String builder = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+input+"&key=AIzaSyCF74h8Kzz1TzgYkhUqGA5kW96dt4UkpC4";
            URL url = new URL(builder);
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            int read;
            char[] buff = new char[1024];
            while ((read=reader.read(buff))!=-1) {
                result.append(buff,0,read);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(result.toString());
            JSONArray prediction = jsonObject.getJSONArray("predictions");
            for (int i=0;i<prediction.length();i++) {
                list.add(prediction.getJSONObject(i).getString("description"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
