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
        // console.log("InsertPARI request");
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
    fetch(url + "/getAllParisbyUser/" + req.params.id)
        .then(response => console.log(JSON.stringify(JSON.parse(response.status))+ " response getParisByUSer"))
        .then(data => {
            if (data != undefined && data != null) {
                res.json(data);
            }

        })
        .catch(err =>
            console.log(err))
}

function getAllMise(req, res) {
    console.log(`getAllMise ` + req.params.id);
    fetch(url + "/getAllMise/" + req.params.id)
        .then(response =>  console.log(JSON.stringify(JSON.parse(response.status))+ " response getAllMisee"))
        .then(data => {
            console.log(data + " MISE")
            if (data != undefined && data != null) {
                res.json(data);
            } else {
                res.json(null);
            }
        })
        .catch(err =>
            console.log(err))
}

module.exports = { insertpari, getParisByUSer, getAllMise };
