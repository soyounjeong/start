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
<%@ page import="com.koreait.member.BoardDAO" %>
<%
  if(session.getAttribute("userid") == null){
%>
    <script>
      alert('로그인 후 이용하세요');
      location.href='../login.jsp';
    </script>
<%
  }else {
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="com.koreait.member.BoardDAO"/>
<jsp:useBean id="board" class="com.koreait.member.BoardDTO"/>
<%
  board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
  if((dao.del(board) == 1)){
%>
  <script>
    alert('삭제되었습니다.');
    location.href='list.jsp';
  </script>
<%
  }else{
%>
  <script>
    alert('삭제 실패!');
    history.back();
  </script>
<%
    }
  }
%>