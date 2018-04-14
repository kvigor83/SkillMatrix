<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

    <title>Skill Matrix</title>

    <link href="/skills/css/styles.css" rel="stylesheet" type="text/css">
    <link href="/skills/css/modal.css" rel="stylesheet" type="text/css">
    <link href="/skills/css/multi-list.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/skills/js/modal.js" type="text/javascript"></script>
</head>

<body>

<header class="compact">
    <button id="addBtn" class="button-header" onclick="displayBlock()">About</button>
    <h1><a href="#">Single Page App Skill Matrix</a></h1>
</header>

<div class="main-content">
    <div class="nav">
        <input class="form-control" id="myInput" type="text" placeholder="Search..">
        <input type="checkbox" id="menu"/>
        <label for="menu" id="nav-icon">&#9776; HARD SKILLS</label>


        <div class="multi-level" id="123">
            <script id="skills-template" type="x-handlebars-template">
                {{#each this}}
                <div class="item">
                    <input type="checkbox" id="{{id}}"/>
                    {{#if count-nested}}
                    <img src="/skills/images/Arrow.png" class="arrow"><label for="{{id}}">{{name}}</label>
                    <ul>
                        {{#each nested1}}
                        <li>
                            <div class="sub-item">
                                <input type="checkbox" id="{{n1-id}}"/>
                                {{#if count-nested}}
                                <img src="/skills/images/Arrow.png" class="arrow"><label
                                    for="{{n1-id}}">{{name}}</label>
                                <ul>
                                    {{#each nested2}}
                                    <li>
                                        <div class="sub-item">
                                            <input type="checkbox" id="{{n2-id}}"/>
                                            {{#if count-nested}}
                                            <img src="/skills/images/Arrow.png" class="arrow"><label
                                                for="{{n2-id}}">{{name}}</label>
                                            <ul>
                                                {{#each nested3}}
                                                <li>
                                                    <div class="sub-item">
                                                        <input type="checkbox" id="{{n3-id}}"/>
                                                        {{#if count-nested}}
                                                        <img src="/skills/images/Arrow.png" class="arrow"><label
                                                            for="{{n3-id}}">{{name}}</label>
                                                        <ul>
                                                            {{#each nested4}}
                                                            <li>
                                                                <div class="sub-item">
                                                                    <input type="checkbox" id="{{n4-id}}"/>
                                                                    <label for="{{n4-id}}">- {{name}}</label>
                                                                </div>
                                                            </li>
                                                            {{/each}}
                                                        </ul>
                                                        {{else}}
                                                        <label for="{{n2-id}}">- {{name}}</label>
                                                        {{/if}}
                                                    </div>
                                                </li>
                                                {{/each}}
                                            </ul>
                                            {{else}}
                                            <label for="{{n2-id}}">- {{name}}</label>
                                            {{/if}}
                                        </div>
                                    </li>
                                    {{/each}}
                                </ul>
                                {{else}}
                                <label for="{{n1-id}}">- {{name}}</label>
                                {{/if}}
                            </div>
                        </li>
                        {{/each}}
                    </ul>
                    {{else}}
                    <label for="{{id}}">- {{name}}</label>
                    {{/if}}
                </div>
                {{/each}}
            </script>
        </div>

    </div>

    <div class="single-skill page">

        <div class="overlay"></div>

        <div class="preview-large">
            <h3>Single skill view</h3>
            <%--<img src=""/>--%>
            <p></p>

            <span class="close">×</span>
        </div>

    </div>

    <div class="error page">
        <h3>Sorry, something went wrong :(</h3>
    </div>
</div>

<div id="addModal" class="modal-window">
    <div class="modal-content">
        <span class="close-modal" onclick="displayNone()">&times;</span>
        <p></p>
        <p>©2018 Demo project SkillMatrix Kastsiuchenka Ihar / Group HTP-9 Java</p><br><br>
        <p> </p>
    </div>
</div>

<script>
    $(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#123 label").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });

        });
    });
</script>

<script src="/skills/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/skills/js/handlebars-v4.0.11.js" type="text/javascript"></script>
<script src="/skills/js/script.js" type="text/javascript"></script>



</body>
</html>