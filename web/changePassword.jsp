<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}

            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            /* Set a style for all buttons */
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            /* Extra styles for the cancel button */
            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            /* Center the image and position the close button */
            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
                position: relative;
            }
            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                padding-top: 60px;
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }

            .close:hover,
            .close:focus {
                color: red;
                cursor: pointer;
            }

            /* Add Zoom Animation */
            .animate {
                -webkit-animation: animatezoom 0.6s;
                animation: animatezoom 0.6s
            }

            @-webkit-keyframes animatezoom {
                from {-webkit-transform: scale(0)} 
                to {-webkit-transform: scale(1)}
            }

            @keyframes animatezoom {
                from {transform: scale(0)} 
                to {transform: scale(1)}
            }

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <div id="container">
            <form class="modal-content animate" action="<c:url value='changePassword'/>" method="post">
                <div class="container">
                    <%
                        String errPass = (String) request.getSession().getAttribute("errPass");
                        String errConfirm = (String) request.getSession().getAttribute("errConfirm");
                        if (errPass != null) {
                    %>
                    <h3 style="color: red">Sorry, Password Incorrect!</h3>
                    <%
                        }
                        else if (errConfirm!= null) {
                    %>
                    <h3 style="color: red">Sorry, Confirm Password error!</h3>
                    <%
                        }
                    %>
                    <label for="email"><b>Old Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>

                    <label for="psw"><b>New Password</b></label>
                    <input type="password" placeholder="Enter New Password" name="newPassword" required>

                    <label for="psw"><b>Confirm Password</b></label>
                    <input type="password" placeholder="Confirm Password" name="password2" required>

                    <button type="submit">Change</button>
                    <br>
                    <label>
                        <a href="userInfo.jsp">Cancel</a> <br>
                        Forgot <a href="index.jsp">password?</a>
                    </label>
                </div>

                <div class="container" style="background-color:#f1f1f1">

                </div>

            </form>
        </div>

    </body>
</html>

</html>
