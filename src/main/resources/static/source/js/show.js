var articleId ="1280072085774807042";//博客id

/**
 * 页面渲染
 */

function putArticleDetail(data){
    console.log(data)
    var center =$(
      '<div class="kyx-article-top">'+
        '<div class="article-top">'+data.title+'</div>'+
        '<div class="article-info">'+
        '<span class="am-badge am-radius am-badge-secondary">'+data.selectType+'</span>'+
        '<span class="">&nbsp;<span class="am-icon-calendar">&nbsp;'+data.createTime+'&nbsp;</span></span>'+
        '<span>&nbsp;<i class="am-icon-user">&nbsp;kyx</i></span>'
        +'</div>'
        +'</div>'
    );
    $('.kyx-article-top').append(center);
    $('#mdText').text(data.text);
    var wordsView;
    wordsView = editormd.markdownToHTML("wordsView",{
        htmlDecode: "true",
        emoji: true,
        taskList: true,
        tex: true,
        flowChart: true,
        sequenceDiagram: true
    });
}

/**
 * 处理响应header
 */
$.ajax({
    type:'HEAD',
    url:window.location.href,
    async:false,
    success: function (data, status, xhr) {
        articleId =xhr.getResponseHeader("articleId");
    }
});

$.ajax({
    type:'GET',
    url:"/getArticleDetail",
    contentType:"application/json",
    dataType:"json",
    data:{articleId:articleId},
    success:function (data) {
        //放入数据
        putArticleDetail(data.data);
    }
});