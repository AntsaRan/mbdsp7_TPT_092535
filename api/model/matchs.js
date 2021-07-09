const { Double, Decimal128 } = require('bson');
let mongoose = require('mongoose');
/*var aggreatePaginate = require("mongoose-aggregate-paginate-v2");
const mongoosePaginate = require('mongoose-paginate-v2');*/

let Schema = mongoose.Schema;

let MatchSchema = Schema({
    id: Number,
    equipe1: String,
    equipe2: String,
    date: Date,
    lieu:String,
    etat:Number,
    scoreeq1:Number,
    scoreeq2:Number,
    idequipe1: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Equipe',
    },
    idequipe2:{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Equipe',
    }
});
/*MatchsSchema.plugin(mongoosePaginate);
MatchsSchema.plugin(aggreatePaginate);*/
// C'est à travers ce modèle Mongoose qu'on pourra faire le CRUD
module.exports = mongoose.model('Match', MatchSchema);