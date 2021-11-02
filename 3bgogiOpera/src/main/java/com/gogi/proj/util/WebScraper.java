package com.gogi.proj.util;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

	public String selectAbattDate(String abatt) {

		String abattDate = "";
		
		// Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
		String url = "https://mtrace.go.kr/mtracesearch/pigLotNoSearch.do?lotNo="+abatt; // 크롤링할 url지정
		
		Document doc = null; // Document에는 페이지의 전체 소스가 저장된다

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
		Elements element = doc.select("table.type2");

		// Iterator을 사용하여 하나씩 값 가져오기

		int first = 0;

		Iterator<Element> ie1 = element.select("a").iterator();

		while (ie1.hasNext()) {
			
			if (first == 0) {
				abattDate = ie1.next().text();
			}
			
			first++;
			break;
		}
		
		return abattDate;

	}
}
