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
                    <th>商品Id</th>
                    <th>名称</th>
                    <th>图片</th>
                    <th>单价</th>
                    <th>库存</th>
                    <th>描述</th>
                    <th>类目</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list productPage.content as product>
                <tr>
                    <td>${product.productId}</td>
                    <td><a href="/sell/seller/product/index?productId=${product.productId}"> ${product.productName}</a></td>
                    <td><img width="50" height="50" src="${product.productIcon}" alt=""></td>
                    <td>${product.productPrice}</td>
                    <td> ${product.productStock}</td>
                    <td>${product.productDescription} </td>
                    <td> ${product.categoryType}</td><!--//10-2-->
                    <td>${product.createTime}</td>
                    <td>${product.updateTime}</td>
                    <td>
                        <a href="/sell/seller/product/index?productId=${product.productId}">修改</a>
                    </td>
                    <td>
                        <#if product.getProductStatusEnum().message=="在线">
                            <a href="/sell/seller/product/off_sale?productId=${product.productId}">下架</a>
                        <#else >
                            <a href="/sell/seller/product/on_sale?productId=${product.productId}">上架</a>
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
                        <li><a href="/sell/seller/product/list?page=${currentPage-1}&size=${currentSize}">上一页</a></li>
                </#if>

                <#list 1..productPage.getTotalPages() as pageIndex>
                <#if currentPage==pageIndex><!--currentPage由controller传过来表示当前页-->
                    <li class="disabled"><a href="#">${pageIndex}</a></li>
                <#else>
                    <li><a href="/sell/seller/product/list?page=${pageIndex}&size=${currentSize}">${pageIndex}</a></li>
                </#if>
                </#list>

                <#if currentPage gte productPage.getTotalElements()>
                    <li class="disabled" ><a href="#">下一页</a></li>
                <#else >
                    <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=${currentSize}">下一页</a></li>
                </#if>

            </ul>
        </div>
    </div>
</div>
</body>
</html>

