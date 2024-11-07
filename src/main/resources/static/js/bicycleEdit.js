const urlParams = new URLSearchParams(window.location.search);
const bicycleId = urlParams.get("bicycleId");
$(document).ready(function () {
  function loadBicycle() {
    $.ajax({
      url: "/admin/api/bicycle/" + bicycleId,
      method: "GET",
      success: function (data) {
        $('input[name="bicycleName"]').val(data.bicycleName);

        // Set the radio buttons based on bicycleStatus
        if (data.bicycleStatus === '1') {
          $('input[name="bicycleStatus"][value="1"]').prop('checked', true);
        } else {
          $('input[name="bicycleStatus"][value="0"]').prop('checked', true);
        }
      },
      error: function () {
        alert("Failed to fetch branch data.");
      }
    });
  }

  loadBicycle();
  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    var bicycleName = $("input[name='bicycleName']").val().trim();
    var bicycleStatus = $("input[name='bicycleStatus']:checked").val();
    if (!bicycleName) {
      alert("모든 정보를 입력해주세요.");
      return;
    }
    // Ajax 요청을 통해 중복 체크 및 추가
    $.ajax({
      type: "POST",
      url: "/admin/api/bicycle/update",
      data: {
        bicycleId: bicycleId,
        bicycleName: bicycleName,
        bicycleStatus: bicycleStatus
      },
      success: function (response) {
        if (response == 'SUCCESS') {
          window.location.href = "/admin/bicycle/main";
        } else if (response == 'EXIST_NAME') {
          alert("이미 존재하는 자전거 이름 입니다.")
        }
      },
      error: function (xhr, status, error) {
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
      }
    });
  });
});