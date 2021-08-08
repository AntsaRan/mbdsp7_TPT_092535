
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var VerifyToken = require('./verifyToken');

let auth = require('./auth')

router.post('/login', function (req, res) {
  // console.log("tody ato login");
  auth.loginUser(req, res);
});

router.get('/logout', function (req, res) {
  res.status(200).send({ auth: false, token: null });
});
router.post('/sign', function (req, res) {
  // console.log("tody ato inscription");
  auth.sign(req, res);
});

router.post('/firetoken', function (req, res) {
  auth.fireauth(req, res);
});

router.route('/user/:id')
  .get(function (req, res) { auth.getUserByID(req, res) })

router.put('/updateuser', function (req, res) {
  auth.updateuser(req, res);
});
module.exports = router;
