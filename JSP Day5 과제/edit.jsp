<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="con.koreait.db.Dbconn" %>
<%@ page import="java.sql.Connection" %>
<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/25
  Time: 12:03 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("userid") == null){
%>
    <script>
        alert('로그인 후 이용하세요');
        location.href='../login.jsp';
    </script>
<%
}else{
    String b_idx = request.getParameter("b_idx");

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";

    String b_title = "";
    String b_content = "";

    try {
        conn = Dbconn.getConnection();
        if(conn != null){
            sql = "SELECT b_title, b_content FROM tb_board WHERE b_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            rs = pstmt.executeQuery();
            if(rs.next()){
                b_title = rs.getString("b_title");
                b_content = rs.getString("b_content");
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
    <h2>글 수정하기</h2>
    <form method="post" action="edit_ok.jsp">
        <%-- "edit_ok.jsp?b_idx=<%=b_idx%>"이렇게 할때는 get 방식--%>
        <%--post로 넘기면 이렇게 하는 것이 좋다 hidden으로 전송 --%>
        <input type="hidden" name="b_idx" value="<%=b_idx%>">
        <p>작성자 : <%=session.getAttribute("userid")%></p>
        <p><label> 제목 : <input type="text" name="b_title" value="<%=b_title%>"></label></p>
        <p>내용</p>
        <p><textarea rows="5" cols="40" name="b_content"><%=b_content%></textarea> </p>
        <p><input type="submit" value="수정">
            <input type="reset" value="다시 작성">
            <input type="button" value="돌아가기" onclick="history.back()">
        </p>
    </form>
</body>
</html>
<%
    }
%>