window.onload = function(){
    let href = window.location.href;
    let temp = href.lastIndexOf('?');
    // 拿到地址栏的id
    let id = Number.parseInt(href.substring(temp+4,href.length));
    getUniqueClothes(id);
};

function getUniqueClothes(id){
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `id=${id}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getUniqueClothes",
        success: function (result) {
            if(result.head.code === '200'){
                $('#message-picture').append(`<img src="../../images/clothes/${result.body.imgPath}" width="100%" height="100%" alt="picture" />`);
            } else {
                alert('服务器出现一些 小差错...')
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}