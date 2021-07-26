var jwt = require('jsonwebtoken');
var config = require('../config');
const url = "https://grails-api.herokuapp.com/oracle";
//const https = require('https');
var request = require('request');
var FormData = require('form-data');
var bcrypt = require('bcryptjs');
// Récupérer tous les matchs (GET)
const fetch = require('node-fetch');
const headers = {
    "Content-Type": "application/form-data",
    "Accept": "application/json"
}


function loginUser(req, res) {
    console.log("LOGIN");
    var mail = req.body.mail;
    var pwd = req.body.pwd;
    const options = {
        url: url + '/authentification',
        json: true,
        form: {
            mail: mail,
            pwd: pwd
        }
    }
    request.post(options, (err, response, body) => {
        console.log(JSON.stringify(body.date) + "body");
        if (err) {
            return console.log(err);
        }else if(body){
            console.log(JSON.stringify(body) + "body nisy");
            const token = jwt.sign({ id: body.id }, config.token, {
                expiresIn: 3600 // expires in 24 hours
            });
            res.status(200).send({ auth: true, user: body, token: token });
        }else{
            res.status(200).send({ auth: false, user: null, token: null });

        }
    });
} 


function sign(req, res) {
    console.log("tody ato sign");
    var parieur = req.body.parieur;
    console.log(req.body.date + " parieur");
    const options = {
        url: url + '/insertUser',
        json: true,
        body: {
            nom: parieur.nom,
            prenom: parieur.prenom,
            dateNaissance:req.body.date,
            pseudo: parieur.pseudo,
            pwd: parieur.pwd,
            jetons: 0,
            mail:parieur.mail
         }

    }
    request.post(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        }else if(body){
            console.log(JSON.stringify(body) + "body");
            res.status(200).send({msg: body});
        }else{
            res.status(500).send({ msg:" error"});
        }
    });
} 

module.exports = { loginUser,sign };
