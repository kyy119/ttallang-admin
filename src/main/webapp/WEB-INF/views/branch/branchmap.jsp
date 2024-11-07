<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>자전거 위치</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/branchMapStyle.css">
    <script src="${pageContext.request.contextPath}/js/branchMap.js"></script>
    <!-- Kakao Maps API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1bfc76317e78b81b1a32b1be44269182"></script>

</head>
<body>
<div id="header">
    <jsp:include page="../header/header.jsp" />
</div>
<div class="content">
    <div id="map"></div>
    <div id="sidePanel">
        <h2>자전거 고유번호</h2>
        <ul id="bicycleList"></ul> <!-- 자전거 목록을 표시할 리스트 -->
        <input type="text" name="bicycleName" placeholder="자전거 이름"><br><br>
        <button type="submit" id="submitBtn">추가하기</button>
    </div>
</div>
</body>
</html>
