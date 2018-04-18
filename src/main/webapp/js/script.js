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


    // Opens up a preview for one of the skills.
    function renderSingleSkillPage(index, data) {

        var page = $('.single-skill'),
            container = $('.preview-large');

        // Find the wanted skill by iterating the data object and searching for the chosen index.
        if (data.length) {
            data.forEach(function (item) {
                if (item.id == index) {
                    // Populate '.preview-large' with the chosen skill's data.
                    container.find('h3').text(item.name);
                    container.find('img').attr('src', item.image.large);
                    container.find('p').text(item.description);
                }
            });
        }
        // Single skill page buttons

        var singleSkillPage = $('.single-skill');

        singleSkillPage.on('click', function (e) {

            if (singleSkillPage.hasClass('visible')) {

                var clicked = $(e.target);

                // If the close button or the background are clicked go to the previous page.
                if (clicked.hasClass('close') || clicked.hasClass('overlay')) {
                    // Change the url hash with the last used filters.
                    createQueryHash(filters);
                }

            }

        });

        // Show the page.
        page.addClass('visible');

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