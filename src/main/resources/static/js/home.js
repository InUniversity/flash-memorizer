const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');
const messageError = document.querySelector(".message-error");
const messageClose = document.querySelector(".message-close");

registerLink.addEventListener('click', ()=>{
    wrapper.classList.remove('message');
    wrapper.classList.add('active');
})

loginLink.addEventListener('click', ()=>{
    wrapper.classList.remove('active');
})

btnPopup.addEventListener('click', ()=>{
    wrapper.classList.add('active-popup');
})

iconClose.addEventListener('click', ()=>{
    wrapper.classList.remove('active-popup');
})

//
//document.addEventListener("DOMContentLoaded", () => {
//    if (registerFailedParam && wrapper) {
//        if (registerFailedParam === "true") {
//            wrapper.classList.add("active-popup");
//            wrapper.classList.add("active");
//        }
//    }
//});
//
//document.addEventListener("DOMContentLoaded", () => {
//    if (loginFailedParam && wrapper) {
//        if (loginFailedParam === "true") {
//            wrapper.classList.add("active-popup");
//        }
//    }
//});

messageClose.addEventListener("click", () => {
    messageError.style.display = "none";
});