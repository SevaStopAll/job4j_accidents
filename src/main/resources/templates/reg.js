let defColor = null;
function checkPasswords(){
    let input = document.getElementById('username');
    let input1 = document.getElementById('password');
    let input2 = document.getElementById('password2');
    let button = document.getElementById('submit');
    if (defColor == null) {
        defColor = input.style.borderColor;
    }
    let isDisable = input.value == '';
    if (isDisable) {
        input.style.borderColor = 'red';
    } else {
        input.style.borderColor = defColor;
    }
    if (input1.value !='' && input1.value == input2.value)
    {
        input1.style.borderColor = defColor;
        input2.style.borderColor = defColor;
        button.disabled = isDisable;
    }else{
        input1.style.borderColor = 'red';
        input2.style.borderColor = 'red';
        button.disabled = true;
    };
};