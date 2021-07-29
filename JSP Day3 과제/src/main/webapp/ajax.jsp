<%--
  Created by IntelliJ IDEA.
  User: soyounjeong
  Date: 2021/07/22
  Time: 7:00 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax</title>
</head>
<body>
  <h2>Ajax</h2>
  <button onclick="sendRequest()">요청 보내기 </button>
  <p id="text"></p>
  <script>
    'use strict';
    function sendRequest(){
      const xhr = new XMLHttpRequest();
      // 비동기 : 먼저 급한거 먼저 처리
      xhr.open('GET', 'ajax_ok.jsp?userid=apple&userpw=1111', true); // 비동기로 데이터 전송
      xhr.send();

      // XMLHttpRequest.DONE : 4(완료), xhr.status : 200(정상적인 접근)
      xhr.onreadystatechange = function (){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
          document.getElementById('text').innerHTML = xhr.responseText;
        }
      }
    }
  </script>
</body>
</html>
