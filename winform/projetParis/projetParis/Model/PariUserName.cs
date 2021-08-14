using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class PariUserName
    {
        private int id;
        private int idUtilisateur;
        private string nom;
        private int mise;
        private DateTime datepari;
        private string idMatch;
        private string matchRegle;

        public PariUserName()
        {
        }

        public PariUserName(int id, int idUtilisateur, string nom, int mise, DateTime datepari, string idMatch, string matchRegle)
        {
            Id = id;
            IdUtilisateur = idUtilisateur;
            Nom = nom;
            Mise = mise;
            Datepari = datepari;
            IdMatch = idMatch;
            MatchRegle = matchRegle;
        }

        public int Id { get => id; set => id = value; }
        public int IdUtilisateur { get => idUtilisateur; set => idUtilisateur = value; }
        public string Nom { get => nom; set => nom = value; }
        public int Mise { get => mise; set => mise = value; }
        public DateTime Datepari { get => datepari; set => datepari = value; }
        public string IdMatch { get => idMatch; set => idMatch = value; }
        public string MatchRegle { get => matchRegle; set => matchRegle = value; }
    }
}
