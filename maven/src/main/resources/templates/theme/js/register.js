 $(function () {
        var verificationcode;
        var emailregular = /^([a-zA-Z0-9]+)@([0-9a-zA-Z]+).([0-9a-zA-Z]{2,6})$/; //邮箱
        var modile=/^1[345789]\d{9}$/; //手机号
        $("input[type=submit]").css("display","none");
        var starttime;

     /**
      * 判断用户名是否以存在
      */
     $("#username").blur(function () {
            var username=$(this).val();
            $.post("selbyusername",{username:username},function (data) {
                if(data==0) {
                    layer.msg('用户名已被占用',{icon:2,time:1000});
                    $("#username").val("");
                    $("#username").focus();
                }
            });
        });

     var i=0;
     $("#isemails").click(function (){
            if(i==1)
            {
                $("#emailxx").html("邮箱号：");
                $("#emailyzms").html("邮箱验证码：");
                $("#emailyzm").html("获取邮箱验证码");
                $(this).html("使用手机验证");
                $("#iphone").attr("name","u_email");
                i=i-2;
            }else{
                $("#emailxx").html("手机号：");
                $("#emailyzms").html("手机验证码：");
                $("#emailyzm").html("获取手机验证码");
                $(this).html("使用邮箱验证");
                $("#iphone").attr("name","u_phone");
                i=0;
            }
            i++;
     });

     /**
      * 获取邮箱验证码
      */

     $("#emailyzm").click(function () {
            clearInterval(starttime);
            verificationcode="";
            var email=$("#iphone").val(); //获取到的邮箱或者电话号码
            var isphone=$("#emailxx").html();
            if(isphone=="邮箱号：")
            {
                if(!(emailregular.test(email))){
                    layer.msg('邮箱格式错误',{icon:2,time:1000});
                }else{
                    fn();
                    $.post("emailyzm",{email:email},function f(data) {
                        if (data.indexOf('n')==0)
                        {
                            layer.msg('邮箱不存在',{icon:2,time:1000});
                        }else{
                            verificationcode=data;
                        }
                    });
                }
            }else{
                if(!(modile.test(email)))
                {
                    layer.msg('手机号格式错误',{icon:2,time:1000});
                }else{
                    fn();
                    $.post("phoneyzm",{phone:email},function (data) {
                        if (data.indexOf('n')==0)
                        {
                            layer.msg('手机号不存在',{icon:2,time:1000});
                        }else{
                            verificationcode=data;
                        }
                    })
                }
            }
     });

     function fn()
     {
         layer.msg('验证码已发送',{icon:1,time:1000});
         var s=100;
         $('#time i').css("display","inline-block");
         starttime=setInterval(function(){
             $('#time i').html(s);
             s--;
             if(s<0){
                 verificationcode="";
                 clearInterval(starttime);
                 $('#time i').css("display","none");
                 return;
             }
         },1000);
     }

     /**
      * 注册
      */
     $("#regist").click(function () {
            var usernameregular=/^[a-zA-Z]{4,20}$/; //用户名
            var username=$("input[name=u_name]").val(); //用户名值
            var pwd=$("input[name=u_pwd]").val(); //密码值
            var rpwd=$("input[name=rpassword]").val(); //确认密码
            var email=$("#iphone").val(); //邮箱
            var isphone=$("#emailxx").html(); //判断是邮箱还是手机号
            var  verification=$("input[name=verificationcode]").val(); //验证码
            if(username==""){
                layer.msg('用户名不能为空',{icon:2,time:1000});
                $("input[name=u_name]").focus();
            }else if(!(usernameregular.test(username.value))){
                layer.msg('用户名为4-20位英文字母组成的字符串',{icon:2,time:1000});
                $("input[name=u_name]").focus();
            }else if(pwd==""){
                layer.msg('密码不能为空',{icon:2,time:1000});
                $("input[name=u_pwd]").focus();
            }else if(pwd!=rpwd){
                layer.msg('两次密码不一致',{icon:2,time:1000});
                $("input[name=rpassword]").focus();
            }else if($("#iphone").attr("name")=="u_email"&&email==""){
                layer.msg('邮箱不能为空',{icon:2,time:1000});
                $("input[name=u_email]").focus();
            }else if($("#iphone").attr("name")=="u_phone"&&email==""){
                layer.msg('手机号不能为空',{icon:2,time:1000});
                $("input[name=u_email]").focus();
            }
            else if(verification=="") {
                layer.msg('验证码不能为空',{icon:2,time:1000});
                $("input[name=verificationcode]").focus();
            }else if(verificationcode!=verification){
                layer.msg('验证码错误',{icon:2,time:1000});
                $("input[name=verificationcode]").focus();
            }else {
                    if(isphone=="邮箱号："){
                        if(!(emailregular.test(email))){
                            layer.msg('邮箱格式错误',{icon:2,time:1000});
                            $("input[name=u_email]").focus();
                            return false;
                            }
                        }else{
                        if(!(modile.test(email))){
                            layer.msg('手机号码格式错误',{icon:2,time:1000});
                            $("input[name=u_phone]").focus();
                            return false;
                            }
                        }
                    $("input[type=submit]").click();
            }
        });
    });