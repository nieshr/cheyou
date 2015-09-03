// 按星级获取评论
function getCommentByStars(diysiteId, stars, page)
{
    if (null == diysiteId || null == stars || null == page)
    {
        return;
    }
    
    $.ajax({
        type:"get",
        url:"/shop/comment/" + diysiteId + "?stars=" + stars + "&page=" + page,
        success:function(res){
            $("#comment-list").html(res);
        }
    });
}
