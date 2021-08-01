
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());

let jeton = require('./jetons')

router.post('/login', function (req, res) {
 // console.log("tody ato login");
  auth.loginUser(req, res);
});

router.get('/logout', function (req, res) {
  res.status(200).send({ auth: false, token: null });
});

router.put('/achatjeton',function(req,res){
    console.log("ACHAT JETON")
    jeton.achatjetons(req,res);
})
module.exports = router;
