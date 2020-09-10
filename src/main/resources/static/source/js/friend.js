$(function () {


friendAjax();


function friendAjax() {
    $.ajax({
        type:"GET",
        url:"allFriendUrl",
        dataType:"json",
        contentType:"application/json",
        success:function (data) {
            $.each(data.data,function (index,dt) {
                var message =$(' <a href="'+dt['friendUrl']+'" class="friendItem">\n' +
                    '                <div class="box">\n' +
                    '                    <img class="picture " src="'+dt['pictureUrl']+'">\n' +
                    '                    <div class="info">\n' +
                    '                        <h3 style="white-space: nowrap">'+dt['friendName']+'</h3>\n' +
                    '                        <p>'+dt['blogInfo']+'</p>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </a>');
                $(".friend-url").append(message);
            })
        }
    })

}


})