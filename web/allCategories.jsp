<!-- JS Files -->

<script src="js/jquery.tools.min.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        $("#prod_nav ul").tabs("#panes > div", {effect: 'fade', fadeOutSpeed: 400});
    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $(".pane-list li").click(function () {
            window.location = $(this).find("a").attr("href");
            return false;
        });

    }); //close doc ready
</script>

<c:set var='view' value='/index' scope='session' />
<div id="container">
    <div>
        <c:forEach var="category" items="${allCategories}">
            <div>
                <h2>${category.name}</h2>
                <img src="img/demo/${category.image}" width="217" height="145" alt="">
                

                <p style="text-align: right; margin-right: 16px">
                    <a href="<c:url value='category?${category.categoryId}'/>" class="button">All Product</a>
                    <a href="<c:url value='editCategory?${category.categoryId}'/>" class="button">Edit</a>
                    <a href="<c:url value='deleteCategory?${category.categoryId}'/>" class="button">Delete</a>
                    <a href="<c:url value='addProduct2?${category.categoryId}'/>" class="button">Add Product</a>
                </p>
            </div>
        </c:forEach>

        <br clear ="all">
        <br clear ="all">
        <br clear ="all">


    </div>

    <div style="clear: both"></div>
    <br clear ="all">
    <c:forEach var="category" items="${newCategories}">
        <c:if test="${category.categoryId == 4}" >
            <div class="one-fourth last">
                <div class="heading_bg">
                    <h2>${category.name}</h2>
                </div>

                <a href="<c:url value='category?${category.categoryId}'/>">
                    <img src="img/demo/${category.getImage()}" width="217" height="145" alt="">

                </a>
            </div>
        </c:if>
        <c:if test="${category.categoryId < 4}">
            <div class="one-fourth">
                <div class="heading_bg">
                    <h2>${category.name}</h2>
                </div>

                <a href="<c:url value='category?${category.categoryId}'/>">
                    <img src="img/demo/${category.getImage()}" width="217" height="145" alt="">

                </a>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear: both; height: 40px"></div>
</div>