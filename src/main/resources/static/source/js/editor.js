var testEditor;
$(function () {
    testEditor = editormd("my-editormd",{
        width:"98%",
        height:"600",
        syncScrolling:"single",
        path:"editor.md-master/lib/",
        previewTheme:"light",
        codeFold:true,
        emoji:true,
        tocm:true,
        tex:true,
        flowChart:true,
        sequenceDiagram:true,
        htmlDecode:true,
        //图片上传
        imageUpload:true,
        imageFormats:["jpg","jpeg","gif","png","bmp","webp"],
        imageUploadURL:"/uploadImage",
        onload:function () {
            console.log('onload', this);
        },
        saveHTMLToTextarea:true,
        toolbarIcons:function () {
            return ["bold", "del", "italic", "quote", "|", "h1", "h2", "h3", "h4", "h5", "h6", "|", "list-ul", "list-ol", "hr", "|", "link", "image", "code", "code-block", "table", "datetime", "html-entities", "emoji", "|", "watch", "preview", "fullscreen", "clear", "search", "|", "help", "info"]

        }
    })
});

var publish=$(".publish");
var EditorTitle=$(".kyx-editor-title");
var myEditormdHtmlCode =$("#my-editormd-html-code");
var noticeTitle =$(".notice-box-title");
var noticeContent =$(".notice-box-content");
var noticeBox =$(".notice-box");
/*
*发布博客提示
* */
publish.click(function () {
    var EditorTitleValues =EditorTitle.val();
    var myEditorndHtmlCodeValues =myEditormdHtmlCode.val();
    if (EditorTitleValues.length==0){
        noticeTitle.show();
        setTimeout(function () {
            noticeTitle.hide();
        },3000)
    }else if (myEditorndHtmlCodeValues.length==0){
        noticeContent.show();
        setTimeout(function () {
            noticeContent.hide();
        },3000)
    }else{
        $('#my-alert').modal();
    }
});


/*发布博客*/
var selectCategories = $("#select-categories");
var selectGrade =$("#select-grade");
var selectType =$("#select-type");
var originalAuthors =$("#originalAuthor");
var EditorTitle =$(".kyx-editor-title");
/*
* 显示文章作者
* */
selectType.blur(function () {
    var selectValue =selectType.val();
    if (selectValue=="原创"){
        $("#originalAuthorHide").hide();
    }else if (selectValue=="转载"){
        $("#originalAuthorHide").show();
    }
});
/**
 * 添加标签
 * @type {jQuery.fn.init|jQuery|HTMLElement}
 */
var addTag =$(".add-Tag");
$(function () {
    var i =0;
    var append =function(){
        var t =$(
            '<span>'+
            '<i class="am-icon-tag"></i>'+'<p class="tag-value" contenteditable="true">请输入标签</p>'+
            '<i class="am-icon-remove kyx-re"></i>'
            +'</span>'
        )
        $(".tag").append(t);
        $("tag-value").click(function () {
            $(this).focus();
        })
    }
    addTag.click(function () {
        if (i>=4){
            addTag.attr('disabled','disabled');
        }else {
            append();
            i++;
        }
    });
    $(".tag").on("click",".kyx-re",function () {
        console.log("remove");
        $(this).parent().remove();
        i--;
        if (i<4){
            addTag.removeAttr("disabled");
        }
    });
});

/**
 * 获取文章草稿
 * @type {jQuery.fn.init|jQuery|HTMLElement}
 */
$(function () {
   $.ajax({
       type: "GET",
       url: "/getDraftArticle",
       data: {},
       dataType: "json",
       async:false,
       success:function (data) {
            if (data.status == 201){
            EditorTitle.val(''+data.data.title);
            $("#content").val(''+data.data.text);
            selectCategories.val(''+data.data.selectCategorie);
            selectType.val(''+data.data.selectType);
            selectGrade.val(''+data.data.selectGrade);
            if(data.data.selectType=="转载"){
                originalAuthors.val(''+data.data.originalAuthor);
                $("#originalAuthorHide").show();
            }else{
                $("#originalAuthorHide").hide();
            }
            var tags =data.data.tagValues;
            for(var i in tags){
                $(".tag").append($(
                    '<span>'+
                    '<i class="am-icon-tag"></i>'+'<p class="tag-value" contenteditable="true">'+tags[i]+'</p>'+
                    '<i class="am-icon-remove kyx-re"></i>'
                    +'</span>'
                ));
            }
            var aid = data.data.id;
            if (aid!=null){
                $(".surePublish").attr("id",aid);
            }
            }
       }
   })
});

/**
 * 发布博客
 * @type {jQuery.fn.init|jQuery|HTMLElement}
 */

var surePublish =$(".surePublish");
surePublish.click(function () {
    /**
     * 文章标签
     */
    var tagValue =[];
    for (var i=0;i<$(".tags").find("p").length;i++){
        tagValue.push($(".tags").find("p").eq(i).html());
        console.log(tagValue)
    }

    var ArticleTag = tagValue;
    /*
    * 文章类型
    * */
    var selectTypes =selectType.val();

    /*
    * 文章
    * */
    var selectCategorie = selectCategories.val();
    /*
    * 博客分类
    * */
    var selectedGrades = selectGrade.val();
    /*
    * 原作者
    * */
    var originalAuthor = originalAuthors.val();
    /*
    * 标题
    * */
    var title =EditorTitle.val();

    var data = {
        id:surePublish.attr("id"),
        selectType:selectTypes,
        selectCategorie:selectCategorie,
        selectedGrades:selectedGrades,
        originalAuthor:originalAuthor,
        title:title,
        text:myEditormdHtmlCode.val(),
        articleHtmlContent:testEditor.getHTML(),
        tagValues:tagValue
    };
    console.log(surePublish.attr("id"))
    $.ajax({
        type: "POST",
        url: "/publishEditor",
        traditional: true,
        data:JSON.stringify(data),
        contentType: "application/json",
        success:function (data) {
            if (data.status == 200) {
                $("#my-alert").modal("close");
            }
            if (data.status ==201){
                $("#my-alert").modal("close");
            }
            console.log("ok");
        }
    });

});
