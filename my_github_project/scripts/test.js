'use strict';

const requestAnswerContainer = document.getElementById('request_answer_container');

function clearReqAnswer()
{
    requestAnswerContainer.innerHTML = '<span class="text-muted ml-3">No repository</span>';
    d3.select('.d3test').selectAll('circle').data([]).exit().remove();
}

function getAllRepoListener (data)
{
    let res = JSON.parse(this.responseText);

    requestAnswerContainer.innerHTML = '<ul class="list-group">';
    for (let i in res)
    {
        requestAnswerContainer.innerHTML += '<li class="list-group-item">' + ((res[i].private) ? '<i class="fas fa-lock pr-3"></i>' : '<i class="fas fa-folder-open pr-3"></i>')
            + res[i].name + '<span class="text-muted ml-2">' + res[i].language + '</span></li>';
    }
    requestAnswerContainer.innerHTML += '</ul>';

    d3.select('.d3test').selectAll('circle')
        .data(res).exit().remove();
    d3.select('.d3test').selectAll('circle')
        .data(res).enter().append('circle')
        .attr('cx', (d, i) => (960.0 / (res.length+1) * (i+1)))
        .attr('cy', 270)
        .attr('r', 20)
        .style('fill', (d) => ((d.private) ? 'red' : 'limegreen'));
}

function getAllRepositories()
{
    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", getAllRepoListener);
    oReq.open("GET", "http://localhost:3000/allrepos");
    oReq.send();
}

function getRepositoriesFromUser()
{
    const username = document.getElementById('usernameInput').value;
    const type = document.getElementById('typeSelect').value;

    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", getAllRepoListener);
    oReq.open("GET", "http://localhost:3000/repofromuser?username=" + username + "&type=" + type);
    oReq.send();
}