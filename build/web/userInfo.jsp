<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}

            input[type=text], select, textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                margin-top: 6px;
                margin-bottom: 16px;
                resize: vertical;
            }

            input[type=submit] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <form action="<c:url value='updateInfo'/>" method="post">
                <label>Your Name</label>
                <input type="text" name="name" value="${customer.name}">

                <label>Your Phone</label>
                <input type="text" name="phone" value="${customer.phone}">
                
                <label>Your Address</label>
                <input type="text" name="address" value="${customer.address}">
                
                <label>City</label>
                <input type="text" name="cityRegion" value="${customer.cityRegion}">
                
                <label>Creditcard</label>
                <input type="text" name="creditcard" value="${customer.ccNumber}">
                                
                <br>
                
                <input type="submit" value="Save"><br>
                <h4><a href="changePassword.jsp">Change Password</a><h4>
            </form>
        </div>

    </body>
</html>
