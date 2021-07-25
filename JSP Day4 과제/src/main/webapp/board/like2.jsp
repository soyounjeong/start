<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 11:16 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="con.koreait.db.Dbconn" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%
    request.setCharacterEncoding("UTF-8");

    String b_idx = request.getParameter("b_idx");


    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";
    int like = 0;

    try{
        conn = Dbconn.getConnection();
        if(conn != null){
            sql = "update tb_board set b_like = b_like+1 where b_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            pstmt.executeUpdate();

            sql = "select b_like from tb_board where b_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            rs = pstmt.executeQuery();

            if(rs.next()){
                like = rs.getInt("b_like");
                out.println(like);
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>