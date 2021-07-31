<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="con.koreait.db.Dbconn" %>
<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/21
  Time: 9:17 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--화면에 보여질 화면이 아님!--%>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:setProperty name="member" property="*"/>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<%
    if((member = dao.login(member)) != null){ // null이 아니면 로그인 실행
        session.setAttribute("userid", member.getUserid());
        session.setAttribute("idx",member.getIdx());
        session.setAttribute("name", member.getUsername());
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
%>
