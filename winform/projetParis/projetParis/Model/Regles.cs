using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class Regles
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        private string id;
        private string titre;
        private string definition;
        private string ordre;

        public string Id { get => id; set => id = value; }
        public string Titre { get => titre; set => titre = value; }
        public string Definition { get => definition; set => definition = value; }
        public string Ordre { get => ordre; set => ordre = value; }

        public Regles(string id, string titre, string definition, string ordre)
        {
            Id = id;
            Titre = titre;
            Definition = definition;
            Ordre = ordre;
        }
    }
}
