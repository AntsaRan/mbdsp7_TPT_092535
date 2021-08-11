
var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var VerifyToken = require('./verifyToken');

let point = require('./points')

  router.post('/insertpoint', function(req, res) {
    //console.log( " tody e ");
    point.insertpoint(req,res);
  });

  router.get('/getpoints',function(req,res){
   // console.log( " tody e ");
    point.getpoints(req,res);
  })

  router.delete('/deletepoint/:id',function(req,res){
      point.deletepoint(req,res);
  })
  
  module.exports = router;
