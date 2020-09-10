/**
 * 放入标签
 * @param data
 */
function putLabel(data) {

    $.each(data,function (index,d) {
        var content =$(
            '<a href="tag?tag='+d['labelName']+'" class="blog-tag">'+d["labelName"]+'&nbsp;&nbsp;</a>'
        )
        $(".kyx-label").append(content);
    });

}
$.ajax({
    type: "GET",
    url: "tag/tags",
    contentType: "application/json",
    dataType: "json",
    success:function (data) {
        putLabel(data.data);
    },
    error:function () {
        alert("出错了");
    }
});