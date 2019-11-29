'use strict';

const username = document.getElementById('username');
const password = document.getElementById('password');
const errorContainer = document.getElementById('error_container');

function connectionListener(data)
{
    const res = JSON.parse(this.responseText);

    if (res.connected)
    {
        window.location.href = '../pages/graph.html';
    }
    else
    {
        errorContainer.innerHTML += '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n' +
            '            <strong>Connection error</strong> Impossible to connect to GitHub with this username and password\n' +
            '            <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
            '                <span aria-hidden="true">&times;</span>\n' +
            '            </button>\n' +
            '        </div>\n';
    }
}

function connectUser()
{
    if ((username.value === "") || (password.value === ""))
    {
        errorContainer.innerHTML += '<div class="alert alert-warning alert-dismissible fade show" role="alert">\n' +
            '            <strong>Empty input</strong> All inputs must be filled\n' +
            '            <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
            '                <span aria-hidden="true">&times;</span>\n' +
            '            </button>\n' +
            '        </div>';
        return;
    }

    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", connectionListener);
    oReq.open("POST", "http://localhost:3000/connect?username=" + username.value + "&password=" + password.value);
    oReq.send();
}