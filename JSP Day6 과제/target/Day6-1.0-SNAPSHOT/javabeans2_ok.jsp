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
    property값을 javabeans2의 name값과 일치시켜줬더니 자동으로 들어가서 set이 자동으로 된다.
    이게 setter 메소드를 대체하는 것
    <jsp:setProperty name="student" property="id"/>
    <jsp:setProperty name="student" property="name"/>
    <jsp:setProperty name="student" property="gender"/>
    <jsp:setProperty name="student" property="age"/>
--%>

<%--이름이 필드명이 같다는 존재하여 이렇게 쓸 수 있다.--%>
<jsp:setProperty name="student" property="*"/>
<%--
    파라미터는 mem_age로 넘어왔지만, 난 age에다가 셋팅할꺼야
    name값이 어쩔수없이 틀린경우는 파라미터로 맞출수 있다.
--%>
<jsp:setProperty name="age" property="age" param="mem_age"/>
<html>
<head>
    <title>자바빈즈 테스트 -2</title>
</head>
<body>
    // 이게 getter 메소드 대체
    <p>번호 : <jsp:getProperty name="student" property="id"/></p>
    <p>이름 : <jsp:getProperty name="student" property="name"/></p>
    <p>성별 : <jsp:getProperty name="student" property="gender"/></p>
    <p>나이 : <jsp:getProperty name="student" property="age"/></p>
</body>
</html>
