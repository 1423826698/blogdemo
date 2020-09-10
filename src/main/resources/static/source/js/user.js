$(function () {
    var Name =$(".user label").text();
    var Id ="";
    $(".guestSet").addClass("c");
    $("#commeSet").css("display","none")
    f();
    $(".guestSet,.commeSet").click(function () {
            $(this).addClass("c");
            $(this).siblings().removeClass("c")

        if ($(this)[0].className.search("guestSet")==10){
            console.log("留言")
            $("#commeSet").css("display","none")
            $("#guestSet").css("display","block")

            f();
        }else if ($(this)[0].className.search("commeSet")==10){
            console.log("评论")
            $("#commeSet").css("display","block")
            $("#guestSet").css("display","none")
            f2();
        }
    })

    $(".fr1").click(function () {
        $(this).parent().siblings().empty();
    })
    $(".fr").click(function () {
        $.ajax({
            type:"get",
            url:"allMessRead",
            data:{name:Name},
            dataType:"json",
            contentType:"application/json",
            success:function () {
                f();
            }
        })
    })



    function f() {

        $.ajax({
            type:"get",
            url:"getUserGuest",
            data:{name:Name},
            dataType:"json",
            contentType:"application/json",
            success:function (data) {
                put(data.data)

                $(".is-read").click(function () {
                    $(this).removeClass("am-btn-danger")
                    $(this).text("已读")
                    Id =$(this).parent().siblings(".rid").html();
                    console.log(Id)
                    f1(Id);

                })

            }
        })


    }
    function f1(ID) {
        $.ajax({
            type: "get",
            url:"partMessRead",
            data:{id:ID},
            dataType: "json",
            contentType: "application/json",
            success:function (data) {

            }
        })
    }
    function f2() {
        $.ajax({
            type:"get",
            url:"allComment",
            data:{name:Name},
            dataType:"json",
            contentType:"application/json",
            success:function (data) {
                putComment(data.data)
                $(".delete").click(function () {
                    var rid =$(this).parent().siblings(".rid").html();
                    var $this =$(this);
                    $.ajax({
                        type:"get",
                        url:"deleteComment",
                        data:{rid:rid},
                        dataType:"json",
                        contentType:"application/json",
                        success:function () {
                            $this.parent().parent().remove()
                        }
                    })
                })
            }
        })
    }
    function put(data) {
        $(".data-list ul").empty();
            $.each(data,function (index,dt) {
                var message =$('<li>\n' +
                    '                                <i class="data-cricle"></i>\n' +
                    '                                <span class="msg-type">留言</span>\n' +
                    '                                <span class="msg-title">\n' +
                    '                                    <a target="_blank" href="/guest">\n' +
                    '                                        '+dt['repName']+'+\n' +
                    '                                    </a>\n' +
                    '                                    给你留了言\n' +
                    '                                </span>\n' +
                    '<span><button class="am-btn-danger is-read">未读</button></span>\n'+
                    '\n' +
                    '                                <p class="msg-text">\n' +
                    '                                    <span data-v-0947769e="" class="bb-span-wrap">\n' +
                    '                                        <a href="/#">\n' +
                    '                                            '+dt['repMess']+'+\n' +
                    '                                        </a>\n' +
                    '                                    </span>\n' +
                    '                                    <em class="fr" style="color: #cccccc;">\n' +
                    '                                        '+dt['rcreateTime']+'+\n' +
                    '                                    </em>\n' +
                    '                                </p>\n' +
                    '<div class="rid" style="display: none">'+dt['rid']+'</div>'+
                    '                            </li>');
                    if (dt['risRead']==0){
                        message =$('<li>\n' +
                            '                                <i class="data-cricle"></i>\n' +
                            '                                <span class="msg-type">留言</span>\n' +
                            '                                <span class="msg-title">\n' +
                            '                                    <a target="_blank" href="/guest">\n' +
                            '                                        '+dt['repName']+'+\n' +
                            '                                    </a>\n' +
                            '                                    给你留了言\n' +
                            '                                </span>\n' +
                            '<span><button class="is-read">已读</button></span>\n'+
                            '\n' +
                            '                                <p class="msg-text">\n' +
                            '                                    <span data-v-0947769e="" class="bb-span-wrap">\n' +
                            '                                        <a href="/#">\n' +
                            '                                            '+dt['repMess']+'+\n' +
                            '                                        </a>\n' +
                            '                                    </span>\n' +
                            '                                    <em class="fr" style="color: #cccccc;">\n' +
                            '                                        '+dt['rcreateTime']+'+\n' +
                            '                                    </em>\n' +
                            '                                </p>\n' +
                            '<div class="rid" style="display: none">'+dt['rid']+'</div>'+
                            '                            </li>');
                    }
                $(".data-list ul").append(message);
            })


    }


    function putComment(data) {
        $(".blogMessage").empty()
        $.each(data,function (index,dt) {
            var comment =$('<tr>\n' +
                '                            <td class="rid">'+dt['rid']+'</td>\n' +
                '                            <td>'+dt['repMess']+'</td>\n' +
                '                            <td>'+dt['rcreateTime']+'</td>\n' +
                '                            <td><button class="delete">删除</button></td>\n' +
                '                        </tr>')
            $(".blogMessage").append(comment);

        })
    }

})