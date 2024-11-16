<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bicycleMapStyle.css">
    <script src="${pageContext.request.contextPath}/js/bicycleMap.js"></script>
    <!-- Kakao Maps API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1bfc76317e78b81b1a32b1be44269182"></script>
</head>
<body>
<jsp:include page="../header/header.jsp" />
<div id="map" style="height: 750px; width: 100%;"></div><br>
</body>
</html>
