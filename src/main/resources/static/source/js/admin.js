
$(function () {
    /**
     * 侧边栏切换
     */
    $("#users-info,#blog-info").css("display","none");
    friendUrl();
$(".blog-manager,.u-info,.web-info").click(function () {
    $(this).addClass("ac").siblings().removeClass("ac");
    if ($(this).attr('class')=="u-info ac"){
        UserAjax(5,1);
        $("#users-info").css("display","block");
    }else {
        $("#users-info").css("display","none");
    }
    if($(this).attr('class')=="blog-manager ac"){
        BlogAjax(5,1);
        $("#blog-info").css("display","block");
    }else{
        $("#blog-info").css("display","none")
        console.log("none")
    }
    if ($(this).attr('class')=="web-info ac"){
        friendUrl();
        $("#web-info").css("display","block")
    }else {
        $("#web-info").css("display","none")
    }
});



    /**
     * 分布页查询
     * @param data
     * @param current
     */
    function pageHelperusers(data,current,id){
    var co =data.data.records;
    layui.use('laypage',function () {
        var laypage =layui.laypage;
        laypage.render({
            elem:id,
            count:co,
            limit:5,
            curr:current,
            jump:function (obj,first) {
                if (!first) {
                    current=obj.curr;
                    if (id="more-page"){
                        UserAjax(5, obj.curr);
                    }
                    if (id="page-blog"){
                        BlogAjax(5,current);
                    }

                }
            }
        });

    });

}

    /**
     * 博客查询
     * @param size
     * @param current
     * @constructor
     */

function BlogAjax(size,current) {
    $.ajax({
        type:"GET",
        url:"getBlog",
        dataType:"json",
        contentType:"application/json",
        data:{pageSize:size,currentPage:current},
        success:function (data) {
            putBlog(data.data);
            pageHelperusers(data,current,"page-blog");
        },
        error:function () {
            alert("false")
        }
    });
}
    /**
     * 用户查询
     * @param size
     * @param current
     * @constructor
     */


function UserAjax(size,current) {
    $.ajax({
        type:"GET",
        url:"getUsers",
        dataType:"json",
        contentType:"application/json",
        data:{pageSize:size,currentPage:current},
        success:function (data) {
            putUsers(data.data);
            pageHelperusers(data,current,"more-page");
        }
    })
}

    /**
     * 博客信息渲染
     * @param data
     */
    function putBlog(data) {
    var blogMessage =$(".blogMessage");
    blogMessage.empty();
    $.each(data.content,function (index,dt) {
        var msg =$(' <tr>\n' +
            '   <td class="id">'+dt['id']+'</td>\n' +
            '   <td>'+dt['title']+'</td>\n' +
            '   <td>'+dt['createTime']+'</td>\n' +
            '   <td>\n' +
            '   <div class="am-btn-toolbar">\n' +
            '   <div class="am-btn-group am-btn-group-xs">\n' +
            '   <button  class="blog-ed am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-save"></span> 编辑</button>\n' +
            '   <button  class="blog-del am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>\n' +
            '   </div>\n' +
            '   </div>\n' +
            '   </td>\n' +
            '   </tr>');
        blogMessage.append(msg);
    })

        /**
         * 博客删除
         */
        $(".blog-del").click(function () {
            var d=$(this).parent().parent().parent().parent().find($(".id")).text();
            delBlog(d);
            $(this).parent().parent().parent().parent().remove();
        })
        /**
         * 博客编辑
         */
        $(".blog-ed").click(function () {
            alert("aa")
            window.location.href="/editor?id="+$(this).parent().parent().parent().parent().find($(".id")).text();
        })
    }

    /**
     * 用户信息渲染
     */
function putUsers(data) {
    var userMessage =$(".userMessage");
    userMessage.empty();
    $.each(data.content,function (index,dt) {
        var messge =$('<tr>'+
            '<td>'+(index+1)+'</td>'+
            '<td class="username">'+dt['username']+'</td>'+
            '<td>' +
            '<div class="am-btn-toolbar">'+
            '<button class="am-btn am-btn-danger kyx-user-delete">删除</button>'+
            '</div>'+
            '</td>'+
            '</tr>');

        userMessage.append(messge);
    })


        /**
         * 用户删除
         */
        $(".kyx-user-delete").click(function () {
            var a= $(this).parent().parent().parent().find($(".username")).text();
            deleteUser(a);
            $(this).parent().parent().parent().remove();
        });

    }


    /**
     * 删除用户
     * @param name
     */
    function deleteUser(name) {
    $.ajax({
        type:"GET",
        url:"deleteUser",
        data: {Name:name},
        dataType:"json",
        contentType: "application/json",
        success:function () {
            alert("删除成功")

        }
    })
}

    /**
     * 博客删除
     * @param id
     */
    function delBlog(id) {
        $.ajax({
            type:"GET",
            url:"delBlog",
            data:{Id:id},
            contentType:"application/json",
            dataType:"json",
            success:function () {
                alert("删除成功")
            },
            error:function () {
                alert("错误")
            }
        })
    }

    /**
     * 友联信息
     */
    function friendUrl() {
        $.ajax({
            type:"get",
            url:"allFriendUrl",
            contentType:"application/json",
            dataType:"json",
            success:function (data) {
                $(".friendMessage").empty();
                $.each(data.data, function (index, message) {
                    var dt = ('<tr>\n' +
                        '                        <td class="id">'+message['id']+'</td>\n' +
                        '                        <td>'+message['friendName']+'</td>\n' +
                        '                        <td>'+message['blogInfo']+'</td>\n' +
                        '                           <td>'+message['friendUrl']+'</td>'+
                        '                        <td>'+message['pictureUrl']+'</td>\n' +
                        '<td class="am-btn-toolbar">'+
                        '<button class="am-btn am-btn-danger friend-delete">删除</button>'+
                        '</td>'+
                        '                    </tr>'
                    )
                    $(".friendMessage").append(dt);
                })

                $(".friend-delete").click(function () {
                   // console.log($(this).parent().siblings(".id").html());
                    var id =$(this).parent().siblings(".id").html();
                    $.ajax({
                         type:"get",
                         url:"deleteFriend",
                         data:{id:id},
                         contentType:"application/json",
                         dataType:"json",
                         success:function () {
                             $(this).parent().remove();
                         }
                     })
                    $(this).parent().parent().remove()
                })
            }
        })
    }

    /**
     * 添加友联
     */
    $(".friend-submit").click(function () {
        var name =$(this).siblings()[1].value;
        var message =$(this).siblings()[2].value;
        var url =$(this).siblings()[3].value;
        var picture =$(this).siblings()[4].value;

        $.ajax({
            type:"POST",
            url:"addFriendUrl",
            data:JSON.stringify({friendName:name,blogInfo:message,friendUrl:url,pictureUrl:picture}),
            contentType:"application/json",
            dataType:"json",
            success:function () {
                friendUrl();
            }
        })
    })
})