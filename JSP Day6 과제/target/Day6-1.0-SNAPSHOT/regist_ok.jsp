<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>

<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/22
  Time: 12:31 오전
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%--member만들고 받아가지고 오기--%>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:setProperty name="member" property="*"/>
<%--대부분은 다 DTO랑 맞췄는데 username과는 맞추지 못해 아래 코드 추가--%>
<jsp:setProperty name="member" property="username" param="name"/>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<%
    if(dao.join(member)== 1){ // 만약 회원가입이 완료 되면
%>
    <script>
        alert('회원가입 완료되었습니다.');
        location.href='login.jsp';
    </script>
<%
    }else{
%>
    <script>
        alert('회원가입 실패되었습니다.');
        history.back();
    </script>
<%
    }
%>
