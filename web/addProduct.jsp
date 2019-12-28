<!--
<body id="edit-profile">
     Container     
    <div id="container">
        <div id="container-inner">
            <h1>New Product</h1>
            <form action="AddProductServlet" method="post">
                <fieldset>
                    <div>
                        <label>Photo directory: </label>
                        <input type="text" name="image" />
                    </div>
                     Your first name 
                    <div>
                        <label>Name: </label>
                        <input type="text" name="name" />
                    </div>

                    <div>
                        <label>Price: </label>
                        <input type="text" name="price" />
                    </div>

                    <div>
                        <label>Category Id: </label>
                        <input type="text" name="category_id" />
                    </div>

                    <div>
                        <label>Quantity: </label>
                        <input type="text" name="quantity" />
                    </div>

                    <div>
                        <label>Description: </label>
                        <textarea name="description" rows="10" cols="5"></textarea>
                    </div>

                    <div>
                        <label>Description Details: </label>
                        <textarea name="description_detail" rows="10" cols="5"></textarea>
                    </div>

                    <div>
                        <label >Acsessories: </label>
                        <textarea name="acsessories" rows="3" cols="5"></textarea>
                    </div>

                    <div>
                        <label>Guaranty: </label>
                        <textarea name="guaranty"  rows="3" cols="5"></textarea>
                    </div>

                    <br>
                    <input id="submit" name="submit" type="submit" value="Add" />

                </fieldset>
            </form>
        </div>
    </div>

</body>
</html>-->


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
            <h1>New Product</h1>
            <form action="AddProductServlet" method="post">
                <label>Photo directory: </label>
                <input type="text" name="image" />

                <label>Name: </label>
                <input type="text" name="name" />

                <label>Price: </label>
                <input type="text" name="price" />

                <label>Quantity: </label>
                <input type="text" name="quantity" />

                <label>Category : </label>
                <input type="text" name="category" value = "${category.name}"/>

                <label>Description: </label>
                <input type="text" name="description"  />

                <label>Description Details: </label>
                <input type = "text" name="description_detail" />

                <label>Accessories: </label>
                <input type = "text" name="acsessories" />

                <label>Guaranty: </label>
                <input type = "text" name="guaranty" />

                <br>

                <input type="submit" value="Add"><br>
            </form>
        </div>

    </body>
</html>
