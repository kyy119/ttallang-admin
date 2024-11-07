<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Payment History</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap Date Picker -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@chenfengyuan/datepicker@1.0.10/dist/datepicker.min.css">
    <script src="https://cdn.jsdelivr.net/npm/@chenfengyuan/datepicker@1.0.10/dist/datepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paymentMainStyle.css">
    <script src="${pageContext.request.contextPath}/js/paymentMain.js"></script>


</head>
<body>
<jsp:include page="../header/header.jsp" />
<div class="container">
    <div class="row mb-3">
        <div class="col">
            <label for="startDatePicker" class="form-label">시작 날짜</label>
            <input type="text" id="startDatePicker" class="form-control" placeholder="시작 날짜를 선택해 주세요" readonly>
        </div>
        <div class="col">
            <label for="endDatePicker" class="form-label">종료 날짜</label>
            <input type="text" id="endDatePicker" class="form-control" placeholder="종료 날짜를 선택해 주세요" readonly>
        </div>
        <div class="col-auto align-self-end">
            <button class="btn btn-primary" onclick="calculateTotal()">계산</button>
        </div>
    </div>
    <div class="table-container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>결제 날짜</th>
                <th>금액</th>
            </tr>
            </thead>
            <tbody id="payList">
            </tbody>
        </table>
    </div>
    <div id="totalAmount" class="total-amount">총액: 0 원</div>
</div>
</body>
</html>
