
$(function () {
    var tags ="";
    var total ;
    function putTag(data) {
        $(".tag-title").html("目前总计："+data.records +"篇    "+data.content[0]['specificTag'])
        total =data.records;
        $(".timeline-wrap").empty();
        $.each(data.content,function (index,dt) {
            var tag =(' <div class="timeline-row-major">\n' +
                '                            <span class="node am-animation-slide-top am-animation-delay-1"></span>\n' +
                '                            <div class="am-comment-main am-animation-slide-top am-animation-delay-1">\n' +
                '                                <header class="am-comment-hd">\n' +
                '                                    <div class="am-comment-meta">\n' +
                '                                        <a href="article/'+dt['id']+'">'+dt['title']+'</a>\n' +
                '                                    </div>\n' +
                '                                </header>\n' +
                '                                <div class="am-comment-bd">\n' +
                '                                    <i class="am-icon-calendar">'+dt['createTime']+'</i>\n' +
                '                                    <i class="am-icon-tag">'+dt['labelValues']+'</i>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>')
            $(".timeline-wrap").append(tag);

        })

    }
    var cur =1;
function pageHelper(data){
        layui.use('laypage',function () {
            var  laypage =layui.laypage;
            laypage.render({
                elem:'my-id',
                count:total,
                limit:5,
                curr:cur ,
                jump:function (obj,first) {
                    if (!first){
                        cur =obj.curr;
                        f(5,obj.curr);
                    }
                }
            })
        })
}

$.ajax({
    type: "head",
    url:window.location.href,
    async:false,
    success:function (data,status,xhr) {
        console.log(xhr.getResponseHeader("tag"))
        tags =xhr.getResponseHeader("tag");
    }
})

function f(size,num) {
    $.ajax({
        type:"get",
        url:"getTagDetail",
        data:{pageSize:size,pageNum:num,tag:tags},
        dataType:"json",
        contentType:"application/json",
        success:function (data) {
            putTag(data.data)
            pageHelper(data)

        }
    })
}
f(5,1);
})
