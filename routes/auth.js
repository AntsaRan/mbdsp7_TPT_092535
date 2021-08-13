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
        if (err) {
            return console.log(err);
        }else if(body!=undefined){
           console.log(JSON.stringify(body) + "body nisy");
            const token = jwt.sign({ id: body.id }, config.token, {
                expiresIn: 3600 // expires in 24 hours
            });
            res.status(200).send({ auth: true, user: body, token: token ,expiresIn:3600});
        }else{
            res.status(200).send({ auth: false, user: null, token: null });

        }
    });
} 

function loginAdmin(req,res){
    //http://grails-api.herokuapp.com/oracle/authentificationAdmin
    console.log("loginadmin")
    var pseudo = req.body.pseudo;
    var pwd = req.body.password;
    console.log("loginadmin " + pseudo +" "+pwd)
    const options = {
        url: url + '/authentificationAdmin',
        json: true,
        body: {
            pseudo: pseudo,
            password: pwd
        }
    }
    request.post(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        }else if(body!=undefined){
            console.log( JSON.stringify(body )+ ' LOGADMN USER')
            const token = jwt.sign({ id: body.id }, config.token, {
                expiresIn: 3600 // expires in 24 hours
            });
            res.status(200).send({ auth: true, user: body, token: token ,expiresIn:3600});
        }else{
            res.status(200).send({ auth: false, user: null, token: null });

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

            res.status(200).send({msg: body});
        }else{
            res.status(500).send({ msg:" error"});
        }
    });
}
function updateuser(req, res) {
    var parieur = req.body;
    console.log(req.body.nom + " parieur");
    const options = {
        url: url + '/updateUser/'+parieur.id,
        json: true,
        body: {
            nom: parieur.nom,
            prenom: parieur.prenom,
            dateNaissance:parieur.dateNaissance,
            pseudo: parieur.pseudo,
            pwd: parieur.pwd,
            jetons: parieur.jetons,
            mail:parieur.mail
         }

    }
    request.put(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        }else if(body){
            console.log(JSON.stringify(body))
            res.status(200).send({msg: body});
        }else{
            res.status(500).send({ msg:" error"});
        }
    });
} 
function fireauth(req, res) {

    var token = req.body.token;
    var iduser = req.body.iduser;
    const options = {
        url: url + '/insererDevice',
        json: true,
        body: {
          idUtilisateur: iduser,
          token:token
         }
    }
    request.post(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        }else if(body){
            res.status(200).send({msg: body});
        }else{
            res.status(500).send({ msg:" error"});
        }
    });
} 
function getUserByID(req, res) {
    console.log(`getUserByID`+req.params.id);
    fetch(url + "/getUserbyId/"+req.params.id)
        .then(response =>   
            response.json())
        .then(data => {
           // console.log(JSON.stringify(data) + " getuserbyid");
            res.json(data);
        })
        .catch(err =>
            console.log(err))
}
module.exports = { loginAdmin,loginUser,sign ,fireauth,getUserByID,updateuser};
