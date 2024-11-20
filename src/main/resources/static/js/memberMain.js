$(document).ready(function () {
  loadAllMemberList();

  // Radio button change event handler
  $('input[name="report"]').change(function () {
    if ($(this).val() === 'allMember') {
      loadAllMemberList();
    } else {
      loadNoPayMemberList();
    }
  });

  // Load all members
  function loadAllMemberList() {
    $.ajax({
      url: '/admin/api/member/allMemberList',
      method: 'GET',
      success: function (data) {
        renderTable(data, 'all');
      },
      error: function () {
        alert('Failed to load all members list');
      }
    });
  }

  // Load unpaid members
  function loadNoPayMemberList() {
    $.ajax({
      url: '/admin/api/member/noPayList',
      method: 'GET',
      success: function (data) {
        renderTable(data, 'noPay');
      },
      error: function () {
        alert('Failed to load unpaid members list');
      }
    });
  }

  // Search filter
  $('#searchButton').click(function () {
    var searchValue = $('input[name="customerName"]').val().toLowerCase();
    $('#reportTable tbody tr').each(function () {
      var listItemText = $(this).text().toLowerCase();
      $(this).toggle(listItemText.includes(searchValue));
    });
  });

  // Render table with different columns for all members and unpaid members
  function renderTable(data, type) {
    $('#reportTable thead').empty();
    $('#reportTable tbody').empty();

    if (type === 'all') {
      $('#reportTable thead').append(`
                        <tr>
                            <th>사용자 ID</th>
                            <th>사용자 이름</th>
                            <th>전화 번호</th>
                            <th>생년월일</th>
                            <th>이메일</th>
                        </tr>
                    `);
      data.forEach(function (item) {
        $('#reportTable tbody').append(
            "<tr><td>" + item.userName + "</td><td>" + item.customerName
            + "</td><td>" + formatPhoneNumber(item.customerPhone) + "</td><td>" + formatDate(item.birthday)
            + "</td><td>" + item.email + "</td></tr>");
      });
    } else {
      $('#reportTable thead').append(`
                        <tr>
                            <th>사용자 ID</th>
                            <th>사용자 이름</th>
                            <th>미납액</th>
                        </tr>
                    `);
      data.forEach(function (item) {
        $('#reportTable tbody').append(
            "<tr><td>" + item[0] + "</td><td>" + item[1] + "</td><td>" + formatCurrency(item[2]) + "원"
            + "</td></tr>");
      });
    }
  }
  function formatCurrency(amount) {
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
  function formatDate(dateString) {
    return dateString.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
  }
  function formatPhoneNumber(phoneNumber) {
    return phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
  }
});