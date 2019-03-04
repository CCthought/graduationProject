window.onload = function () {
    initializeTopbar();
    search(1,5);
    core();
    getPageItems(1, 10);
};

// search start ***********************************
function search(currentPage,pageSize){
    let search = document.getElementById("search");
    document.getElementById("search-button").onclick = function(){
        if(search.value === ""){
            alert("亲爱的用户，搜索内容不能为空哦");
            return false;
        }
        let keyWords = search.value.trim().split(/\s+/);
        let keyWordsComma = '';
        for(let i = 0; i < keyWords.length; i++){
            if(i !== keyWords.length - 1){
                keyWordsComma = keyWordsComma + keyWords[i] + ",";
            } else{
                keyWordsComma = keyWordsComma + keyWords[i];
            }
        }
        window.location.href = encodeURI("html/search/index.html?keyWords="+keyWordsComma);
    }
}
// search end ***********************************


// core js start *********************************************************************************
function core() {
    let core = document.getElementById('core');
    // 左边的div  为后期鼠标移入事件提供支持
    let coreLeftDiv = core.children[0];
    // 中间的div
    let coreMiddleDiv = core.children[1];
    // 中间div下的ul
    let coreMiddleDivUl = coreMiddleDiv.children[0];
    // 中间div下的 square
    let coreMiddleSquare = coreMiddleDiv.children[1];
    // 为span注册鼠标事件 当鼠标移入的时候 背景显示红色

    // core 左边选择栏 start&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    let coreLeftDivUlLis = coreLeftDiv.children[0].children;
    for (let i = 0; i < coreLeftDivUlLis.length; i++) {
        coreLeftDivUlLis[i].onmouseover = function () {
            if (coreLeftDivUlLis[i].id === "clothes-li") {
                let clothesAbsolute = document.getElementById('clothes-absolute');
                clothesAbsolute.onmouseover = function () {
                    clothesAbsolute.style.display = 'block';
                };
                clothesAbsolute.onmouseout = function () {
                    clothesAbsolute.style.display = 'none';
                };
                clothesAbsolute.style.display = 'block';
            } else if (coreLeftDivUlLis[i].id === "food-li") {
                let clothesAbsolute = document.getElementById('food-absolute');
                clothesAbsolute.onmouseover = function () {
                    clothesAbsolute.style.display = 'block';
                };
                clothesAbsolute.onmouseout = function () {
                    clothesAbsolute.style.display = 'none';
                };
                clothesAbsolute.style.display = 'block';
            } else if (coreLeftDivUlLis[i].id === "book-li") {
                let clothesAbsolute = document.getElementById('book-absolute');
                clothesAbsolute.onmouseover = function () {
                    clothesAbsolute.style.display = 'block';
                };
                clothesAbsolute.onmouseout = function () {
                    clothesAbsolute.style.display = 'none';
                };
                clothesAbsolute.style.display = 'block';
            } else {
                let clothesAbsolute = document.getElementById('poster-absolute');
                clothesAbsolute.onmouseover = function () {
                    clothesAbsolute.style.display = 'block';
                };
                clothesAbsolute.onmouseout = function () {
                    clothesAbsolute.style.display = 'none';
                };
                clothesAbsolute.style.display = 'block';
            }
        };

        coreLeftDivUlLis[i].onmouseout = function () {
            if (coreLeftDivUlLis[i].id === "clothes-li") {
                let clothesAbsolute = document.getElementById('clothes-absolute');
                clothesAbsolute.style.display = 'none';
            } else if (coreLeftDivUlLis[i].id === "food-li") {
                let clothesAbsolute = document.getElementById('food-absolute');
                clothesAbsolute.style.display = 'none';
            } else if (coreLeftDivUlLis[i].id === "book-li") {
                let clothesAbsolute = document.getElementById('book-absolute');
                clothesAbsolute.style.display = 'none';
            } else {
                let clothesAbsolute = document.getElementById('poster-absolute');
                clothesAbsolute.style.display = 'none';
            }
        };
    }
    // core 左边选择栏 end&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    // core 左边选择栏 end&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    // 轮播图 start&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    let oldIndex = 1; // 标记以前span元素的current样式，为了animate动画的 speed参数考虑
    // 中间div下的square下的span集合
    let coreMiddleSquareSpans = coreMiddleSquare.children;
    // 为span注册 鼠标移入移出事件
    for (let i = 0; i < coreMiddleSquareSpans.length; i++) {
        coreMiddleSquareSpans[i].setAttribute("index", i + 1);  // index属性时正整数

        coreMiddleSquareSpans[i].onmouseover = function () {
            clearInterval(imgInterval);
            for (let i = 0; i < coreMiddleSquareSpans.length; i++) {
                if (coreMiddleSquareSpans[i].getAttribute('class') !== null) {
                    oldIndex = i + 1;
                }
                coreMiddleSquareSpans[i].removeAttribute('class');
            }
            coreMiddleSquareSpans[i].className = "current";
            let index = coreMiddleSquareSpans[i].getAttribute("index");
            if (Math.abs(index - oldIndex) === 1) {
                animate(coreMiddleDivUl, -(index - 1) * 650, 20);
            } else {
                animate(coreMiddleDivUl, -(index - 1) * 650, 1);
            }
            imgInterval = setInterval(leftFocus, 5000);
        };
    }

    // 为>   < 注册点击事件
    let ltAndgt = coreMiddleDiv.children[2];

    let ltAndgtSpans = ltAndgt.children;
    let currentIndex = 1;
    for (let i = 0; i < ltAndgtSpans.length; i++) {
        if (i === 0) {
            //说明是左边
            ltAndgtSpans[i].onclick = function () {
                for (let i = 0; i < coreMiddleSquareSpans.length; i++) {
                    if (coreMiddleSquareSpans[i].getAttribute('class') !== null) {
                        currentIndex = i + 1;
                    }
                    coreMiddleSquareSpans[i].removeAttribute('class');
                }
                clearInterval(imgInterval);
                // 最左边 什么都不管
                if (currentIndex === 1) {
                    coreMiddleSquareSpans[i].className = "current";
                    // 处理用户一来  就直接点击最左边的按钮
                    imgInterval = setInterval(leftFocus, 5000);
                    return false;
                }
                coreMiddleSquareSpans[currentIndex - 2].className = "current";
                // offsetLeft 没有 px单位  为left有px单位
                animate(coreMiddleDivUl, coreMiddleDivUl.offsetLeft + 650, 20);
                imgInterval = setInterval(leftFocus, 5000);
            };
        }

        if (i === 1) {
            //说明是左边
            ltAndgtSpans[i].onclick = leftFocus;
        }
    }

    // 只将最右边的 < 图标抽取出来 因为 setInterval 需要使用
    function leftFocus() {
        if (this === window) {
            for (let i = 0; i < coreMiddleSquareSpans.length; i++) {
                if (coreMiddleSquareSpans[i].getAttribute('class') !== null) {
                    currentIndex = i + 1;
                }
                coreMiddleSquareSpans[i].removeAttribute('class');
            }
            // setInterval到达最右边 所以要快速恢复到最前面
            if (currentIndex === coreMiddleSquareSpans.length) {
                coreMiddleSquareSpans[0].className = "current";
                animate(coreMiddleDivUl, 0, 1);
            } else {
                coreMiddleSquareSpans[currentIndex].className = "current";
                // offsetLeft 没有 px单位  为left有px单位
                animate(coreMiddleDivUl, coreMiddleDivUl.offsetLeft - 650, 20);
            }
        } else {  //说明是点击事件
            for (let i = 0; i < coreMiddleSquareSpans.length; i++) {
                if (coreMiddleSquareSpans[i].getAttribute('class') !== null) {
                    currentIndex = i + 1;
                }
                coreMiddleSquareSpans[i].removeAttribute('class');
            }
            // 在执行之前 必须先清除了 setInternal 不然可能会造成用户刚点，setInternal又执行的尴尬情况
            clearInterval(imgInterval);
            // 最左边 什么都不管
            if (currentIndex === coreMiddleSquareSpans.length) {
                coreMiddleSquareSpans[coreMiddleSquareSpans.length - 1].className = "current";
                // 处理用户一来  就直接点击最右边的按钮
                imgInterval = setInterval(leftFocus, 5000);
                return false;
            }
            coreMiddleSquareSpans[currentIndex].className = "current";

            // offsetLeft 没有 px单位  为left有px单位
            animate(coreMiddleDivUl, coreMiddleDivUl.offsetLeft - 650, 20);
            imgInterval = setInterval(leftFocus, 5000);
        }
    }

    let imgInterval = setInterval(leftFocus, 5000);

    //设置任意的一个元素,移动到指定的目标位置
    function animate(element, target, speed) {
        if (element.timeId) {
            clearInterval(element.timeId);
        }
        //定时器的id值存储到对象的一个属性中
        element.timeId = setInterval(function () {
            //获取元素的当前的位置,数字类型
            let current = element.offsetLeft;
            //每次移动的距离
            let step = 50;
            step = current < target ? step : -step;
            //当前移动到位置
            current += step;
            if (Math.abs(current - target) > Math.abs(step)) {
                element.style.left = current + "px";
            } else {
                //清理定时器
                clearInterval(element.timeId);
                //直接到达目标
                element.style.left = target + "px";
            }
        }, speed);
    }

    // 轮播图 end&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
}

//core js end *********************************************************************************



function getPageItems(currentPage, pageSize) {
    $('#core-items').empty();
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `pageSize=${pageSize}&currentPage=${currentPage}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getPageIndexItems",
        success: function (result) {
            if (result.head.code === '200') {
                // 说明有数据
                let domId = document.getElementById("core-items");
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
        let itemPathIndexOf = result.body.items[i].imgPath.indexOf("/");
        let itemPath = result.body.items[i].imgPath.substring(0,itemPathIndexOf);
        $(domId).append(`<div class="addedClothes"><a href="html/${itemPath}/${itemPath}.html?id=${result.body.items[i].id}&category=${result.body.items[i].category}"><img src="images/${result.body.items[i].imgPath}" width="100%" height="260px" alt="picture"/></a><span style="display: inline-block;font-size: 16px;">${result.body.items[i].name}<br />原价￥${result.body.items[i].price} 折扣价<span style="color: red;">￥${result.body.items[i].discount}</span><br />销售量:${result.body.items[i].saled}</span></div>`);
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
            getPageItems(currentPage - 1, pageSize);
        }
    };

    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getPageItems(currentPage + 1, pageSize);
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getPageItems(i, pageSize);
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