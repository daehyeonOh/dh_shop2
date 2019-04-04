<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="../include/head.jsp"%>
<body>

<%@ include file="../include/main_header.jsp"%>
<%@ include file="../include/left_column.jsp"%>

<h1>회원정보수정</h1>

	<form action="${path}/user/" method="post">
		<input type="hidden" name="_method" value="patch">
		<input type="text" value="<sec:authentication property="principal.id"/>" disabled="disabled"><br>
		<input type="password" name="password" placeholder="input password"><br>
		<input type="text" name="name" placeholder="name" value="<sec:authentication property="principal.name"/>"><br>
		<input type="email" name="email" placeholder="email" value="<sec:authentication property="principal.email"/>"><br>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
		<input type="submit" name="submit" value="회원정보수정"><button>취소</button>
	</form>


<%@ include file="../include/main_footer.jsp"%>
<%@ include file="../include/plugin_js.jsp"%>


</body>
</html>