
<%@page import="entity.Product"%>

<%@page import="java.util.List"%>

<%@page import="entity.ProductDetail"%>

<script src="sliderengine/jquery.js"></script>
<script src="sliderengine/amazingslider.js"></script>
<link rel="stylesheet" type="text/css" href="sliderengine/amazingslider-1.css">
<script src="sliderengine/initslider-1.js"></script>

<%

    session.setAttribute("view", "/product");

    Product selectedProduct = (Product) session.getAttribute("selectedProduct");

    ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");
    
    
%>


<div id="container">

    <div class="one">

        <div class="heading_bg">

            <h2><%=selectedProduct.getName()%></h2>

        </div>

    </div>

    <div class="one-half">
        <div id="amazingslider-wrapper-1" style="display:block;position:relative;max-width: 450px;margin: 0px auto 98px; ">
            <div id="amazingslider-1" style="display: block;position: relative;margin: 0 auto;">
                <ul class="amazingslider-slides" style="display: none;">
                    <%
                        for (String img : selectedProductDetail.getAllImages()) {
                    %>
                    <li><img src="images/<%=img%>" alt="aaa" title="" /></li>
                        <%
                            }

                        %>
                </ul>
                <ul class="amazingslider-thumbnails" style="display:none;">
                    <%                        for (String img : selectedProductDetail.getAllImages()) {
                    %>
                    <li><img src="images/tn-<%=img%>" alt="" title="aaa" /></li>
                        <%
                            }
                        %>
                </ul>
            </div>
        </div>
    </div>
    <div class="one-half last">
        <ul id="tabify_menu" class="menu_tab" style="margin: 0;">
            <li><a href="#fane1">Product Details</a></li>
        </ul>
        <div id="fane1" class="tab_content">
            <h3>Technical Details</h3>
            <p>
                <%=selectedProductDetail.getInformation()%>
            </p>
            <h3>Accessories</h3>
            <p>
                <%=selectedProductDetail.getAccessories()%>
            </p>
            <h3>Warranty Strategy</h3>
            <p><%=selectedProductDetail.getGuaranty()%></p>
            <h3>Price</h3>
            <p><%=selectedProduct.getPrice()%>$</p>
            <h3>Quantity</h3>
            <div>
                <c:choose>
                    <c:when test="${selectedProduct.getQuantity() == 0}">
                        <i style="color:red;">Out of stock</i>
                    </c:when>
                    <c:otherwise>
                        <p>${selectedProduct.getQuantity()}</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <p style="text-align: left;margin-right: 16px;">
                <%if(selectedProduct.getQuantity() == 0) {%>
                <a onclick="alert('This product is out of stock')"  class="button" >Add to cart</a>
                <%} else{%>
                <a href="<c:url value='addToCart?${selectedProduct.getProductId()}'/>"  class="button" >Add to cart</a>
                <%}%>
            </p>
        </div>
    </div>
    <div style="clear: both;height: 40px"></div>
</div>