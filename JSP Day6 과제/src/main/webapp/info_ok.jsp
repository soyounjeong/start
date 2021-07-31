<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/22
  Time: 12:31 오전
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="con.koreait.db.Dbconn" %>
<%@ page import="java.sql.Connection" %>

<%
    if(session.getAttribute("userid") == null){
%>
    <script>
        alert('로그인 후 이용하세요');
        location.href='login.jsp';
    </script>
<%
    }else{
        request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<jsp:setProperty name="member" property="*"/>
<jsp:setProperty name="member" property="username" param="name"/>
<%
    member.setIdx(Integer.parseInt(String.valueOf(session.getAttribute("idx"))));
    member.setUserid((String)session.getAttribute("userid"));

    if(dao.info_ok(member) == 1){
%>
    <script>
        alert('회원가입 정보가 수정되었습니다.');
        location.href='info.jsp';
    </script>
<%
     }else{
%>
    <script>
        alert('회원가입 정보가 수정 실패!');
        history.back();
    </script>
<%
     }
    }
%>