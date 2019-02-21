window.onload = function () {
    initializeTopbar();
    core();
};

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
            imgInterval = setInterval(leftFocus,5000);
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
                    imgInterval = setInterval(leftFocus,5000);
                    return false;
                }
                coreMiddleSquareSpans[currentIndex - 2].className = "current";
                // offsetLeft 没有 px单位  为left有px单位
                animate(coreMiddleDivUl, coreMiddleDivUl.offsetLeft + 650, 20);
                imgInterval = setInterval(leftFocus,5000);
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
                imgInterval = setInterval(leftFocus,5000);
                return false;
            }
            coreMiddleSquareSpans[currentIndex].className = "current";

            // offsetLeft 没有 px单位  为left有px单位
            animate(coreMiddleDivUl, coreMiddleDivUl.offsetLeft - 650, 20);
            imgInterval = setInterval(leftFocus,5000);
        }
    }

     let imgInterval = setInterval(leftFocus,5000);

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
}
//core js end *********************************************************************************
