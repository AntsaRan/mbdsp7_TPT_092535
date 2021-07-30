const url = "https://grails-api.herokuapp.com/api";
//const https = require('https');

// Récupérer tous les matchs (GET)
const fetch = require('node-fetch');
const headers = {
    "Content-Type": "application/json"
}
function getEquipes(req, res) {    
    fetch(url + "/equipes")
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

// Récupérer une equipe par son id (GET)
function getEquipebyId(req, res) {
    let equipeid = req.params.id;
    fetch(url + "/equipe/"+equipeid)
    .then(response => response.json())
    .then(data => {
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}
// Récupérer une equipe par son id (GET)
function getequipebyName(req, res) {
    let name = req.params.nom;
    fetch(url + "/equipes?nom="+name)
    .then(response => response.json())
    .then(data => {
        //console.log(JSON.stringify(data) + " DATA equipes")
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}


module.exports = { getEquipes, postMatchs,getequipebyName, getEquipebyId, updateMatch, deleteMatch };
