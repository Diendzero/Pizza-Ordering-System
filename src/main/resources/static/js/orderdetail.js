function showAlert() {

    var alertElement = document.createElement("div");
    alertElement.classList.add("alert");
    alertElement.innerHTML = "Required option notcompleted, please complete the selection!";
    document.body.appendChild(alertElement);

    var alertHeight = alertElement.offsetHeight;
    alertElement.style.top = -alertHeight + "px";

    setTimeout(function() {
        alertElement.style.top = "0";
    }, 100);

    setTimeout(function() {
        alertElement.style.top = -alertHeight + "px";
        setTimeout(function() {
            document.body.removeChild(alertElement);
        }, 500);
    }, 3000);
}