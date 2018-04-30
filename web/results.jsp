<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Results</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp" >UAFS</a>
        <div class="collapse navbar-collapse justify-content-end">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="index.jsp"><span class="sr-only">(current)</span></a>
            </div>
        </div>
    </div>
</nav>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">UA Numa Search</h1>
    </div>
</section>

<br>

<form class="form-signin" action="index.jsp" method="post">
    <div class="row">
        <div class="col">
        </div>
        <div class="col">
            <input type="text" name="custID" style="text-align:center" class="form-control" placeholder="Enter search" required autofocus>
        </div>
        <div class="col">
        </div>
    </div>
    <div class="row">
        <div class="col">
        </div>
        <div class="col">
        </div>
        <div class="col">
            <br>
            <button type="submit" class="btn btn-lg btn-primary btn-block">Search</button>
            <br>
        </div>
        <div class="col">
        </div>
        <div class="col">
        </div>
    </div>
</form>

</body>
</html>