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