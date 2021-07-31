<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 2:34 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  }else {
    
%>
<jsp:useBean id="dao" class="com.koreait.member.BoardDAO"/>
<jsp:useBean id="board" class="com.koreait.member.BoardDTO"/>
<jsp:setProperty name="board" property="*"/>
<%
  board.setIdx(Integer.parseInt(String.valueOf(session.getAttribute("idx"))));
  board.setUserid((String)session.getAttribute("userid"));
  if(dao.del(board) == 1){
%>
  <script>
    alert('삭제되었습니다.');
    location.href='list.jsp';
  </script>
<%
    }
  }
%>