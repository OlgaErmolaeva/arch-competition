<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/justified-nav.css" rel="stylesheet">
</head>

<body>

<c:set var="user" value="${sessionScope.get('user')}"/>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">


        <div id="navbar" class="navbar-collapse collapse">


            <c:choose>
                <c:when test="${not empty user}">
                    <div class="navbar-brand">Hello, ${user.name}!</div>
                    <form action="logout" method="post" class="navbar-form navbar-right">
                        <button type="submit" class="btn btn-success">Sign out</button>

                    </form>
                </c:when>
                <c:otherwise>
                    <form action="login" method="post" class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="text" name="login" placeholder="login" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="Password" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a href="../registration.html" class="btn btn-success">Sign up</a>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div class="tableContainer">
    <div class="container">
        <div class="masthead buffer">
            <h3 class="text-muted">Design projects</h3>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Picture</th>
                <th>Votes</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="designProject" items="${requestScope.get('designProjects')}">
                <tr>
                    <td>${designProject.getName()}</td>
                    <td>${designProject.getDescription()}</td>
                    <td><img src="${designProject.getPicture()}" height="120"></td>
                    <td>

                        <c:choose>
                            <c:when test="${not empty user}">
                                <c:choose>
                                    <c:when test="${user.selectedProjectId != 0}">
                                        ${designProject.getVotes()}
                                        <c:if test="${user.selectedProjectId == designProject.id}">
                                                You selected this project
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        ${requestScope.get('usersVote')}
                                        <form action="voting" method="post">
                                            <div>
                                                <input type="hidden" name="button" value="${designProject.getId()}">
                                                <input type="hidden" name="user" value="${sessionScope.get('name')}">
                                            </div>
                                            <button type="submit" class="btn btn-success">Vote</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>

                            </c:when>
                            <c:otherwise>
                                You should log in to vote
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </div>

</div> <!-- /container -->
<!-- Site footer -->
<footer class="footer">
    <p>&copy; 2016 Olga Ermolaeva</p>
</footer>
</body>
</html>
