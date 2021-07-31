<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/26
  Time: 1:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="con.koreait.db.Dbconn" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%
  request.setCharacterEncoding("UTF-8");
  if(session.getAttribute("userid") == null){
%>
  <script>
    alert('로그인 후 이용하세요');
    location.href='../login.jsp';
  </script>
<%
    }else {
        String b_idx = request.getParameter("b_idx");
        String re_content = request.getParameter("re_content");

        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "";


        try {
            conn = Dbconn.getConnection();
            if (conn != null) {
                sql = "insert into tb_reply(re_userid, re_content, re_boardidx) values (?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, (String) session.getAttribute("userid") );
                pstmt.setString(2, re_content );
                pstmt.setString(3, b_idx);
                pstmt.executeUpdate(); // 실행 구문
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
%>
    <script>
        alert('등록되었습니다.');
        location.href='view.jsp?b_idx=<%=b_idx%>';
    </script>
<%
    }
%>

