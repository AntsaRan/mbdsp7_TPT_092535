var jwt = require('jsonwebtoken');
var config = require('../config');
const url = "http://grails-api.herokuapp.com/oracle";
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
function getachat(req, res) {
    fetch(url + "/getCountHistoAchatbyMonth")
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

function getvente(req, res) {
    fetch(url + "/getCountHistoVentebyMonth")
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
function getParisMonth(req, res) {
    fetch(url + "/getCurrentMonthParis")
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
getParisMonth
module.exports = { getachat,getvente ,getParisMonth};
