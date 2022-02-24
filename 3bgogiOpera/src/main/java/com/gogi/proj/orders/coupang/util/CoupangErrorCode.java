package com.gogi.proj.orders.coupang.util;

/**
 * 
 * @author myoungHunPark
 *2022. 2. 23.
 *@detail 쿠팡 에러코드에 관한 설명
 */
public enum CoupangErrorCode {

	OK("OK", "성공"),
	NOT_FOUND_SHIPMENT_BOX("NOT_FOUND_SHIPMENT_BOX","존재하지 않는 송장번호입니다."),
	INVALID_STATUS("INVALID_STATUS","배송진행상태가 유효하지 않습니다."),
	PERMISSION_DENIED("PERMISSION_DENIED","권한이 없습니다."),
	DUPLICATE_INVOICE_NUMBER("DUPLICATE_INVOICE_NUMBER","이미 저장된 송장번호가 있습니다."),
	INVALID_INVOICE_NUMBER("INVALID_INVOICE_NUMBER","송장번호가 유효하지 않습니다."),
	ORDER_DELIVERY_CANCELED("ORDER_DELIVERY_CANCELED","취소된 주문건입니다. or 출고 준비중 상태인경우-> 출고중지 요청건입니다."),
	ORDER_DELIVERY_PARTIAL_STOP_REQUESTED("ORDER_DELIVERY_PARTIAL_STOP_REQUESTED","출고중지 요청건입니다."),
	ORDER_DELIVERY_CANCELED_HOLDING_FOR_CANCEL("ORDER_DELIVERY_CANCELED_HOLDING_FOR_CANCEL","취소대기상태의 주문건입니다."),
	UNDEFINED_ERROR_OCCUR("UNDEFINED_ERROR_OCCUR","알수없는 오류"),
	NOT_ALLOW_INVOICE_NUMBER_RE_UPLOAD("NOT_ALLOW_INVOICE_NUMBER_RE_UPLOAD","이미 저장된 송장번호가 있어, 송장번호 등록이 불가능합니다. (동일송장번호 업로드 조건 : 주문자/수취인정보 동일)"),
	NOT_FOUND_INVOICE_NUMBER("NOT_FOUND_INVOICE_NUMBER","이미 출고 예정일이 등록된 상품이어서 운송장 번호를 입력하셔야 합니다."),
	NOT_FOUND_VENDOR_ITEM("NOT_FOUND_VENDOR_ITEM","합포장건 중 누락된 상품이 있습니다."),
	NOT_ALLOW_VENDOR_DIRECT("NOT_ALLOW_VENDOR_DIRECT","업체직송은 분리배송 할 수 없습니다."),
	PSD_NOT_ALLOW_TO_CHANGE("PSD_NOT_ALLOW_TO_CHANGE","출고예정일은 최초 등록 이후 변경 할 수 없습니다."),
	NOT_ALLOW_SEPARATE_AFTER_NORMAL_DELIVERY("NOT_ALLOW_SEPARATE_AFTER_NORMAL_DELIVERY","일반배송 접수 후 분리배송을 다시 할 수 없습니다."),
	IS_SEPARATE_MUST_BE_TRUE_WHEN_SEPARATE_DELIVERY("IS_SEPARATE_MUST_BE_TRUE_WHEN_SEPARATE_DELIVERY","분리배송 접수 후 일반배송으로 처리 할 수 없습니다. 분리배송 여부를 Y로 수정하시기 바랍니다."),
	NOT_ALLOW_SEPARATE_WHEN_ONE_VENDOR_ITEM("NOT_ALLOW_SEPARATE_WHEN_ONE_VENDOR_ITEM","상품이 한가지인 경우 분리배송 할 수 없습니다."),
	EMPTY("없음","등록되지 않은 에러 코드입니다");
	
	private String errorCode;
	private String errorDetail;
	
	private CoupangErrorCode(String errorCode, String errorDetail) {
		this.errorCode = errorCode;
		this.errorDetail = errorDetail;
	}

	public String getErrorCode() {
		
		return errorCode;
	}

	public static String getErrorCodeDetail(String errorCode) {
		for (CoupangErrorCode errorCodes : CoupangErrorCode.values()) {
			if (errorCodes.getErrorCode().equals(errorCode)) {
				return errorCodes.errorDetail;
			}
		}
		
		return CoupangErrorCode.EMPTY.errorDetail;
		
	}
	
}
