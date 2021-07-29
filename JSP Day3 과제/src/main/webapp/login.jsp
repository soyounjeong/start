<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/21
  Time: 9:15 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userid = null;
    if(session.getAttribute("userid") != null){
        userid = (String)session.getAttribute("userid"); // session변수를 읽어올 수 있음
        // => session 객체 타입으로 되서(string)으로 형 변환해야 함
    }
%>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <%
        if(userid == null) {
    %>
    <%--session이 없으면 아래 페이지를 보여준다.--%>
    <form method="post" action="login_ok.jsp">
        <p><label> 아이디 : <input type="text" name="userid"></label></p>
        <p><label> 비밀번호 : <input type="password" name="userpw"></label></p>
        <p><input type="submit" value="로그인"></p>
    </form>
    <%
        }else{
    %>
    <%--session이 있으면 아래 페이지를 보여준다.--%>
    <h3><%=userid%>님 환영합니다.</h3>
    <p>
        <input type="button" value="정보수정" onclick="location.href='info.jsp'">
        <input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
    </p>
    <%
        }
    %>
</body>
</html>
