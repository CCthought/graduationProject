window.onload = function () {
    initializeTopbar();
    let url = decodeURI(window.location.search);
    let keyWords = url.substring(10,url.length);
    getPageSearchItems(1,5,keyWords);
};


function getPageSearchItems(currentPage, pageSize, keyWords) {
    $("#core-search").empty();
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `pageSize=${pageSize}&currentPage=${currentPage}&keyWords=${keyWords}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getPageSearchItems",
        success: function (result) {
            let domId = document.getElementById("core-search");
            if (result.head.code === '200') {
                // 说明有数据
                console.log(result);
                if(result.body.items.length === 0){
                    $(domId).append('<img src="../../images/search.png" width = "100%" height="100%" />');
                }else{
                    spanSquare(domId, result, currentPage, pageSize,keyWords);
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


// 渲染翻页小方块
function spanSquare(domId, result, currentPage, pageSize,keyWords) {
    for (let i = 0; i < result.body.items.length; i++) {
        let itemPathIndexOf = result.body.items[i].imgPath.indexOf("/");
        let itemPath = result.body.items[i].imgPath.substring(0,itemPathIndexOf);
        $(domId).append(`<div class="addedItems"><a href="../../html/${itemPath}/${itemPath}.html?id=${result.body.items[i].id}&category=${result.body.items[i].category}"><img src="../../images/${result.body.items[i].imgPath}" width="100%" height="260px" alt="picture"/></a><span style="display: inline-block;font-size: 16px;">${result.body.items[i].name}<br />原价￥${result.body.items[i].price} 折扣价<span style="color: red;">￥${result.body.items[i].discount}</span><br />销售量:${result.body.items[i].saled}</span></div>`);
    }
    // 增加<  >小方块
    $(domId).append('<div class="pageTurning"><div id="pageTurningSquareFront" class="pageTurningSquare">&lt;</div>' +
        '' +
        '<div class="pageTurningSquare" id="pageTurningSquareLatter">&gt;</div></div>'
    );
    // 增加1,2,3 ... 小方块
    let pageTurningSquareLatter = document.getElementById("pageTurningSquareLatter");
    for (let i = 0; i < Math.ceil(result.body.totalRecords / pageSize); i++) {
        $(pageTurningSquareLatter).before(`<div class="pageTurningSquare">${i + 1}</div>`);
    }

    // 说明是第一页
    let pageTurningSquare = $(".pageTurningSquare");
    pageTurningSquare[0].onclick = function () {
        if (currentPage === 1) {
            return false;
        } else {
            getPageSearchItems(currentPage - 1, pageSize, keyWords);
        }
    };

    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getPageSearchItems(currentPage + 1, pageSize, keyWords)
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getPageSearchItems(i, pageSize, keyWords)
        }
    }
    // 为1，2，3 ... 注册专属CSS样式  current
    pageTurningSquare[currentPage].className = "pageTurningSquare current";

    // 为 < > 小方块注册 鼠标移入 禁止状态
    document.getElementById("pageTurningSquareFront").onmouseover = function () {
        if (currentPage === 1) {
            this.style.cursor = "not-allowed";
        }
    };

    pageTurningSquareLatter.onmouseover = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            this.style.cursor = "not-allowed";
        }
    }
}