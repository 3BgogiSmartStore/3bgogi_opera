package com.gogi.proj.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gogi.proj.security.AdminVO;

@Component
public class AdminLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginSuccessHandler.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private String targetUrlParameter;
	
	private String defaultUrl;
	
	private boolean useReferer;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static List<String> accessIpList;
	
	public AdminLoginSuccessHandler() {
		
		targetUrlParameter = "";
		
		defaultUrl = "/main/home.do";
		
		useReferer = false;
		
	}
	
	
	
	public RequestCache getRequestCache() {
		return requestCache;
	}



	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}



	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}



	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}



	public String getDefaultUrl() {
		return defaultUrl;
	}



	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}



	public boolean isUseReferer() {
		return useReferer;
	}



	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}

	public static long ipToLong(String ipAddress) {
        String[] ipAddressTemp = ipAddress.split("\\.");
        long ipAddressLong = (Long.parseLong(ipAddressTemp[0]) << 24) + 
        		
		(Long.parseLong(ipAddressTemp[1]) << 16) + 
		(Long.parseLong(ipAddressTemp[2]) << 8) + (Long.parseLong(ipAddressTemp[3]));
        
		return ipAddressLong;
    }

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
			Authentication auth) throws IOException, ServletException {
	
	    /*if(authCounting == 0) {
	    	res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('접근이 허용된 ip가 아닙니다');history.go(-1);</script>");
            out.flush();
            
            return;
		}*/
	    
		/*if(clientIp.contains("220.79.1.99") || clientIp.contains("127.0.0.1")) {
			
		}else {
			
			res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('접근이 허용된 ip가 아닙니다');history.go(-1);</script>");
            out.flush();
            
            return;
		}*/
		
		AdminVO adVO = (AdminVO) auth.getPrincipal();
		
		Collection<SimpleGrantedAuthority> authList = (Collection<SimpleGrantedAuthority>) adVO.getAuthorities();
		
		Iterator<SimpleGrantedAuthority> iter = authList.iterator();
		
		while( iter.hasNext()) {
			
			SimpleGrantedAuthority simpleAuth = iter.next();
			
			/*if(simpleAuth.getAuthority().equals("ROLE_SELLER")) {
				defaultUrl = "/seller/exchange.do";
				targetUrlParameter = "/seller/exchange.do";
			}*/
		}
		
		logger.info("Login Success !");
		logger.info("Login ID [ {} ].",auth.getName());

		
		clearAuthenticationAttributes(req);
		
		int intRedirectStrategy = decideRedirectStrategy(req, res);
		
		switch(intRedirectStrategy) {
		
		case 1:
			useTargetUrl(req, res);
			break;
			
		case 2:
			useSessionUrl(req, res);
			break;
			
		case 3:
			useRefererUrl(req, res);
			break;
			
		default:
			useDefaultUrl(req, res);
			
		}
		
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			
			return;
			
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
	}
	
	private void useTargetUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		if(savedRequest != null) {
			
			requestCache.removeRequest(req, res);
			
		}
		
		String targetUrl = req.getParameter(targetUrlParameter);
		
		redirectStrategy.sendRedirect(req, res, targetUrl);
		
	}
	
	private void useSessionUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		String targetUrl = savedRequest.getRedirectUrl();
		
		redirectStrategy.sendRedirect(req, res, targetUrl);
		
	}
	
	private void useRefererUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String targetUrl = req.getHeader("REFERER");
		
		redirectStrategy.sendRedirect(req, res, targetUrl);
		
	}
	
	private void useDefaultUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		redirectStrategy.sendRedirect(req, res, defaultUrl);
		
	}
	
	
	private int decideRedirectStrategy(HttpServletRequest req, HttpServletResponse res) {
		
		int result = 0;
		
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		if(!"".equals(targetUrlParameter)) {
			
			String targetUrl = req.getParameter(targetUrlParameter);
			
			if(StringUtils.hasText(targetUrl)) {
				
				result = 1;
				
			}else {
				
				if(savedRequest != null) {
					
					result = 2;
					
				}else {
					
					String refererUrl = req.getHeader("REFERER");
					
					if(useReferer && StringUtils.hasText(refererUrl)) {
						
						result = 3;
						
					}else {
						
						result = 0;
						
					}
					
				}
				
			}
			
			return result;
			
		}
		
		if(savedRequest != null) {
			
			result = 2;
			
			return result;
			
		}
		
		String refererUrl = req.getHeader("REFERER");
		
		if(useReferer && StringUtils.hasText(refererUrl)) {
			
			result = 3;
			
		}else {
			
			result = 0;
			
		}
		
		return result;
		
	}

}
