<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="../include/head.jsp"%>
<body>

<%@ include file="../include/main_header.jsp"%>
<%@ include file="../include/left_column.jsp"%>
<%
	String referer = request.getHeader("REFERER");
	String redirect = request.getParameter("loginRedirect");
	if(redirect==null){
		pageContext.setAttribute("loginRedirect", referer);
	}
%>

<h1>로그인</h1>

	<form action="${path}/login" method="post">
		<input type="text" name="username" placeholder="아이디" value="${username}"><br>
		<input type="password" name="password" placeholder="비밀번호"><br>
		<c:if test="${not empty errormsg}">
		    <font color="red">
		        <p>${errormsg}</p>
		    </font>
		</c:if>
		<label for="remember-me">Remember Me</label>
   	 	<input type="checkbox" id="remember-me" name="remember-me"/>
   	 	<input type="hidden" value="${loginRedirect}" name="loginRedirect"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br>
		<input type="submit" name="submit" value="Login">
	</form>

<%@ include file="../include/main_footer.jsp"%>
<%@ include file="../include/plugin_js.jsp"%>


</body>
</html>