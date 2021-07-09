let Match = require('../model/matchs');
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
            console.log(JSON.stringify(data) + " DATA")
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}

// Récupérer une equipe par son id (GET)
function getEquipebyId(req, res) {
    console.log(`getEquipebyId ` + req.params.id)
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
    console.log(`getEquipebyNAme` + req.params.nom)
    let name = req.params.nom;
    fetch(url + "/equipes?nom="+name)
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data) + " DATA equipes")
        res.json(data);
    })
    .catch(err =>
        console.log(err))
}

// Ajout d'un match (POST)
function postMatchs(req, res) {
    let match = new Match();
    /* match.id = req.body.id;
     match.nom = req.body.nom;
     match.dateDeRendu = req.body.dateDeRendu;
     match.rendu = req.body.rendu;
 
     console.log("POST assignment reçu :");
     console.log(assignment)
 
     assignment.save( (err) => {
         if(err){
             res.send('cant post assignment ', err);
         }
         res.json({ message: `${assignment.nom} saved!`})
     })*/
}

// Update d'un assignment (PUT)
function updateMatch(req, res) {
    /*console.log("UPDATE recu assignment : ");
    console.log(req.body);
    Assignment.findByIdAndUpdate(req.body._id, req.body, {new: true}, (err, assignment) => {
        if (err) {
            console.log(err);
            res.send(err)
        } else {
          res.json({message: 'updated'})
        }
      // console.log('updated ', assignment)
    });
*/
}

// suppression d'un assignment (DELETE)
function deleteMatch(req, res) {

    Assignment.findByIdAndRemove(req.params.id, (err, assignment) => {
        if (err) {
            res.send(err);
        }
        res.json({ message: `${assignment.nom} deleted` });
    })
}



module.exports = { getEquipes, postMatchs,getequipebyName, getEquipebyId, updateMatch, deleteMatch };
