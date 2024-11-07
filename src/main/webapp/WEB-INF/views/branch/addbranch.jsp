<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>대여소 추가</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addBranchStyle.css">
    <script src="${pageContext.request.contextPath}/js/addBranch.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div id="form-container">
    <img src="/images/spot.png" alt="Location Icon" width="50" height="50">
    <h3>대여소 추가</h3>
    <form id="branchForm">
        <input type="text" name="branchName" placeholder="대여소 이름">
        <input type="text" id="streetAdr" name="streetAdr" placeholder="도로명 주소" readonly>
        <button type="button" onclick="sample4_execDaumPostcode()">위치 찾기</button>
        <button type="submit" id="submitBtn">추가</button>
    </form>
</div>
</body>
</html>
