<div id="container">
    <table border="1" cellpadding="5">
        <caption><h2 style="text-align: center">List Of Customer</h2></caption>
        <tr>
            <th>Name</th>
            <th>More Info</th>
            <th>Odering</th>
            <th>Purchased</th>
        </tr>
        <c:forEach var="customer" items="${allCustomers}">
            <tr>
                <td>${customer.name}</td>
                <td><a href="<c:url value='customerInfo?${customer.customerId}'/>">More Info</a></td>
                <td><a href="<c:url value='customerOrdering?${customer.customerId}'/>">Odering</a></td>
                <td><a href="<c:url value='customerPurchased?${customer.customerId}'/>">Purchased</a></td>
                
            </tr>
        </c:forEach>
    </table>
</div>