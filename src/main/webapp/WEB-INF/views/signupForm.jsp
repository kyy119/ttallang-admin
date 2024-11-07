<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>관리자 회원가입</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<form id="signupForm">
	<div class="mb-3">
		<label for="userName" class="form-label">아이디</label>
		<input type="text" id="userName" name="userName" class="form-control" required>
	</div>
	<div class="mb-3">
		<label for="userPassword" class="form-label">비밀번호</label>
		<input type="password" id="userPassword" name="userPassword" class="form-control" required>
	</div>
	<button type="submit" class="btn btn-primary">가입하기</button>
	<a href="${pageContext.request.contextPath}/login/form" type="button" class="btn btn-warning">취소</a>
</form>
</body>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>
</html>
