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
    };
    request.post(options, (err, response, body) => {
        if (err) {
            return console.log(err);
        }else if(body.id>0){
            const token = jwt.sign({ id: body.id }, config.token, {
                expiresIn: 3600 // expires in 24 hours
            });
            res.status(200).send({ auth: true, user: body, token: token });
        }else{
            res.status(200).send({ auth: true, user: null, token: null });

        }
    });
} 


module.exports = { loginUser };
