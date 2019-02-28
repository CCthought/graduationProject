window.onload = function () {
    initializeTopbar();
    getPageBook(1, 5);
};

function getPageBook(currentPage, pageSize) {
    $('#core-book').empty();
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `pageSize=${pageSize}&currentPage=${currentPage}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getPageIndexBook",
        success: function (result) {
            if (result.head.code === '200') {
                // 说明有数据
                let domId = document.getElementById("core-book");
                console.log(result);
                if (result.body.items.length > 0) {
                    spanSquare(domId, result, currentPage, pageSize);
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
function spanSquare(domId, result, currentPage, pageSize) {
    for (let i = 0; i < result.body.items.length; i++) {
        $(domId).append(`
            <div class="addedBook">
            <a href="../../html/book/book.html?id=${result.body.items[i].id}&category=${result.body.items[i].category}">
                <img src="../../images/${result.body.items[i].imgPath}" width="100%" height="260px" alt="picture"/>
            </a>
            <span style="display: inline-block;font-size: 16px;">${result.body.items[i].name}<br />原价￥${result.body.items[i].price} 折扣价
            <span style="color: red;">￥${result.body.items[i].discount}</span><br />销售量:${result.body.items[i].saled}</span>
            </div>
            `);
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
            getPageBook(currentPage - 1, pageSize);
        }
    };

    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getPageBook(currentPage + 1, pageSize);
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getPageBook(i, pageSize);
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
