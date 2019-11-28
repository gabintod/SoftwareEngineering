'use strict'

const requestAnswerContainer = document.getElementById('request_answer_container');

function reqListener (data)
{
    var res = this.responseText.substring(1,this.responseText.length-1).split(',');

    requestAnswerContainer.innerHTML = '<ul class="list-group">';
    for (let i in res)
    {
        requestAnswerContainer.innerHTML += '<li class="list-group-item">' + res[i].substring(1, res[i].length-1) + '</li>';
    }
    requestAnswerContainer.innerHTML += '</ul>';
}

function requestTest ()
{
    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", reqListener);
    oReq.open("GET", "http://localhost:3000/api");
    oReq.send();
};