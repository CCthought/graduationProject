window.onload = function () {
    initializeTopbar();
    let id = GetQueryString("id");
    let category = GetQueryString("category");
    // 获取 衣服相关信息
    getUniqueClothes(id, category);
    // 获取 评论所有信息
    getComments(id, category, 1, 5);
    // 当textare获取焦点时，判断是否用户存在
    ifExistWhenAddComments();
    // 添加评论
    addComment(id, category);
};

function getUniqueClothes(id, category) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `id=${id}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getUniqueClothes",
        success: function (result) {
            if (result.head.code === '200') {
                $('#message-picture').append(`<img src="../../images/${result.body.imgPath}" width="100%" height="100%" alt="picture" />`);
                document.getElementById('message-clothes-name').innerHTML = result.body.name;
                document.getElementById('message-clothes-price-li-del').innerHTML = result.body.price;
                document.getElementById('message-clothes-price-li-span').innerHTML = result.body.discount;
                document.getElementById('saledSpan').innerHTML = result.body.saled;
                // 获取所有评论 因为评论和items是不同项目，所以要通过另一个ajax去获取所有的评论
                getCountsComments(id, category, document.getElementById('commentsSpan'));
                document.getElementById('locationSpan').innerHTML = result.body.location;
                let colorUl = document.getElementById('message-clothes-color-ul');
                for (let i = 0; i < result.body.color.length; i++) {
                    $(colorUl).append(` <li class="message-clothes-color-li">${result.body.color[i]}</li>`);
                }
                let sizeUl = document.getElementById('message-clothes-size-ul');
                for (let i = 0; i < result.body.size.length; i++) {
                    $(sizeUl).append(` <li class="message-clothes-size-li">${result.body.size[i]}</li>`);
                }

                //为提交购物车 做准备
                let color = "";
                let size = "";

                // 为颜色 注册点击事件
                let colorLi = document.getElementById('message-clothes-color-ul').children;
                for (let i = 1; i < colorLi.length; i++) {
                    colorLi[i].onclick = function () {
                        for (let i = 1; i < colorLi.length; i++) {
                            colorLi[i].style.backgroundColor = "#DDDDDD";
                        }
                        color = this.innerHTML;
                        this.style.backgroundColor = "red";
                    };
                }
                //为size 注册点击事件
                let sizeLi = document.getElementById('message-clothes-size-ul').children;
                for (let i = 1; i < sizeLi.length; i++) {
                    sizeLi[i].onclick = function () {
                        for (let i = 1; i < sizeLi.length; i++) {
                            sizeLi[i].style.backgroundColor = "#DDDDDD";
                        }
                        size = this.innerHTML;
                        this.style.backgroundColor = "red";
                    };
                }

                //为color 和 size  注册鼠标移入移出事件
                for (let i = 1; i < colorLi.length; i++) {
                    colorLi[i].onmouseover = function () {
                        this.style.cursor = "pointer";
                    }
                }
                for (let i = 1; i < sizeLi.length; i++) {
                    sizeLi[i].onmouseover = function () {
                        this.style.cursor = "pointer";
                    }
                }

                // 购买按钮 和 购物车 注册鼠标移动事件
                $('.message-clothes-buyAndCart')[0].onmouseover = function () {
                    this.style.cursor = 'pointer';
                };
                $('.message-clothes-buyAndCart')[1].onmouseover = function () {
                    $('.message-clothes-buyAndCart')[1].style.cursor = 'pointer';
                }

                // 判断输入的数 是不是 正整数
                document.getElementById('numberLi').onblur = function () {
                    if (!(/(^[1-9]\d*$)/.test(document.getElementById('numberLi').value))) {
                        alert('你输入的数不是正整数');
                        document.getElementById('numberLi').value = 1;
                    }
                };

                // plus +
                document.getElementById('plus').onclick = function () {
                    let oldValue = document.getElementById('numberLi').value;
                    document.getElementById('numberLi').value = Number.parseInt(oldValue) + 1;
                };

                // minus -
                document.getElementById('minus').onclick = function () {
                    let oldValue = document.getElementById('numberLi').value;
                    if (Number.parseInt(oldValue) === 1) {
                        return;
                    }
                    document.getElementById('numberLi').value = Number.parseInt(oldValue) - 1;
                };

                //  购物车 注册鼠标 点击事件
                $('.message-clothes-buyAndCart')[1].onclick = function () {
                    let colorFlag = false;
                    let sizeFlag = false;
                    for (let i = 1; i < colorLi.length; i++) {
                        if (colorLi[i].style.backgroundColor === 'red') {
                            colorFlag = true;
                        }
                    }
                    for (let i = 1; i < sizeLi.length; i++) {
                        if (sizeLi[i].style.backgroundColor === 'red') {
                            sizeFlag = true;
                        }
                    }
                    if (colorFlag === true && sizeFlag === true) {
                        //  添加购物车
                        if (getCookie('account') !== "") {
                            let name = result.body.name;
                            let price = result.body.discount;
                            let location = result.body.location;
                            let account = getCookie('account');
                            let count = document.getElementById("numberLi").value;
                            let imgPath = result.body.imgPath;
                            let itemId = result.body.id;
                            let category = result.body.category;
                            // -1 表示 点击按钮是购物车
                            addCart(name, price, location, account, count, imgPath, itemId, category, color, size, -1);
                        } else {
                            alert('亲爱的用户，请先登陆');
                            window.location.href = "../../html/login/login.html"
                        }
                    } else if (colorFlag === false) {
                        alert('亲爱的用户，请选择一个颜色');
                    } else if (sizeFlag === false) {
                        alert('亲爱的用户，请选择一个大小');
                    }
                };

                // 购买按钮  注册鼠标 点击事件
                $('.message-clothes-buyAndCart')[0].onclick = function () {
                    let colorFlag = false;
                    let sizeFlag = false;
                    for (let i = 1; i < colorLi.length; i++) {
                        if (colorLi[i].style.backgroundColor === 'red') {
                            colorFlag = true;
                        }
                    }
                    for (let i = 1; i < sizeLi.length; i++) {
                        if (sizeLi[i].style.backgroundColor === 'red') {
                            sizeFlag = true;
                        }
                    }
                    if (colorFlag === true && sizeFlag === true) {
                        //  添加购物车
                        if (getCookie('account') !== "") {
                            let name = result.body.name;
                            let price = result.body.discount;
                            let location = result.body.location;
                            let account = getCookie('account');
                            let count = document.getElementById("numberLi").value;
                            let imgPath = result.body.imgPath;
                            let itemId = result.body.id;
                            let category = result.body.category;
                            // -2 表示 点击按钮是 立即购买
                            addCart(name, price, location, account, count, imgPath, itemId, category, color, size, -2);
                        } else {
                            alert('亲爱的用户，请先登陆');
                            window.location.href = "../../html/login/login.html"
                        }
                    } else if (colorFlag === false) {
                        alert('亲爱的用户，请选择一个颜色');
                    } else if (sizeFlag === false) {
                        alert('亲爱的用户，请选择一个大小');
                    }
                }

            } else {
                alert('服务器出现一些 小差错...')
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

// 获取评论信息
function getCountsComments(id, category, domId) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8083/getCountsComments",
        data: `itemId=${id}&category=${category}`,
        success: function (result) {
            domId.innerHTML = result.body;
        },
        error: function () {
            alert("异常！");
        }
    });
}

// 获取所有的评论 List<Comment>
function getComments(id, category, currentPage, pageSize) {
    $('#comments').empty();
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8083/getPageComments",
        data: `itemId=${id}&category=${category}&currentPage=${currentPage}&pageSize=${pageSize}`,
        success: function (result) {
            if (result.body.items.length > 0) {
                let commentAccount = document.getElementById("comments");
                for (let i = 0; i < result.body.items.length; i++) {
                    $(commentAccount).append(`<div class="commentOneByOne">
                    <div id="comment-account">${result.body.items[i].account}</div>
                    <textarea id="comment-text" rows="5" cols="60" readonly="readonly">${result.body.items[i].message}</textarea>
                    <div id="comment-time">${result.body.items[i].strCommentTime}</div></div>`);
                }
                let domId = document.getElementById("comments");
                spanSquare(id, category, currentPage, pageSize, domId, result);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

// 购物车
function addCart(name, price, location, account, count, imgPath, itemId, category, color, size, flag) {
    $.ajax({
        async: true,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        url: "http://localhost:8082/insertCart",
        data: JSON.stringify({
            name: name,
            price: price,
            location: location,
            color: color.trim(),
            size: Number.parseInt(size.trim()),
            count: count,
            account: account,
            itemId: itemId,
            imgPath: imgPath,
            category: category
        }),
        success: function (result) {
            if (flag === -1) {
                alert("加入购物车 成功");
                window.location.href = "../../html/clothes/index.html";
            } else {
                // 点击立即购买 已经将数据丢到购物车里面了 现在拿到cartId  然后跳转到confirmOrder页面
                $.ajax({
                    async: true,
                    type: "GET",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    url: "http://localhost:8082/getCartIdByItemIdAndAccount",
                    data: `account=${account}&itemId=${itemId}&color=${color}&size=${size}`,
                    success: function (result) {
                        if (result.head.code === '200') {
                            window.location.href = '../../html/cart/confirmOrder.html?id='+result.body;
                        } else {
                            alert(result.head.result);
                        }
                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

// 渲染翻页小方块
function spanSquare(id, category, currentPage, pageSize, domId, result) {
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
            getComments(id, category, currentPage - 1, pageSize)
        }
    };
    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getComments(id, category, currentPage + 1, pageSize,);
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getComments(id, category, i, pageSize,);
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

// 当textare获取焦点时，判断是否用户存在
function ifExistWhenAddComments() {
    let commentsTextare = document.getElementById("myComments-textare");
    commentsTextare.onfocus = function () {
        let account = getCookie("account");
        if (account === "") {
            alert('亲爱的用户，请先登录');
            commentsTextare.onfocus = null; // 每一次都刷新 新的页面
            window.location.href = "../../html/login/login.html";
        }
    }
}

// 添加评论
function addComment(itemId, category) {
    let commentsButton = document.getElementById("myComments-button");
    let account = getCookie("account");
    let commentTime = new Date().getTime() / 1000;
    commentsButton.onclick = function () {
        if (document.getElementById("myComments-textare").value === "") {
            alert("亲爱的用户，评论不能为空哦！");
            return false;
        }
        $.ajax({
            async: true,
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8083/addComment",
            data: JSON.stringify({
                itemId: itemId,
                category: category,
                account: account,
                message: document.getElementById("myComments-textare").value,
                commentTime: commentTime
            }),
            success: function (result) {
                if (result.head.code === '200') {
                    getComments(itemId, category, 1, 5);
                    getCountsComments(itemId, category, document.getElementById("commentsSpan"));
                } else {
                    alert(result.head.result);
                }
            },
            error: function () {
                alert("异常！");
            }
        });

    }
}
