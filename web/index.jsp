<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 2018/7/14
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="./layui/layui.js"></script>
    <style>
      #jindu{
        position: relative;
        transition: 1s;
        width: 0px;
        height: 10px;
        background-color: #000000;
        left: 0px;
      }
    </style>
  </head>
  <body>
  <input id="uploadFile" type="file">
  <button onclick="uploadFiles()">submit</button>
  <div id="jindu"></div>
  </body>
</html>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
  var jindu = document.getElementById("jindu");


  setTimeout(function () {
      jindu.style.width = "100px";
  },5000);
    //上传文件
    function uploadFiles(){
        jindu.style.width = "0px";
        jindu.style.left = "100px";

        var formData = new FormData();
        formData.append("file",$("#uploadFile")[0].files[0]);//append()里面的第一个参数file对应permission/upload里面的参数file

        $.ajax({
            type:"post",
            async:true,  //这里要设置异步上传，才能成功调用myXhr.upload.addEventListener('progress',function(e){}),progress的回掉函数
            Accept:'text/html;charset=UTF-8',
            data:formData,
            contentType:"multipart/form-data",
            url: "/upload",
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            xhr:function(){
                myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload){ // check if upload property exists
                    myXhr.upload.addEventListener('progress',function(e){
                        var loaded = e.loaded;                  //已经上传大小情况
                        var total = e.total;                      //附件总大小
                        var percent = Math.floor(100*loaded/total)+"%"; //已经上传的百分比
                        if (loaded == total){
                            console.log("上传成功！");


                        }
                        console.log("已经上传了："+percent);
                        $("#processBar").css("width",percent);
                    }, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            success:function(data){
                console.log("上传成功!!!!");
            },
            error:function(){
                alert("上传失败！");
            }
        });
    }
</script>
