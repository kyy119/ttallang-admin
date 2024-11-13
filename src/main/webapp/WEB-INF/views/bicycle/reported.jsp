<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bicycle Report Details</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bicycleReportedStyle.css">
    <script src="${pageContext.request.contextPath}/js/bicycleReported.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div class="report-container">
    <div class="report-id" hidden="hidden"></div>
    <div class="report-header"></div>
    <div class="report-date"></div>
    <div class="report-content"></div>
    <button class="submit-button" id="submitBtn">처리 완료</button>
</div>
</body>
</html>
