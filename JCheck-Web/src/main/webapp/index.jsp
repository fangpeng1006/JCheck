<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<script type="text/javascript">
    function changeImage(img) {
        img.src=img.src+'?'+new Date().getTime()
    }
</script>
<h2>Hello World!</h2>
用户名：<input/><br/>
密码：<input/><br/>
验证码：<input/><img src="/JCheck_Web_war/image" onclick="changeImage(this)">
</body>
</html>
