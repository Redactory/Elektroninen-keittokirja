<%-- 
    Document   : Kirjautuminen
    Created on : 11-Nov-2013, 14:00:23
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
        <title>Sisään kirjautuminen</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="container">
            <section>
                <h1>Sisään kirjautuminen</h1>
            </section>

            <form class="form-horizontal" role="form" action="Kirjautuminen" method="POST">
                <div class="form-group">
                    <label for="inputlogin1" class="col-md-2 control-label">Käyttäjätunnus</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="inputPassword1" name="Tunnus">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword1" class="col-md-2 control-label">Salasana</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" id="inputPassword1" name="Salasana">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Muista kirjautuminen
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
                    </div>
                </div>
            </form>
            
            <TD><FORM METHOD="LINK" ACTION="Rekisteroityminen">
                    <button style="float: left" type="submit3" class="btn btn-default">Kirjaudu</button>
                </FORM></TD>
                
        </div>
        <h1>${Virhe}</h1>
    </body>
</html>

