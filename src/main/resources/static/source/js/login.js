$(function () {

    var name =$("#qqEmail");
    var pwd =$("#blogPassword");
    $(".register").click(function () {
        loginAjax();
    });

    function loginAjax() {
        $.ajax({
            type:"get",
            url:"loginUser",
            data:{username:name.val(),password:pwd.val()},
            contentType:"application/json",
            dataType:"json",
            success:function (data) {
                    alert(data.msg);
                window.location.href ="lastUrl";
            },
            error:function () {
                alert("失败")
            }
        });
    }



})