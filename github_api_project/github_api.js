'use strict';
const express = require('express');
const app = express();
const Octokit = require("@octokit/rest");
var octokit = null;
var authuser = null;




// API functions    ----------------------------------------------------------------------------------------------------
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

const getContribStats = async (owner, repo) => {
    return await octokit.repos.getContributorsStats({
        owner: owner,
        repo: repo
    });
};

const getCollabList = async (owner, repo) => {
    return await octokit.repos.listCollaborators({
        owner: owner,
        repo: repo
    });
};



//  Requests    --------------------------------------------------------------------------------------------------------
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
        authuser = username;
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
    authuser = null;

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


//  get data for graph
app.get('/graphdata', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    const repoList = await getRepoList();
    let contribStats;
    let repos = [];
    let users = [];
    let links = [];

    for (let i in repoList.data)
    {
        repos.push({
            name: repoList.data[i].name,
            size: repoList.data[i].size,
            private: repoList.data[i].private
        });
        let repoOwner = repoList.data[i].owner.login;
        contribStats = await getContribStats(repoOwner, repoList.data[i].name);
        for (let j in contribStats.data)
        {
            let user = contribStats.data[j].author.login;
            if ((authuser !== user) && (!users.includes(user)))
            {
                users.push(user);
            }
            links.push({
                from: user,
                to: repoList.data[i].name,
                weight: contribStats.data[j].total,
                owner: (user === repoOwner)
            });
        }
        if (contribStats.data.length <= 0)
            links.push({
                from: repoOwner,
                to: repoList.data[i].name,
                weight: 1,
                owner: true
            });
    }

    console.log({
        authuser: authuser,
        users: users,
        repos: repos,
        links: links
    });

    res.send({
        authuser: authuser,
        users: users,
        repos: repos,
        links: links
    });
});

// octokit.repos.listCollaborators({
//   owner,
//   repo
// })

// octokit.repos.getReadme({
//   owner,
//   repo
// })



app.listen(3000);