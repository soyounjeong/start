<%@ page import="com.koreait.reply.replyDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/23
  Time: 1:29 오후
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
    board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));

    if(dao.view(board) != null){
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
            <title> 글 보기</title>
                <script>
                    function like(){
                        const xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function (){
                            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
                                document.getElementById('like').innerHTML = xhr.responseText;
                            }
                        }
                        xhr.open('GET', 'like_ok.jsp?b_idx=<%=board.getIdx()%>',true);
                        xhr.send();
                    }
                </script>
    </head>
    <body>
        <h2>글 보기</h2>
            <table border="1" width="800">
                <tr>
                    <td>제목</td><td><%=board.getTitle()%></td>
                </tr>
                <tr>
                    <td>날짜</td><td><%=board.getRegdate()%></td>
                </tr>
                <tr>
                    <td>작성자</td><td><%=board.getUserid()%></td>
                </tr>
                <tr>
                    <td>조회수</td><td><%=board.getHit()%></td>
                </tr>
                <tr>
                    <td>좋아요</td><td><span id="like"> <%=board.getLike()%></span></td>
                </tr>
                <tr>
                    <td>내용</td><td><%=board.getContent()%></td>
                </tr>
                <tr>
                    <td>파일</td>
                    <td>
                        <%
                            if(board.getFile() != null && !board.getFile().equals("")){
                                out.println("첨부파일");
                                out.println("<img src='../upload/" + board.getFile()+"' alt='첨부파일' width='150'>");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                    <input type="button" value="수정" onclick="location.href='./edit.jsp?b_idx=<%=board.getIdx()%>'">
                    <input type="button" value="삭제" onclick="location.href='./delete.jsp?b_idx=<%=board.getIdx()%>'">
                    <input type="button" value="좋아요" onclick="like()">
                    <input type="button" value="리스트" onclick="location.href='./list.jsp'">
                    </td>
                </tr>
            </table>
            <hr/>
            <form method="post" action="reply_ok.jsp">
                <input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
                <p><%=session.getAttribute("userid")%> : <input type="text" size="40" name="re_content">
                    <input type="submit" value="확인"> </p>
            </form>
            <hr/>
            <jsp:useBean id="reply" class="com.koreait.reply.replyDTO"/>
            <jsp:useBean id="dao2" class="com.koreait.reply.replyDAO"/>
            <%
                int idx = Integer.parseInt(String.valueOf(board.getIdx()));
                List<replyDTO> list = dao2.reply_aa(idx);

                for(replyDTO reply_ok : list){
            %>

            <p><%=reply_ok.getUserid()%> : <%=reply_ok.getContent()%> (<%=reply_ok.getRegdate()%>)</p>
    </body>
</html>
<%
        }
    }
%>