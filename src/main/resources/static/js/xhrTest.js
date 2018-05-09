$(document).ready(function () {

    $("#getBtn").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var search = {}
    search["username"] = $("#username").val();
    //search["email"] = $("#email").val();

    $("#getBtn").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/employees/10001",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#div1').html(json);

            console.log("SUCCESS : ", data);
            $("#getBtn").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#div1').html(json);

            console.log("ERROR : ", e);
            $("#getBtn").prop("disabled", false);

        }
    });

}