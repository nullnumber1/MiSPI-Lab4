function commandButtonClick(val) {
    document.querySelector(".form-coordinate-x__wrapper > input").value = val;
    displayMessage("X: " + val)
}

function commandLinkClick(val) {
    let input = document.querySelector(".form-radius__wrapper > input")
    if (input.value !== val) {
        input.value = val;
        deletePoints();
        loadPointsFromTable();
    }
    displayMessage("R: " + val)
}

function displayMessage(text) {
    let msg = document.querySelector('.message')
    msg.innerHTML = text;
    msg.style.visibility = 'visible'
    setTimeout(() => {
        msg.style.visibility = 'hidden'
    }, 3000)
}