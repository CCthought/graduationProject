window.onload = function () {
    initializeTopbar();
    let id = GetQueryString("id");
    if (id === "-1") {
        getPageCarts(1, 5, getCookie("account"));
    } else {
        selectCartById(id); //这里返回的数据 也是类似于getPageCarts()
    }
};

// 获取购物车 数据
function getPageCarts(currentPage, pageSize, account) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8082/getPageCarts",
        data: `currentPage=${currentPage}&pageSize=${pageSize}&account=${account}`,
        success: function (result) {
            if (result.head.code === '200') {
                let domId = document.getElementById("cart");
                renderCarts(domId, result);
                commitOrder(result);
            } else {
                alert(result.head.result);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

function selectCartById(id) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8082/selectCartById",
        data: `id=${id}`,
        success: function (result) {
            if (result.head.code === '200') {
                let domId = document.getElementById("cart");
                renderCarts(domId, result);
                commitOrder(result);
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
function renderCarts(domId, result) {
    $(domId).empty();
    if (result.body.items.length === 0) {
        $(domId).append("<img alt='picture' src='../../images/cart.png' width='1015' height='838' style='margin-left: 300px'/>");
        return;
    }
    $(domId).append(`
        <div class="cart-header">
        <span style="margin-left: 180px;font-size: 25px;">商品信息</span>
        <span style="margin-left: 210px;font-size: 25px;">单价</span>
        <span style="margin-left: 120px;font-size: 25px;">数量</span>
        <span style="margin-left: 200px;font-size: 25px;">价格</span>
        <span style="margin-left: 200px;font-size: 25px;">相关操作</span>
        </div>
    `);

    for (let i = 0; i < result.body.items.length; i++) {
        let totalMoney = result.body.items[i].price * result.body.items[i].count;
        $(domId).append(`<div id="cart-message${result.body.items[i].id}" class="cart-message">
        <div class="cart-message-img"><img alt="picture" src="../../images/${result.body.items[i].imgPath}" width="100%" height="100%"/></div>
        <div class="cart-message-name">${result.body.items[i].name}</div>
        <div class="cart-message-price">${result.body.items[i].price}</div>
        <div class="cart-message-number">
            <ul>
                <li class="cart-message-number-li">数量</li>
                <li class="cart-message-number-li" style="margin-top: 6px">
                    <label>
                        <input readonly="readonly" id="numberLi${result.body.items[i].id}" type="text" name="numberLi" maxlength="3" style="width: 40px" value=${result.body.items[i].count} />
                    </label>
                </li>
                <li style="display: none;" id="plus${result.body.items[i].id}" class="cart-message-number-li" onmouseover="this.style.cursor = 'pointer'">+</li>
                <li style="display: none;" id="minus${result.body.items[i].id}" class="cart-message-number-li" onmouseover="this.style.cursor = 'pointer'"
                    style="font-size: 30px; margin-top: 2px;">-
                </li>
            </ul>
        </div>
        <div id="totalMoney${result.body.items[i].id}" class="cart-message-money">${totalMoney}</div>
        <div class="cart-message-operation">
            <div id="cart-message-operation-remove${result.body.items[i].id}" class="cart-message-operation-remove" 
            onmouseover="this.style.cursor='pointer'" onclick="linkComment(${result.body.items[i].itemId},${result.body.items[i].category})">追加评论</div>
            <div id="cart-message-operation-commit${result.body.items[i].id}" class="cart-message-operation-commit"
             onmouseover="this.style.cursor='pointer'" style="display: none;"
              onclick=window.location.href="../../html/cart/confirmOrder.html?id=${result.body.items[i].id}">结算</div>
        </div>
    </div>`);

        if (result.body.items[i].color) {
            $("#cart-message" + result.body.items[i].id).append(`
                        <div class="cart-message-absolute">
                            颜色：${result.body.items[i].color}<br />
                            尺寸：${result.body.items[i].size}
                         </div>
            `);
        }

        let numberLi = document.getElementById("numberLi" + result.body.items[i].id);
        let plus = document.getElementById("plus" + result.body.items[i].id);
        let minus = document.getElementById("minus" + result.body.items[i].id);
        let price = result.body.items[i].price;
        let totalMoneyDom = document.getElementById("totalMoney" + result.body.items[i].id);
        let cartId = result.body.items[i].id;
        focusOnNumber(numberLi, plus, minus, price, totalMoneyDom, cartId);

    }
    $(domId).append(`<div id="cart-message-removeAndCommit">
            共${result.body.totalRecords}件商品
            <div id="cart-message-removeall" class="cart-message-removeAndCommit" onmouseover="this.style.cursor = 'pointer'"
             style="background-color: rgb(255,237,237);display: none;" onclick="removeAllCarts()">移除所有</div>
            <div id="cart-message-removeall" class="cart-message-removeAndCommit" onmouseover="this.style.cursor = 'pointer'"
             style="background-color: rgb(255,0,54);display: none;" onclick="window.location.href='../../html/cart/confirmOrder.html?id=-1'">全部结算</div>
        </div>`);
    // 增加翻页按钮
    spanSquare(result.body.currentPage, result.body.pageSize, domId, result, getCookie('account'));
}

// 为数量 输入框 + - 注册相应的事件  !!!该页面完全没有使用 不过为了可维护性 没有删除
function focusOnNumber(numberLi, plus, minus, price, totalMoneyDom, cartId) {
    // 判断输入的数 是不是 正整数
    numberLi.onblur = function () {
        if (!(/(^[1-9]\d*$)/.test(numberLi.value))) {
            alert('你输入的数不是正整数');
            numberLi.value = 1;
            totalMoneyDom.innerHTML = price;
        } else {
            changeCount(cartId, numberLi.value); //更改数据库 count数量
        }
    };

    // plus +
    plus.onclick = function () {
        let oldValue = numberLi.value;
        numberLi.value = Number.parseInt(oldValue) + 1;
        changeCount(cartId, numberLi.value); //更改数据库 count数量
        totalMoneyDom.innerHTML = price * numberLi.value;
    };

    // minus -
    minus.onclick = function () {
        let oldValue = numberLi.value;
        if (Number.parseInt(oldValue) === 1) {
            return;
        }
        numberLi.value = Number.parseInt(oldValue) - 1;
        changeCount(cartId, numberLi.value); //更改数据库 count数量
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
            getPageCarts(currentPage - 1, pageSize, account);
        }
    };
    // 说明是最后一页
    pageTurningSquare[pageTurningSquare.length - 1].onclick = function () {
        if (currentPage === pageTurningSquare.length - 2) {
            return false;
        } else {
            getPageCarts(currentPage + 1, pageSize, account);
        }
    };
    //  为1,2,3 ... 小方块 注册点击事件
    for (let i = 1; i < pageTurningSquare.length - 1; i++) {
        pageTurningSquare[i].onclick = function () {
            getPageCarts(i, pageSize, account);
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

// !!!该页面完全没有使用 不过为了可维护性 没有删除
function changeCount(id, count) {
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

// 追加评论
function linkComment(itemId, category) {
    if (category === 1) {
        window.location.href = "../../html/clothes/clothes.html?id=" + itemId + "&category=" + category;
        return false;
    } else if (category === 2) {
        window.location.href = "../../html/food/food.html?id=" + itemId + "&category=" + category;
        return false;
    } else if (category === 3) {
        window.location.href = "../../html/book/book.html?id=" + itemId + "&category=" + category;
        return false;
    } else if (category === 4) {
        window.location.href = "../../html/trousers/trousers.html?id=" + itemId + "&category=" + category;
        return false;
    }
}

//为提交订单相关事件 做好准备
function commitOrder(result) {
    let addressDom = document.getElementById("order-span-address");
    let receiverDom = document.getElementById("order-span-receiver");
    let phoneDom = document.getElementById("order-span-phone");
    let moneyDom = document.getElementById("order-span-money");
    let commitOrderDom = document.getElementById("order-commit-reallyCommit");
    if (result.body.totalRecords > 1) {
        $.ajax({
            async: true,
            type: "GET",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8082/getAllMoney",
            data: "account=" + getCookie('account'),
            success: function (result) {
                if (result.head.code === '200') {
                    moneyDom.innerHTML = result.body;
                } else {
                    alert(result.head.result);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    } else {
        moneyDom.innerHTML = result.body.items[0].price * result.body.items[0].count;
    }
    // 订单 提交事件
    commitOrderDom.onclick = function () {
        if (document.getElementById("order-address").value === "") {
            let addressTips = document.getElementById("order-address-Tips");
            addressTips.style.display = "block";
            return false;
        }
        if (document.getElementById("order-receiver").value === "") {
            let receiverTips = document.getElementById("order-receiver-Tips");
            receiverTips.style.display = "block";
            return false;
        }
        if (document.getElementById("order-phone").value === "") {
            let phoneTips = document.getElementById("order-phone-Tips");
            phoneTips.style.display = "block";
            return false;
        }

        let balance = Number.parseInt(getCookie("balance"));
        if (balance < Number.parseInt(document.getElementById("order-span-money").innerHTML)) {
            alert("亲爱的用户，你的余额不足，请尽快充值");
            window.location.href = "../../html/common/recharge.html";
            return false;
        }
        // 执行到这里 说明前面的校验已经全部通过 可以向数据库提交订单了
        let orderAddress = document.getElementById("order-address");
        let orderReceiver = document.getElementById("order-receiver");
        let orderPhone = document.getElementById("order-phone");
        if (result.body.totalRecords <= 5) {
            insertOrder(result, orderAddress, orderReceiver, orderPhone);
        } else {  // 因为totalRecord大于5 所以 仅仅分页的数据 完全不够
            let orderAddress = document.getElementById("order-address");
            let orderReceiver = document.getElementById("order-receiver");
            let orderPhone = document.getElementById("order-phone");
            $.ajax({
                async: true,
                type: "GET",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                url: "http://localhost:8082/getAllCarts",
                data: "account=" + getCookie("account"),
                success: function (result) {
                    if (result.head.code === '200') {
                        insertOrder(result, orderAddress, orderReceiver, orderPhone); //result 没有items
                    } else {
                        alert(result.head.result);
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    };
    // 判断address receiver phone 输入框
    document.getElementById("order-address").onfocus = function () {
        hiddenTIps();
    };
    document.getElementById("order-address").onblur = function () {
        addressDom.innerHTML = document.getElementById("order-address").value;
    };
    document.getElementById("order-receiver").onfocus = function () {
        hiddenTIps()
    };
    document.getElementById("order-receiver").onblur = function () {
        receiverDom.innerHTML = document.getElementById("order-receiver").value;
    };
    document.getElementById("order-phone").onfocus = function () {
        hiddenTIps();
    };
    document.getElementById("order-phone").onblur = function () {
        if (!(/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/.test(document.getElementById("order-phone").value))) {
            alert("无效的手机号码");
            document.getElementById("order-phone").value = "";
            return false;
        }
        phoneDom.innerHTML = document.getElementById("order-phone").value;
    };

    function hiddenTIps() {
        document.getElementById("order-address-Tips").style.display = "none";
        document.getElementById("order-receiver-Tips").style.display = "none";
        document.getElementById("order-phone-Tips").style.display = "none";
    }
}

// money 可以是负 可以是正
function changeMoney(money, account) {
    $.ajax({
        async: true,
        type: "get",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8080/recharge",
        data: "money=" + money + "&account=" + account,
        success: function (result) {
            if (result.head.code === '200') {
                let oldValue = Number.parseInt(getCookie('balance'));
                setCookie('balance', oldValue + money, 30);
            } else {
                alert(result.head.result);
            }
        },
        error: function () {
            alert("异常！");
        }
    });
}

function insertOrder(result, orderAddress, orderReceiver, orderPhone) {
    let resultData = result;
    if (result.body.hasOwnProperty('items')) { // 说明是分页数据小于5
        let isLastOne = result.body.items.length - 1;
        for (let i = 0; i < result.body.items.length; i++) {
            $.ajax({
                async: true,
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                url: "http://localhost:8084/insertOrder",
                data: JSON.stringify({
                    itemId: result.body.items[i].itemId,
                    category: result.body.items[i].category,
                    name: result.body.items[i].name,
                    price: result.body.items[i].price,
                    location: result.body.items[i].location,
                    color: result.body.items[i].color.trim(),
                    size: Number.parseInt(result.body.items[i].size),
                    count: result.body.items[i].count,
                    account: getCookie('account'),
                    imgPath: result.body.items[i].imgPath,
                    address: orderAddress.value,
                    receiver: orderReceiver.value,
                    phone: orderPhone.value
                }),
                success: function (result) {
                    if (result.head.code === '200') {
                        if (i === isLastOne) {
                            alert('结算成功');
                            let money = Number.parseInt(document.getElementById("order-span-money").innerHTML);
                            let account = getCookie('account');
                            changeMoney(-money, account);
                            deleteCart(resultData, -1); // -1表示totalRecords < 5
                        }
                    } else {
                        if (i === isLastOne) {
                            alert(result.head.result);
                        }
                    }
                },
                error: function () {
                    if (i === isLastOne) {
                        alert("异常！");
                    }
                }
            });
        }
    } else { // 说明分页数据大于5
        let isLastOne = result.body.length - 1;
        for (let i = 0; i < result.body.length; i++) {
            $.ajax({
                async: true,
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                url: "http://localhost:8084/insertOrder",
                data: JSON.stringify({
                    itemId: result.body[i].itemId,
                    category: result.body[i].category,
                    name: result.body[i].name,
                    price: result.body[i].price,
                    location: result.body[i].location,
                    color: result.body[i].color.trim(),
                    size: Number.parseInt(result.body[i].size),
                    count: result.body[i].count,
                    account: getCookie('account'),
                    imgPath: result.body[i].imgPath,
                    address: orderAddress.value,
                    receiver: orderReceiver.value,
                    phone: orderPhone.value
                }),
                success: function (result) {
                    if (result.head.code === '200') {
                        if (i === isLastOne) {
                            alert('结算成功');
                            let money = Number.parseInt(document.getElementById("order-span-money").innerHTML);
                            let account = getCookie('account');
                            changeMoney(-money, account);
                            deleteCart(resultData, -2); // -2 表示totalRecords > 5
                        }
                    } else {
                        if (i === isLastOne) {
                            alert(result.head.result);
                        }
                    }
                },
                error: function () {
                    if (i === isLastOne) {
                        alert("异常！");
                    }
                }
            });
        }
    }
}

function deleteCart(result, flag) { // flag -2 表示 totalRecords > 5   -1 表示 totalRecords 小于 5
    if (flag === -2 || result.body.items.length > 1) {
        $.ajax({
            async: true,
            type: "GET",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8082/deleteAllCarts",
            data: "account=" + getCookie('account'),
            success: function (result) {
                if (result.head.code === '200') {
                    window.location.href = '../../html/cart/index.html';
                } else {
                    alert(result.head.result);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    } else {
        $.ajax({
            async: true,
            type: "GET",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8082/deleteCartById",
            data: "id=" + result.body.items[0].id,
            success: function (result) {
                if (result.head.code === '200') {
                    window.location.href = '../../html/cart/index.html';
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
