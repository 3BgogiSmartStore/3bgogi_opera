package com.gogi.proj.orders.cj.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Jeon KiChan
 * @date : 2021. 10. 21.
 * @lastUpdate : 2022-03-04
 * @ClassName : 지역 별 배송 불가 지역 체크
 */
public enum CjDetailDeliveryAreaCheck {

	// 아래에 기재된 지역은 새벽배송이 불가능한 지역
	DUCYANGGU("덕양구" , Arrays.asList("북한동","효자동","벽제동","선유동","고양동","대자동","내곡동","신평동","원신동","흥도동","행주동","대덕동")),
	ILSANDONGGU("일산동구", Arrays.asList("사리현동","지영동","문봉동","고봉동")),
	ILSANSUGU("일산서구", Arrays.asList("송포동", "송산동")),
	PAJUSI("파주시", Arrays.asList("검산동","매금동","교하동","오도동","상지석동","산남동","하지석동","서패동","문산읍","파주읍","법원읍","조리읍","월롱면","탄현면","광탄면","파평면","적성면","장단면","진동면","진서면","운정동","운정1동","운정2동","운정3동")),
	INCHEONSUGU("인천광역시 서구", Arrays.asList("아라동")),
	INCHEONSISUGU("인천시 서구", Arrays.asList("아라동")),
	INCHEONJOONGGU("인천광역시 중구", Arrays.asList("중산동","운남동","영종동","운서동","운북동","을왕동","용유동","남북동","덕교동","무의동")),
	INCHEONSIJOONGGU("인천시 중구", Arrays.asList("중산동","운남동","영종동","운서동","운북동","을왕동","용유동","남북동","덕교동","무의동")),
	INCHEON("인천광역시", Arrays.asList("강화군")),
	INCHEONSI("인천시", Arrays.asList("강화군")),
	NAMYANGJUSI("남양주시",Arrays.asList("일패동","이패동","삼패동","와부읍","화도읍","오남읍","수동면","조안면","양정동")),
	HANAMSI("하남시" , Arrays.asList("배알미동","상산곡동","하사창동","상사창동","항동","초이동","광암동")),
	GIMPOSI("김포시" , Arrays.asList("통진읍","대곶면","하성면")),
	HWASEONGSI("화성시", Arrays.asList("황계동","배양동","기안동","송산동","안녕동","봉담읍","우정읍","향남읍","매송면","비봉면","마도면","송산면","서신면","팔탄면","장안면","양감면","정남면","기배동","화산동")),
	YONGINSI("용인시", Arrays.asList("처인구")),
	CHEOINGU("처인구", Arrays.asList("남동", "유방동", "운학동","호동","해곡동","포곡읍","모현읍","이동읍","남사읍","백암면","양지면","중앙동","역삼동","유림동","동부동")),
	ANSANSI("안산시", Arrays.asList("안산동","반월공단","수암동","대부북동","대부동동","대부남동","선감동")),
	GUNPOSI("군포시", Arrays.asList("대야동")),
	GURISI("구리시", Arrays.asList("동구동")),
	GWANGMYEONGSI("광명시", Arrays.asList("학온동")),
	SUJEONGGU("수정구", Arrays.asList("위례동")),
	JUNGWONGU("중원구", Arrays.asList("상대동")),
	GWONSEONGU("권선구", Arrays.asList("곡선동")),
	YEONGTONGGU("영통구", Arrays.asList("광교동")),
	OSANSI("오산시", Arrays.asList("청학동","가장동", "인계동", "서랑동","서동","벌음동","두곡동","탑동","누읍동","가수동","중앙동","남촌동","신장동","세마동","초평동","대원동")),
	GIGEUNGGU("기흥구", Arrays.asList("기흥동","서농동","구성동")),
	UIJEONGBUSI("의정부시", Arrays.asList("송산동","자금동","흥선동")),
	PYEONGTAEKSI("평택시", Arrays.asList("모곡동","칠괴동","칠원동","도일동","가재동","장안동","독곡동","평택동","통복동","군문동","유천동","지제동","신대동","소사동","월곡동","청룡동","팽성읍","안중읍","포승읍","청북읍","진위면","서탄면","고덕면","오성면","현덕면","중앙동","송탄동","송북동","신편동","원평동")),
	SIHEUNGSI("시흥시", Arrays.asList("포동", "미산동", "안현동", "매화동", "도창동", "금이동", "과림동", "계수동","죽율동", "무지내동", "배곧동", "신현동","연성동"));
	
	private String areas;
	private List<String> dongList;
	
	CjDetailDeliveryAreaCheck(String areas, List<String> dongList) {
		// TODO Auto-generated constructor stub
		this.areas = areas;
		this.dongList =  dongList;
	}
	
	public static boolean hasDongName(String dong, List<String> dongLists) {
		
		boolean result = true; 
				
				
		for( String dongName : dongLists) {
			if( dong.contains(dongName)) {
				
				return false;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @MethodName : hasAreaName
	 * @date : 2021. 10. 21.
	 * @author : Jeon KiChan
	 * @param area
	 * @return
	 * @메소드설명 : 배송 불가 지역 확인
	 */
	public static boolean hasAreaName(String area) {

		for( CjDetailDeliveryAreaCheck guType : CjDetailDeliveryAreaCheck.values() ) {
			if(area.contains(guType.areas)) {
				
				
				return hasDongName(area, guType.dongList);
			}
		}
		
		 return true;
	}
}
