<div id="container">
    <div class="one">
        <div class="heading_bg">
            <h2>Confirmation</h2>
        </div>
        <p >
        <table>
            <strong>Your order has been successful processed and will be delivery within 24 hours</strong>
            <br />
            <br />
            <strong>Please keep note of your confirmation number: ${orderRecord.confirmationNumber}</strong>
            <br>
            <strong>If you have any query concerning your order, feel free to </strong><Strong><a href="contact.jsp">contact us</a></strong>
            <br><br>
            <strong>Thank you for your shopping at the BigShot Store! See you soon!</strong>
        </table>
        </p>
    </div>
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
                        ${products[iter.index].name}
                    </td>
                    <td>
                        ${orderedProduct.quantity}
                    </td>
                    <td>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&dollar; "
                                          value="${products[iter.index].price
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
                                      value="${orderRecord.amount+initParam.deliveryFee}"/></td>
            </tr>
            <tr>
                <td colspan="3"><strong>Date Processed:</strong>
                    ${orderRecord.dateCreated}
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
</div>