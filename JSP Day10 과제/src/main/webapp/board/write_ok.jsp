<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %><%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 11:16 오전
  To change this template use File | Settings | File Templates.
--%>
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
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
    board.setUserid((String)session.getAttribute("userid"));

    String uploadPath = request.getRealPath("upload");
    System.out.println(uploadPath);
    int size = 1024*1024*10;
    MultipartRequest multi = new MultipartRequest(request, uploadPath, size , "UTF-8", new DefaultFileRenamePolicy());

    board.setTitle(multi.getParameter("b_title"));
    board.setContent(multi.getParameter("b_content"));
    board.setFile(multi.getFilesystemName("b_file"));

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