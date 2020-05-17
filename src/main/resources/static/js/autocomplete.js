jQuery(document).ready(function ($) {
    $(function () {
        $("#doctor-autocomplete").autocomplete({
            source: 'doctors-by-term',
            minLength: 3,
            select: function (event, ui) {
                $("#doctor-autocomplete").val(ui.item.label);
                $("#doctor-id").val(ui.item.id);
                return false;
            }
        });
    });
});
jQuery(document).ready(function ($) {
    $(function () {
        $("#instructor-autocomplete").autocomplete({
            source: 'instructors-by-term',
            minLength: 3,
            select: function (event, ui) {
                $("#instructor-autocomplete").val(ui.item.label);
                $("#instructor-id").val(ui.item.id);
                return false;
            }
        });
    });
});
jQuery(document).ready(function ($) {
    $(function () {
        $("#flight-autocomplete").autocomplete({
            source: 'flights-by-term',
            minLength: 1,
            select: function (event, ui) {
                $("#flight-autocomplete").val(ui.item.label);
                $("#flight-id").val(ui.item.id);
                $("#crew-autocomplete").removeAttr("hidden");
                return false;
            }
        });
    });
});
jQuery(document).ready(function ($) {
    $(function () {
        $("#crew-autocomplete").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: 'crew-by-term',
                    data: {
                        term: request.term,
                        flightId: $("#flight-id").val()
                    },
                    success: function (data) {
                        response(data);
                    }
                })
            },
            minLength: 1,
            select: function (event, ui) {
                $("#crew-autocomplete").val(ui.item.label);
                $("#crew-id").val(ui.item.id);
                return false;
            }
        });
    });
});