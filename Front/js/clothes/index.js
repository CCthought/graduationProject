window.onload = function () {
    getPageClothes(5, 1);
};

function getPageClothes(pageSize, currentPage) {
    $('#core-clothes').empty();
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `pageSize=${pageSize}&currentPage=${currentPage}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getPageIndexClothes",
        success: function (result) {
            if (result.head.code === '200') {
                if (result.body.items.length > 0) {
                    for (let i = 0; i < result.body.items.length; i++) {
                        $('#core-clothes').append(`<div class="addedClothes"><a href="../../html/clothes/${ result.body.items[i].id}.html?id=${ result.body.items[i].id}"><img src="../../images/clothes/${result.body.items[i].imgPath}" width="100%" height="260px" alt="picture"/></a><span style="display: inline-block;font-size: 16px;">${result.body.items[i].name}<br />原价￥${result.body.items[i].price} 折扣价<span style="color: red;">￥${result.body.items[i].discount}</span><br />销售量:${result.body.items[i].saled}</span></div>`);
                    }
                    // 增加<  >小方块
                    $('#core-clothes').append('<div class="pageTurning"><div id="pageTurningSquareFront" class="pageTurningSquare">&lt;</div>' +
                        '' +
                        '<div class="pageTurningSquare" id="pageTurningSquareLatter">&gt;</div></div>'
                    );
                    // 增加1,2,3 ... 小方块
                    for (let i = 0; i < Math.ceil(result.body.totalRecords / pageSize); i++) {
                        $('#pageTurningSquareLatter').before(`<div class="pageTurningSquare">${i + 1}</div>`);
                    }

                    // 说明是第一页
                    $(".pageTurningSquare")[0].onclick = function () {
                        if (currentPage === 1) {
                            return false;
                        } else {
                            getPageClothes(pageSize, currentPage - 1);
                        }
                    };

                    $(".pageTurningSquare")[0].onmouseover = function () {
                        
                    };

                    // 说明是最后一页
                    $(".pageTurningSquare")[$('.pageTurningSquare').length - 1].onclick = function () {
                        if (currentPage === $('.pageTurningSquare').length - 2) {
                            return false;
                        } else {
                            getPageClothes(pageSize, currentPage + 1);
                        }
                    };
                    //  为1,2,3 ... 小方块 注册点击事件
                    for (let i = 1; i < $('.pageTurningSquare').length - 1; i++) {
                        $('.pageTurningSquare')[i].onclick = function () {
                            getPageClothes(pageSize, i);
                        }
                    }
                    // 为1，2，3 ... 注册专属CSS样式  current
                    $('.pageTurningSquare')[currentPage].className = "pageTurningSquare current";
                }
            } else {
                alert('服务器 出现了一些小差错');
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}
