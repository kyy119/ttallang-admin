const urlParams = new URLSearchParams(window.location.search);
const branchId = urlParams.get("branchId");

function sample4_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      $("#streetAdr").val(data.roadAddress);
    }
  }).open();
}

$(document).ready(function () {
  function loadBranch() {
    $.ajax({
      url: "/admin/api/branches/" + branchId,
      method: "GET",
      success: function (data) {
        $('input[name="branchName"]').val(data.branchName);
        $('#streetAdr').val(data.roadAddress);
        if (data.branchStatus === '1') {
          $('input[name="branchStatus"][value="1"]').prop('checked', true);
        } else {
          $('input[name="branchStatus"][value="0"]').prop('checked', true);
        }
      },
      error: function () {
        alert("Failed to fetch branch data.");
      }
    });
  }

  loadBranch();
  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    var branchName = $("input[name='branchName']").val().trim();
    var streetAdr = $("#streetAdr").val().trim();
    var branchStatus = $("input[name='branchStatus']:checked").val();
    // 입력값 확인
    if (!branchName || !streetAdr) {
      alert("모든 정보를 입력해주세요.");
      return;
    }

    // Ajax 요청을 통해 중복 체크 및 추가
    $.ajax({
      type: "POST",
      url: "/admin/api/branches/update",
      data: {
        branchId: branchId,
        branchName: branchName,
        streetAdr: streetAdr,
        branchStatus: branchStatus
      },
      success: function (response) {
        if (response == 'SUCCESS') {
          window.location.href = "/admin/branch/main";
        } else if (response == 'EXIST_NAME') {
          alert("이미 존재하는 대여소 이름 입니다.")
        } else if (response == 'EXIST_STREET') {
          alert("이미 존재하는 주소 입니다.")
        }
      },
      error: function (xhr, status, error) {
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
      }
    });
  });
});