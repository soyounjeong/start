<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 11:16 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%
    request.setCharacterEncoding("UTF-8");
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
<%--<jsp:setProperty name="board" property="*"/>--%>
<%
    int size = 1024*1024*10;
    String uploadPath = request.getRealPath("upload");
    MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());

    board.setIdx(Integer.parseInt(String.valueOf(multi.getParameter("b_idx"))));
    board.setTitle(multi.getParameter("b_title"));
    board.setContent(multi.getParameter("b_content"));
    board.setFile(multi.getFilesystemName("b_file"));
    if(dao.B_edit_ok(board) != 0){
%>
    <script>
        alert('수정되었습니다.');
        location.href='./view.jsp?b_idx=<%=board.getIdx()%>';
    </script>

<%
    }else{
%>
<script>
    alert('수정 실패');
    history.back();
</script>
<%
        }
    }
%>