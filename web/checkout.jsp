<%--<c:set var='view' value='/checkout' scope='session' />--%>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#checkoutForm").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                phone: {
                    required: true,
                    number: true,
                    minlength: 9
                },
                address: {
                    required: true
                },
                creditcard: {
                    required: true,
//                    creditcard: true
                }
            }
        });
    });
</script>
<div id="container">
    <div class="one-half">
        <div class="heading_bg">
            <h2>Checkout</h2>
        </div>
        <p>
            <strong>In order to purchase the items in your shopping cart,
                please provide us with the following information:</strong>
        </p>
        <%
            String err = (String) request.getSession().getAttribute("error");
            if (err != null) {
        %>
        <h3 style="color: red">Please fill full the information!</h3>
        <%
            }
        %>
        <form action="<c:url value='purchase' />"
              method="post">
            <fieldset>
                <label>Name<span>*</span></label>
                <input type="text" name="name"
                       value="${customer.name}" />
            </fieldset>
            <fieldset>
                <label>Email<span>*</span></label>
                <input type="text" name="email"
                       value="${customer.email}" />
            </fieldset>
            <fieldset>
                <label>Phone <span>*</span></label>
                <input type="text" name="phone"
                       value="${customer.phone}" />
            </fieldset>
            <fieldset>
                <label>Address <span>*</span></label>
                <input type="text" size="45" name="address"
                       value="${customer.address}" />
            </fieldset>
            <fieldset>
                <label>City <span>*</span></label>
                <input type="text" size="45" name="cityRegion"
                       value="${customer.cityRegion}" />
            </fieldset>
            <fieldset>
                <label>Credit Card Number<span>*</span></label>
                <input type="text" size="45" name="creditcard"
                       value="${customer.ccNumber}" />
            </fieldset>
            <fieldset>
                <input value="Submit purchase" class="button white"
                       type="submit">
            </fieldset>
        </form>
    </div>
    <div class="one-half last">
        <div class="heading_bg">
            <h2>Order Information</h2>
        </div>
        <p>
            <strong>Next-working day delivery is guaranteed</strong>
        </p>
        <p>
            <strong>
                A
                <fmt:formatNumber type="currency" currencySymbol="&euro; "
                                  value="${initParam.deliveryFee}"/>
                delivery surcharge is applied to all purchase orders
            </strong>
        </p>
        <table>
            <th>Total</th>
            <th>Delivery Surcharge</th>
            <th>Credit Total</th>
            <tr>
                <td><fmt:formatNumber type="currency"
                                  currencySymbol="&dollar; " value="${cart.getSubtotal()}"/></td>
                <td><fmt:formatNumber type="currency"
                                  currencySymbol="&dollar; " value="${initParam.deliveryFee}"/></td>
                <td><fmt:formatNumber type="currency"
                                  currencySymbol="&dollar; " value="${cart.getSubtotal()+initParam.deliveryFee}"/></td>
            </tr>
        </table>
    </div>
    <div style="clear:both; height: 40px"></div>
</div> 