let Match = require('../model/matchs');
const url = "https://grails-api.herokuapp.com/api";
//const https = require('https');

// Récupérer tous les matchs (GET)
const fetch = require('node-fetch');
const headers = {
    "Content-Type": "application/json"
}
function getMatchs(req, res) {
    console.log(`getmatchs`);
    
    fetch(url + "/matches")
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

// Récupérer un match par son id (GET)
function getMatchbyId(req, res) {
    console.log(`getmatchbyid`)
    let matchId = req.params.id;
     console.log(`ID `+ matchId)
    fetch(url + "/match/"+matchId)
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data) + " DATA")
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}
// Récupérer un match par son id (GET)
function getMatchEquipe(req, res) {
    console.log(`getMatchEquipe`)
    let equipeid = req.params.id;
     console.log(`ID `+ equipeid)
    fetch(url + "/matches/equipe/"+equipeid)
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data) + " DATA")
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}

function getMatchRegles(req, res){
    console.log(`getMatchRegles`)
    let idmatch = req.params.idmatch;
     console.log(`ID `+ idmatch)
    fetch(url + "/matchRegles/"+idmatch)
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data) + " DATA")
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}




module.exports = { getMatchs, getMatchbyId,getMatchEquipe,getMatchRegles };
