<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>index</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">

    <script type="text/javascript" language="javascript" src="mywebapp/mywebapp.nocache.js"></script>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">

        <p>
            ${sessionScope.get("name")}
        </p>

        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="login" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
                <a href="registration.html" class="btn btn-success">Sign up</a>
            </form>
        </div>
    </div>
</nav>

<div class="jumbotron">

    <div class="container">
        <div class="masthead buffer">
            <h3 class="text-muted">Design projects</h3>
        </div>

        <div id="gwtContainer"></div>


    </div>

</div> <!-- /container -->
<!-- Site footer -->
<footer class="footer">
    <p>&copy; 2016 Olga Ermolaeva</p>
</footer>
</body>
</html>
