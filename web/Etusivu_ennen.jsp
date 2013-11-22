<%-- 
    Document   : Etusivu
    Created on : 13-Nov-2013, 16:27:04
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
            <t:pohja pageTitle="Etusivu_ennen">
                <h1>Keittokirjan etusivu</h1>
            </t:pohja>            
        </div>

        <!--Laatikko, joka sulkee muut sisäänsä.-->
        <div class="panel panel-default">
            <div class="panel-body">

                <!--Segmentti, joka huolehtii reseptihaku-osuudesta-->
                <div class="col-md-6 panel panel-default">
                    <h2>Haku reseptin nimellä</h2>

                    <form class="col-md-5 form-horizontal" role="form" action="Reseptihaku" method="POST">
                        <div class="form-group">
                            <label for="inputName1" class="col-md-2 control-label">Nimi</label>
                            <div class="col-md-5">
                                <input type="name" class="form-control" id="inputPassword1" name="Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-default">Hae resepti</button>
                            </div>
                        </div>
                    </form>
                </div>

                <!--Segmentti, joka huolehtii reseptien listauksen.-->
                <div class="col-md-5 panel panel-default">
                    <h2>Kaikki reseptit</h2>
                    <ul>
                        <c:forEach var="ruoka" items="${ruokalaji}">
                            <li>${ruoka.ruokalajinNimi}, ${ruoka.raaka_aineet}</li>
                        </c:forEach>
                    </ul>
                </div>
                <!--This is a comment. Comments are not displayed in the browser-->
                    <TD><FORM METHOD="LINK" ACTION="localhost:8080/ElektroninenKeittokirja/Muutokset">
                            <button style="float: left" type="submit3" class="btn btn-default">Lisäys/Poisto</button>
                        </FORM></TD>
            </div>
        </div>
    </body>
</html>