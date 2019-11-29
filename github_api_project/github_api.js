'use strict';
const express = require('express');
const app = express();
const Octokit = require("@octokit/rest");
var octokit = null;

const getRepoList = async () => {
    return await octokit.repos.list();
};

const getRepoListFromUser = async (username, type) => {
    return await octokit.repos.listForUser({
        username: username,
        type: type
    });
};

const getAuthUserInfos = async () => {
    return await octokit.users.getAuthenticated();
};

app.use(express.static(`${__dirname}/public`));
app.use(express.json());
app.get('/allrepos', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    const repoList = await getRepoList();

    console.log(repoList);

    let repoNameList = [];
    for (let i in repoList.data)
    {
        repoNameList.push({
            name: repoList.data[i].name,
            private: repoList.data[i].private,
            language: repoList.data[i].language
        });
    }

    res.send(repoNameList);
});

app.get('/repofromuser', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    const username = req.query.username;
    const type = req.query.type;
    const repoList = await getRepoListFromUser(username, type);

    let repoNameList = [];
    for (let i in repoList.data)
    {
        repoNameList.push({
            name: repoList.data[i].name,
            private: repoList.data[i].private,
            language: repoList.data[i].language
        });
    }

    res.send(repoNameList);
});



//  authentify
app.post('/connect', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    const username = req.query.username;
    const password = req.query.password;

    try
    {
        octokit = Octokit({
            auth: {
                username: username,
                password: password
            },
            userAgent: "github_api",
            log: console
        });
        await getAuthUserInfos();
    }
    catch (e)
    {
        res.send({connected: false, exception: e});
        return;
    }

    res.send({connected: true});
});


//  disconnect authentification
app.post('/disconnect', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    octokit = null;

    res.send();
});


//  test authentification
app.get('/testAuth', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    if (octokit == null)
    {
        res.send({connected: false});
        return;
    }

    try
    {
        await getAuthUserInfos();
    }
    catch (e)
    {
        res.send({connected: false, exception: e});
        return;
    }

    res.send({connected: true});
});

// octokit.repos.listCollaborators({
//   owner,
//   repo
// })

// octokit.repos.getReadme({
//   owner,
//   repo
// })

// octokit.repos.getContributorsStats({
//   owner,
//   repo
// })

app.listen(3000);