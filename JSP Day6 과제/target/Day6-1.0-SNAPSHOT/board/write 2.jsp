<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 10:47 오전
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
%>
<html>
<head>
    <title>글쓰기</title>
</head>
<body>
    <h2>글쓰기</h2>
    <%-- enctype="multipart/form-data" : 텍스트 등 다양한 파일을 보낼 수 있음--%>
    <form method="post" action="write_ok.jsp" enctype="multipart/form-data">
        <p>작성자 : <%=session.getAttribute("userid")%></p>
        <p><label> 제목 : <input type="text" name="b_title"></label></p>
        <p>내용</p>
        <p><textarea rows="5" cols="40" name="b_content"></textarea> </p>
        <%-- 파일을 하면 전송방식이 달라짐 --%>
        <p>파일 : <input type="file" name="b_file"></p>
        <p><input type="submit" value="등록"> <input type="reset" value="다시 작성">
            <input type="button" value="리스트" onclick="location.href='list.jsp'">
        </p>
    </form>
</body>
</html>
<%
    }
%>