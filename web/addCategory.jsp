
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
            <form action="AddCategoryServlet" method="post">
                <h3>Add Category</h3>
                <label>Photo directory: </label>
                <input type="text" name="image" />

                <label>Name: </label>
                <input type="text" name="name"  />

                <br>

                <input type="submit" value="Add"><br>
            </form>
        </div>

    </body>
</html>
