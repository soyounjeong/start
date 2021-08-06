<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.koreait.board.BoardDTO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
    int totalCount = 0;
    int pagePerCount = 10;	// 페이지당 글개수
    int start = 0;	// 시작 글번호
    int replycnt_str = 0;

    Date from = new Date();
    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
    String to = fm.format(from);	// 2021-07-27
//	System.out.println(to);



    // http://localhost:9090/Day5/board/list.jsp?pageNum=3
    String pageNum = request.getParameter("pageNum");
    if(pageNum != null && !pageNum.equals("")){
        start = (Integer.parseInt(pageNum)-1) * pagePerCount;	// 2 * 10
    }else{
        pageNum = "1";
        start=0;
    }


%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리스트</title>
</head>
<body>
<h2>리스트</h2>
<p>게시글 : <%=dao.totalCount(board)%>개</p>

<table border="1" width="800">
    <tr>
        <th width="50">번호</th>
        <th width="300">제목</th>
        <th width="100">글쓴이</th>
        <th width="75">조회수</th>
        <th width="200">날짜</th>
        <th width="75">좋아요</th>
    </tr>
    <%
        List<BoardDTO> boardDTOS = dao.list(start, pagePerCount);
        for(BoardDTO list : boardDTOS){
            int idx = list.getIdx();
            String newDateStr = "";
            if(to.equals(list.getRegdate().substring(0,10))){ // 날짜가 초까지나오는데 20201011
                newDateStr = "<img src='./new.png' alt='새글'>";
            }

            String fileStr = "";
            if(list.getFile() != null && !list.getFile().equals("")){
                fileStr = "<img src='./file.png' alt='파일'>";
            }

    %>
    <tr>
        <td><%=list.getIdx()%></td>
        <td><a href="./view.jsp?b_idx=<%=list.getIdx()%>"><%=list.getTitle()%></a> <%=dao.replycnt(idx)%> <%=fileStr%> <%=newDateStr%></td>
        <td><%=list.getUserid()%></td>
        <td><%=list.getHit()%></td>
        <td><%=list.getRegdate()%></td>
        <td><%=list.getLike()%></td>
    </tr>
    <%
        }

        int pageNums = 0;
        if(dao.totalCount(board) % pagePerCount == 0){
            pageNums = (dao.totalCount(board) / pagePerCount);	// 20 / 10
        }else{
            pageNums = (dao.totalCount(board) / pagePerCount) + 1;
        }

    %>
    <tr>
        <td colspan="6" align="center">
            <%
                for(int i=1; i<=pageNums; i++){
                    out.print("<a href='list.jsp?pageNum="+i+"'>["+i+"]</a> ");
                }
            %>
        </td>
    </tr>
</table>


<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'">
    <input type="button" value="메인" onclick="location.href='../login.jsp'">
</p>
</body>
</html>
