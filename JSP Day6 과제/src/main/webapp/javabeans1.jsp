<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/27
  Time: 1:37 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.koreait.member.MemberDTO" %>
<%
  // 자바 문법 이용한 객체 생성
  MemberDTO memberDTO = new MemberDTO();
  memberDTO.setUserid("apple");
  memberDTO.setUserpw("1234");
  memberDTO.setUsername("김사과");
%>
// 방법1) 액션 태그 이용
// id : 객체명 , class: 클래스명 , scope : 현재 범위(페이지)
<jsp:useBean id="member" class="com.koreait.member.MemberDTO" scope="page"/>

// 방법2) get&set
// 아이디가 name이어야 함(동일)
<jsp:setProperty name="member"  property="userid" value="banana"/>
<jsp:setProperty name="member"  property="userpw" value="1111"/>
<jsp:setProperty name="member"  property="username" value="반하나"/>
<html>
<head>
    <title>자바빈즈 테스트 - 1</title>
</head>
<body>
  <h2>자바빈즈 테스트 -1 </h2>
  <hr/>
  // 방법1) 액션 태그 이용
  <p>memberDTO의 아이디 : <%=memberDTO.getUserid()%></p>
  <p>memberDTO의 비밀번호 : <%=memberDTO.getUserpw()%></p>
  <p>memberDTO의 이름 : <%=memberDTO.getUsername()%></p>
  <hr/>
  // 방법2) get&set
  // getProperty는 value가 없어야 한다.
  <p><jsp:getProperty name="member"  property="userid"/></p>
  <p><jsp:getProperty name="member"  property="userpw" /></p>
  <p><jsp:getProperty name="member"  property="username"/></p>

</body>
</html>
