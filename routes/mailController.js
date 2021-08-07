
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());

let mail = require('./mailer')

router.post('/login', function (req, res) {
 // console.log("tody ato login");
  auth.loginUser(req, res);
});

router.post('/mail', function (req, res) {
 mail.main(req,res);
});

module.exports = router;
