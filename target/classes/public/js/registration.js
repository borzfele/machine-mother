let isUsernameValid = false;
let isEmailValid = false;
let isPasswordValid = false;

function checkUsername() {

    let usernameField = $("#username");
    let username = usernameField.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/check-username",
        data: JSON.stringify(username),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {

            if (username === "" || response.result) {
                usernameField.css("opacity", "0.5");
                usernameField.css("background-color", "red");
                usernameField.css("color", "white");
                isUsernameValid = false;
            } else {
                usernameField.removeAttr("style");
                isUsernameValid = true;
            }

        },
        error: function (e) {
            console.log("ERROR : ", e);

        }
    });
}

function validateEmail(email) {
    let re = /^(?:[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;
    return re.test(email);
}

function checkEmail() {
    let emailField = $("#email");
    let email = emailField.val();

    if(email === "" || !validateEmail(email)) {
        emailField.css("opacity", "0.5");
        emailField.css("background-color", "red");
        emailField.css("color", "white");
        isEmailValid = false;
    } else {
        emailField.removeAttr("style");
        isEmailValid = true;
    }
}

function checkPassword() {
    let passwordField = $("#password");
    let passwordAgainField = $("#password-again");

    if (passwordField.val() === ""
        || passwordAgainField.val() === ""
        || passwordField.val() !== passwordAgainField.val()) {

        passwordField.css("opacity", "0.5");
        passwordField.css("background-color", "red");
        passwordField.css("color", "white");
        passwordAgainField.css("opacity", "0.5");
        passwordAgainField.css("background-color", "red");
        passwordAgainField.css("color", "white");
        isPasswordValid = false;
    } else {
        passwordField.removeAttr("style");
        passwordAgainField.removeAttr("style");
        isPasswordValid = true;
    }
}

function main() {

    let usernameField = $("#username");
    let emailField = $("#email");
    let passwordAgainField = $("#password-again");
    let passwordField = $("#password");

    usernameField.on("focusout", function () {
        checkUsername();
    });

    emailField.on("focusout", function () {
        checkEmail();
    });

    passwordAgainField.on("focusout", function () {
        checkPassword();
    });

    passwordField.on("focusout", function () {
        checkPassword();
    });

    $("#reg-form").on("submit", function (e) {
        if (!isUsernameValid || !isEmailValid || !isPasswordValid) {
            e.preventDefault();
            alert("fasz");
        } else {
            alert("picsa");
        }
    })
}

$(document).ready( function () {
    main();
});