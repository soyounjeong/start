<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/22
  Time: 8:29 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userid = request.getParameter("userid");

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "";
    String url = "jdbc:mysql://localhost:3306/jcp";
    String uid = "root";
    String upw = "16100036";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, uid, upw);
        if (conn != null) { // conn이 null이 아니면
            sql = "select mem_idx from tb_member where mem_userid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                out.println("no");
            } else {
                out.println("ok");
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>