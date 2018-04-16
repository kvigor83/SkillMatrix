$(function () {

    // Globals variables

    // 	An array containing objects with information about the skills.
    var skills = []

    // These are called on page load

    // Get data about our skills from skills.json.
    $.getJSON("skills.json", function (data) {

        // Write the data into our global variable.
        skills = data;

        // Call a function to create HTML for all the skills.
        generateAllSkillsHTML(skills);

        // Manually trigger a hashchange to start the app.
        $(window).trigger('hashchange');
    });

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

    // An event handler with calls the render function on every hashchange.
    // The render function will show the appropriate content of out page.
    $(window).on('hashchange', function () {
        render(decodeURI(window.location.hash));
    });


    // Navigation

    function render(url) {

        // Get the keyword from the url.
        var temp = url.split('/')[0];

        // Hide whatever page is currently shown.
        $('.main-content .page').removeClass('visible');


        var map = {

            // The "Homepage".
            '': function () {

                // Clear the filters object, uncheck all checkboxes, show all the skills
                filters = {};
                checkboxes.prop('checked', false);

                renderSkillPage(skills);
            },

            // Single Skills page.
            '#product': function () {

                // Get the index of which skill we want to show and call the appropriate function.
                var index = url.split('#product/')[1].trim();

                renderSingleSkillPage(index, skills);
            },

            // Page with filtered skills
            '#filter': function () {

                // Grab the string after the '#filter/' keyword. Call the filtering function.
                url = url.split('#filter/')[1].trim();

                // Try and parse the filters object from the query string.
                try {
                    filters = JSON.parse(url);
                }
                    // If it isn't a valid json, go back to homepage ( the rest of the code won't be executed ).
                catch (err) {
                    window.location.hash = '#';
                    return;
                }

                renderFilterResults(filters, skills);
            }

        };

        // Execute the needed function depending on the url keyword (stored in temp).
        if (map[temp]) {
            map[temp]();
        }
        // If the keyword isn't listed in the above - render the error page.
        else {
            renderErrorPage();
        }

    }

    // This function is called only once - on page load.
    // It fills up the skills list via a handlebars template.
    // It recieves one parameter - the data we took from skills.json.
    function generateAllSkillsHTML(data) {

        // var list = $('.all-skills .skills-list');
        var list = $(".multi-level");

        var theTemplateScript = $('#skills-template').html();
        var theTemplate = Handlebars.compile(theTemplateScript);
        list.append(theTemplate(data));

    }

    // Opens up a preview for one of the skills.
    // Its parameters are an index from the hash and the skills object.
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

        // Show the page.
        page.addClass('visible');

    }


    // Shows the error page.
    function renderErrorPage() {
        var page = $('.error');
        page.addClass('visible');
    }


});