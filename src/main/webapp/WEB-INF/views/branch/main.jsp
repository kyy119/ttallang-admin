<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Branch Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/branchMainStyle.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/branchMain.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div class="container">
    <div class="search-bar">
        <input type="text" id="searchInput" placeholder="대여소 이름 검색">
        <button id="searchButton"><i class="fas fa-search"></i></button>
    </div>
    <label><input type="radio" name="branchStatus" value="1">활성화</label>
    <label><input type="radio" name="branchStatus" value="0">비활성화</label>
    <table>
        <thead>
        <tr>
            <th>대여소 이름</th>
            <th>자전거 개수</th>
            <th>수정</th>
        </tr>
        </thead>
        <tbody id="branchTableBody"></tbody>
    </table>
    <a href="/admin/branch/addBranch"><div class="add-button">추가</div></a>
</div>
</body>
</html>
