<div id="container">
    <c:forEach var="orderedProductings" items="${orderedProductingss}" varStatus="iters">
        <h3>Number.${iters.index}</h3>
        <div class="two-third">
            <div class="heading_bg">
                <h3>Order Summary</h3>
            </div>
            <table>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                    <c:forEach var="orderedProducting" items="${orderedProductings}"
                               varStatus="iter">
                    <tr>
                        <td>
                            ${productingss[iters.index][iter.index].name}
                        </td>
                        <td>
                            ${orderedProducting.quantity}
                        </td>
                        <td>
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&dollar; "
                                              value="${productingss[iters.index][iter.index].price
                                                       * orderedProducting.quantity}"/>
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
                                          value="${customerOrderings[iters.index].amount+initParam.deliveryFee}"/></td>
                </tr>
                <tr>
                    <td colspan="3"><strong>Date Processed:</strong>
                        ${customerOrderings[iters.index].dateCreated}
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
                        ${customer.name}
                        <br>
                        ${customer.address}
                        <br>
                        ${customer.cityRegion}
                        <br>
                        <hr>
                        <strong>Email:</strong>
                        ${customer.email}
                        <br>
                        <strong>Telephone number:</strong>
                        ${customer.phone}
                    </td>
                </tr>
            </table>
        </div>
    <div style="clear:both; height: 40px"></div>
    </c:forEach>
    <div style="clear:both; height: 40px"></div>
</div>