<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="con.koreait.db.Dbconn"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSet rs_reply =null;
    String sql = "";
    int totalCount = 0;
    int pagePerCount = 10; // 페이지당 글 갯수
    int start = 0; // 시작 글번호

    // 데이터를 저장할 객체
    Date from = new Date(); // 현재 시간 저장 객체
    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd"); // 년,월,일 형식으로 저장
    String to = fm.format(from); // 현재 날짜 형태로 포맷팅 ex) 2021-07-27
//    System.out.println(to); // 2021-07-27

    // list.jsp?pageNum=2이면
    String pageNum = request.getParameter("pageNum");
    if(pageNum != null && !pageNum.equals("")){ // 만약 번호가 null이 아니거나 데이터가 없을경우
        start = (Integer.parseInt(pageNum)-1)*pagePerCount; // 페이지에서 1을 빼고 1*10이되니까 start가 10번
    } else{
        pageNum = "1";
        start =0;
    }

    try{
        conn = Dbconn.getConnection();
        sql = "select count(b_idx) as total from tb_board";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        if(rs.next()){
            totalCount = rs.getInt("total");
        }

        sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc limit ?,?";
        /*
            select * from tb_board order by b_idx desc limit 0,10; -- 0부터 10개를 가져와라.
            select * from tb_board order by b_idx desc limit 10,10; -- 인덱스 10번부터 110개 가져와라
            select * from tb_board order by b_idx desc limit 20,10; -- 인덱스 20번부터 110개 가져와라
         */


        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, start);
        pstmt.setInt(2, pagePerCount);
        rs = pstmt.executeQuery();
    }catch(Exception e){
        e.printStackTrace();
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
<p>게시글 : <%=totalCount%>개</p>

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
        while(rs.next()){
            String b_idx = rs.getString("b_idx");
            String b_userid = rs.getString("b_userid");
            String b_title = rs.getString("b_title");
            String b_regdate = rs.getString("b_regdate").substring(0, 10); // 날짜를 끊은 regdate
            String b_hit = rs.getString("b_hit");
            String b_like = rs.getString("b_like");
            String b_file = rs.getString("b_file");

            // 새 글 new 이미지 삽입
            String newDateStr = "";
            if(to.equals(b_regdate)){ // 2021-07-27과 10자리 끊은것과 같니?
                newDateStr = "<img src='./new1.png' alt='새글'>";
            }

            // 파일 삽입있으면 이미지 삽입
            String fileStr = "";
            if(b_file != null && !b_file.equals("")){
                fileStr = "<img src='./file.png' alt='파일' width=25 height=25>";
            }


            sql = "select count(re_idx) as replycnt from tb_reply where re_boardidx=?"; // 글번호가 몇번인거
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b_idx);
            rs_reply = pstmt.executeQuery();

            int replycnt = 0;
            String replycnt_str="";
            if(rs_reply.next()) {
                replycnt = rs_reply.getInt("replycnt");
                if(replycnt > 0){
                    replycnt_str = "["+replycnt+"]";
                }
            }
    %>


    <tr>
        <td><%=b_idx%></td>
        <td><a href="./view.jsp?b_idx=<%=b_idx%>"><%=b_title%></a> <%=replycnt_str%> <%=fileStr%><%=newDateStr%></td>
        <td><%=b_userid%></td>
        <td><%=b_hit%></td>
        <td><%=b_regdate%></td>
        <td><%=b_like%></td>
    </tr>

    <%
        }
        int pageNums = 0; // 번호가 몇개 증가될 것인지
        if(totalCount % pagePerCount == 0){
            pageNums = (totalCount / pagePerCount); // 20 / 10
        }else{
            pageNums = (totalCount / pagePerCount) + 1; // 자투리가 있으면 페이지 +1
        }
    %>
    <tr>
        <td colspan="6" align="center">
            <%
                for(int i =1; i<=pageNums; i++){
                    out.print("<a href='list.jsp?pageNum="+i+"'>["+i+"]</a> ");
                }
            %>
        </td>
    </tr>
</table>


<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'">
    <input type="button" value="메인" onclick="location.href='../login.jsp'"></p>
</body>
</html>