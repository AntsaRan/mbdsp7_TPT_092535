const { Double, Decimal128 } = require('bson');
let mongoose = require('mongoose');
var aggreatePaginate = require("mongoose-aggregate-paginate-v2");
const mongoosePaginate = require('mongoose-paginate-v2');

let Schema = mongoose.Schema;

let EquipeSchema = Schema({
    id: Number,
    nom: String,
    logo: String
});
EquipeSchema.plugin(mongoosePaginate);
EquipeSchema.plugin(aggreatePaginate);
// C'est à travers ce modèle Mongoose qu'on pourra faire le CRUD
module.exports = mongoose.model('Equipe', EquipeSchema);