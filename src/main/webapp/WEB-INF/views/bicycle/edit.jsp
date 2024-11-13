<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>대여소 추가</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bicycleEditStyle.css">
    <script src="${pageContext.request.contextPath}/js/bicycleEdit.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div id="form-container">
    <img src="/images/markerStar.png" alt="Location Icon" width="50" height="50">
    <h3>자전거 수정</h3>
    <div id="branchSelectContainer"></div>
    <form id="branchForm">
        <input type="text" name="bicycleName" placeholder="자전거 이름">
        <label><input type="radio" name="bicycleStatus" value="1">활성화</label>
        <label><input type="radio" name="bicycleStatus" value="0">비활성화</label>
        <button type="submit" id="submitBtn">수정</button>
    </form>
    <button id="return">취소</button>
</div>
</body>
</html>
