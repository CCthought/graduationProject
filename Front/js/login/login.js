window.onload = function () {
    initializeTopbar();
    let login = document.getElementById('login');
    let account = document.getElementById('account');
    let password = document.getElementById('password');

    (function () {
        login.onclick = function () {
            if (account.value === "") {
                alert('用户名不能为空');
                return false;
            } else if (account.value.length < 3) {
                alert('用户名长度不能小于3');
                return false;
            }
            if (password.value === "") {
                alert('密码不能为空');
                return false;
            }

            //执行到这里 说明前面的校验已经成功
            $.ajax({
                async: true,
                type: "POST",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                url: "http://localhost:8080/login",
                data: $('#myForm').serialize(),
                success: function (result) {
                    if (result.head.code === '200') {
                        alert('登陆成功，跳转到首页');
                        setCookie('account', result.body, 30);
                        window.location.href = '../../index.html';
                    } else {
                        alert(result.head.result);
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });

        }
    })();

};