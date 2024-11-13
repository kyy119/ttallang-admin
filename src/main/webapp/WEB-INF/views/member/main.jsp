<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 이름 검색</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/memberMainStyle.css">
    <script src="${pageContext.request.contextPath}/js/memberMain.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />

<div class="container">
    <h2>회원 이름 검색</h2>
    <div class="search-container">
        <input type="text" name="customerName" placeholder="이름 검색">
        <button id="searchButton">검색</button>
    </div>

    <div class="radio-container">
        <label><input type="radio" name="report" value="allMember" checked> 전체 회원</label>
        <label><input type="radio" name="report" value="noPayMember"> 미납 회원</label>
    </div>

    <div class="table-container">
        <table id="reportTable">
            <thead></thead>
            <tbody></tbody>
        </table>
    </div>
</div>
</body>
</html>
