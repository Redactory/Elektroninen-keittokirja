<%-- 
    Document   : Rekisteroityminen
    Created on : 12-Dec-2013, 13:46:45
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

        <title>Uuden käyttäjätilin luominen</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
<div class="col-md-10">
            <t:pohja pageTitle="Rekisteroityminen">
                <h1>Rekisteröityminen</h1>
            </t:pohja> 

            <!--Laatikko, joka sulkee muut sisäänsä.-->
            <div class="panel panel-default">
                <div class="panel-body">

                    <!--Segmentti, joka huolehtii lisäyksistä-->
                    <div class="col-md-8 panel panel-default">
                        <h2>Rekisteröitymislomake</h2>

                        <form class="col-md-5 form-horizontal" role="form" action="Rekisteroityminen" method="POST">
                            <div class="form-group">
                                <label for="inputName1" class="col-md-4 control-label">Käyttäjän nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Käyttäjätunnus</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="user">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Salasana</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="password">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Salasana uudelleen</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="passwordAgain">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>                
        </div>
    </body>
</html>
