<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/26
  Time: 1:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
%>
<jsp:useBean id="reply" class="com.koreait.reply.replyDTO"/>
<jsp:useBean id="dao2" class="com.koreait.reply.replyDAO"/>
<%
    reply.setR_idx(Integer.parseInt(String.valueOf(request.getParameter("re_idx"))));
    reply.setUserid(String.valueOf(session.getAttribute("re_userid")));
    if(dao2.reply_ok(reply) == 1){
%>
    <script>
        alert('등록되었습니다.');
        location.href='view.jsp?b_idx=<%=reply.getR_idx()%>';
    </script>
<%
        }
    }
%>

