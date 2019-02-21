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

function clearCookie(name) {
    setCookie(name, "", -1);
}
// 当点击退出登陆时，执行该方法
function logout() {
    clearCookie('account');
    window.location.href = '\\Front\\html\\login\\login.html';
}

// 所有引入topBar的页面 都应该在window.onload 下 调用   initializeTopbar
function initializeTopbar() {
    let account = getCookie('account');
    if (account !== '') {
        let login = document.getElementById('loginStatus');
        let register = document.getElementById('registerStatus');
        let logout = document.getElementById('logoutStatus');

        login.innerHTML = '欢迎您，' + account;
        login.href = "javascript:void()";
        register.style.display = 'none';
        logout.style.color = 'black';
        logout.style.display = 'block';
    }
}