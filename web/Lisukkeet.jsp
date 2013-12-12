<%-- 
    Document   : Lisukkeet
    Created on : 05-Dec-2013, 18:25:43
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

        <title>Lisukkeiden muokkaaminen</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="col-md-10">
            <t:pohja pageTitle="Lisukkeet">
                <h1>Lisukkeiden lisäykset</h1>
            </t:pohja> 

            <!--Laatikko, joka sulkee muut sisäänsä.-->
            <div class="panel panel-default">
                <div class="panel-body">

                    <!--Segmentti, joka huolehtii lisäyksistä-->
                    <div class="col-md-8 panel panel-default">
                        <h2>Lisättävä lisuke</h2>

                        <form class="col-md-5 form-horizontal" role="form" action="LisukkeetLisays" method="POST">
                            <div class="form-group">
                                <label for="inputName1" class="col-md-4 control-label">Lisukkeen nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Liittyvä ruokalaji</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="food">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputRecipe1" class="col-md-4 control-label">Kuvaus</label>
                                <div class="col-md-5">
                                    <textarea NAME="description" rows="4" cols="50"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button type="submit" class="btn btn-default">Lisää</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Segmentti, joka huolehtii poistoista.-->
                    <div class="col-md-8 panel panel-default">
                        <h2>Lisukkeen poisto</h2>
                        <form class="col-md-5 form-horizontal" role="form" action="LisukkeetPoisto" method="POST">
                            <div class="form-group">
                                <label for="inputName1" class="col-md-4 control-label">Nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="remove">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button type="submit" class="btn btn-default">Poista</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Segmentti, joka huolehtii Muokkauksista.-->
                    <div class="col-md-8 panel panel-default">
                        <h2>Lisukkeen muokkaus</h2>
                        <form class="col-md-5 form-horizontal" role="form" action="LisukkeetMuokkaus" method="POST">
                            <div class="form-group">
                                <label for="inputName1" class="col-md-4 control-label">Vanha nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" value="" name="oldName">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Uusi Nimi</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="newName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputIngredients1" class="col-md-4 control-label">Uusi Ruokalaji</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="inputPassword1" name="newFood">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputRecipe1" class="col-md-4 control-label">Uusi Kuvaus</label>
                                <div class="col-md-5">
                                    <textarea NAME="newDescription" rows="4" cols="50"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button type="submit" class="btn btn-default">Muokkaa</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>                
        </div>
    </body>
</html>
