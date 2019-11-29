'use strict';

class Text
{
    constructor(x, y, ts, color, text)
    {
        // coordinates
        this.x = x;
        this.y = y;

        this.ts = ts;           // text-size
        this.color = color;     // text-color
        this.text = text;       // text
    }
}

class Circle
{
    constructor(x, y, r, stkw)
    {
        // coordinates
        this.x = x;
        this.y = y;

        this.r = r;         // ray
        this.stkw = stkw;   // stroke weight
    }
}

class Line
{
    constructor(fx, fy, tx, ty, stroke, stkw)
    {
        // from coordinates
        this.fx = fx;
        this.fy = fy;
        // to coordinates
        this.tx = tx;
        this.ty = ty;

        this.stroke = stroke;   // stroke color
        this.stkw = stkw;       // stroke weight
    }
}

class User
{
    constructor(x, y, name, main)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.circle = new Circle(x, y, 10, (main) ? 2 : 0);
        this.text = new Text(x + 12, y + 5, 10, 'black', name)
    }
}

class Repository
{
    constructor(x, y, properties)
    {
        this.x = x;
        this.y = y;
        this.name = properties.name;
        this.size = properties.size;
        this.dislaysize = 5 + (this.size + '').length * 5;
        this.private = properties.private;
        this.circle = new Circle(x, y, this.dislaysize, (this.private) ? 2 : 0);
        this.text = new Text(x - this.dislaysize, y + this.dislaysize + 15, 15, 'black', this.name);
    }
}

class Link
{
    constructor(from, to, weight, owner)
    {
        this.fx = from.x;
        this.fy = from.y;
        this.tx = to.x;
        this.ty = to.y;
        this.weight = weight;
        this.displayweight = Math.min((weight + '').length, 1);
        this.line = new Line(this.fx, this.fy, this.tx, this.ty, (this.weight > 0) ? ((owner) ? 'red' : 'black') : 'grey', this.displayweight);
    }
}



function testAuthListener(data)
{
    const res = JSON.parse(this.responseText);

    if (!res.connected)
    {
        window.location.href = '../pages/index.html';
    }

    console.log('connected');
    getData();
}

function disconnectListener(data)
{
    window.location.href = '../pages/index.html';
}

function getDataListener(data)
{
    const res = JSON.parse(this.responseText);

    let users = [];
    let repos = [];
    let links = [];

    //  parse auth user
    users.push(new User(550, 730, res.authuser, true));

    //  parse other users
    for (let i in res.users)
    {
        users.push(new User((1100 * (parseInt(i) + 1)) / (res.users.length + 1), 20 + parseInt(i)%2 * 30, res.users[i], false));
    }

    //  parse repositories
    for (let i in res.repos)
    {
        repos.push(new Repository((1100 * (parseInt(i) + 1)) / (res.repos.length + 1), 300 + parseInt(i)%2 * 150, res.repos[i]));
    }

    //  parse links
    for (let i in res.links)
    {
        for (let j in users)
        {
            if (users[j].name === res.links[i].from)
            {
                for (let k in repos)
                {
                    if (repos[k].name === res.links[i].to)
                    {
                        links.push(new Link(users[j], repos[k], res.links[i].weight, res.links[i].owner));
                        break;
                    }
                }
                break;
            }
        }
    }

    let texts = [];
    let circles = [];
    let lines = [];

    for (let i in users)
    {
        circles.push(users[i].circle);
        texts.push(users[i].text);
    }

    for (let i in repos)
    {
        circles.push(repos[i].circle);
        texts.push(repos[i].text);
    }

    for (let i in links)
    {
        lines.push(links[i].line);
    }


    drawGraph(texts, circles, lines);
}

function drawGraph(txts, crls, lks)
{
    // links
    d3.select('#graph').selectAll('line').data([]).exit().remove();
    d3.select('#graph').selectAll('line').data(lks).enter().append('line')
        .attr('x1', (d) => d.fx)
        .attr('y1', (d) => d.fy)
        .attr('x2', (d) => d.tx)
        .attr('y2', (d) => d.ty)
        .attr('stroke', (d) => d.stroke)
        .attr('stroke-width', (d) => d.stkw);

    // circles
    d3.select('#graph').selectAll('circle').data([]).exit().remove();
    d3.select('#graph').selectAll('circle').data(crls).enter().append('circle')
        .attr('cx', (d) => d.x)
        .attr('cy', (d) => d.y)
        .attr('r', (d) => d.r)
        .attr('fill', (d, i) => ('hsl(' + (360.0 / crls.length * i) + ', 100%, 50%)'))
        .attr('stroke', 'black')
        .attr('stroke-width', (d) => d.stkw);

    // texts
    d3.select('#graph').selectAll('text').data([]).exit().remove();
    d3.select('#graph').selectAll('text').data(txts).enter().append('text')
        .attr('x', (d) => d.x)
        .attr('y', (d) => d.y)
        .attr('font-size', (d) => d.ts)
        .attr('fill', (d) => d.color)
        .text((d) => d.text);
}



function testAuthentification()
{
    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", testAuthListener);
    oReq.open("GET", "http://localhost:3000/testAuth");
    oReq.send();
}

function disconnect()
{
    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", disconnectListener);
    oReq.open("POST", "http://localhost:3000/disconnect");
    oReq.send();
}

function getData()
{
    const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", getDataListener);
    oReq.open("GET", "http://localhost:3000/graphdata");
    oReq.send();

    // getDataListener(JSON.stringify({
    //     authuser: 'gabintod',
    //     users: [
    //         'FrancoisRigaut',
    //         'YannisFalco',
    //         'montoyadamien'
    //     ],
    //     repos: [
    //         {
    //             name: 'Ps6-final',
    //             size: 3150,
    //             private: false
    //         },
    //         {
    //             name: 'Goodenof',
    //             size: 960,
    //             private: true
    //         },
    //         {
    //             name: 'pico-8',
    //             size: 150,
    //             private: false
    //         }
    //     ],
    //     links: [
    //         {
    //             from: 'FrancoisRigaut',
    //             to: 'Goodenof',
    //             weight: 125,
    //             owner: false
    //         },
    //         {
    //             from: 'montoyadamien',
    //             to: 'Goodenof',
    //             weight: 960,
    //             owner: true
    //         },
    //         {
    //             from: 'gabintod',
    //             to: 'pico-8',
    //             weight: 150,
    //             owner: true
    //         },
    //         {
    //             from: 'FrancoisRigaut',
    //             to: 'pico-8',
    //             weight: 0,
    //             owner: false
    //         },
    //         {
    //             from: 'montoyadamien',
    //             to: 'pico-8',
    //             weight: 0,
    //             owner: false
    //         }
    //     ]
    // }));
}