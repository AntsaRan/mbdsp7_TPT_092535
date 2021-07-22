using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class Equipe
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        private string id;
        public string nom;
        public string logo;

       

        public Equipe()
        {
        }


        public string Id { get => id; set => id = value; }
        public string Nom { get => nom; set => nom = value; }
        public string Logo { get => logo; set => logo = value; }

        public Equipe(string id, string nom, string logo)
        {
            Id = id;
            Nom = nom;
            Logo = logo;
        }

        public Equipe(string nom, string logo)
        {
            this.nom = nom;
            this.logo = logo;
        }
    }
}
