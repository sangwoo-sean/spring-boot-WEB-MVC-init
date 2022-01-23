<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Hello world</h1>
    <h1>${test}</h1>
    <div>
        <form action="/test.do" method="post">
            <input type="text" placeholder="name" name="name">
        </form>
        <form action="/fileupload.do" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <button>제출</button>
        </form>
        <img src="${url}" alt="이미지가 없습니다.">
    </div>
</body>
</html>