

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>

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
            <form action="EditProductServlet" method="post">
                <h3>Edit Product</h3>
                <label>Photo directory: </label>
                <input type="text" name="image" value="${editProduct.image}"/>

                <label>Name: </label>
                <input type="text" name="name"  value="${editProduct.name}"/>

                <label>Price: </label>
                <input type="text" name="price"  value="${editProduct.price}"/>

                <label>Quantity: </label>
                <input type="text" name="quantity" value="${editProduct.quantity}""/>

                <label>Category : </label>
                <input type="text" name="category"  value="${editProduct.categoryId.name}"/>
<!--                <select name="category">
                    <option value="Mac">Mac</option>
                    <option value="iPhone">iPhone</option>
                    <option value="iPad">iPad</option>
                    <option value="Accessories">Accessories</option>
                </select>-->
                <label>Description: </label>
                <input type="text" name="description" value="${editProduct.description}" />

                <label>Description Details: </label>
                <input type = "text" name="description_detail" value="${editProduct.descriptionDetail}"/>

                <label>Accessories: </label>
                <input type = "text" name="acsessories" value="${editProductDetail.accessories}"/>

                <label>Guaranty: </label>
                <input type = "text" name="guaranty" value="${editProductDetail.guaranty}"/>

                <br>

                <input type="submit" value="Save"><br>
            </form>
        </div>

    </body>
</html>
