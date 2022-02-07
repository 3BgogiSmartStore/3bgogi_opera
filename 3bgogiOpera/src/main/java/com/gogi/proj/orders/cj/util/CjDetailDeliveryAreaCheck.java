package com.gogi.proj.orders.cj.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Jeon KiChan
 * @date : 2021. 10. 21.
 * @lastUpdate : 2022-01-24
 * @ClassName : 지역 별 배송 불가 지역 체크
 */
public enum CjDetailDeliveryAreaCheck {

	// 아래에 기재된 지역은 새벽배송이 불가능한 지역
	DUCYANGGU("덕양구" , Arrays.asList("신원동","북한동","효자동","오금동","용두동","벽제동","선유동","고양동","대자동","관산동","내유동","내곡동","화정동","강매동","행주내동","행주외동","신평동","현천동","덕은동","향동동","원신동","흥도동","행주동","대덕동")),
	ILSANDONGGU("일산동구", Arrays.asList("산황동","사리현동","지영동","설문동","문봉동","성석동","고봉동")),
	ILSANSUGU("일산서구", Arrays.asList("구산동","법곳동","송포동", "송산동")),
	PAJUSI("파주시", Arrays.asList("아동동","야동동","검산동","매금동","교하동","오도동","상지석동","산남동","당하동","문발동","송촌동","하지석동","서패동","신촌동","연다산동","금릉동","문산읍","파주읍","법원읍","조리읍","월롱면","탄현면","광탄면","파평면","적성면","군내면","장단면","진동면","진서면","운정동","운정1동","운정2동","운정3동")),
	INCHEONSUGU("인천광역시 서구", Arrays.asList("시천동","공촌동","원창동","오류동","검암경서동","신현원창동","불로대곡동","오류왕길동","아라동")),
	INCHEONSISUGU("인천시 서구", Arrays.asList("시천동","공촌동","원창동","오류동","검암경서동","신현원창동","불로대곡동","오류왕길동","아라동")),
	INCHEONJOONGGU("인천광역시 중구", Arrays.asList("중산동","운남동","영종동","운서동","운북동","을왕동","용유동","남북동","덕교동","무의동")),
	INCHEONSIJOONGGU("인천시 중구", Arrays.asList("중산동","운남동","영종동","운서동","운북동","을왕동","용유동","남북동","덕교동","무의동")),
	INCHEON("인천광역시", Arrays.asList("강화군")),
	INCHEONSI("인천시", Arrays.asList("강화군")),
	NAMYANGJUSI("남양주시",Arrays.asList("호평동","평내동","금곡동","일패동","이패동","삼패동","수석동","지금동","도농동","다산동","와부읍","진전읍","진접읍","화도읍","진건읍","오남읍","퇴계원읍","별내면","수동면","조안면","양정동")),
	HANAMSI("하남시" , Arrays.asList("천현동","하산곡동","배알미동","상산곡동","당정동","풍산동","미사동","감북동","감일동","감이동","교산동","춘궁동","하사창동","상사창동","항동","초일동","초이동","광암동","신장동")),
	GIMPOSI("김포시" , Arrays.asList("통진읍","양촌읍","대곶면","월곶면","하성면")),
	HWASEONGSI("화성시", Arrays.asList("황계동","배양동","기안동","송산동","안녕동","새솔동","봉담읍","우정읍","향남읍","남양읍","매송면","비봉면","마도면","송산면","서신면","팔탄면","장안면","양감면","정남면","기배동","화산동")),
	YONGINSI("용인시", Arrays.asList("처인구")),
	ANSANSI("안산시", Arrays.asList("안산동","반월공단","수암동","장상동","대부북동","대부동동","대부남동","선감동")),
	GUNPOSI("군포시", Arrays.asList("대야동")),
	GURISI("구리시", Arrays.asList("동구동")),
	GWANGMYEONGSI("광명시", Arrays.asList("학온동")),
	BUNDANGRU("분당구", Arrays.asList("율동")),
	SUJEONGGU("수정구", Arrays.asList("위례동")),
	JUNGWONGU("중원구", Arrays.asList("갈현동","상대동")),
	GWONSEONGU("권선구", Arrays.asList("곡선동")),
	YEONGTONGGU("영통구", Arrays.asList("광교동")),
	GIGEUNGGU("기흥구", Arrays.asList("기흥동","서농동","구성동")),
	UIJEONGBUSI("의정부시", Arrays.asList("송산동","자금동","흥선동")),
	UIWANGSI("의왕시", Arrays.asList("삼동"));
	
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
