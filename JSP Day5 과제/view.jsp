<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 1:29 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="con.koreait.db.Dbconn"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%
    String b_idx = request.getParameter("b_idx");

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";

    String b_userid = "";
    String b_title = "";
    String b_content = "";
    String b_regdate = "";
    int b_like = 0;
    int b_hit = 0;


    try {
        conn = Dbconn.getConnection();
        if (conn != null) {
            sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            pstmt.executeUpdate();

            sql = "select b_idx, b_userid, b_title, b_content, b_regdate, b_like, b_hit from tb_board where b_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                b_userid = rs.getString("b_userid");
                b_title = rs.getString("b_title");
                b_content = rs.getString("b_content");
                b_regdate = rs.getString("b_regdate");
                b_like = rs.getInt("b_like");
                b_hit = rs.getInt("b_hit");
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>글보기 페이지</title>
    <script>
        function like(){
            const xhr = new XMLHttpRequest(); // 객체 생성
            xhr.onreadystatechange = function (){ // 값이 바뀔때마다 함수 생성
                if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){ // 정상적인 호출
                    document.getElementById('like').innerHTML = xhr.responseText; // 아래 아이디가 like가 일치하면 서버에서 온걸로 데이터 변경
                }
            }
            xhr.open('GET', './like_ok.jsp?b_idx=<%=b_idx%>', true);
            xhr.send();
        }
    </script>
</head>
<body>
    <h2>글보기</h2>
    <table border="1" width="800">
        <tr>
            <td>제목</td><td><%=b_title%></td>
        </tr>
        <tr>
            <td>날짜</td><td><%=b_regdate%></td>
        </tr>
        <tr>
            <td>작성자</td><td><%=b_userid%></td>
        </tr>
        <tr>
            <td>조회수</td><td><%=b_hit%></td>
        </tr>
        <tr>
            <td>좋아요</td><td><span id="like"><%=b_like%></span></td>
        </tr>
        <tr>
            <td>내용</td><td><%=b_content%></td>
        </tr>
        <tr>
            <td colspan="2">
                <%
                    if(b_userid.equals((String)session.getAttribute("userid"))){
                %>
                <%-- get 방식으로 보내야 해서 뒤에 글 번호 순으로--%>
                <input type="button" value="수정" onclick="location.href='./edit.jsp?b_idx=<%=b_idx%>'">
                <input type="button" value="삭제" onclick="location.href='./delete.jsp?b_idx=<%=b_idx%>'">
                <%
                    }
                %>

                <input type="button" value="좋아요" onclick="like()">
                <input type="button" value="리스트" onclick="location.href='./list.jsp'">
            </td>
        </tr>
    </table>
    <hr/>
    <form method="post" action="reply_ok.jsp">
        <input type="hidden" name="b_idx" value="<%=b_idx%>">
        <p><%=session.getAttribute("userid")%> : <input type="text" size="40" name="re_content"> <input type="submit" value="확인"></p>
    </form>
    <hr/>
    <%
        sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx=? order by re_idx desc";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, b_idx);
        rs = pstmt.executeQuery(); // select를 rs에 저장

        while (rs.next()){
            int re_idx = rs.getInt("re_idx");
            String re_userid = rs.getString("re_userid");
            String re_content = rs.getString("re_content");
            String re_regdate = rs.getString("re_regdate");
    %>
        <p><%=re_userid%> : <%=re_content%> (<%=re_regdate%>)
            <%
                if(re_userid.equals((String) session.getAttribute("userid"))){
            %>
            <input type="button" value="삭제" onclick="location.href='reply_del.jsp?re_idx=<%=re_idx%>&b_idx=<%=b_idx%>'">
            <%
                }
            %>
        </p>
<%
    }
%>
</body>
</html>
