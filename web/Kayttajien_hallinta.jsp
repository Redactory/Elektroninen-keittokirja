<%-- 
    Document   : Kayttajien_hallinta
    Created on : 13-Dec-2013, 16:21:20
    Author     : teematve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">

        <title>Käyttäjien hallinta</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="col-md-10">
            <t:pohja pageTitle="Kayttajien_hallinta">
                <h1>Käyttäjien hallinta</h1>
            </t:pohja>

            <!--Laatikko, joka sulkee muut sisäänsä.-->
            <div class="panel panel-default">
                <div class="panel-body">

                    <!--Segmentti, joka huolehtii poistoista.-->
                    <div class="col-md-8 panel panel-default">
                        <h2>käyttäjän poisto</h2>
                        <form class="col-md-5 form-horizontal" role="form" action="Kayttajien_hallinta" method="POST">
                            <div class="form-group">
                                <label for="inputName1" class="col-md-4 control-label">Nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="remove">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Tunnus</label>
                                <select name="sel">
                                    <c:forEach var="kayttaja" items="${kayttajat}">
                                        <option value="Kokeilu">${kayttaja.tunnus}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button type="submit" class="btn btn-default">Poista</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Segmentti, joka huolehtii reseptien listauksen.-->
                    <div class="col-md-5 panel panel-default">
                        <h2>Kaikki käyttäjät</h2>
                        <ul>
                            <c:forEach var="kayttaja" items="${kayttajat}">
                                <li>${kayttaja.nimi}, ${kayttaja.tunnus}</li>
                            </c:forEach>
                        </ul>
                    </div>                    
                </div>
            </div>                
        </div>
    </body>
</html>
