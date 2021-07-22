
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
//var VerifyToken = require('./VerifyToken');
//var jwt = require('jsonwebtoken');
//var bcrypt = require('bcryptjs');
//var config = require('../config');
let auth = require('./auth')

  router.post('/login', function(req, res) {
    auth.loginUser(req,res);
  });

  router.get('/logout', function(req, res) {
    res.status(200).send({ auth: false, token: null });
  });

  module.exports = router;
