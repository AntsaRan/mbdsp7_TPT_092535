using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class Match
    {
        [MongoDB.Bson.Serialization.Attributes.BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        private string id;
        private Equipe equipe1;
        private Equipe equipe2;
        private DateTime date;
        private string lieu;
        private string etat;
        private int scoreEquipe1;
        private int scoreEquipe2;
        private int cornerEquipe1;
        private int cornerEquipe2;
        private int possessionEquipe1;
        private int possessionEquipe2;

        public string Id { get => id; set => id = value; }
        public DateTime Date { get => date; set => date = value; }
        public string Lieu { get => lieu; set => lieu = value; }
        public string Etat { get => etat; set => etat = value; }
        public int ScoreEquipe1 { get => scoreEquipe1; set => scoreEquipe1 = value; }
        public int ScoreEquipe2 { get => scoreEquipe2; set => scoreEquipe2 = value; }
        public int CornerEquipe1 { get => cornerEquipe1; set => cornerEquipe1 = value; }
        public int CornerEquipe2 { get => cornerEquipe2; set => cornerEquipe2 = value; }
        public int PossessionEquipe1 { get => possessionEquipe1; set => possessionEquipe1 = value; }
        public int PossessionEquipe2 { get => possessionEquipe2; set => possessionEquipe2 = value; }
        internal Equipe Equipe1 { get => equipe1; set => equipe1 = value; }
        internal Equipe Equipe2 { get => equipe2; set => equipe2 = value; }

        public Match()
        {
        }

        public Match(string id, DateTime date, string lieu, string etat, int scoreEquipe1, int scoreEquipe2, int cornerEquipe1, int cornerEquipe2, int possessionEquipe1, int possessionEquipe2, Equipe equipe1, Equipe equipe2)
        {
            Id = id;
            Date = date;
            Lieu = lieu;
            Etat = etat;
            ScoreEquipe1 = scoreEquipe1;
            ScoreEquipe2 = scoreEquipe2;
            CornerEquipe1 = cornerEquipe1;
            CornerEquipe2 = cornerEquipe2;
            PossessionEquipe1 = possessionEquipe1;
            PossessionEquipe2 = possessionEquipe2;
            Equipe1 = equipe1;
            Equipe2 = equipe2;
        }
    }
}
