function setCookie(cname, cvalue, exdays) {
    let d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires + "; path = /";
}

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function clearCookie(name,balance) {
    setCookie(name, "", -1);
    setCookie(balance, "", -1);
}
// 当点击退出登陆时，执行该方法
// 该方法在代码块中会被使用
// <a id='logoutStatus' href="javascript:void(0);" style="display: none;" onClick = "logout();">退出登录</a>
function logout() {
    clearCookie('account','balance');
    window.location.href = '\\Front\\html\\login\\login.html';
}

// 所有引入topBar的页面 都应该在window.onload 下 调用   initializeTopbar
function initializeTopbar() {
    let account = getCookie('account');
    if (account !== '') {
        let login = document.getElementById('loginStatus');
        let register = document.getElementById('registerStatus');
        let logout = document.getElementById('logoutStatus');
        let recharge = document.getElementById("recharge");
        let balance = document.getElementById("balance");
        login.innerHTML = '欢迎您，' + account;
        login.href = "javascript:void()";
        register.style.display = 'none';
        logout.style.color = 'black';
        logout.style.display = 'block';
        recharge.style.display = "block";
        balance.style.display = "block";
        $.ajax({
            async: true,
            type: "get",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:8080/getBalance",
            data: "account="+getCookie("account"),
            success: function (result) {
                if (result.head.code === '200') {
                    document.getElementById("accountMoney").innerHTML = result.body;
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

// 点击购物车的时候 判断用户登陆没有
function isLoginWhenClickCart(){
    this.onclick = function(){
        if(getCookie('account') === ''){
            alert('亲爱的用户，请先登陆');
            window.location.href = "/Front/html/login/login.html";
        } else{
            window.location.href = "/Front/html/cart/index.html";
        }
        return false;
    }
}

// 获取地址栏 参数
/**
 * @return {string}
 */
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
