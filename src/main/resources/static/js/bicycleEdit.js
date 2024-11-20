const urlParams = new URLSearchParams(window.location.search);
const bicycleId = urlParams.get("bicycleId");
let bicycleLatitude, bicycleLongitude;

$(document).ready(function () {

  function loadBranch() {
    $.ajax({
      url: "/admin/api/allBranch",
      method: "GET",
      success: function (branches) {
        const branchSelect = $('<select id="branchSelect"></select>');

        // Iterate over each branch to create an option element
        branches.forEach(branch => {
          const option = $('<option></option>')
          .val(branch.branchId)
          .text(branch.branchName);

          // Check if branch coordinates match bicycle coordinates
          if (branch.latitude === bicycleLatitude && branch.longitude
              === bicycleLongitude) {
            option.prop("selected", true);  // Set the option as selected if coordinates match
          }

          branchSelect.append(option);
        });

        $("#branchSelectContainer").append(branchSelect);
      },
      error: function () {
        alert("Failed to fetch branch data.");
      }
    });
  }

  // Load bicycle details and set up initial form state
  function loadBicycle() {
    $.ajax({
      url: "/admin/api/bicycle/" + bicycleId,
      method: "GET",
      success: function (data) {
        $('input[name="bicycleName"]').val(data.bicycleName);
        bicycleLatitude = data.latitude;
        bicycleLongitude = data.longitude;

        if (data.bicycleStatus === '1') {
          $('input[name="bicycleStatus"][value="1"]').prop('checked', true);
        } else {
          $('input[name="bicycleStatus"][value="0"]').prop('checked', true);
        }

        loadBranch();  // Load branches after we get the bicycle data
      },
      error: function () {
        alert("Failed to fetch bicycle data.");
      }
    });
  }

  loadBicycle();

  // Handle form submission
  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    const bicycleName = $("input[name='bicycleName']").val().trim();
    const bicycleStatus = $("input[name='bicycleStatus']:checked").val();
    const selectedBranchId = $("#branchSelect").val();

    if (!bicycleName) {
      alert("모든 정보를 입력해주세요.");
      return;
    }

    $.ajax({
      type: "POST",
      url: "/admin/api/bicycle/update",
      data: {
        bicycleId: bicycleId,
        bicycleName: bicycleName,
        bicycleStatus: bicycleStatus,
        branchId: selectedBranchId
      },
      success: function (response) {
        window.location.href = "/admin/bicycle/main";
      },
      error: function (xhr, status, error) {
        if (xhr.status === 400) {
          if (xhr.responseText === "EXIST_NAME") {
            alert("해당 자전거 이름이 이미 존재합니다.");
          } else if(xhr.responseText == "RENTING"){
            alert("대여중인 자전거는 수정 불가합니다.");
          } else if(xhr.responseText == "REPORT"){
            alert("신고된 자전거 입니다. 신고 페이지를 가서 수정을 해주세요.")
          }
        } else{
          alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
      }
    });
  });
  $("#return").on('click', function () {
    window.location.href = "/admin/bicycle/main";
  });
});