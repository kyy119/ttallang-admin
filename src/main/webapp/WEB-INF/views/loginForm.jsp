<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>로그인</title>
  <link href="${pageContext.request.contextPath}/css/loginForm.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <style>
    /* General page styling */
    body, html {
      margin: 0;
      padding: 0;
      height: 100%;
      font-family: Arial, sans-serif;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #f5f5f5;
    }

    .login-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      width: 400px;
      padding: 40px;
      background-color: white;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
      border-radius: 8px;
      text-align: center;
    }

    .login-title {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 30px;
    }

    form {
      width: 100%;
    }

    .mb-3 {
      margin-bottom: 15px;
    }

    label.form-label {
      font-size: 14px;
      color: #555;
    }

    input.form-control {
      font-size: 16px;
      padding: 10px;
      border-radius: 4px;
      border: 1px solid #ccc;
    }

    button.btn-primary {
      width: 100%;
      background-color: #4d5c6b;
      color: white;
      border: none;
      padding: 10px;
      font-size: 16px;
      font-weight: bold;
      border-radius: 4px;
      transition: background-color 0.3s ease;
      margin-top: 20px;
    }

    button.btn-primary:hover {
      background-color: #3b4954;
    }

    a.btn-primary {
      display: block;
      width: 100%;
      margin-top: 10px;
      padding: 10px;
      text-decoration: none;
      background-color: #4d5c6b;
      color: white;
      border-radius: 4px;
      font-weight: bold;
      text-align: center;
    }

    a.btn-primary:hover {
      background-color: #3b4954;
    }

    .login-title img {
      width: 300px;
      height: auto;
      margin-bottom: 10px;
    }

  </style>
</head>
<body>
<!-- 로그인 폼 -->
<div class="login-container">
  <div class="login-title">
    <img src="${pageContext.request.contextPath}/images/ttallangImg.png" alt="따릉이 KOSA BIKE Logo">
  </div>
  <form id="loginForm">
    <div class="mb-3">
      <label for="username" class="form-label">아이디</label>
      <input type="text" id="username" name="username" class="form-control" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">비밀번호</label>
      <input type="password" id="password" name="password" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">로그인</button>
    <a href="${pageContext.request.contextPath}/signup/form" type="button" class="btn btn-primary">회원가입</a>
  </form>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</html>
