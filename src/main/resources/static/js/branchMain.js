$(document).ready(function() {
function loadBranches(filterStatus = null) {
  $.ajax({
    url: "/admin/api/branches",
    method: "GET",
    success: function(data) {
      var tableBody = $("#branchTableBody");
      tableBody.empty();
      data.forEach(function(branch) {
        var tableBody = $("#branchTableBody");
        tableBody.empty();
        data.forEach(function(branch) {
          if (filterStatus === null || branch.branchStatus === filterStatus) {
            let statusText = branch.branchStatus === '1' ? '(활성화)' : '(비활성화)';
            tableBody.append("<tr><td><a href='/admin/branch/map?branchId=" + branch.branchId + "'>" + branch.branchName + statusText + "</a></td><td>" + branch.bicycleCount + "</td><td><a href='/admin/branch/edit?branchId=" + branch.branchId + "'>수정하기</a></td></tr>");
          }
        });

      });
    },
    error: function() {
      alert("Failed to fetch branch data.");
    }
  });
}

// Initial data load
loadBranches();

// Search functionality
$("#searchButton").click(function() {
var searchTerm = $("#searchInput").val().toLowerCase();
$("#branchTableBody tr").filter(function() {
$(this).toggle($(this).text().toLowerCase().indexOf(searchTerm) > -1);
});
});
$("input[name='branchStatus']").change(function() {
let selectedStatus = $(this).val();
loadBranches(selectedStatus);
});
});