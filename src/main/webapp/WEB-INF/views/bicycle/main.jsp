<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>신고 상태 리스트</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bicycleMainStyle.css">
    <script src="${pageContext.request.contextPath}/js/bicycleMain.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div class="content">
    <div class="radio-section">
        <label><input type="radio" name="report" value="allBike" checked>전체 자전거</label>
        <label><input type="radio" name="report" value="reported"> 신고</label>
        <label><input type="radio" name="report" value="notReported"> 미신고</label>
    </div>

    <table>
        <tr>
            <th>자전거 고유번호</th>
            <th>자전거 신고 상태</th>
        </tr>
        </thead>
        <tbody id="reportList">
        </tbody>
    </table>
</div>
</body>
</html>
