<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="../include/head.jsp"%>
<body>

<%@ include file="../include/main_header.jsp"%>
<%@ include file="../include/left_column.jsp"%>

<h1>회원가입</h1>
	
	<form action="${path}/user/" method="post">
		<input type="text" name="id" placeholder="id"><br>
		<input type="password" name="password" placeholder="password"><br>
		<input type="text" name="name" placeholder="name"><br>
		<input type="email" name="email" placeholder="email"><br>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
		<input type="submit" name="submit" value="회원가입"><button>취소</button>
	</form>


<%@ include file="../include/main_footer.jsp"%>
<%@ include file="../include/plugin_js.jsp"%>


</body>
</html>