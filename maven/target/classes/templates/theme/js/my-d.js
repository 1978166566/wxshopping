$(function () {
                    $("#cur").click(function () {
                        AjaxSel(0);
                    });
                    $("#cur1").click(function () {
                        AjaxSel(1);
                    });
                    $("#cur2").click(function () {
                        AjaxSel(2);
                    });
                    $("#cur3").click(function () {
                        AjaxSel(3);
                    });
                    $("#cur4").click(function () {
                        AjaxSel(4);
                    });

                    /**
                     * 按类型查询
                     * @param type
                     * @constructor
                     */
                    function AjaxSel(type)
                    {
                        $("input[name=o_type]").val(type);
                        $("input[name=pagenum]").val(1);
                        var userid=$("#userid").val();
                        $.post("Selajax",{"id":userid,"o_type":type},function (data) {
                            AjaxSel1(data);
                        });
                    }

                    /**
                     * 查询到值后绑定到元素上
                     * @param data
                     * @constructor
                     */
                    function AjaxSel1(data)
                    {
                        $("#pagesfye div").remove();
                        $(".s li").remove();
                        if(data=="n")
                        {
                            return;
                        }
                        var result=$.parseJSON(data.split(';')[0]);
                        $("input[name=pages]").val(data.split(';')[1]);
                        var zong="";
                        var li="";
                        for(var i in result)
                        {
                            li="<li>\n" +
                                "<div class=\"member-minute clearfix\">\n" +
                                "<span>"+new Date(result[i].o_time).getFullYear()+"-"+
                                (new Date(result[i].o_time).getMonth()+1<10?'0'+(new Date(result[i].o_time).getMonth()+1):new Date(result[i].o_time).getMonth()+1)+"-"+
                                new Date(result[i].o_time).getDate()+" "+
                                (new Date(result[i].o_time).getHours()<10?'0'+(new Date(result[i].o_time).getHours()):new Date(result[i].o_time).getHours())+":"+
                                (new Date(result[i].o_time).getMinutes()<10?'0'+(new Date(result[i].o_time).getMinutes()):new Date(result[i].o_time).getMinutes())+":"+
                                (new Date(result[i].o_time).getSeconds()<10?'0'+(new Date(result[i].o_time).getSeconds()):new Date(result[i].o_time).getSeconds())+"</span>\n"+
                                "<span>订单号：<em id='o_number'>"+result[i].o_number+
                                "</em></span>\n"+
                                "</div>\n"+
                                "<div class=\"member-circle clearfix\">\n"+
                                "<div class=\"ci1\">";
                            for (var j in result[i].list)
                            {
                                li+="<div class=\"ci7 clearfix\">\n" +
                                    "<span class=\"gr1\"><a href="+"discountProduct?cyid="+result[i].list[j].CY_ID+"><img src=" +result[i].list[j].P_IMAGE+
                                    " width=\"60\" height=\"60\" title=\"\" about=\"\"></a></span>\n" +
                                    "<span class=\"gr2\"><a href="+"discountProduct?cyid="+result[i].list[j].CY_ID+">" +result[i].list[j].CY_NAME+
                                    "</a></span>\n" +
                                    "<span class=\"gr3\">X<em>" +result[i].list[j].OD_QUANTITY+
                                    "</em></span>\n" +
                                    "</div>";
                            }
                            li+="</div>\n" +
                                "<div class=\"ci2\" >" +result[i].list[0].U_NAME+"</div>\n" +
                                "<div class=\"ci3\"><b>￥<em>" +result[i].o_totalprice+
                                "</em></b>";
                            if (result[i].o_payment==1)
                            {
                                li+="<p>货到付款</p>\n";
                            }else {
                                li+="<p>在线支付</p>";
                            }
                            li+="</div>\n" +
                                "<div class=\"ci4\"><p>" +
                                new Date(result[i].o_time).getFullYear()+"-"+
                                (new Date(result[i].o_time).getMonth()+1<10?'0'+(new Date(result[i].o_time).getMonth()+1):new Date(result[i].o_time).getMonth()+1)+"-"+
                                new Date(result[i].o_time).getDate()+
                                "</p></div>\n" +
                                "<div class=\"ci5\">";
                            if(result[i].o_type==1)
                            {
                                li+="<p>等待付款</p>";
                            }else if(result[i].o_type==2){
                                li+="<p>等待商家发货</p>";
                            }else if(result[i].o_type==3){
                                li+="<p>等待收货</p>";
                            }else if(result[i].o_type==4){
                                li+="<p>交易完成</p>";
                            }else if(result[i].o_type==5)
                            {
                                li+="<p>订单已取消</p>";
                            }else if(result[i].o_type==6){
                                li+="<p>已退货</p>";
                            }

                            li+="</div>\n" +
                                "<div class=\"ci5 ci8\">";
                            if(result[i].o_type==4||result[i].o_type==5||result[i].o_type==6)
                            {
                                li+="<p><a href=\"queryorder?o_number="+result[i].o_number+"&id="+result[i].u_id+
                                    "\">查看</a> | <a id='delorder' href=\"#\">删除</a></p>";
                            }
                            if(result[i].o_type==4)
                            {
                                li+="<p><a id='salesreturn' href=\"#\">退货</a></p>";
                            }
                            if(result[i].o_type!=4&&result[i].o_type!=5&&result[i].o_type!=6)
                            {
                                li+="<p><a href=\"queryorder?o_number="+result[i].o_number+"&id="+result[i].u_id+
                                    "\">查看</a> | <a id='cancellation' href=\"#\">取消订单</a> </p>";
                            }
                            if(result[i].o_type==3)
                            {
                                li+="<p><a id='confirmreceipt' href=\"#\" class=\"member-touch\">确认收货</a> </p>";
                            }
                            li+="</div>\n" + "</div>\n" + "</li>";
                            zong+=li;
                        }
                        var pages=$.parseJSON(data.split(';')[2]);
                        var pagenum=$("input[name=pagenum]").val();
                        var div="<div class=\"fr pc-search-g pc-search-gs\">\n" +
                            "<a class=\"fl\" id=\"s\" href=\"javascript:void(0);\">上一页</a>\n" +
                            "<p id=\"p\" style=\"display: inline;\">";
                        for(var j in pages)
                        {
                            if(pages[j]!=0)
                            {
                                if(pages[j]==pagenum)
                                {
                                    div+="<a href=\"javascript:void(0);\" class='pagenum' value='" +pages[j]+ "' style=\"color: red;\">" +pages[j]+ "</a>\n";
                                }else{
                                    div+="<a href=\"javascript:void(0);\" class='pagenum' value='" +pages[j]+ "'>"+pages[j]+"</a>\n";
                                }
                            }
                        }
                        div+="</p><a id=\"x\" href=\"javascript:void(0);\">下一页</a></div>";
                        $("#pagesfye").append(div);
                        $(".s").append(zong);
                        fn();
                    }

                    /**
                     * 上一页
                     */
                    $("#pagesfye").on("click",'#s',function () {
                        var o_type=$("input[name=o_type]").val();
                        var pagenum=$("input[name=pagenum]").val();
                        var userid=$("#userid").val();
                        if(pagenum!=1)
                        {
                            --pagenum;
                            $("input[name=pagenum]").val(pagenum);
                        }
                        $.post("Selajax",{"id":userid,"o_type":o_type,"pageno":pagenum},function (data) {
                            AjaxSel1(data);
                        });
                    });

                    /**
                     * 下一页
                     */
                    $("#pagesfye").on('click','#x',function () {
                        var o_type=$("input[name=o_type]").val(); //类型
                        var pagenum=$("input[name=pagenum]").val(); //当前页码
                        var pages=$("input[name=pages]").val(); //总页数
                        var userid=$("#userid").val();
                        if(pagenum!=pages)
                        {
                            ++pagenum;
                            $("input[name=pagenum]").val(pagenum);
                        }
                        $.post("Selajax",{"id":userid,"o_type":o_type,"pageno":pagenum},function (data) {
                            AjaxSel1(data);
                        });
                    });

                    /**
                     * 点击相应的页数
                     */
                    $("#pagesfye").on('click','.pagenum',function () {
                       var o_type=$("input[name=o_type]").val();
                       var pagenum=$(this).attr("value");
                        var userid=$("#userid").val();
                       $("input[name=pagenum]").val(pagenum);
                       $.post("Selajax",{"id":userid,"o_type":o_type,"pageno":pagenum},function (data){
                           AjaxSel1(data);
                       });
                    });

                    /**
                     * 取消订单
                     */
                    $(".s").on("click","#cancellation",function () {
                        var o_number=$(this).parent().parent().parent().prev().find("#o_number").html();
                        if(confirm("是否取消订单?"))
                        {
                            $.post("Quantity",{"o_number":o_number,"o_type":5},function (data) {
                                    if(data=="no"){
                                        layer.msg('订单取消失败',{icon:2,time:1000});
                                        return;
                                    }
                                    if(data=="success")
                                    {
                                        window.location="getorders?id="+1;
                                    }
                            });
                        }
                    });

                    /**
                     * 删除订单
                     */
                    $(".s").on("click","#delorder",function (){
                        var o_number=$(this).parent().parent().parent().prev().find("#o_number").html();
                        if(confirm("是否删除?"))
                        {
                            $.post("delorder",{"o_number":o_number},function (data) {
                                if(data=="no"){
                                    layer.msg('删除订单失败',{icon:2,time:1000});
                                    return;
                                }
                                if(data=="success")
                                {
                                    window.location="getorders?id="+1;
                                }
                            });
                        }
                    });

                    /**
                     *退货
                     */
                    $(".s").on("click","#salesreturn",function (){
                        var o_number=$(this).parent().parent().parent().prev().find("#o_number").html();
                        if(confirm("是否退货?"))
                        {
                                $.post("Quantity",{"o_number":o_number,"o_type":6},function (data) {
                                    if(data=="no")
                                    {
                                        layer.msg('退货失败',{icon:2,time:1000});
                                        return;
                                    }
                                    if(data=="success")
                                    {
                                        window.location="getorders?id="+1;
                                    }
                                });
                        }
                    });

                    /**
                     * 确认收货
                      */
                    $(".s").on("click","#confirmreceipt",function () {
                        var o_number=$(this).parent().parent().parent().prev().find("#o_number").html();
                            $.post("Quantity",{"o_number":o_number,"o_type":4},function (data) {
                                if(data=="no")
                                {
                                    layer.msg('收货失败',{icon:2,time:1000});
                                    return;
                                }
                                if(data=="success")
                                {
                                    window.location="getorders?id="+1;
                                }
                            });
                    });

                    /**
                     * 按订单号查询订单
                     */
                    $("#isorders").click(function () {
                        var o_number=$("input[name=number]").val();
                        var userid=$("#userid").val();
                        var zong="";
                        $.post("isnumber",{"number":o_number,"id":userid},function (data){
                            if(data==2)
                            {
                                layer.msg('此订单不存在',{icon:2,time:1000});
                             return;
                            }else{
                                $.post("SelOrder",{"number":o_number,"id":userid},function (data) {
                                        $(".s li").remove();
                                        $("#pagesfye div").remove();
                                        var json=$.parseJSON(data);
                                        li="<li>\n" +
                                            "<div class=\"member-minute clearfix\">\n" +
                                            "<span>"+new Date(json.o_time).getFullYear()+"-"+
                                            (new Date(json.o_time).getMonth()+1<10?'0'+(new Date(json.o_time).getMonth()+1):new Date(json.o_time).getMonth()+1)+"-"+
                                            new Date(json.o_time).getDate()+" "+
                                            (new Date(json.o_time).getHours()<10?'0'+(new Date(json.o_time).getHours()):new Date(json.o_time).getHours())+":"+
                                            (new Date(json.o_time).getMinutes()<10?'0'+(new Date(json.o_time).getMinutes()):new Date(json.o_time).getMinutes())+":"+
                                            (new Date(json.o_time).getSeconds()<10?'0'+(new Date(json.o_time).getSeconds()):new Date(json.o_time).getSeconds())+"</span>\n"+
                                            "<span>订单号：<em id='o_number'>"+json.o_number+
                                            "</em></span>\n"+
                                            "</div>\n"+
                                            "<div class=\"member-circle clearfix\">\n"+
                                            "<div class=\"ci1\">";
                                        for (var j in json.list)
                                        {
                                            li+="<div class=\"ci7 clearfix\">\n" +
                                                "<span class=\"gr1\"><a href="+"discountProduct?cyid="+json.list[j].CY_ID+"><img src=" +json.list[j].P_IMAGE+
                                                " width=\"60\" height=\"60\" title=\"\" about=\"\"></a></span>\n" +
                                                "<span class=\"gr2\"><a href="+"discountProduct?cyid="+json.list[j].CY_ID+">" +json.list[j].CY_NAME+
                                                "</a></span>\n" +
                                                "<span class=\"gr3\">X<em>" +json.list[j].OD_QUANTITY+
                                                "</em></span>\n" +
                                                "</div>";
                                        }
                                        li+="</div>\n" +
                                            "<div class=\"ci2\" >" +json.list[0].U_NAME+"</div>\n" +
                                            "<div class=\"ci3\"><b>￥<em>" +json.o_totalprice+
                                            "</em></b>";
                                        if (json.o_payment==1)
                                        {
                                            li+="<p>货到付款</p>\n";
                                        }else {
                                            li+="<p>在线支付</p>";
                                        }
                                        li+="</div>\n" +
                                            "<div class=\"ci4\"><p>" +
                                            new Date(json.o_time).getFullYear()+"-"+
                                            (new Date(json.o_time).getMonth()+1<10?'0'+(new Date(json.o_time).getMonth()+1):new Date(json.o_time).getMonth()+1)+"-"+
                                            new Date(json.o_time).getDate()+
                                            "</p></div>\n" +
                                            "<div class=\"ci5\">";
                                        if(json.o_type==1)
                                        {
                                            li+="<p>等待付款</p>";
                                        }else if(json.o_type==2){
                                            li+="<p>等待商家发货</p>";
                                        }else if(json.o_type==3){
                                            li+="<p>等待收货</p>";
                                        }else if(json.o_type==4){
                                            li+="<p>交易完成</p>";
                                        }else if(json.o_type==5)
                                        {
                                            li+="<p>订单已取消</p>";
                                        }else if(json.o_type==6){
                                            li+="<p>已退货</p>";
                                        }

                                        li+="</div>\n" +
                                            "<div class=\"ci5 ci8\">";
                                        if(json.o_type==4||json.o_type==5||json.o_type==6)
                                        {
                                            li+="<p><a href=\"queryorder?o_number="+json.o_number+"id="+json.u_id+
                                                "\">查看</a> | <a id='delorder' href=\"#\">删除</a></p>";
                                        }
                                        if(json.o_type==4)
                                        {
                                            li+="<p><a id='salesreturn' href=\"#\">退货</a></p>";
                                        }
                                        if(json.o_type!=4&&json.o_type!=5&&json.o_type!=6)
                                        {
                                            li+="<p><a href=\"queryorder?o_number="+json.o_number+"id="+json.u_id+
                                                "\">查看</a> | <a id='cancellation' href=\"#\">取消订单</a> </p>";
                                        }
                                        if(json.o_type==3)
                                        {
                                            li+="<p><a id='confirmreceipt' href=\"#\" class=\"member-touch\">确认收货</a> </p>";
                                        }
                                        li+="</div>\n" + "</div>\n" + "</li>";
                                        zong+=li;
                                    $(".s").append(zong);
                                    $("#cur").addClass("cur").siblings().removeClass("cur");
                                    fn();
                                });
                            }
                        });
                    });
                    fn();
                    function fn() {
                        var height=$(".s li");
                        $.each(height,function(index,obj){
                            var parent=$(this).children(":eq(1)");
                            var hei=parent.height();
                            var child=parent.children(":gt(0)");
                            $.each(child,function(index1,ibj){
                                $(ibj).css("height",hei-20);
                            })
                        })
                    }

});