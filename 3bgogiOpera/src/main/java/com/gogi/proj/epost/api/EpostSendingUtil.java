package com.gogi.proj.epost.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.epost.vo.Xsync;
import com.gogi.proj.util.JsonToMapUtil;
import com.gogi.proj.util.naverMapApiUtil;

@Component
public class EpostSendingUtil {

	private static final Logger logger = LoggerFactory.getLogger(EpostSendingUtil.class);
	
	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	private String skey;
	
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	
	//우체국계약고객시스템에서 확인 가능
	
	public String getEpost_api_key() {
		return apiKeyProperties.getProperty("api_key.epost");
	}

	public String epostEncrypting(String epostParams) {

		SEED128 seed = new SEED128();
		
		//우체국계약고객시스템에서 확인 가능
		skey = apiKeyProperties.getProperty("api_key.epost_s_key");
		
		String plainStr = epostParams;
		String encryptStr = "";
		
		if (!plainStr.equals("")) {
			byte[] pbUserKey = skey.getBytes();
			byte[] dataget = plainStr.getBytes();
			
			encryptStr = seed.getEncryptData(skey, plainStr);

		} else if (!encryptStr.equals("")) {
			plainStr = seed.getDecryptData(skey, encryptStr);
			plainStr = plainStr.replaceAll("&micro", "&amp;micro");
		}

		return encryptStr;
		
	}
	
	public String epostplainStr(String epostParams) {
		
		SEED128 seed = new SEED128();
		
		//우체국계약고객시스템에서 확인 가능
		skey = apiKeyProperties.getProperty("api_key.epost_s_key");

		String plainStr = seed.getDecryptData(skey, epostParams);
		plainStr = plainStr.replaceAll("&micro", "&amp;micro");

		return plainStr;
		
	}
	
	public RegDataVO epostSending(String param, String epostUrl) throws Exception {
		
        String urlParameters = "key="+apiKeyProperties.getProperty("api_key.epost")
                +"&regData="+param;
        URL obj = new URL(epostUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Host", "biz.epost.go.kr");
        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.1(Java/1.8.0_91)");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        System.out.println(response);
        in.close();
        
        //송장생성일 경우
        if(epostUrl.equals("http://ship.epost.go.kr/api.InsertOrder.jparcel")) {
        	return epostXMLParsingDeliv(response.toString());
        	
        //송장 삭제일 경우
        }else if(epostUrl.equals("http://ship.epost.go.kr/api.GetResCancelCmd.jparcel")) {
        	
        	return epostXMLParsingDelete(response.toString());
        	
        }else if(epostUrl.equals("http://ship.epost.go.kr/api.GetResCancelCmd.jparcel")) {
        	
        	return epostXMLParsingDeliv(response.toString());
        	
        }else {
        	
        	return null;
        }
		
		
	}
	
	
	/**
	 * 
	 * @MethodName : epostXMLParsingDelete
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param xmlData
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 * @메소드설명 : 송장 삭제할 때에 사용
	 */
	public RegDataVO epostXMLParsingDelete(String xmlData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, ParseException {
		
		JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
		String objString = xmlJSONObj.toString();
		
		Map<String, Object> results = naverMapApiUtil.returnJson(objString);
		
		RegDataVO rdVO = (RegDataVO)JsonToMapUtil.getMapToDAO(results, (Object)new RegDataVO());
		
		return rdVO;
	}
	
	/**
	 * 
	 * @MethodName : epostXMLParsingDeliv
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param xmlData
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 * @메소드설명 : 송장 생성시 사용
	 */
	public RegDataVO epostXMLParsingDeliv(String xmlData) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, ParseException {
		JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
		String objString = xmlJSONObj.toString();
		
		Map<String, Object> results = naverMapApiUtil.returnJson(objString);
		
		RegDataVO rdVO = (RegDataVO)JsonToMapUtil.getMapToDAO(results, (Object)new RegDataVO());
		
		return rdVO;

	}
	
	
	/**
	 * 
	 * @MethodName : epostXMLParsingDeliv
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param xmlData
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 * @메소드설명 : 송장 생성시 사용
	 */
	public Object epostXMLParsingDelivByObjectClass(String xmlData, Object objClass) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, ParseException {
		JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
		String objString = xmlJSONObj.toString();
		
		logger.info("xml 파싱 내역 = {}"+objString);
		
		Map<String, Object> results = naverMapApiUtil.returnJson(objString);
		
		return JsonToMapUtil.getMapToDAO(results, objClass);

	}
	
	
	public Object selfPrintepostSendingTest(String param, String epostUrl, Object objClass) throws Exception {
		
        String urlParameters = "key="+apiKeyProperties.getProperty("api_key.epost")
                +"&regData="+param;
        URL obj = new URL(epostUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Host", "biz.epost.go.kr");
        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.1(Java/1.8.0_91)");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        Charset charset = Charset.forName("UTF-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        System.out.println("response toString = "+response.toString());
        in.close();
        
        return epostXMLParsingDelivByObjectClass(response.toString(), objClass);
		
	}
	
	
	public Xsync stoppedDelivAreaCheck(String param, String epostUrl) throws Exception {

		String urlParameters = "key="+apiKeyProperties.getProperty("api_key.epost")
        +"&"+param;
		
		URL obj = new URL(epostUrl+"?"+urlParameters);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//add reuqest header
		con.setRequestMethod("GET");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Host", "biz.epost.go.kr");
		con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.1(Java/1.8.0_91)");
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    response.append(inputLine);
		}
		
		in.close();
		
		Xsync xsync = new Xsync();
		
		return (Xsync) epostXMLParsingDelivByObjectClass(response.toString(), xsync);
	}

}
