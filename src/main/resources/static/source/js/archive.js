let total =0;
var currentPage =1;
var oldyear='';
var i =0;
var j =1;

function putInArchiveArticle(data) {

    $(".categoriesTimeLine").empty();

    $.each(data.content,function (index,dt) {
        var year =$('<div>'+dt['createTime'].split('-')[0]+'</div>');
        var cen =(' <div class="timeline-row-major">\n' +
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
                if (oldyear!=dt['createTime'].split('-')[0]){
                    $(".categoriesTimeLine").append(year);
                }
            $(".categoriesTimeLine").append(cen);
        oldyear = dt['createTime'].split('-')[0];
        total++;
    });
    var til =$('<div style="text-align: center">总共'+total+'篇文章</div>');
    $(".categoriesTimeLine").append(til);
    j=total;
    total =0;
}

function articleTime(data) {
    var map =new Map();

    $.each(data.content,function (index,obj) {
        //console.log(obj['createTime'].substring(0,7))
        if (!map.has(obj['createTime'].substring(0,7))){
            map.set(obj['createTime'].substring(0,7),1);
        }else {
            map.set(obj['createTime'].substring(0,7),map.get(obj['createTime'].substring(0,7))+1);
        }
    });
    for (var [key,value] of map ){
        var time = ('<div class="archive-time" >' +
            '<span>' +
            '<a>' +
                key+
            '</a>' +
               "("+ value+")"+
            '</span></div>');
        $(".categories-title").append(time)
    }
}
Ajax(false);
function laypage(){
    layui.use("laypage",function () {
        var layPage =layui.laypage;

        layPage.render({
            elem: 'pages',
            count:j,
            limit:5,
            jump:function (obj,first) {
                currentPage = obj.curr;
                Ajax(first);

            }
        });


    });
}

function Ajax(first) {

    $.ajax({
        url:"/getArchiveArticle",
        Type:"GET",
        DataType:"json",
        data:{
            rows:10,
            page:currentPage
        },
        success:function (data) {
            if (!first) {
                scrollTo(0,0);

                putInArchiveArticle(data.data);


            }
        }
    })
}


$.ajax({
    url:"/getArchiveTotal",
    Type:"GET",
    DataType:"json",
    success:function (data) {
        laypage();
        articleTime(data.data);

    }
});

