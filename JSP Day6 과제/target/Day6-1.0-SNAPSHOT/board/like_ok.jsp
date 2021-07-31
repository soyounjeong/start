<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="con.koreait.db.Dbconn"%>
<%
    if(session.getAttribute("userid") == null){
%>
<script>
    alert('로그인 후 이용하세요');
    location.href='../login.jsp';
</script>
<%
    }else{
%>
<jsp:useBean id="board" class="com.koreait.member.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.BoardDAO"/>
<jsp:setProperty name="board" property="*"/>
<%
    board.setIdx(Integer.parseInt(String.valueOf(session.getAttribute("idx"))));

    if(!((dao.like(board)) == null)){

%>
    <script>
        alert('등록되었습니다.');
        location.href='list.jsp';
    </script>
<%
        }
    }
%>