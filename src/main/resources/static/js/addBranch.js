function sample4_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      $("#streetAdr").val(data.roadAddress);
    }
  }).open();
}

$(document).ready(function () {
  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    var branchName = $("input[name='branchName']").val().trim();
    var streetAdr = $("#streetAdr").val().trim();

    // 입력값 확인
    if (!branchName || !streetAdr) {
      alert("모든 정보를 입력해주세요.");
      return;
    }

    // Ajax 요청을 통해 중복 체크 및 추가
    $.ajax({
      type: "POST",
      url: "/admin/api/branches/add",
      data: {
        branchName: branchName,
        streetAdr: streetAdr
      },
      success: function (response) {
        if (response === "EXIST_BRANCH") {
          alert("이미 대여소 이름이 존재합니다.");
        } else if (response === "EXIST_ADDRESS") {
          alert("이미 도로명이 존재합니다.");
        } else if (response === "SUCCESS") {
          window.location.href = "/admin/branch/main";
        }
      },
      error: function (xhr, status, error) {
        alert(xhr);
        alert(status);
        alert(error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
      }
    });
  });
});