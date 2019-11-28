'use strict';
const express = require('express');
const app = express();
const Octokit = require("@octokit/rest");
const octokit = Octokit({
    auth: {
        username: "gabintod",
        password: "passe2710*"
    },
    userAgent: "github_api",
    log: console
});

const getRepoList = async () => {
    return await octokit.repos.list();;
};


app.use(express.static(`${__dirname}/public`));
app.get('/api', async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', 'null');

    const repoList = await getRepoList();
    let repoNameList = [];
    for (let i in repoList.data)
    {
        repoNameList.push(repoList.data[i].name);
    }

    res.send(repoNameList);
});

app.listen(3000);