<%@ page import="pageRank.Book" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UA Numa Search</title>
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

    <%
        /*
         * TEST: Data initialization.
         * The first time that we arrive on the search page,
         * the data structures will be null. On page load, this is
         * where they are initialized.
         *
         * Afterwards, they'll contain data persistent across
         * all pages and clients. They won't be initialized again
         * until tomcat server restarts.
         */
        if (application.getAttribute("link") == null) {

            // init 1, page name and word frequency
            Book book = new Book();
            application.setAttribute("book", book);

            // init 2, page name and links links
            Map<String, String> link = new HashMap<>();
            link.put("anarchism", "https://en.wikipedia.org/wiki/Anarchism");
            link.put("aardvark", "https://en.wikipedia.org/wiki/Aardvark");
            link.put("audi", "https://en.wikipedia.org/wiki/Audi");
            application.setAttribute("link", link);
        }
    %>

    <br>

    <form class="form-signin" action="results.jsp" method="post">
        <div class="row">
            <div class="col">
            </div>
            <div class="col">
                <input type="text" name="searchQuery" style="text-align:center" class="form-control" placeholder="Enter search" required autofocus>
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