<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/27
  Time: 1:52 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="student" class="com.koreait.member.Student"/>
<%--value가 빠지면 이전에썻던 것이 자동으로 들어가서 지금 오류가 안나는 거--%>
<%--
<jsp:setProperty name="student" property="id"/>
<jsp:setProperty name="student" property="name"/>
<jsp:setProperty name="student" property="gender"/>
<jsp:setProperty name="student" property="age"/>
--%>
<jsp:setProperty name="student" property="*"/>
<html>
<head>
    <title>자바빈즈 테스트 -2</title>
</head>
<body>
<p>번호 : <jsp:getProperty name="student" property="id"/></p>
<p>이름 : <jsp:getProperty name="student" property="name"/></p>
<p>성별 : <jsp:getProperty name="student" property="gender"/></p>
<p>나이 : <jsp:getProperty name="student" property="age"/></p>
</body>
</html>
