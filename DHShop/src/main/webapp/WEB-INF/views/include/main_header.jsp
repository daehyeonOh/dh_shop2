<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>

<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = "";
	if (auth != null) {
		Object principal = auth.getPrincipal();
		if (principal != null) {
			name = auth.getName();
		}
	}
%>

<a href="${path}/">DH SHOP</a>

<%
	if(name.equals("anonymousUser") || name.equals("")) {
%>
	<a href="${path}/signIn">로그인</a>
	ㅣ<a href="${path}/signUp">회원가입</a>
<%
	} else {
%>
	<sec:authentication property="principal.name"/>님, 반갑습니다.
	<a href="#" onclick="document.getElementById('logout-form').submit();">로그아웃</a>|
	<a href="${path}/member/modifyPage">회원정보수정</a>|
	<a href="#">장바구니</a>
	<form id="logout-form" action="${path}/logout" method="POST">
	   <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	</form>
<%
	}
%>
 <br>

