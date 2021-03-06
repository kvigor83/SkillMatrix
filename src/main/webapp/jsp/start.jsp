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
        <div class="multi-level" id="multi-tree">
        </div>

        <input type="checkbox" id="menu1"/>
        <label for="menu1" id="nav-icon">&#9776; SOFT SKILLS</label>

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
    </div>
</div>

<input type="text" id="data"/>
<%--<a id="delete" href="#">Delete</a>--%>
<%--<a id="update" href="#">Update data</a>--%>
<%--<a id="update" href="#">UPDATE</a>--%>
<div id="container"></div>

</body>
<script id="skills-template" type="x-handlebars-template">
    {{#each this}}
    <div class="item" id="123">
        <input type="checkbox" id="{{id}}"/>
        {{#if count-nested}}
        <img src="/skills/images/Arrow.png" class="arrow">
        <img src="/skills/images/edit.png" class="edit">
        <img src="/skills/images/add.png" class="add">
        <label for="{{id}}">{{name}}</label>
        <ul>
            {{#each nested1}}
            <li>
                <div class="sub-item">
                    <input type="checkbox" id="{{n1-id}}"/>
                    {{#if count-nested}}
                    <img src="/skills/images/Arrow.png" class="arrow">
                    <img src="/skills/images/edit.png" class="edit">
                    <img src="/skills/images/add.png" class="add">
                    <label for="{{n1-id}}">{{name}}</label>
                    <ul>
                        {{#each nested2}}
                        <li>
                            <div class="sub-item">
                                <input type="checkbox" id="{{n2-id}}"/>
                                {{#if count-nested}}
                                <img src="/skills/images/Arrow.png" class="arrow">
                                <img src="/skills/images/edit.png" class="edit">
                                <img src="/skills/images/add.png" class="add">
                                <label for="{{n2-id}}">{{name}}</label>
                                <ul>
                                    {{#each nested3}}
                                    <li>
                                        <div class="sub-item">
                                            <input type="checkbox" id="{{n3-id}}"/>
                                            {{#if count-nested}}
                                            <img src="/skills/images/Arrow.png" class="arrow">
                                            <img src="/skills/images/edit.png" class="edit">
                                            <img src="/skills/images/add.png" class="add">
                                            <label for="{{n3-id}}">{{name}}</label>
                                            <ul>
                                                {{#each nested4}}
                                                <li>
                                                    <div class="sub-item">
                                                        <input type="checkbox" id="{{n4-id}}"/>
                                                        <img src="/skills/images/edit.png" class="edit">
                                                        <img src="/skills/images/delete.png" class="delete">
                                                        <label for="{{n4-id}}">- {{name}}</label>
                                                    </div>
                                                </li>
                                                {{/each}}
                                            </ul>
                                            {{else}}
                                            <img src="/skills/images/edit.png" class="edit">
                                            <img src="/skills/images/delete.png" class="delete">
                                            <img src="/skills/images/add.png" class="add">
                                            <label for="{{n2-id}}">- {{name}}</label>
                                            {{/if}}
                                        </div>
                                    </li>
                                    {{/each}}
                                </ul>
                                {{else}}
                                <img src="/skills/images/edit.png" class="edit">
                                <img src="/skills/images/delete.png" class="delete">
                                <img src="/skills/images/add.png" class="add">
                                <label for="{{n2-id}}">- {{name}}</label>
                                {{/if}}
                            </div>
                        </li>
                        {{/each}}
                    </ul>
                    {{else}}
                    <img src="/skills/images/edit.png" class="edit">
                    <img src="/skills/images/delete.png" class="delete">
                    <img src="/skills/images/add.png" class="add">
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
<script>
    //    $('#update').click(function () {
    //$('img').bind('click', function(){
    $('body').on('click', '.edit', function () {
        var ids = $(this).siblings('input').attr('id');
        alert(ids);
        $.ajax({
            type: "POST",
            cache: false,
            url: 'frontController',
            data: {
                'command': 'updateData',
                'adr': ids,
                'data': $("#data").val()
            },
            success: function (response) {
                $('#container').html(response);
                $.getJSON("skills.json", function (data) {
                    generateAllSkillsHTML(data);
                });
            },
            error: function ()//Если запрос не удачен
            {
                $('#container').html("Запрос не удался!");
            }
        });
    });

    function generateAllSkillsHTML(data) {

        var list = $(".multi-level");
//        document.getElementById('elem').onclick = function() {
//            this.innerHTML = '';
//        }
        var theTemplateScript = $('#skills-template').html();
        var theTemplate = Handlebars.compile(theTemplateScript);
        document.getElementById('multi-tree').innerHTML = '';
        list.append(theTemplate(data));

    }

    $(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#multi-tree label,img").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });

        });
    });
</script>
<script src="/skills/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/skills/js/handlebars-v4.0.11.js" type="text/javascript"></script>
<script src="/skills/js/script.js" type="text/javascript"></script>
</html>