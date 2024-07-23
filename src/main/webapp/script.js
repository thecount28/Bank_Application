document.addEventListener('DOMContentLoaded', function() {
    const slider = document.querySelector('.slider');
    const toggleSwitch = document.getElementById('switch');

    toggleSwitch.addEventListener('change', function() {
        if (this.checked) {
            slider.style.transform = 'translateX(-50%)';
        } else {
            slider.style.transform = 'translateX(0)';
        }
    });

    function validateLogin(type) {
        const form = type === 'admin' ? document.forms["adminLoginForm"] : document.forms["customerLoginForm"];
        const username = form["username"].value;
        const password = form["password"].value;

        if (username === "" || password === "") {
            alert("Username and Password must be filled out.");
            return false;
        }
        return true;
    }

    // Make validateLogin function globally accessible
    window.validateLogin = validateLogin;
});