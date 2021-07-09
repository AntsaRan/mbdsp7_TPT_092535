const express = require('express')
const app = express()
const https = require('https');
const port = 3000
let match = require('./routes/matchs');
let equipe = require('./routes/equipe');
// Pour accepter les connexions cross-domain (CORS)
app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Access-Control-Allow-Headers")
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,x-access-token");
    res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    next();
});

app.route('/getAllMatches')
.get(match.getMatchs);

app.route('/match/:id')
.get(match.getMatchbyId);

app.route('/match/equipe/:id')
.get(match.getMatchEquipe);

app.route('/equipe/:id')
.get(equipe.getEquipebyId);

app.route('/equipenom/:nom')
.get(equipe.getequipebyName);

app.route('/matchregles/:idmatch')
.get(match.getMatchRegles);

app.listen(port, () => {
    console.log(`app listening at http://localhost:${port}`)
});
