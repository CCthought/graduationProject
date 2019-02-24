window.onload = function () {
    let href = window.location.href;
    let temp = href.lastIndexOf('?');
    // 拿到地址栏的id
    let id = Number.parseInt(href.substring(temp + 4, href.length));
    getUniqueClothes(id);
};

function getUniqueClothes(id) {
    $.ajax({
        async: true,
        type: "GET",
        dataType: "json",
        data: `id=${id}`,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:8081/getUniqueClothes",
        success: function (result) {
            if (result.head.code === '200') {
                $('#message-picture').append(`<img src="../../images/clothes/${result.body.imgPath}" width="100%" height="100%" alt="picture" />`);
                document.getElementById('message-clothes-name').innerHTML = result.body.name;
                document.getElementById('message-clothes-price-li-del').innerHTML = result.body.price;
                document.getElementById('message-clothes-price-li-span').innerHTML = result.body.discount;
                document.getElementById('saledSpan').innerHTML = result.body.saled;
                document.getElementById('commentsSpan').innerHTML = result.body.comments;
                document.getElementById('locationSpan').innerHTML = result.body.location;
                let colorUl = document.getElementById('message-clothes-color-ul');
                for (let i = 0; i < result.body.color.length; i++) {
                    $(colorUl).append(` <li class="message-clothes-color-li">${result.body.color[i]}</li>`);
                }
                let sizeUl = document.getElementById('message-clothes-size-ul');
                for (let i = 0; i < result.body.color.length; i++) {
                    $(sizeUl).append(` <li class="message-clothes-size-li">${result.body.size[i]}</li>`);
                }

                // 为颜色 注册点击事件
                let colorLi = document.getElementById('message-clothes-color-ul').children;
                for (let i = 1; i < colorLi.length; i++) {
                    colorLi[i].onclick = function () {
                        for (let i = 1; i < colorLi.length; i++) {
                            colorLi[i].style.backgroundColor = "#DDDDDD";
                        }
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
                    }
                    document.getElementById('numberLi').value = 1;
                };

                // plus +
                document.getElementById('plus').onclick = function () {
                   let oldValue =  document.getElementById('numberLi').value;
                    document.getElementById('numberLi').value = Number.parseInt(oldValue) + 1;
                };

                // minus -
                document.getElementById('minus').onclick = function () {
                    let oldValue =  document.getElementById('numberLi').value;
                    if(Number.parseInt(oldValue) === 1){
                        return;
                    }
                    document.getElementById('numberLi').value = Number.parseInt(oldValue) - 1;
                };

                // 购买按钮 和 购物车 注册鼠标 点击事件
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
                        alert('abc');
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