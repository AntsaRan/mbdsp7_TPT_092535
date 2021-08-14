
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var VerifyToken = require('./verifyToken');

let stat = require('./stat')

  router.get('/getachat', function(req, res) {
    console.log( " tody e achat ");
    stat.getachat(req,res);
  });

  router.get('/getvente',function(req,res){
   console.log( " tody e vente ");
   stat.getvente(req,res);
  })

 router.get('/getParisMonth',function(req,res){
    stat.getParisMonth(req,res);

 })
  
  module.exports = router;
