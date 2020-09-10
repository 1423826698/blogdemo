var tota =0;
var currentPa;
function putInArticle(data) {
    var sessionCenter = $(".kyx-session-content");
    sessionCenter.empty();
    $.each(data,function (index,d) {
        //index++;
        var html =$(
            '<div class="content">'+
            '<div class="layui-col-md4">'+
            '<div class="img">'+
            '<img height="145px" src="source/image/'+index+'\.webp">'+
            '</div>'+
            '</div>'+
            '<div class="layui-col-md8">'+
            '<div class="kyx-center">'+
           '<header class="">'+
            '<h1>'+'<a class="kyx-article-title">'+d['title']+'</a>'+'</h1>'
            +'</header>'+
            '<div class="article-entry">'+
            '<p>'+d['articleTabled']+'</p>'+
            '</div>'+
            '</div>'+'</div>'+
            '<hr>'+
            '<div class="kyx-footer">'+'<span>'+'<i class="am-icon-user">&nbsp; kyx</i>'+'</span>'+
                 '<span>'+'<i class="am-icon-calendar">&nbsp; '+d['createTime']+'</i>'+'</span>' +
                    '<span>'+'<i class="am-icon-comment">&nbsp; 评论 10</i>'+'</span>'+
                    '<span>'+'<i class="am-icon-eye">&nbsp;'+d['look']+'</i>'+'</span>'+
                    '<a href="'+d['articleUrl']+
            '">阅读全文<i class="am-icon-angle-double-right"></i>'+'</a>'+
            '</div>'
                +'</div>');
        sessionCenter.append(html);
        var $footer =$(".kyx-footer");

      //  console.log(d['tagValues'].length)
       for (var i=0;i<d['tagValues'].length;i++){
            var articleTag =$('<span>'+'<i class="am-icon-tag">'+d['tagValues'][i]+'</i>'+'</span>');
                $footer.eq(index).append(articleTag);

        }

    })
}

/**
 * 分页查询文章
 */

function Laypage(data) {
    layui.use("laypage",function () {
        var layPage =layui.laypage;
        tota =data.data.records;
        layPage.render({
          elem:'pages',
          count:tota,
          limit:5,
          curr:currentPa,
          jump:function (obj,first) {
              currentPa = obj.curr;
              if (!first) {
                  ajaxFirst(currentPa);
              }
          }
        });
    })
}

/**
 *分页查询博客文章
 */
function ajaxFirst(currentPage) {
    var json1 ={page:currentPage,pageSize:5};
    $.ajax({
        type:"GET",
        url:"/myArticle",
        data:json1,
        dataType:"json",
        contentType:"application/json",
        success:function (data) {

            scrollTo(0,0);
            putInArticle(data.data.content);

            console.log("ok")
            Laypage(data);
        }
    })
};
ajaxFirst(1);


allArticle();allLabel();allGuest();
function allArticle() {
    $.ajax({
        type:"get",
        url: "ArticleCounts",
        dataType: "json",
        contentType: "application/json",
        success:function (data) {
            $(".articleCount").html(data.data);
        }
    })
}
function allLabel() {
    $.ajax({
        type:"get",
        url:"LabelsCounts",
        success:function (data) {
            $(".labelsCount").html(data.data);
        }
    })
}
function allGuest() {
    $.ajax({
        type:"get",
        url:"GuestCounts",
        success:function (data) {
            $(".guestCount").html(data.data);
        }
    })
}
/**
 * 网站运行时间
 * @param time
 */
function siteRunningTime(time) {
    var theTime;
    var StrTime ="";
    if (time>86400){
        theTime =parseInt(time/86400);
        StrTime +=theTime+"天";
        time -=theTime*86400;
    }
    if (time>3600){
        theTime =parseInt(time/3600);
        StrTime +=theTime+"小时";
        time -=theTime*3600;
    }
    if (time>60){
        theTime =parseInt(time/60);
        StrTime +=theTime+"分钟";
        time -=theTime*60;
    }
    StrTime +=time+"秒";
    $(".siteRunningTime").html(StrTime);
}
var oldDate =new Date("2020/9/10 15:00:00");
var currentTime =new Date().getTime();
var time =parseInt((currentTime-oldDate.getTime())/1000);
setInterval(function () {
    siteRunningTime(time);
    time++;
},1000)