<html>
<head>
    <meta charset="utf-8">
    <title>登陆页面</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="loginForm" role="form">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">用户</label>
                    <div class="col-sm-10">
                        <input type="username" class="form-control" id="username" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" />Remember me</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    /*$(function(){
        $("#loginForm").validate({
            rules: {
                "username": {
                    required: true
                },
                "password":{
                    required:true
                }
            },
            messages:{
                "username":{
                    required:"用户名不能为空"
                },
                "password":{
                    required:"密码不能为空"
                }
            },
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                            $.messager.confirm("提示信息","登录成功",function(){
                                window.location.href="/personal";
                            })
                        }else{
                            $.messager.popup(data.msg);
                        } }
                });
            },
            //自定义错误样式
            errorClass:"text-danger",
            //未通过验证,进行高亮处理或其他处理；
            highlight:function(input){
                $(input).closest(".form-group").addClass("has-error");
            },
            //通过验证,清除高亮效果或其他处理；
            unhighlight:function(input){
                $(input).closest(".form-group").removeClass("has-error");
            }
        });
    });
*/
</script>

</body>
</html>

