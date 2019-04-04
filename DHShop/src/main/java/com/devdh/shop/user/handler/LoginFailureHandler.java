package com.devdh.shop.user.handler;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.devdh.shop.commons.util.MessageUtils;
import com.devdh.shop.user.service.UserService;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Inject
	UserService service;
	
	private String usernamename;
	private String passwordname;
	private String errormsgname;
	private String defaultFailureUrl;
	private String redirectUrl;
	
	/**
	 * [spring security 예외]
	 * BadCredentialException 	 					비밀번호가 일치하지 않을 때
	 * InternalAuthenticationServiceException 	 	존재하지 않은 아이디 일 때
	 * AuthenticationCredentialNotFoundException  	인증 요구 거부됐을 때
	 * LockedException 								인증거부 - 잠긴 계정
	 * DisabledException 							인증거부 - 계정 비활성화
	 * AccountExpriedException 						인증거부 - 계정 유효기간 만료
	 * CredentialExpiredException 					인증거부 - 비밀번호 유효기간 만료
	 */
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter(usernamename);
		String password = request.getParameter(passwordname);
		String redirecturl = request.getParameter(redirectUrl);
		String errormsg = "";
		
		// 비밀번호 다름
		if (exception instanceof BadCredentialsException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials");
		}
		// 존재하지 않은 계정
		else if (exception instanceof InternalAuthenticationServiceException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials");
		}
		// 계정 비활성화
		else if(exception instanceof DisabledException) {
			int disabledType = service.selectDisabledType(username);
			errormsg = MessageUtils.getMessage("error.Disabled-Type" + disabledType);
		}
		else if(exception instanceof LockedException) {
			errormsg = MessageUtils.getMessage("error.Locked");
		}
		else if(exception instanceof CredentialsExpiredException) {
			errormsg = MessageUtils.getMessage("error.CredentialsExpired");
		} else if(exception instanceof SessionAuthenticationException) {
			errormsg = MessageUtils.getMessage("error.SessionAuthentication");
		}
		
		request.setAttribute(usernamename, username);
		request.setAttribute(passwordname, password);
		request.setAttribute(redirectUrl, redirecturl);
		request.setAttribute(errormsgname, errormsg);
		
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

	public String getUsernamename() {
		return usernamename;
	}

	public void setUsernamename(String usernamename) {
		this.usernamename = usernamename;
	}

	public String getPasswordname() {
		return passwordname;
	}

	public void setPasswordname(String passwordname) {
		this.passwordname = passwordname;
	}

	public String getErrormsgname() {
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	
	
}
