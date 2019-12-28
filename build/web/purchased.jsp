<div id="container">
    <c:forEach var="orderedProducteds" items="${orderedProductedss}" varStatus="iters">
        <h3>Number.${iters.index}</h3>
        <div class="heading_bg">
            <h3>Orderd Summary</h3>
        </div>
        <table>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
                <c:forEach var="orderedProducted" items="${orderedProducteds}"
                           varStatus="iter">
                <tr>
                    <td>
                        ${productedss[iters.index][iter.index].name}
                    </td>
                    <td>
                        ${orderedProducted.quantity}
                    </td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&dollar; "
                                          value="${productedss[iters.index][iter.index].price
                                                   * orderedProducted.quantity}"/>
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
                                      value="${customerOrdereds[iters.index].amount+initParam.deliveryFee}"/></td>
            </tr>
            <tr>
                <td colspan="3"><strong>Date Processed:</strong>
                    ${customerOrdereds[iters.index].dateCreated}
            </tr>
        </table>

        <div style="clear:both; height: 40px"></div>
    </c:forEach>
    <div style="clear:both; height: 40px"></div>
</div>