window.onload = function () {
    initializeTopbar();
    let account = getCookie('account');
    getPageOrders(1, 5, account);
};

// 获取购物车 数据
function getPageOrders(currentPage, pageSize, account) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8084/getPageOrders",
        data: `currentPage=${currentPage}&pageSize=${pageSize}&account=${account}`,
        success: function (result) {
            if (result.head.code === '200') {
                let domId = document.getElementById("order");
                renderOrders(domId, result);
            } else {
                alert(result.head.result);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

// 渲染数据到页面
function renderOrders(domId, result) {
    $(domId).empty();
    if (result.body.items.length === 0) {
        $(domId).append("<img src='../../images/cart.png' width='1015' height='838' style='margin-left: 300px'/>");
        return;
    }
    $(domId).append(`
        <div class="order-header">
        <span style="margin-left: 180px;font-size: 25px;">订单信息</span>
        <span style="margin-left: 210px;font-size: 25px;">单价</span>
        <span style="margin-left: 120px;font-size: 25px;">数量</span>
        <span style="margin-left: 200px;font-size: 25px;">价格</span>
        <span style="margin-left: 200px;font-size: 25px;">相关操作</span>
        </div>
    `);

    for (let i = 0; i < result.body.items.length; i++) {
        let totalMoney = result.body.items[i].price * result.body.items[i].count;
        $(domId).append(`<div id="order-message${result.body.items[i].id}" class="order-message">
        <div class="order-message-img"><img src="../../images/${result.body.items[i].imgPath}" width="100%" height="100%"/></div>
        <div class="order-message-name">${result.body.items[i].name}</div>
        <div class="order-message-price">${result.body.items[i].price}</div>
        <div class="order-message-number">
            <ul>
                <li class="order-message-number-li">数量</li>
                <li class="order-message-number-li" style="margin-top: 6px">
                    <label>
                        <input readonly="readonly" id="numberLi${result.body.items[i].id}" type="text" name="numberLi" maxlength="3" style="width: 40px" value=${result.body.items[i].count} />
                    </label>
                </li>
                <li style = "display: none;" id="plus${result.body.items[i].id}" class="order-message-number-li" onmouseover="this.style.cursor = 'pointer'">+</li>
                <li style = "display: none;"id="minus${result.body.items[i].id}" class="order-message-number-li" onmouseover="this.style.cursor = 'pointer'"
                    style="font-size: 30px; margin-top: 2px;">-
                </li>
            </ul>
        </div>
        <div id="totalMoney${result.body.items[i].id}" class="order-message-money">${totalMoney}</div>
        <div class="order-message-operation">
            <div style="display: none;" id="order-message-operation-remove${result.body.items[i].id}" class="order-message-operation-remove" 
            onmouseover="this.style.cursor='pointer'" onclick="removeOrder(${result.body.items[i].id})">删除</div>
            <div id="order-message-operation-commit${result.body.items[i].id}" class="order-message-operation-commit"
             onmouseover="this.style.cursor='pointer'">买家已于<br />${result.body.items[i].strOrderTime}&nbsp;&nbsp;付款</div>
        </div>
    </div>`);

        if (result.body.items[i].color) {
            $("#order-message" + result.body.items[i].id).append(`
                        <div class="order-message-absolute">
                            颜色：${result.body.items[i].color}<br />
                            尺寸：${result.body.items[i].size}
                         </div>
            `);
        }

        $("#order-message" + result.body.items[i].id).append(`
                         <div class="order-location-absolute">
                            商品正在从<span style="color: red;">${result.body.items[i].location}</span>
                            运往<span style="color: red;">${result.body.items[i].address}</span>
                         </div>
        `);

        let numberLi = document.getElementById("numberLi" + result.body.items[i].id);
        let plus = document.getElementById("plus" + result.body.items[i].id);
        let minus = document.getElementById("minus" + result.body.items[i].id);
        let price = result.body.items[i].price;
        let totalMoneyDom = document.getElementById("totalMoney" + result.body.items[i].id);
        let orderId = result.body.items[i].id;
        focusOnNumber(numberLi, plus, minus, price, totalMoneyDom, orderId);

    }
    $(domId).append(`<div id="order-message-removeAndCommit">
               <span style="font-size: 30px;">共<span style="color: red;">${result.body.totalRecords}</span>个订单</span>
            <div id="order-message-removeall" class="order-message-removeAndCommit" onmouseover="this.style.cursor = 'pointer'"
             style="background-color: rgb(255,237,237);display: none" onclick="removeAllOrders()">移除所有</div>
            <div id="order-message-removeall" class="order-message-removeAndCommit" onmouseover="this.style.cursor = 'pointer'"
             style="background-color: rgb(255,0,54);display: none" onclick="window.location.href='../../html/order/confirmOrder.html?id=-1'">全部结算</div>
        </div>`);
    // 增加翻页按钮
    spanSquare(result.body.currentPage, result.body.pageSize, domId, result, getCookie('account'));

}

// 为数量 输入框 + - 注册相应的事件
function focusOnNumber(numberLi, plus, minus, price, totalMoneyDom, orderId) {
    // 判断输入的数 是不是 正整数
    numberLi.onblur = function () {
        if (!(/(^[1-9]\d*$)/.test(numberLi.value))) {
            alert('你输入的数不是正整数');
            numberLi.value = 1;
            totalMoneyDom.innerHTML = price;
        } else {
            changgeCount(orderId, numberLi.value); //更改数据库 count数量
        }
    };

    // plus +
    plus.onclick = function () {
        let oldValue = numberLi.value;
        numberLi.value = Number.parseInt(oldValue) + 1;
        changgeCount(orderId, numberLi.value); //更改数据库 count数量
        totalMoneyDom.innerHTML = price * numberLi.value;
    };

    // minus -
    minus.onclick = function () {
        let oldValue = numberLi.value;
        if (Number.parseInt(oldValue) === 1) {
            return;
        }
        numberLi.value = Number.parseInt(oldValue) - 1;
        changgeCount(orderId, numberLi.value); //更改数据库 count数量
        totalMoneyDom.innerHTML = price * numberLi.value;
    };
}

// 渲染翻页小方块
function spanSquare(currentPage, pageSize, domId, result, account) {
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
            getPageOrders(currentPage - 1, pageSize, account);
        }
    };
    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getPageOrders(currentPage + 1, pageSize, account);
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getPageOrders(i, pageSize, account);
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

function changgeCount(id, count) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8082/changeCount",
        data: `id=${id}&count=${count}`,
        success: function (result) {
            if (result.head.code === '200') {

            } else {
                alert(result.head.result);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

function removeOrder(id) {
    let isDelete = confirm("你确定要删除该商品吗？");
    if (isDelete === true) {
        $.ajax({
            async: true,
            type: "GET",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8082/deleteOrderById",
            data: `id=${id}`,
            success: function (result) {
                if (result.head.code === '200') {
                    getPageOrders(1, 5, getCookie('account'));
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

function removeAllOrders() {
    let isRemove = confirm("您确定移除所有的购物车吗？");
    if (isRemove === true) {
        $.ajax({
            async: true,
            type: "GET",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8082/removeAllOrders",
            data: `account=${getCookie('account')}`,
            success: function (result) {
                if (result.head.code === '200') {
                    getPageOrders(1, 5, getCookie('account'));
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