<div id="container">
    <table border="1" cellpadding="5">
        <caption><h2 style="text-align: center">List of products</h2></caption>
        <tr>
            <th>Name</th>
            <th>Quantity</th>
        </tr>
        <c:forEach var="product" items="${allProducts}">
            <tr>
                <td><a href="<c:url value='product?${product.productId}'/>">${product.name}</a><td>
                <td>
                    <form action="<c:url value='updateQuantity'/>">
                        <input type="hidden"
                               name="productId"
                               value="${product.productId}" />
                        <input type="text"
                               maxlength="2"
                               size="2"
                               value="${product.quantity}"
                               name="quantity"
                               style="margin:5px" />
                        <input type="submit"
                               name="submit"
                               value="update" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>