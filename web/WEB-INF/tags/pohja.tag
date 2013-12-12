<%@tag description="Geneerinen pohja myrkkytietokannalle" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${pageTitle}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="Etusivu">Elektroninen keittokirja</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="UlosKirjautuminen">Kirjaudu ulos</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="MuutoksetLisaykset">reseptien muokkaaminen</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="Ohjeet">Sovelluksen käyttöohjeet</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="LisukkeetLisays">Lisukkeiden muokkaaminen</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="KayttajanLisays">Käyttäjien muokkaukset (EI KUULU KURSSIIN)</a></li>
                </ul> 
            </div>
        </div>

        <div class="container">
            <jsp:doBody/>
            <c:if test="${Virhe != null}" >
                <h1>${Virhe}</h1>
            </c:if>


        </div>
    </body>
</html>