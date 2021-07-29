<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="con.koreait.db.Dbconn" %><%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/21
  Time: 9:17 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--화면에 보여질 화면이 아님!--%>
<%
    String userid = request.getParameter("userid"); // name 값과 동일해야함
    String userpw = request.getParameter("userpw");


    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String sql ="";

    try{
        conn = Dbconn.getConnection();
        if(conn != null) {
            sql = "select mem_idx, mem_name from tb_member where mem_userid=? and mem_userpw=?";
            pstmt = conn.prepareStatement(sql); // 컴파일
            pstmt.setString(1, userid);
            pstmt.setString(2, userpw);
            rs = pstmt.executeQuery();

            if(rs.next()){
                // 로그인 성공
                session.setAttribute("userid", userid); // 내가 로그인한 아이디 저장
                session.setAttribute("idx", rs.getString("mem_idx"));
                session.setAttribute("name", rs.getString("mem_name"));
%>
            <script>
                alert('로그인 되었습니다.');
                location.href='login.jsp'; // 새로 고침 -> 캐시를 안남김(새로 페이지 로드)
            </script>
<%
            } else{
                // 로그인 실패
%>
            <script>
                alert('아이디 또는 비밀번호를 확인하세요');
                history.back(); // 히스토리 -> 캐시를 남김
            </script>
<%
            }
        }
    }catch(Exception e){
        e.printStackTrace();
}
%>
