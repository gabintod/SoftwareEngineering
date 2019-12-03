# Software Engineering
## Lowerst Common Ancestor
The *Software Engineering* folder contains the **Java** project implementing an LCA finder program.

## Biography
The *Biography Docx* is a biography of the C++ creator Bjarne Stroustrup

## Measuring Engineering Report
The second *.docx* is the repport of how the software engineering process can be measured

## GitHub API graph project
The GitHub API graph project is splitted in two parts, the **Client** part is in the *my_github_project* folder and the
**Server** in the *github_api_project* folder using Node.js.
I am using *XMLHttpRequests* to link the front and back-end together, using IntelliJ Ultimate I start the server with
`npm start` and then open the client in browser from the **index.html** file.

Launching *index.html* you will start on a login page, you have to log in with your GitHub account to see the graph as it
depends on the user. Also when authentified, the request number limit is higher. Pressing *Connect* the server will create the
ocokit object (Node.js GitHub API) and test the connexion, if the connection is valid it redirects you to the *graph.html*.

**Note** : if you go on *graph.html* without being connected, it will automatically redirect you to the connection page.

The graph represents the network from the user to the direct collaborators and their contribution to the repositories in common.
In the graph :
- The lowest circle with black stroke represents the currently logged in user
- The highest circles represents the differents direct collaborators
- The Bigger circles in mid-level are representing all the repositories of the authentificated user
  - The size of the circle is proportional to the size of the repository
  - If the circle has a black line around that means the repository is private
  - Hovering the repositories with your mouse you will reveal its name and links with users
- The lines represents the links between repertories and users :
  - The thickness represents the weight of the contribution, it is proportional to the number of commits
  - The grey color indicates that the contribution is null (0 commits from the user)
  - The red color indicates the owner of the repository

**Note** : It is possible that on the graph a repository does not appear having an owner, it may be because the owner of the repository
is an organisation and not an user. It is possible to have 2 repositories with the same name but different owner, this may be because one of them is a fork of the other.

A third page is in *my_github_api*, the **test.html** is a page I made to test the technologies, it is not meant to be showed but can demonstrate the use of the github
API and octokit library. (User must be authentificated to use this page).