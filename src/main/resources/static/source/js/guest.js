

$(function(){

    var flag =false;
    var name ="";
    var commentId ="";

/**
 * 发表留言
 */
$(".submit-comment").click(function(){
    $.get("isLogin",function (data) {
        if (data.status==502){
            window.location.replace("login")
        }else{
            if (!flag){
                put();
            }else {
                send(name);
                flag = false;
            }
        }
    })

})

    /**
     * 添加留言
     */
    function put() {
        var messages = $("#desc").val();
        var $this =$(this);
        $.ajax({
            type:"POST",
            url:"insertGuest",
            contentType:"application/json",
            dataType:"json",
            async:false,
            data:JSON.stringify({message:messages}),
            success:function(data){
                //$this.parent().parent().find($("#desc")).val("");
                //putInComment(data.data)
                //comment();
                window.location.reload();
            }

        })
        console.log(messages)
    }



    /**
     * 留言查询
     */
    comment();
    function comment() {
        $.ajax({
            type:"get",
            url:"allGuest",
            contentType:"application/json",
            dataType:"json",
            async:false,
            success:function(data){
                //$this.parent().parent().find($("#desc")).val("");
                putInComment(data.data)

            }

        })
    }
/**
 * 填充评论列表
 */
function putInComment(data) {

    $(".visitorComment ul").empty();

    $.each(data, function (index, dt) {
        var com = $('<article class="am-comment am-comment-warning am-comment-flip">\n' +
            '                    <a href="">\n' +
            '                        <img class="am-comment-avatar" src="http://xiongsihao.com/images/avatar.png">\n' +
            '                    </a>\n' +
            '\n' +
            '                    <div class="am-comment-main">\n' +
            '                        <header class="am-comment-hd">\n' +
            '                            <div class="am-comment-meta">\n' +
            '                                <a href="" class="am-comment-author">' + dt['authorName'] + '</a>\n' +
            '                                评论于\n' +
            '                                <time datetime="">' + dt['createTime'] + '</time>\n' +
            '<div class="id" style="display: none">'+dt['id']+'</div>'+
            '<a class="reply"><span class="am-icon-comment"></span>&nbsp;回复</a>' +
            '                            </div>\n' +
            '                        </header>\n' +
            '                        <div class="am-comment-bd reply-comment">\n' +
            '                            <h2 style="font-family: Georgia,\'Times New Roman\',Times,Kai,\'Kaiti SC\',KaiTi,BiauKai,FontAwesome,serif;">\n' +
            '                                ' + dt['message'] + '</h2>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </article>')

        if (dt['reportCommentSet']['0']!=null){
            //console.log(dt['reportCommentSet']['0']['guestId']);
            $.each(dt['reportCommentSet'],function (index,ob) {
                var o = $('<article class="am-comment am-comment-warning am-comment-flip">\n' +
                    '                    <a href="">\n' +
                    '                        <img class="am-comment-avatar" src="http://xiongsihao.com/images/avatar.png">\n' +
                    '                    </a>\n' +
                    '\n' +
                    '                    <div class="am-comment-main">\n' +
                    '                        <header class="am-comment-hd">\n' +
                    '                            <div class="am-comment-meta">\n' +
                    '                                <a href="" class="am-comment-author">'+ob['repName']+'</a>\n' +
                    '                                评论于\n'  +
                    '                                <time datetime="">'+ob['rcreateTime']+'</time>\n' +
                    '<a class="reply"><span class="am-icon-comment"></span>&nbsp;回复</a>'+
                    '<p style="color: red; display:inline">&nbsp;@'+ob['guestName']+'</p>'+
                    '                            </div>\n' +
                    '                        </header>\n' +
                    '                        <div class="am-comment-bd">\n' +
                    '                            <h2 style="font-family: Georgia,\'Times New Roman\',Times,Kai,\'Kaiti SC\',KaiTi,BiauKai,FontAwesome,serif;">\n' +
                    '                                '+ob['repMess']+'</h2>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </article>')
                com.find(".reply-comment").append(o);
                $(".visitorComment ul").append(com);
            })

        }else {
            $(".visitorComment ul").append(com);
        }

    })

}

      /**
       * 取消发送
       */

       /**
        * 发送评论
        */
       function send(name) {
           var message = $("#desc").val();
               $.ajax({
                   type:"post",
                   url:"insertRepGuest",
                   data:JSON.stringify({
                       repMess:message,
                       guestName:name,
                       guestId:commentId
                   }),
                   dataType:"json",
                   contentType:"application/json",
                   success:function (data) {
                        //reply(data.data);
                       //comment()
                       window.location.reload();
                   }
               })
       }
        /**
         * 评论回复
         */
        function reply(data) {

        }

         /**
          * 点赞
          */





/**
 * 填充不存在的留言
 */
function putInNotComment(){

}


    /**
     * 回复
     */
    $(".reply").click(function () {
        alert("ok");
        flag = true;
        var $this =$(this)
        name = $this.siblings(".am-comment-author").text();
        commentId =$this.siblings(".id").text();
        //console.log(commentId)
        if (commentId==""){
            commentId =$this.parent().parent().parent().parent().parent().siblings(".am-comment-hd").children().find(".id").text()
        }
        //console.log(commentId)
        scrollTo(0,0);
    })
});