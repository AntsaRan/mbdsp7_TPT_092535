const url = "http://grails-api.herokuapp.com/oracle";
//const https = require('https');
// Récupérer tous les matchs (GET)
const fetch = require('node-fetch');
var request = require('request');
const headers = {
    "Content-Type": "application/json"
}
//addJetonsUser/51?jetons=100
// Achat de jetons 
function achatjetons(req, res) {
    console.log(JSON.stringify(req.body) + " req.body")

    let id = req.body.id;
    let jeton = req.body.jetons;
    console.log(jeton + " " + id + " id")
    const options = {
        url: url + '/addJetonsUser/' + id,
        json: true,
        form: {
            jetons: jeton
        }
    }
    request.put(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        } else if (body) {
            console.log(JSON.stringify(body) + " body achat")
            res.status(200).send({ msg: body });
        } else {
            res.status(500).send({ msg: " error" });
        }
    });
}

function histojetons(req,res){
  //  http://grails-api.herokuapp.com/oracle/getHistoByUser/51
console.log("historique jetons");
  var id =req.params.id;
console.log(id + ' Id histo')
    request.get(url+"/getHistoByUser/"+id, (err, response, body) => {
        if (err) {
            return console.log(err);
        } else if (body) {
            console.log(body + " HISTORIQUE JETONS")
            res.status(200).send(body);
        } else {
            res.status(500).send({ msg: " error" });
        }
    });
}

function sign(req, res) {
    var parieur = req.body.parieur;
    //console.log(req.body.date + " parieur");
    const options = {
        url: url + '/insertUser',
        json: true,
        body: {
            nom: parieur.nom,
            prenom: parieur.prenom,
            dateNaissance: req.body.date,
            pseudo: parieur.pseudo,
            pwd: parieur.pwd,
            jetons: 0,
            mail: parieur.mail
        }

    }
    request.post(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        } else if (body) {

            res.status(200).send({ msg: body });
        } else {
            res.status(500).send({ msg: " error" });
        }
    });
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
    //console.log(`getmatchsdate`+req.params.date);
    fetch(url + "/matchespardate/" + req.params.date)
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

function top5matchs(req, res) {
    //console.log(`getmatchsdate`+req.params.date);
    fetch(url + "/matchesPlusParie")
        .then(response => response.json())
        .then(data => {
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}



module.exports = {histojetons, top5matchs, getregleId, achatjetons, getMatchbyId, getMatchEquipe, getMatchRegles, getMatchDate };
