<div id="container">
    <c:forEach var="orderedProducts" items="${orderedProductss}" varStatus="iters">
        <h3>Number.${iters.index}</h3>
        <div class="two-third">
            <div class="heading_bg">
                <h3>Order Summary</h3>
            </div>
            <table>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                    <c:forEach var="orderedProduct" items="${orderedProducts}"
                               varStatus="iter">
                    <tr>
                        <td>
                            ${productss[iters.index][iter.index].name}
                        </td>
                        <td>
                            ${orderedProduct.quantity}
                        </td>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&dollar; "
                                              value="${productss[iters.index][iter.index].price
                                                       * orderedProduct.quantity}"/>
                        </td>
                    </tr>
                    </c:forEach>
                <tr>
                    <td colspan="2"><strong>Delivery Surcharge:</strong></td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&euro; "
                                          value="${initParam.deliveryFee}"/></td>
                </tr>
                <tr>
                    <td colspan="2"><strong>Credit Total:</strong></td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&euro; "
                                          value="${customerOrders[iters.index].amount+initParam.deliveryFee}"/></td>
                </tr>
                <tr>
                    <td colspan="3"><strong>Date Processed:</strong>
                        ${customerOrders[iters.index].dateCreated}
                </tr>
            </table>
        </div>
        <div class="sidebar_right">
            <div class="heading_bg">
                <h3>Delivery Address</h3>
            </div>
            <table>
                <tr>    
                    <td colspan="3">
                        ${customers[iters.index].name}
                        <br>
                        ${customers[iters.index].address}
                        <br>
                        ${customers[iters.index].cityRegion}
                        <br>
                        <hr>
                        <strong>Email:</strong>
                        ${customers[iters.index].email}
                        <br>
                        <strong>Telephone number:</strong>
                        ${customers[iters.index].phone}
                    </td>
                </tr>
            </table>
        </div>
                    <h3><a href="<c:url value='checkoutAd?${iters.index}'/>" class="button">Check Out</a></h3>
    <div style="clear:both; height: 40px"></div>
    </c:forEach>
    <div style="clear:both; height: 40px"></div>
</div>