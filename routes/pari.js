var request = require('request');
const url = "https://grails-api.herokuapp.com/oracle";
// Récupérer tous les matchs (GET)

const headers = {
    "Content-Type": "application/form-data",
    "Accept": "application/json"
}
const fetch = require('node-fetch');


function insertpari(req, res) {
    //console.log("insertpari request" );
    var pari = req.body;
    //console.log("insertpari request" + pari);
    const options = {
        url: url + '/insertPari',
        json: true,
        body: {
            idUtilisateur: pari.idparieur,
            idMatch: pari.idMatch,
            matchRegle: pari.idTypeRegle,
            mise: pari.mise
        }
    };

    request.post(options, (err, response, body) => {
        console.log("InsertPARI request");
        if (err) {
            // console.log("err");
            return console.log(err);
        }
        //    console.log(`Status: ${response.statusCode}`);
        res.status(200).send({ insert: "ok" });

    });

}

function getParisByUSer(req, res) {
    //   console.log(`getparisuser`+req.params.id);
    request.get(url + "/getAllParisbyUser/" + req.params.id, (err, response, body) => {
        // console.log("InsertPARI request");
        if (err) {
            // console.log("err");
            return console.log(err);
        } else {
            if (body != undefined && body != null) {
                res.status(200).send(body);
            } else {
                res.status(204).send(null);
            }
        }
    });
}

function getAllMise(req, res) {
    console.log(url + "/getAllMise/" + req.params.id);
    request.get(url + "/getAllMise/" + req.params.id, (err, response, body) => {
        // console.log("InsertPARI request");
        if (err) {
            // console.log("err");
            return console.log(err);
        } else {
            if (body) {
                res.status(200).send(body);
            } else {
                res.status(204).send(null);
            }
        }
    });
}

module.exports = { insertpari, getParisByUSer, getAllMise };
