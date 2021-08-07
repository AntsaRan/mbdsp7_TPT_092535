
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
let pari = require('./pari')

  router.post('/insertpari', function(req, res) {
    //console.log( " tody e ");
    pari.insertpari(req,res);
  });

  router.get('/getparisuser/:id',function(req,res){
   // console.log( " tody e ");
    pari.getParisByUSer(req,res);
  })
  router.get('/getAllMise/:id',function(req,res){
  //  console.log( " tody e ");
    pari.getAllMise(req,res);
  })

  module.exports = router;
