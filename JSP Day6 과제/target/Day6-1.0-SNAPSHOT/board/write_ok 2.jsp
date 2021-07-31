<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 11:16 오전
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.*"%>
<%@ page import="con.koreait.db.Dbconn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--파일 업로드를 하게 되면 Day5에 했던 것과 다르게 코드가 많이 달라진다. Day5와 비교 --%>
<%
    if (session.getAttribute("userid") == null) {
%>
    <script>
        alert('로그인 후 이용하세요');
        location.href = '../login.jsp';
    </script>
<%
} else {
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="board" class="com.koreait.member.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.BoardDAO"/>
<jsp:setProperty name="board" property="*"/>
<%
    if(dao.write_ok(board) == 1){
%>
    <script>
        alert('등록되었습니다.');
        location.href='list.jsp';
    </script>
<%
    }else{
%>
    <script>
        alert('등록 실패되었습니다.');
        location.href='list.jsp';
    </script>
<%
        }
    }
%>