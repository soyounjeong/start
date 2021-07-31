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
<%
    request.setCharacterEncoding("UTF-8");
    if (session.getAttribute("userid") == null) {
%>
    <script>
        alert('로그인 후 이용하세요');
        location.href = '../login.jsp';
    </script>
<%
} else {
%>
<jsp:useBean id="board" class="com.koreait.member.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.BoardDAO"/>
<jsp:setProperty property="title" param="b_title" name="board"/>
<jsp:setProperty property="content" param="b_content" name="board"/>
<jsp:setProperty property="file" param="b_file" name="board"/>
<%
    board.setUserid((String)session.getAttribute("userid"));
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
        history.back();
    </script>
<%
        }
    }
%>