using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class Pari
    {
        private int id;
        private int idUtilisateur;
        private string idMatch;
        private string matchregle;
        private float mise;
        private DateTime dateParis;

        public Pari(int id, int idUtilisateur, string idMatch, string matchregle, float mise, DateTime dateParis)
        {
            Id = id;
            IdUtilisateur = idUtilisateur;
            IdMatch = idMatch;
            Matchregle = matchregle;
            Mise = mise;
            DateParis = dateParis;
        }

        public Pari()
        {

        }

        public int Id { get => id; set => id = value; }
        public int IdUtilisateur { get => idUtilisateur; set => idUtilisateur = value; }
        public string IdMatch { get => idMatch; set => idMatch = value; }
        public string Matchregle { get => matchregle; set => matchregle = value; }
        public float Mise { get => mise; set => mise = value; }
        public DateTime DateParis { get => dateParis; set => dateParis = value; }
    }
}
