<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  header {
    background-color: #f5f5f5;
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .logo {
    font-size: 24px;
    font-weight: bold;
    display: flex;
    align-items: center;
  }

  .logo i {
    margin-right: 8px;
  }

  nav {
    display: flex;
    gap: 20px;
  }

  nav a {
    text-decoration: none;
    color: #333;
    font-size: 16px;
  }
</style>
<header>
  <div class="logo">
    <img src="/images/ttallangImg.png" alt="Location Icon" width="150" height="70">
  </div>
  <nav>
    <a href="/admin/branch/main">대여소 관리</a>
    <a href="/admin/bicycle/main">자전거 관리</a>
    <a href="/admin/payment/main">결제 내역 조회</a>
    <a href="/admin/member/main">회원 관리</a>
  </nav>
</header>
