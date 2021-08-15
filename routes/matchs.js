const url = "https://grails-api.herokuapp.com/api";
const { response } = require('express');
//const https = require('https');
var request = require('request');
// Récupérer tous les matchs (GET)
const fetch = require('node-fetch');
const headers = {
    "Content-Type": "application/json"
}
function getMatchs(req, res) {
    fetch(url + "/matches")
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

// Récupérer un match par son id (GET)
function getregleId(req, res) {
    let rid = req.params.id;
    //  console.log(rid + " rid")
    fetch(url + "/regle/" + rid)
        .then(response => response.json())
        .then(data => {
            // console.log(JSON.stringify(data) + " DATA")
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

// Récupérer un match par son id (GET)
function getMatchbyId(req, res) {
    let matchId = req.params.id;
    // console.log(matchId + " matchId")
    fetch(url + "/match/" + matchId)
        .then(response => response.json())
        .then(data => {
            //  console.log(JSON.stringify(data) + " DATA")
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}
// Récupérer un match par son id (GET)
function getMatchEquipe(req, res) {
    let equipeid = req.params.id;
    // console.log(`ID ` + equipeid)
    fetch(url + "/matches/equipe/" + equipeid)
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

function getMatchRegles(req, res) {
    // console.log(`getMatchRegles`)
    let idmatch = req.params.idmatch;
    // console.log(`ID ` + idmatch)
    fetch(url + "/matchRegles/" + idmatch)
        .then(response => response.json())
        .then(data => {
            if (data) {
                res.json(data);

            } else {
                res.status(500).send({ msg: " error" });
            }
        })
        .catch(err =>
            console.log(err))
}

function getMatchDate(req, res) {
    console.log(`getmatchsdate` + req.params.date + "date");
    let date = req.params.date;
    request.get(url + "/matchespardate/" + date, (err, response, body) => {
        // console.log("InsertPARI request");
        if (err) {
            // console.log("err");
            return console.log(err);
        } else {
            if (body != undefined && body != null) {
                res.status(200).send(body);
            } else {
                res.status(204).send(null);
            }
        }
    });

}

function top5matchs(req, res) {
    //console.log(`getmatchsdate`+req.params.date);
    fetch(url + "/matchesPlusParie")
        .then(response =>
            console.log(JSON.stringify(JSON.parse(response.status)) + "response match par date"))
        .then(data => {
            if (data) {
                res.json(data);
            } else {
                res.json(null);
            }

        })
        .catch(err =>
            console.log(err))
}



module.exports = { top5matchs, getregleId, getMatchs, getMatchbyId, getMatchEquipe, getMatchRegles, getMatchDate };
