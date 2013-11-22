<%-- 
    Document   : Muutokset
    Created on : 18-Nov-2013, 13:49:49
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

        <title>Muutokset</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <div class="col-md-10">
            <t:pohja pageTitle="Muutokset">
                <h1>Sivujen päivitystoiminnot</h1>
            </t:pohja>            
        </div>

        <!--Laatikko, joka sulkee muut sisäänsä.-->
        <div class="panel panel-default">
            <div class="panel-body">

                <!--Segmentti, joka huolehtii lisäyksistä-->
                <div class="col-md-8 panel panel-default">
                    <h2>Reseptin lisäys</h2>

                    <form class="col-md-5 form-horizontal" role="form" action="MuutoksetLisaykset" method="POST">
                        <div class="form-group">
                            <label for="inputName1" class="col-md-4 control-label">Nimi</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputIngredients1" class="col-md-4 control-label">Kehittäjä</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="maker">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputIngredients1" class="col-md-4 control-label">Tyyppi</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="type">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputIngredients1" class="col-md-4 control-label">Raaka-aineet</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="ingredients">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputIngredients1" class="col-md-4 control-label">Valmistusohjeet</label>
                            <div class="col-md-5">
                                <textarea NAME="recipe" rows="4" cols="50"></textarea>
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
                    <h2>Reseptin poisto</h2>
                    <form class="col-md-5 form-horizontal" role="form" action="MuutoksetPoisto" method="POST">
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
                    <h2>Reseptien muokkaus</h2>
                    <form class="col-md-5 form-horizontal" role="form" action="MuutoksetMuokkaa" method="POST">
                        <div class="form-group">
                            <label for="inputName1" class="col-md-4 control-label">Nimi</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputIngredients1" class="col-md-4 control-label">Raaka-aineet</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="inputPassword1" name="igChange">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputRecipe1" class="col-md-4 control-label">Valmistusohjeet</label>
                            <div class="col-md-5">
                                <textarea NAME="recipeChange" rows="4" cols="50"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-default">Muokkaa</button>
                            </div>
                        </div>
                    </form>
                </div>

                <!--Paluu etusivulle.-->
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Etusivulle</button>
                    </div>
                </div>
            </div>
        </div>
        <h1>${Virhe}</h1>
    </body>
</html>
