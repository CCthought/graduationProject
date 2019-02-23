window.onload = function () {
    let forms = document.getElementsByTagName('form');
    let form = forms[0];

    let account = document.getElementById('account');
    let realname = document.getElementById('realname');
    let password = document.getElementById('password');
    let confirmPassword = document.getElementById('confirmPassword');
    let phone = document.getElementById('phone');
    let email = document.getElementById('email');
    let sex = document.getElementsByName('sex');
    let ids = [account, realname, password, confirmPassword, phone, email];

    let accountSpan = document.getElementById('accountSpan');
    let realnameSpan = document.getElementById('realnameSpan');
    let passwordSpan = document.getElementById('passwordSpan');
    let phoneSpan = document.getElementById('phoneSpan');
    let emailSpan = document.getElementById('emailSpan');
    let sexSpan = document.getElementById('sexSpan');
    let spans = [realnameSpan, passwordSpan, phoneSpan, emailSpan, sexSpan];

    // 判断能否提交表单
    let flag = true;
    // 专门判断ajax请求unique account 是否成功
    let ajaxSuccess = false;

    // 为ids中的每个元素绑定事件
    (function () {
        for (let i = 0; i < ids.length; i++) {
            ids[i].onfocus = function () {
                for (let i = 0; i < spans.length; i++) {
                    spans[i].innerHTML = '';
                }
            }
        }
        // 为性别单独注册事件
        for (let i = 0; i < sex.length; i++) {
            sex[i].onclick = function () {
                for (let i = 0; i < spans.length; i++) {
                    spans[i].innerHTML = '';
                }
            }
        }
    })();
    // 单独为account注册一个事件，用于判断后台数据库有否有相同的名字
    (function () {
        account.onblur = function () {
            // action="http://localhost:8080/register" method="post" enctype="application/x-www-form-urlencoded"
            // data: $('myForm').serialize(),
            if(account.value === ""){
                accountSpan.style.color = 'red';
                accountSpan.innerHTML = '用户名不能为空';
                accountSpan.style.display = 'inline';
                return false;
            } else if (account.value.length < 3) {
                accountSpan.style.color = 'red';
                accountSpan.innerHTML = '用户名长度不能小于2';
                accountSpan.style.display = 'inline';
                return false;
            }

            $.ajax({
                async: true,
                type: "GET",
                dataType: "json",
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                url: "http://localhost:8080/isUniqueAccount",
                data: "account="+account.value,
                success: function (result) {
                    if (result.head.code == 200) {
                        accountSpan.style.color = 'green';
                        accountSpan.innerHTML = '此用户可以使用';
                        accountSpan.style.display = 'inline';
                        ajaxSuccess = true;
                    } else{
                        accountSpan.style.color = 'red';
                        accountSpan.innerHTML = result.head.result;
                        accountSpan.style.display = 'inline';
                        ajaxSuccess = false;
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    })();

    // 注册表单提交事件
    (function () {
        document.getElementById('register').onclick = function () {
            flag = true;
            if (account.value === "") {
                accountSpan.style.color = 'red';
                accountSpan.innerHTML = '用户名不能为空';
                accountSpan.style.display = 'inline';
                flag = false;
            } else if (account.value.length < 3) {
                accountSpan.style.color = 'red';
                accountSpan.innerHTML = '用户名长度不能小于2';
                accountSpan.style.display = 'inline';
                flag = false;
            }

            if (realname.value === "") {
                realnameSpan.innerHTML = '真实姓名不能为空';
                realnameSpan.style.display = 'inline';
                flag = false;
            }

            if (password.value === "" && confirmPassword.value === "") {
                passwordSpan.innerHTML = "密码不能为空";
                passwordSpan.style.display = 'inline';
                flag = false;
            } else if (password.value !== confirmPassword.value) {
                passwordSpan.innerHTML = "两次输入的密码不一致";
                passwordSpan.style.display = 'inline';
                flag = false;
            }

            if (phone.value === "") {
                phoneSpan.innerHTML = '电话不能为空';
                phoneSpan.style.display = 'inline';
                flag = false;
            } else if (!(/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/.test(phone.value))) {
                phoneSpan.innerHTML = '无效的手机号码';
                phoneSpan.style.display = 'inline';
                flag = false;
            }

            if (email.value === "") {
                emailSpan.innerHTML = '邮箱不能为空';
                emailSpan.style.display = 'inline';
                flag = false;
            } else if (!(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+(([.\-])[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(email.value))) {
                emailSpan.innerHTML = '无效的邮箱地址';
                emailSpan.style.display = 'inline';
                flag = false;
            }
            let isEmptySex = 0;
            for (let i = 0; i < sex.length; i++) {
                if (sex[i].checked) {
                    isEmptySex = 1;
                    break;
                }
            }

            if (isEmptySex === 0) {
                sexSpan.innerHTML = '性别不能为空';
                sexSpan.style.display = 'inline';
                flag = false;
            }
            if (flag === false || ajaxSuccess === false) {
                return false;
            }
            //如果程序执行到这里，说明前面的 参数 校验已经通过了
            $.ajax({
                async: true,
                type: "POST",
                dataType: "json",
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                url: "http://localhost:8080/register",
                data: $('#myForm').serialize(),
                success: function (result) {
                    if (result.head.code == 200) {
                       alert('注册成功,到登陆界面');
                       window.location.href = "../../html/login/login.html";
                    } else{
                        alert('注册失败 ... 请稍后尝试')
                    }
                },
                error: function () {
                    alert("注册失败 ... 请稍后尝试");
                }
            });
        }
    })();
};

