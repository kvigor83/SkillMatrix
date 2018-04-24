$(function () {

    // 	An array containing objects with information about the skills.
    var skills = []

    // These are called on page load

    // Get data about our skills from skills.json.
    $.getJSON("skills.json", function (data) {

        // Write the data into our global variable.
        skills = data;

        // Call a function to create HTML for all the skills.
        generateAllSkillsHTML(skills);

    });


    // It fills up the skills list via a handlebars template.
    // It receives one parameter - the data we took from skills.json.
    function generateAllSkillsHTML(data) {

        // var list = $('.all-skills .skills-list');
        var list = $(".multi-level");

        var theTemplateScript = $('#skills-template').html();
        var theTemplate = Handlebars.compile(theTemplateScript);
        list.append(theTemplate(data));

    }


    // Shows the error page.
    function renderErrorPage() {
        var page = $('.error');
        page.addClass('visible');
    }


});
// Delete one of the skill and review skill tree.
//     $('#update').click(function () {
//
//         $.ajax({
//             type: "POST",
//             cache: false,
//             url: 'frontController',
//             data: {
// //                'command':'updateData',
//                 'data': $("#data").val()
//             },
//             success: function (response) {
// //                $('#container').html(response.serverInfo);
//                 $('#container').html(response);
//             },
//             error: function()//Если запрос не удачен
//             {
//                 $('#container').html("Запрос не удался!");
//             }
//         });
//     });