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
    var koreanRegex = /^[가-힣\s]+$/;
    if (!koreanRegex.test(branchName)) {
      alert("지점명에는 한글만 입력 가능합니다.");
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
        window.location.href = "/admin/branch/main";
      },
      error: function (xhr, status, error) {
        if (xhr.status === 400) {
          if (xhr.responseText === "EXIST_BRANCH") {
            alert("해당 지점 이름이 이미 존재합니다.");
          } else if (xhr.responseText === "EXIST_STREET") {
            alert("해당 주소가 이미 존재합니다.");
          } else {
            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
          }
        } else {
          alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
      }
    });
  });
});