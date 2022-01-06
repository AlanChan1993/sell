<html>
<head>
    <meta charset="utf-8">
     <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th>订单Id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td> ${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td> ${orderDTO.orderAmount}</td>
                    <td>${orderDTO.orderStatusEnums.msg} </td>
                    <td> ${orderDTO.payStatusEnums.msg}</td><!--//9-3-->
                    <td>${orderDTO.updateTime}</td>
                    <td>${orderDTO.orderId}</td>
                    <td>

                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                    </td>
                    <td>
                        <#if orderDTO.getOrderStatusEnums().msg =="新订单">
                            <a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <!--分页-->
        <div class="col-md-12 column">
            <ul class="pagination pull-right ">
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                        <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${currentSize}">上一页</a></li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as pageIndex>
                <#if currentPage==pageIndex><!--currentPage由controller传过来表示当前页-->
                    <li class="disabled"><a href="#">${pageIndex}</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${pageIndex}&size=${currentSize}">${pageIndex}</a></li>
                </#if>
                </#list>

                <#if currentPage gte orderDTOPage.getTotalElements()>
                    <li class="disabled" ><a href="#">下一页</a></li>
                <#else >
                    <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${currentSize}">下一页</a></li>
                </#if>

            </ul>
        </div>
    </div>
</div>
<script>
    var  websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket('ws://');
    }else{
        alert('该浏览器不支持websocket';
    }
    websocket.onopen=function (event) {
        console.log('建立连接');
    }
    websocket.onclose = function (event) {
        console.log('连接关闭');
    };
    websocket.onmessage=function (event) {
        console.log('收到消息:'+event.value());
        //弹窗提醒，播放音乐
    }
    websocket.onerror=function () {
        alert('websocket通信发生错误！');
    }
    window.onbeforeunload=function () {
        websocket.close();
    }
</script>
</body>
</html>

