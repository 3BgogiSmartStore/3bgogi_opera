package com.gogi.proj.todayPickup.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class URLUtil {

	public String getCoordiante(String apiUrl,  Map<String, String> requestHeaders, String methodType, String body) throws IOException {
		
        String responseBody = get(apiUrl,requestHeaders, methodType, body);
		
		return responseBody;
	}
	
	public static String get(String apiUrl, Map<String, String> requestHeaders, String methodType, String body){
        HttpURLConnection con = connect(apiUrl);
        
        try {
            con.setRequestMethod(methodType);
            
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
                
            }
            
            con.setDoOutput(true);
            
            if(body != null && !body.equals("")) {            	
            	OutputStream os= con.getOutputStream();
            	
            	os.write(body.getBytes());
            	
            	os.flush();
            	
            }
            
            int responseCode = con.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
                
            } else { 
                return readBody(con.getErrorStream());
                
            }
            
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
            
        } finally {
            con.disconnect();
            
        }
        
    }
	
	public static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
            
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
            
        }
        
    }

    public static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
                
            }

            System.out.println(responseBody.toString());
            
            return responseBody.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
            
        }
    }
}
