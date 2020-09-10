/*
注册账户
 */

$(function () {
    var getcode =$(".get-code");
    var regl =/^\w{5,}@[QQ||qq]{2}\.[a-z]+$|\,$/;

    getcode.click(function () {
        var username =$("#qqEmail").val();

        if(regl.test(username)){
            alert("ok")
            getCode(username)
        }else{
         $(".notice-box-email").show();
         setTimeout(function () {
             $(".notice-box-email").hide();
             },3000)

        }

    });

    function getCode(username) {

        console.log(username);
        $.ajax({
            type:"GET",
            url:"getCode",
            data:{userName:username},
            dataType:"json",
            contentType:"application/json",
            success:function () {
                console.log("发送成功")
            }
        });
    }

    $(".register").click(function () {
        console.log("注册")
        var verificationCode =$("#blogVeCode").val();
        $.ajax({
            type:"GET",
            url:"judgeCode",
            data:{verifCode:verificationCode},
            contentType:"application/json",
            success:function (data) {
                alert(data+" 1")
                console.log(data)
                if (data=="验证成功"){
                    startRegister();
                }else if(data=="验证失败"){
                    $(".notice-box-vercode").show();
                    setTimeout(function () {
                        $(".notice-box-vercode").hide();
                    },3000)
                }
            },
            error:function (data) {
                alert("错误")
                console.log(data)
            }
        })
    });


    function startRegister() {
        var usname =$("#qqEmail").val();
        var paword =$("#blogPassword").val();
        var reg =/^\w{5,}@[QQ||qq]{2}\.[a-z]+$|\,$/;
        if (reg.test(usname)&&paword.length<=15&&paword.length>5){
            var dt ={
                username:usname,
                password:paword,
            };
            $.ajax({
                type: "POST",
                url: "register",
                data: JSON.stringify(dt),
                dataType: "json",
                contentType: "application/json",
                success:function () {
                    console.log("注册成功")
                }
            });
        }else if (reg.test(usname)){
                $(".notice-box-password").show();
                setTimeout(function () {
                    $(".notice-box-password").hide();
                },3000)
        }else{
            $(".notice-box-email").show();
            setTimeout(function () {
                $(".notice-box-email").hide();
            },3000)
        }


    }

});
