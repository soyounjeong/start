<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/21
  Time: 9:24 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate(); // session을 날려버리고 아래로 행동을 취하라.
%>
<script>
    alert('로그아웃 되었습니다.');
    location.href='login.jsp';
</script>
