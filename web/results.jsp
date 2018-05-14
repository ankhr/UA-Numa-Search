<%@ page import="java.util.*" %>
<%@ page import="pageRank.Book" %>
<%@ page import="pageRank.PageNode" %>
<%@ page import="pageRank.UANuma" %>
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
        String searchQuery = request.getParameter("searchQuery");

        UANuma uaNuma = (UANuma) application.getAttribute("uaNuma");

        List<PageNode> results = uaNuma.getSearchResults(searchQuery);
        //List<PageNode> results = uaNuma.getSimilarPages(uaNuma.hashMap.get(request.getParameter(searchQuery)));
    %>

    <form class="form-signin" action="results.jsp" method="post">
        <div class="row">
            <div class="col"></div>
            <div class="col-6 align-self-center">
                <input type="text" name="searchQuery" style="text-align:center" class="form-control" placeholder="Enter search" required autofocus>
                <br>
                <br>
            </div>
            <div class="col"></div>
        </div>
    </form>

    <div class="container">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col" style="text-align:right">#</th>
                    <th scope="col" style="text-align:center">Page</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i=0; i<results.size(); i++) {
                %>
                <tr>
                    <td style="text-align: right; width: 30%"><%=i+1%></td>
                    <%
                        String pageName = results.get(i).toString();
                    %>
                    <td style="text-align: center"><a target="_blank" href="suggestions.jsp?value=<%=pageName%>"><%=pageName%></a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

</body>
</html>