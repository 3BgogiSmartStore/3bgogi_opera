package com.gogi.proj.epost.vo;

import com.gogi.proj.orders.vo.OrdersVO;

public class Xsync {
	
	private OrdersVO orVO;
	private String error_code;
	private String message;
	private String areaNm;
	private String strtZipCd;
	private String endZipCd;
	private String applyStrtYmd;
	private String applyEndYmd;
	private String stopReason;
	
	public Xsync() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Xsync(OrdersVO orVO, String error_code, String message, String areaNm, String strtZipCd, String endZipCd,
			String applyStrtYmd, String applyEndYmd, String stopReason) {
		super();
		this.orVO = orVO;
		this.error_code = error_code;
		this.message = message;
		this.areaNm = areaNm;
		this.strtZipCd = strtZipCd;
		this.endZipCd = endZipCd;
		this.applyStrtYmd = applyStrtYmd;
		this.applyEndYmd = applyEndYmd;
		this.stopReason = stopReason;
	}

	public OrdersVO getOrVO() {
		return orVO;
	}

	public void setOrVO(OrdersVO orVO) {
		this.orVO = orVO;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAreaNm() {
		return areaNm;
	}

	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}

	public String getStrtZipCd() {
		return strtZipCd;
	}

	public void setStrtZipCd(String strtZipCd) {
		this.strtZipCd = strtZipCd;
	}

	public String getEndZipCd() {
		return endZipCd;
	}

	public void setEndZipCd(String endZipCd) {
		this.endZipCd = endZipCd;
	}

	public String getApplyStrtYmd() {
		return applyStrtYmd;
	}

	public void setApplyStrtYmd(String applyStrtYmd) {
		this.applyStrtYmd = applyStrtYmd;
	}

	public String getApplyEndYmd() {
		return applyEndYmd;
	}

	public void setApplyEndYmd(String applyEndYmd) {
		this.applyEndYmd = applyEndYmd;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	@Override
	public String toString() {
		return "Xsync [orVO=" + orVO + ", error_code=" + error_code + ", message=" + message + ", areaNm=" + areaNm
				+ ", strtZipCd=" + strtZipCd + ", endZipCd=" + endZipCd + ", applyStrtYmd=" + applyStrtYmd
				+ ", applyEndYmd=" + applyEndYmd + ", stopReason=" + stopReason + "]";
	}

	
	/*public Xsync(OrdersVO orVO) {
		super();
		this.orVO = orVO;
	}
	
	public OrdersVO getOrVO() {
		return orVO;
	}

	public void setOrVO(OrdersVO orVO) {
		this.orVO = orVO;
	}

	@Override
	public String toString() {
		return "Xsync [orVO=" + orVO + "]";
	}

	class stoppedZipCdInfo{
		private String areaNm;
		private String strtCipCd;
		private String endZipCd;
		private String applyStrtYmd;
		private String applyEndYmd;
		private String stopReason;
		
		public stoppedZipCdInfo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public stoppedZipCdInfo(String areaNm, String strtCipCd, String endZipCd, String applyStrtYmd,
				String applyEndYmd, String stopReason) {
			super();
			this.areaNm = areaNm;
			this.strtCipCd = strtCipCd;
			this.endZipCd = endZipCd;
			this.applyStrtYmd = applyStrtYmd;
			this.applyEndYmd = applyEndYmd;
			this.stopReason = stopReason;
		}

		public String getAreaNm() {
			return areaNm;
		}

		public void setAreaNm(String areaNm) {
			this.areaNm = areaNm;
		}

		public String getStrtCipCd() {
			return strtCipCd;
		}

		public void setStrtCipCd(String strtCipCd) {
			this.strtCipCd = strtCipCd;
		}

		public String getEndZipCd() {
			return endZipCd;
		}

		public void setEndZipCd(String endZipCd) {
			this.endZipCd = endZipCd;
		}

		public String getApplyStrtYmd() {
			return applyStrtYmd;
		}

		public void setApplyStrtYmd(String applyStrtYmd) {
			this.applyStrtYmd = applyStrtYmd;
		}

		public String getApplyEndYmd() {
			return applyEndYmd;
		}

		public void setApplyEndYmd(String applyEndYmd) {
			this.applyEndYmd = applyEndYmd;
		}

		public String getStopReason() {
			return stopReason;
		}

		public void setStopReason(String stopReason) {
			this.stopReason = stopReason;
		}

		@Override
		public String toString() {
			return "stoppedZipCdInfo [areaNm=" + areaNm + ", strtCipCd=" + strtCipCd + ", endZipCd=" + endZipCd
					+ ", applyStrtYmd=" + applyStrtYmd + ", applyEndYmd=" + applyEndYmd + ", stopReason=" + stopReason
					+ "]";
		}
		
	}
	
	class error{
		private String errorCode;
		private String message;
		
		public error() {
			super();
			// TODO Auto-generated constructor stub
		}

		public error(String errorCode, String message) {
			super();
			this.errorCode = errorCode;
			this.message = message;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "error [errorCode=" + errorCode + ", message=" + message + "]";
		}

		
		
	}*/
}
