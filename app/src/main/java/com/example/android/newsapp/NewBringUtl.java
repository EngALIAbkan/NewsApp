package com.example.android.newsapp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

public class NewBringUtl {
    public static List<News> ExtractData(String json) {
        List<News> data = new ArrayList<>();
        JSONObject response = null;
        try {
            response = new JSONObject(json).getJSONObject("response");

            JSONArray array = response.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject currObject = array.getJSONObject(i);
                    String title = currObject.getString("webTitle");
                    String stories = currObject.getString("sectionName");
                    String date = currObject.getString("webPublicationDate");
                    String url = currObject.getString("webUrl");
                    JSONArray tags=currObject.getJSONArray("tags");
                    String nameFirst="";
                    String nameLast = "";
                    for (int j = 0; j < tags.length(); j++) {
                     JSONObject tag = tags.getJSONObject(j);
                         nameFirst = tag.getString("firstName");
                         nameLast = tag.getString("lastName");

                    }
                    News info = new News(title, stories, url, date, nameFirst, nameLast);
                    data.add(info);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<News> fetchData(String mUrl) {
        StringBuilder JSONData = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(mUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                JSONData.append(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return ExtractData(JSONData.toString());
    }
}



