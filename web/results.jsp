<%@ page import="java.util.*" %>
<%@ page import="pageRank.Book" %>
<%@ page import="pageRank.PageNode" %>
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

    <%
        /*
         * After a search query is submitted, the page redirects here,
         * and stays here for subsequent searches
         * til user explicitly goes back to index.jsp
         */
        String searchQuery = request.getParameter("searchQuery");

        // reference stored data structures
        Book book = (Book) application.getAttribute("book");
        Map<String, String> link = (Map<String, String>) application.getAttribute("link");

        // get word frequency from book
        Map<String, Integer> wordFrequency = book.book.get(searchQuery);

    %>

    <form class="form-signin" action="results.jsp" method="post">
        <div class="row">
            <div class="col">
            </div>
            <div class="col">
                <input type="text" name="searchQuery" style="text-align:center" class="form-control" value="" required autofocus>
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
                <%--<button type="submit" class="btn btn-lg btn-primary btn-block">Search</button>--%>
                <%--<br>--%>
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
        </div>
    </form>

    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col" style="text-align:right">#</th>
                <th scope="col" style="text-align:center">Page</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (link.containsKey(searchQuery.toLowerCase())) {
            %>
            <tr>
                <td style="text-align: right; width: 30%">Link</td>
                                                                         <%//opens link in newtab%>
                <td style="text-align: center"><a target="_blank" href="<%=link.get(searchQuery)%>"><%=link.get(searchQuery)%></a></td>
            </tr>
            <%
                }
            %>

            <tr>
                <td style="text-align: right; width: 30%">Word Frequency</td>
                                                                                <%//shows word frequency%>
                <td style="text-align: center"><a target="_blank" href="<%=searchQuery%>"><%=wordFrequency%></a></td>
            </tr>

            </tbody>
        </table>
    </div>

</body>
</html>