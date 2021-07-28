const url = "https://grails-api.herokuapp.com/api";
//const https = require('https');

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
            if(data){
                res.json(data);

            }else{
                res.status(500).send({ msg:" error"});
            }        
        })
        .catch(err =>
            console.log(err))
}

function getMatchDate(req, res) {
    //console.log(`getmatchsdate`+req.params.date);
    fetch(url + "/matchespardate/"+req.params.date)
        .then(response =>   response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}




module.exports = {getregleId, getMatchs, getMatchbyId, getMatchEquipe, getMatchRegles, getMatchDate };
