const form = document.getElementById('form')
const username = document.getElementById('username')
const firstname = document.getElementById('firstname')
const lastname = document.getElementById('lastname')
const email = document.getElementById('email')
const password = document.getElementById('password')
const password2 = document.getElementById('password2')

form.addEventListener('submit', (ev) => {
    e.preventDefault();

    checkInputs();
});
function checkInputs() {
    //get values from the input
    const usernameValue = username.value.trim();
    const firstnameValue = firstname.value.trim();
    const lastnameValue = lastname.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();

    if (usernameValue === ''){
        // show error
        // add error class
        setErrorFor(username, 'Username cannot be blank');
    } else {
        // add success class
        setSuccessFor(username);
    }

    if (firstnameValue === ''){
        // show error
        // add error class
        setErrorFor(firstname, 'First name cannot be blank');
    } else {
        // add success class
        setSuccessFor(firstname);
    }

    if (lastnameValue === ''){
        // show error
        // add error class
        setErrorFor(lastname, 'Last name cannot be blank');
    } else {
        // add success class
        setSuccessFor(lastname);
    }

    if (emailValue === ''){
        // show error
        // add error class
        setErrorFor(email, 'Email adress cannot be blank');
    } else if (!isEmail(emailValue)){
        setErrorFor(email, 'Email is not valid');
    } else {
        // add success class
        setSuccessFor(email);
    }

    if (passwordValue === ''){
        // show error
        // add error class
        setErrorFor(password, 'Password cannot be blank');
    } else {
        // add success class
        setSuccessFor(password);
    }

    if (password2Value === ''){
        // show error
        // add error class
        setErrorFor(password2, 'Password cannot be blank');
    } else if (passwordValue !== password2Value) {
        setErrorFor(password2, 'Passwords does not match');
    } else {
        // add success class
        setSuccessFor(password2);
    }

}
 function setErrorFor(input, message) {
    const formControl = input.parentElement; // .formControl
     const small = formControl.querySelector('small');

     // add error message inside small
     small.innerText = message;

     // add error class

     formControl.className = 'form-control error';
 }
 function setSuccessFor(input){
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
 }

 function isEmail (email){
    //rejects
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
 }