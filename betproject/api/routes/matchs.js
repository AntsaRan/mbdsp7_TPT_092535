let Match = require('../model/matchs');

// Récupérer tous les assignments (GET)
function getMatchs(req, res){
    Match.find((err, matchs) => {
        if(err){
            res.send(err)
        }
        res.send(matchs);
    });
}

// Récupérer un assignment par son id (GET)
function getOneMatch(req, res){
    let matchId = req.params.id;

    Match.findOne({id: matchId}, (err, match) =>{
        if(err){res.send(err)}
        res.json(match);
    })
}

// Ajout d'un assignment (POST)
function postMatchs(req, res){
    let match = new Match();
   /* match.id = req.body.id;
    match.nom = req.body.nom;
    match.dateDeRendu = req.body.dateDeRendu;
    match.rendu = req.body.rendu;

    console.log("POST assignment reçu :");
    console.log(assignment)

    assignment.save( (err) => {
        if(err){
            res.send('cant post assignment ', err);
        }
        res.json({ message: `${assignment.nom} saved!`})
    })*/
}

// Update d'un assignment (PUT)
function updateMatch(req, res) {
    /*console.log("UPDATE recu assignment : ");
    console.log(req.body);
    Assignment.findByIdAndUpdate(req.body._id, req.body, {new: true}, (err, assignment) => {
        if (err) {
            console.log(err);
            res.send(err)
        } else {
          res.json({message: 'updated'})
        }
      // console.log('updated ', assignment)
    });
*/
}

// suppression d'un assignment (DELETE)
function deleteMatch(req, res) {

    Assignment.findByIdAndRemove(req.params.id, (err, assignment) => {
        if (err) {
            res.send(err);
        }
        res.json({message: `${assignment.nom} deleted`});
    })
}



module.exports = { getMatchs, postMatchs, getOneMatch, updateMatch, deleteMatch };
