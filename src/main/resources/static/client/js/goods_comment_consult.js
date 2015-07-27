$(document).ready(function(){
    $("#commentForm").Validform({
        tiptype: 1
    });
    
    $("#consultForm").Validform({
        tiptype: 1
    });
    
});

// 按星级获取评论
function getCommentByStars(goodsId, stars, page)
{
    if (null == goodsId || null == stars || null == page)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/goods/comment/" + goodsId + "?stars=" + stars + "&page=" + page,
        success:function(res){
            $("#comment-list").html(res);
        }
    });
}

//获取咨询
function getConsult(goodsId, page)
{
    if (null == goodsId || null == page)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/goods/consult/" + goodsId + "?page=" + page,
        success:function(res){
            $("#consult-list").html(res);
        }
    });
}

// 提交评论
function submitComment()
{
	
    $.ajax({
        type:"post",
        url:"/user/comment/add?" + $("#commentForm").serialize(),
        success:function(res){
            if (0 == res.code)
            {
                alert("提交评论成功，请耐心等待审核~~");
            }
            else
            {
                var ss = "" + res.message;
                alert(res["message"]);
            }
        }
    });
}

//提交咨询
function submitConsult()
{
    $.ajax({
        type:"post",
        url:"/user/consult/add?" + $("#consultForm").serialize(),
        success:function(res){
            if (0 == res.code)
            {
                alert("提交咨询成功，请耐心等待审核~~");
            }
            else
            {
                var ss = "" + res.message;
                alert(res["message"]);
            }
        }
    });
}

// 改变星级
function starChange(type, stars)
{
    if (null == type || null == stars)
    {
        return;
    }
    
    var starCount = parseInt(stars);
    
    // 商品满意度
    if ("goodsStar" == type)
    {
        $("#goodsStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.goodsStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.goodsStar").eq(4).html('<img src="/client/images/content/start03.png" />');    
        }
    }
    // 专业技能满意度
    else if ("skillStar" == type)
    {
        $("#skillStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.skillStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.skillStar").eq(4).html('<img src="/client/images/content/start03.png" />');
                
        }
    }
    // 服务满意度
    else if ("serviceStar" == type)
    {
        $("#serviceStar").val(starCount);
        switch(starCount)
        {
        case 1:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 2:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 3:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 4:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
            break;
        case 5:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start01.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start01.png" />');
            break;
        default:
            $("a.serviceStar").eq(0).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(1).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(2).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(3).html('<img src="/client/images/content/start03.png" />');
            $("a.serviceStar").eq(4).html('<img src="/client/images/content/start03.png" />');
                
        }
    }
}