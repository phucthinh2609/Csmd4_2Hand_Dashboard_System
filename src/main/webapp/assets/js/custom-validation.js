$(function () {
    page.elements.frmCreate.validate({
        onkeyup: function(element) { $(element).valid() },
        onclick: false,
        onfocusout: false,
        rules: {
            title: {
                required: true,
                minlength: 5,
                maxlength: 100
            },
            sku: {
                isSku: true
            },
            description: {
                minlength: 5,
                maxlength: 100
            }
        },
        errorLabelContainer: "#mdCreate .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#mdCreate .modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                page.dialogs.alertDanger.modalCreate.html("").removeClass("hide").addClass("show");
            } else {
                page.dialogs.alertDanger.modalCreate.html("").removeClass("show").addClass("hide").empty();
                $("#frmCreate input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            title: {
                required: "Title should be not empty",
                minlength: jQuery.validator.format("Minimum title {0} characters"),
                maxlength: jQuery.validator.format("Maximum title {0} characters")
            },
            description: {
                minlength: jQuery.validator.format("Minimum description {0} characters"),
                maxlength: jQuery.validator.format("Maximum description {0} characters")
            }
        },
        submitHandler: function() {
            page.commands.doCreate();
        }
    });


    page.elements.frmUpdate.validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        onfocusout: false,
        rules: {
            title: {
                required: true,
                minlength: 5,
                maxlength: 100
            },
            sku: {
                isSku: true
            },
            description: {
                minlength: 5,
                maxlength: 100
            }
        },
        errorLabelContainer: "#mdUpdate .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#mdUpdate .modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                page.dialogs.alertDanger.modalUpdate.html("").removeClass("hide").addClass("show");
            } else {
                page.dialogs.alertDanger.modalUpdate.html("").removeClass("show").addClass("hide").empty();
                $("#frmUpdate input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            title: {
                required: "Title should be not empty",
                minlength: jQuery.validator.format("Minimum title {0} characters"),
                maxlength: jQuery.validator.format("Maximum title {0} characters")
            },
            description: {
                minlength: jQuery.validator.format("Minimum description {0} characters"),
                maxlength: jQuery.validator.format("Maximum description {0} characters")
            }
        },
        submitHandler: function() {
            page.commands.doUpdate();
        }
    });


    $.validator.addMethod("isNumberWithSpace", function (value, element) {
        return this.optional(element) || /^\s*[0-9,\s]+\s*$/i.test(value);
    }, "Please enter a numeric value");

    $.validator.addMethod("isSku", function (value, element) {
        return this.optional(element) || /^([A-Z]{2}\-){2}\d{4}$/i.test(value);
    }, "Format sku is 'US-AP-1029'. In there: 'US' is the country, 'AP' is the item name, '1029' is the item code");
});