$(function(){
            $("#xpage").click(function () {
               var pagenumber=$("#page_number").html(); //订单号
               var pagenum=$("#pagenum").html(); //当前页
               $.post("AjaxQueryOrder",{"o_number":pagenumber,"pageno":++pagenum},function (data){
                    Ajaxfun(data);
               });
            });

            $("#spage").click(function () {
                var pagenumber=$("#page_number").html(); //订单号
                var pagenum=$("#pagenum").html(); //当前页
                $.post("AjaxQueryOrder",{"o_number":pagenumber,"pageno":--pagenum},function (data){
                    Ajaxfun(data);
                });
            });

            $("#parentpagenum").on("click","#nepagenum",function () {
                var pagenumber=$("#page_number").html(); //订单号
                var pagenum=$(this).html(); //当前页
                $.post("AjaxQueryOrder",{"o_number":pagenumber,"pageno":pagenum},function (data){
                    Ajaxfun(data);
                });
            });

            function Ajaxfun(data) {
                var json=$.parseJSON(data);
                $("#parentordersdetails li[id='orders_details']").remove();
                $("#parentpagenum").find(".pagenum").remove();
                $("#parentpagenum").find(".nepagenum").remove();
                var li="";
                var a="";
                for (var i=0;i<json.length-1;i++)
                {
                    li+="<li id=\"orders_details\" class=\"clearfix\">\n" +
                        "<div class=\"No1\">"+json[i].CY_ID+
                        "</div>\n" +
                        "<div class=\"No2\"><a href="+"discountProduct?cyid="+json[i].CY_ID+">" +json[i].CY_INTRODUCTION+
                        "</a> </div><div class=\"No3\">"+json[i].OD_QUANTITY+
                        "</div><div class=\"No4\">￥<em>"+json[i].CY_PRICE+
                        "</em></div><div class=\"No5\">￥<em>" +json[i].OD_PRICE+
                        "</em></div></li>";
                }
                $("#parentordersdetails").append(li);
                var json2=json[json.length-1];
                for(var j=0;j<json2.pages.length;j++)
                {
                    if(!isNaN(json2.pages[j])&&json2.pages[j]!=0&&json2.pages[j]==json[0].PAGENO)
                    {
                        a+="<a class=\"pagenum\" id=\"pagenum\" style=\"padding: 10px; color: red;\" href=\"javascript:void(0);\">"+json2.pages[j]+"</a>";
                    }
                    if(!isNaN(json2.pages[j])&&json2.pages[j]!=0&&json2.pages[j]!=json[0].PAGENO)
                    {
                        a+="<a class=\"nepagenum\" id='nepagenum' style=\"padding: 10px;\" href=\"javascript:void(0);\">"+json2.pages[j]+"</a>";
                    }
                }
                $("#spage").after(a);
            }
});