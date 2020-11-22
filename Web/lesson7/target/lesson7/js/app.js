window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}
window.ajax = function(action, success, anyProps = {}, anyDataProps = {}, error = null) {
    if(!success) {
        success = function (response) {
            if (response["error"]) {
                error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    }
    $.ajax(Object.assign({
        type: "POST",
        dataType: "json",
        data: Object.assign({action: action}, anyDataProps),
        success,
    }, anyProps));
}
