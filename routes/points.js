var jwt = require('jsonwebtoken');
var config = require('../config');
const url = "http://grails-api.herokuapp.com/api";
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

function getpoints(req, res) {
    fetch(url + "/points")
        .then(response => response.json())
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
function deletepoint(req, res) {
    console.log("insertpoint request");
    var id = req.params.id;
    console.log("insertpoint request" + id);
    const options = {
        url: url + '/points/' + id,
    };
    request.delete(options, (err, response, body) => {
        console.log("deletepoint request");
        if (err) {
            // console.log("err");
            return console.log(err);
        } else {
            res.status(200).send({msg:"ok"});
        }
    });
}

function insertpoint(req, res) {
    console.log("insertpoint request");
    var point = req.body;
    console.log("insertpoint request" + point);
    const options = {
        url: url + '/points',
        json: true,
        body: {
            adresse: point.adresse,
            lat: point.lat,
            lng: point.lng,
        }
    };

    request.post(options, (err, response, body) => {
        console.log("insertpoint request");
        if (err) {
            // console.log("err");
            return console.log(err);
        } else {
            res.status(200).send({ insert: body });
        }

        //    console.log(`Status: ${response.statusCode}`);

    });

}
module.exports = { deletepoint,insertpoint, getpoints };
