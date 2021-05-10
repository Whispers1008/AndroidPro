package com.example.getwebpagesourcecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    static String getWebSource(String webSite){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String webSourceJSONString = null;

        try{
            URL url = new URL(webSite);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream inStream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine())!=null){
                builder.append(line);
                builder.append("\n");
            }
            if(builder.length()==0){
                return null;
            }
            webSourceJSONString = builder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("webSource: "+webSourceJSONString);
        return webSourceJSONString;
    }
}
