const express = require('express')
const app = express()
const https = require('https');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var AuthController = require('./routes/authController');
var pariController = require('./routes/pariController');
let port = process.env.PORT || 8010;
let match = require('./routes/matchs');
let pari = require('./routes/pari');
let equipe = require('./routes/equipe');
let auth = require('./routes/auth')

// Pour accepter les connexions cross-domain (CORS)
app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Access-Control-Allow-Headers")
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,x-access-token");
    res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    next();
});
app.use('/auth', AuthController);
app.use('/pari', pariController);

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

app.route('/regle/:id')
.get(match.getregleId);

app.route('/match/date/:date')
.get(match.getMatchDate)

app.listen(port, "0.0.0.0");
console.log('Serveur démarré sur http://localhost:' + port);

module.exports = app;  

