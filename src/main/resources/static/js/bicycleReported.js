$(document).ready(function () {
  // URL에서 bicycleId 파라미터 추출
  const urlParams = new URLSearchParams(window.location.search);
  const bicycleId = urlParams.get('bicycleId');

  if (bicycleId) {
    // bicycleId가 존재할 경우, 데이터 요청
    $.ajax({
      url: `/admin/api/bicycle/reported/` + bicycleId,
      type: 'GET',
      success: function (data) {
        const reportDate = new Date(data.reportDate).toISOString().split(
            'T')[0];
        $('.report-id').text(data.reportId);
        $('.report-header').text(data.bicycleName);
        $('.report-date').text(reportDate + " " + data.categoryName);
        $('.report-content').text(data.reportDetails);
      },
      error: function () {
        $('.report-content').text('신고된 내용 없음.');
      }
    });
  } else {
    $('.report-content').text('No Bicycle ID provided in the URL.');
  }
  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    var reportId = $('.report-id').text();
    // Ajax 요청을 통해 중복 체크 및 추가
    $.ajax({
      type: "POST",
      url: "/admin/api/bicycle/report/update",
      data: {
        bicycleId: bicycleId,
        reportId: reportId
      },
      success: function (response) {
        window.location.href = "/admin/bicycle/main";
      },
      error: function (xhr, status, error) {
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
      }
    });
  });
});