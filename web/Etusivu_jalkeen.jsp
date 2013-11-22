<%-- 
    Document   : Etusivu_jalkeen
    Created on : 18-Nov-2013, 13:52:50
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

        <title>Etusivu</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <div class="col-md-10">
            <t:pohja pageTitle="Etusivu_jalkeen">
                <h1>Keittokirjan etusivu</h1>
            </t:pohja>            
        </div>

        <!--Laatikko, joka sulkee muut sisäänsä.-->
        <div class="panel panel-default">
            <div class="panel-body">
                <h1> Elektroninen Keittokirja </h1>

                <!--Segmentti, joka huolehtii reseptihaku-osuudesta-->
                <div class="col-md-6 panel panel-default">
                    <h2>Haku reseptin nimellä</h2>

                    <form class="col-md-5 form-horizontal" role="form" action="Reseptihaku" method="POST">
                        <div class="form-group">
                            <label for="inputName1" class="col-md-2 control-label">Nimi</label>
                            <div class="col-md-10">
                                <input type="name" class="form-control" id="inputPassword1" name="Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-default">Hae resepti</button>
                            </div>
                        </div>
                    </form>

                    <div id="yksi" class="col-md-10 panel panel-default">
                        <div class="panel-heading">RAAKA-AINEET</div>
                        <div class="panel-body">
                            Raaka-aineet
                        </div>
                    </div>

                    <div id="kaksi" class="col-md-10 panel panel-default">
                        <div class="panel-heading">KUVAUS</div>
                        <div class="panel-body">
                            kuvailu
                        </div>
                    </div>

                    <div id="kolme" class="col-md-10 panel panel-default">
                        <div class="panel-heading">VALMISTUSOHJEET</div>
                        <div class="panel-body">
                            valmistusohjeet
                        </div>
                    </div>
                </div>

                <!--Segmentti, joka huolehtii reseptien listauksen.-->
                <div class="col-md-5 panel panel-default">
                    <h2>Kaikki reseptit</h2>
                    <form class="col-md-5 form-horizontal" role="form" action="Reseptihaku" method="POST">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-default">Reseptilista</button>
                            </div>
                        </div>
                        <div class="panel-body">
                            lista resepteistä
                        </div>
                    </form>
                </div>
                <!--This is a comment. Comments are not displayed in the browser-->
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Lisäys/Poisto</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
