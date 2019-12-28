<div id="container">
    <c:forEach var="orderedProductCheckeds" items="${orderedProductCheckedss}" varStatus="iters">
        <h3>Number.${iters.index}</h3>
        <div class="two-third">
            <div class="heading_bg">
                <h3>Order Summary</h3>
            </div>
            <table>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                    <c:forEach var="orderedProductChecked" items="${orderedProductCheckeds}"
                               varStatus="iter">
                    <tr>
                        <td>
                            ${productCheckedss[iters.index][iter.index].name}
                        </td>
                        <td>
                            ${orderedProductChecked.quantity}
                        </td>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&dollar; "
                                              value="${productCheckedss[iters.index][iter.index].price
                                                       * orderedProductChecked.quantity}"/>
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
                                          value="${customerOrderCheckeds[iters.index].amount+initParam.deliveryFee}"/></td>
                </tr>
                <tr>
                    <td colspan="3"><strong>Date Processed:</strong>
                        ${customerOrderCheckeds[iters.index].dateCreated}
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
                        ${customerCheckeds[iters.index].name}
                        <br>
                        ${customerCheckeds[iters.index].address}
                        <br>
                        ${customerCheckeds[iters.index].cityRegion}
                        <br>
                        <hr>
                        <strong>Email:</strong>
                        ${customerCheckeds[iters.index].email}
                        <br>
                        <strong>Telephone number:</strong>
                        ${customerCheckeds[iters.index].phone}
                    </td>
                </tr>
            </table>
        </div>
    <div style="clear:both; height: 40px"></div>
    </c:forEach>
    <div style="clear:both; height: 40px"></div>
</div>