<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/25
  Time: 12:03 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty name="board" property="*"/>
<%
    board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));


    if(dao.B_edit(board) != null) {

%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글수정</title>
</head>
<body>
<h2>글수정</h2>
<form method="post" action="edit_ok.jsp" enctype="multipart/form-data">
    <input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
    <p>작성자 : <%=session.getAttribute("userid")%></p>
    <p><label>제목 : <input type="text" name="b_title" value="<%=board.getTitle()%>"></label></p>
    <p>내용</p>
    <p><textarea rows="5" cols="40" name="b_content"><%=board.getContent()%></textarea></p>
    <%
        if(board.getFile() != null && !board.getFile().equals("")){
            out.println("첨부파일");
            out.println("<img src='../upload/"+board.getFile()+"' alt='첨부파일' width='150'>");
        }
    %>
    <p><input type="file" name="b_file"></p>
    <p><input type="submit" value="수정">
        <input type="reset" value="다시작성">
        <input type="button" value="돌아가기" onclick="history.back()"></p>
</form>
</body>
</html>
<%
        }
    }
%>