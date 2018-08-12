<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 2018/7/24
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .d{
            width: 10px;
            height: 10px;
            border-radius: 50%;
            background-color: #00F7DE;
        }
    </style>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="./layui/layui.js"></script>
    <title>Title</title>
</head>
<body>
<%--layui-anim layui-anim-rotate layui-anim-loop--%>
<div class="" style="width: 100px;height: 100px;background-color: greenyellow" id="onloadBox"></div>
    <input type="file" id="file">
    <button onclick="submitFile()">提交</button>
</body>
</html>
<script>
    var file = document.getElementById("file");
    var onloadBox = document.getElementById("onloadBox");
    var xhr = new XMLHttpRequest();
    xhr.setRequestHeader("Content-Type","multipart/form-data;");
    var fileSize = 0;
    var unit;
    file.onchange = function (ev) {
        if (file.files[0].size > (1024*1024))
        {
            console.log(file.files[0].size/(1024*1024) + "mb");
            fileSize = file.files[0].size/(1024*1024);
            unit = "mb";
        }

        else {
            console.log(file.files[0].size/(1024) + "kb");
            fileSize = file.files[0].size/(1024);
            unit = "kb";
        }
    };
    function submitFile() {
        var from = new FormData();
        from.append("file",file.files[0]);
        xhr.upload.addEventListener("progress",drawFream,false);
        xhr.open("post","/upload");
        xhr.send(from)
    }
    function drawFream(e) {
        // var div =
        var div = document.createElement("div");
        div.setAttribute("class","d");
        onloadBox.appendChild(div);
        var ratio = e.loaded * 100 / e.total;
        onloadBox.innerText = onloadBox.innerText + ratio + "%";
    }
</script>
