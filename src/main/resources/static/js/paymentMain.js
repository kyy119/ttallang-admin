$(document).ready(function () {
  $('#startDatePicker').datepicker({
    format: 'yyyy-mm-dd',
    autoHide: true,
    clearBtn: true,
    days: ['일', '월', '화', '수', '목', '금', '토'],
    daysShort: ['일', '월', '화', '수', '목', '금', '토'],
    daysMin: ['일', '월', '화', '수', '목', '금', '토'],
    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월',
      '12월'],
    monthsShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
      '11월', '12월']
  });

  $('#endDatePicker').datepicker({
    format: 'yyyy-mm-dd',
    autoHide: true,
    clearBtn: true,
    days: ['일', '월', '화', '수', '목', '금', '토'],
    daysShort: ['일', '월', '화', '수', '목', '금', '토'],
    daysMin: ['일', '월', '화', '수', '목', '금', '토'],
    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월',
      '12월'],
    monthsShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
      '11월', '12월']
  });

  window.calculateTotal = function () {
    const startDate = $('#startDatePicker').datepicker('getDate');
    const endDate = $('#endDatePicker').datepicker('getDate');

    if (!startDate || !endDate) {
      alert("시작 날짜와 끝 날짜를 선택해 주세요.");
      return;
    }
    const formattedStartDate = startDate.getFullYear() + "-" +
        String(startDate.getMonth() + 1).padStart(2, '0') + "-" +
        String(startDate.getDate()).padStart(2, '0');
    const formattedEndDate = endDate.getFullYear() + "-" +
        String(endDate.getMonth() + 1).padStart(2, '0') + "-" +
        String(endDate.getDate()).padStart(2, '0');

    $.ajax({
      url: '/admin/api/payment',
      type: 'GET',
      data: {
        startDate: formattedStartDate,
        endDate: formattedEndDate
      },
      success: function (response) {
        let payList = $("#payList");
        payList.empty();
        let totalAmount = 0;
        response.forEach(function (payment) {
          payList.append("<tr><td>" + payment.paymentDate + "</td><td>"
              + payment.paymentAmount + "원</td></tr>");
          totalAmount += payment.paymentAmount;
        });
        $('#totalAmount').text("총액: " + totalAmount + " 원");
      },
      error: function (err) {
        console.error("Error fetching payment data:", err);
      }
    });
  };
});