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

class Link
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
    const res = JSON.parse(data);

    //  parse data

    let texts = [
        new Text(120, 110, 20, 'black', 'Test')
    ];
    let circles = [
        new Circle(100, 100, 20, 1)
    ];
    let links = [
        new Link(0, 0, 1000, 1000, 'hsl(100, 100%, 50%)', 5)
    ];

    drawGraph(texts, circles, links);
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
        .attr('stroke-weight', (d) => d.stkw);

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
    /*const oReq = new XMLHttpRequest();
    oReq.addEventListener("load", getDataListener);
    oReq.open("GET", "http://localhost:3000/graphdata");
    oReq.send();*/

    getDataListener(JSON.stringify({
        authuser: 'gabintod',
        users: [
            'FrancoisRigaut',
            'YannisFalco',
            'montoyadamien'
        ],
        repos: [
            {
                name: 'Ps6-final',
                size: 3150,
                private: false
            },
            {
                name: 'Goodenof',
                size: 960,
                private: true
            },
            {
                name: 'pico-8',
                size: 150,
                private: false
            }
        ],
        links: [
            {
                from: 'FrancoisRigaut',
                to: 'Goodenof',
                size: 125,
                owner: false
            },
            {
                from: 'montoyadamien',
                to: 'Goodenof',
                size: 960,
                owner: true
            },
            {
                from: 'gabintod',
                to: 'pico-8',
                size: 150,
                owner: true
            },
            {
                from: 'FrancoisRigaut',
                to: 'pico-8',
                size: 0,
                owner: false
            },
            {
                from: 'montoyadamien',
                to: 'pico-8',
                size: 0,
                owner: false
            }
        ]
    }));
}