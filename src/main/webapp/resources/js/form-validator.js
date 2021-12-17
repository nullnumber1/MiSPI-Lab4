function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function isInRange(n, min, max) {
    return parseFloat(n) > parseFloat(min) && parseFloat(n) < parseFloat(max);
}

function checkX() {
    let x = document.querySelector(".form-coordinate-x__input > input").value;
    console.log(x);
    return isInRange(x, -4, 4) && isNumeric(x)
}

function checkY() {
    try {
        let y = parseFloat(document.querySelector(".form-coordinate-y__input").value.replace(",", "."));
        document.querySelector(".form-coordinate-y__input").value = y;
        return isNumeric(y) ? isInRange(y, -3, 5) : false;
    } catch (err) {
        console.log("y is invalid")
        return false;
    }
}

function checkR() {
    return true;
}

// form validation
function checkData() {
    let bool = checkX() && checkY() && checkR();
    if (!bool) {
        alert("Entered data is invalid.");
    }
    return bool;
}
