//根据字段名称数组拼接json数据
//keyList-key名集合
//valueList-输入框ID集合
function bilidParam(keyList,valueList) {
    var param={};
    for(var i=0;i<keyList.length;i++){
        var value="";
        var inputVal=$("#"+valueList[i]).val();
        //key对应的值存在即赋值
        if(inputVal){
            value="";
            value=inputVal;
        }

        param[keyList[i]]=value;
    }
    //转成字符串
    // var json = JSON.stringify(param);
    return param;
}

//将表格某列文本中的换行符转化成P标签，解决表格显示文本（原生自带换行格式）不换行问题
function replaceBr(index) {
    var content = $('.table-bordered tr td:nth-child('+index+')');
    content.each(function(){
        var txt = $(this).text();
        var j =0;
        var span = document.createElement("span");
        for(i=0;i<txt.length;i++){
            if(txt.charAt(i)=='\n'){
                var p = document.createElement("p");
                var partTxt = txt.slice(j,i);
                p.innerHTML = partTxt;
                //由于p标签内容为空时，页面不显示空行，加一个<br>
                if(partTxt==''){
                    p.appendChild(document.createElement("br"));
                }
                span.appendChild(p);
                j = i + 1;
            }
        }
        var p_end = document.createElement("p");
        p_end.innerHTML = txt.slice(j);
        $(this).text('');
        span.appendChild(p_end);
        $(this).append(span);
    });

}


function errorMsg(code, msg) {
    alert("错误码：[" + code + "]  摘要:[ " + msg + " ]");

}
function successMsg(msg) {
    alert("操作成功,摘要:[ " + msg + " ]");

}
// javascript 模块化
var ipsSystem = {
    //get请求
    doGet: function (url) {
        window.location.href = url;
    },
    //post请求
    doPost: function (url, PARAMS) {
        var temp = document.createElement("form");
        temp.action = url;
        temp.method = "post";
        temp.style.display = "none";
        for (var x in PARAMS) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = PARAMS[x];
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    },

    //选择菜单栏
    menu: {
        active: function (menuId,btnId) {
            $(".mm-show").removeClass("mm-show")
            $(".mm-active").removeClass("mm-active")
            if(menuId){
                $("#" + menuId).addClass("mm-show");
            }
            $("#" + btnId).addClass("mm-active");
        }
    },
    //页面模块
    page: {
        //get请求打开页面，页面在固定模块渲染
        getPage: function (url, errInfo) {
            $.ajax({
                url: url,
                type: "get",    //不区分大小写
                success: function (view) {
                    $('#rightContent').empty();
                    $('#rightContent').append(view);
                },
                error: function (e) {
                    alert(errInfo);
                    console.error(errInfo + ":" + e)
                }
            });
        }

    },
    //秒杀模块
    seckill: {
        url: {
            //秒杀详情页url
            detailUrl: function (seckillId) {
                return seckillId + "/detail";
            },
            //获取当前系统时间url
            getNowTimeUrl: function () {
                return "../time/now";
            },
            //获取秒杀暴露地址url
            exposerUrl: function (seckillId) {
                return "../" + seckillId + "/exposer";
            },
            //执行秒杀url
            executionUrl: function (url) {
                return "../" + url + "/execution";
            },
            //执行秒杀url(存储过程)
            executionProducerUrl: function (url) {
                return "../" + url + "/executionProducer";
            }
        }

    },
    saveHtml:{
        url:{
            //生成index1页面url
            saveIndex1:function () {
                return "./save/index1";
            },
            getView:function () {
                return "./save/getView";
            }

        }
    }

}

/**
 * 功能：固定表头
 * 参数   viewid     表格的id
 *       scrollid   滚动条所在容器的id
 *       size       表头的行数（复杂表头可能不止一行）
 */
function scroll(viewid,scrollid,size){
    // 获取滚动条容器
    var container = document.getElementById(scrollid);
    // 将表格拷贝一份
    var tb2 = document.getElementById(viewid).cloneNode(true);
    // 将拷贝得到的表格中非表头行删除
    for(var i=tb2.rows.length;i>size;i--){
        // 每次删除数据行的第一行
        tb2.deleteRow(size);
    }
    // 创建一个div
    var bak = document.createElement("div");
    // 将div添加到滚动条容器中
    container.appendChild(bak);
    // 将拷贝得到的表格在删除数据行后添加到创建的div中
    bak.appendChild(tb2);
    // 设置创建的div的position属性为absolute，即绝对定于滚动条容器（滚动条容器的position属性必须为relative）
    bak.style.position = "absolute";
    // 设置创建的div的背景色与原表头的背景色相同（貌似不是必须）
    //bak.style.backgroundColor = "#cfc";
    // 设置div的display属性为block，即显示div（貌似也不是必须，但如果你不希望总是显示拷贝得来的表头，这个属性还是有用处的）
    bak.style.display = "block";
    // 设置创建的div的left属性为0，即该div与滚动条容器紧贴
    bak.style.left = 0;
    // 设置div的top属性为0，初期时滚动条位置为0，此属性与left属性协作达到遮盖原表头
    bak.style.top = "0px";
    bak.style.width = "100%";
    // 给滚动条容器绑定滚动条滚动事件，在滚动条滚动事件发生时，调整拷贝得来的表头的top值，保持其在可视范围内，且在滚动条容器的顶端
    container.onscroll = function(){
        // 设置div的top值为滚动条距离滚动条容器顶部的距离值
        bak.style.top = this.scrollTop+"px";
    }
}

/**
 * 功能：判断数据中的key集合是否全部为空值
 * 使用该判断方法页面需要引用jeBox弹出框模块
 * @param value_list
 * @returns {boolean}
 */
function checkEmpty(value_list) {
    var str="";
    for(var i=0;i<value_list.length;i++){
        //1.ID字段不进行判空,
        //2.该key输入框页面存在不为null
        if(value_list[i]!="id"&&value_list[i]!="id_u"&&$("#"+value_list[i]).val()!=null){
            str+=$("#"+value_list[i]).val();
        }
    }
    if(str==""||str==null){
        jeBox.msg('输入框不能全部为空', {icon: 1,time: 2,maskLock: true, maskClose: true });
        return false;
    }
    return true;
}

//计算2个时间的差值，输出格式时：分：秒
function  count_time_difference(endTime,startTime){
    var  minus = Math.abs(endTime.getTime() - startTime.getTime())/1000; //两时间字符串相减，转成相差秒；
    var  hours = parseInt(minus/3600); //将相差毫秒转成小时
    var minutes = parseInt((minus %3600/60)); //将相差毫秒转成分钟
    var second=parseInt((minus%60));
    var  sum = hours+':'+minutes+":"+second; //小时和分钟拼接
    if (sum !=='NaN:NaN:NaN'){
        return sum;
    }
}


/**
 * 检查页面关键字是否为空
 * 使用该判断方法页面需要引用jeBox弹出框模块
 * @param keywords {"姓名":"name","年纪","age"}
 * @returns {boolean}通过返回true,拒绝返回false
 */
function checkKeywordsEmpty(keywords) {
    for(var p in keywords){//遍历json对象的每个key/value对,p为key
        if(!$("#" + keywords[p]).val()){
            jeBox.msg(p+'字段为空', {icon: 1,time: 2,maskLock: true, maskClose: true });
            return false;
        }
    }
    return true;
}

