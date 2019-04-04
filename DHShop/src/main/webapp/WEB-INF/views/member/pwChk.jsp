<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="../include/head.jsp"%>
<body>

<%@ include file="../include/main_header.jsp"%>
<%@ include file="../include/left_column.jsp"%>

<h1>비밀번호 확인</h1>
	
	<form action="${path}/user/pwChk" method="post">
		<input type="password" name="password">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
		<input type="submit" name="submit" value="확인">
	</form>

<%@ include file="../include/main_footer.jsp"%>
<%@ include file="../include/plugin_js.jsp"%>


</body>
</html>