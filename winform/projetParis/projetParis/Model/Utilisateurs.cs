using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class Utilisateurs
    {
        private int id;
        private string nom;
        private string prenom;
        private DateTime datenaissance;
        private string pseudo;
        private int jetons;
        private string mail;

        public int Id { get => id; set => id = value; }
        public string Nom { get => nom; set => nom = value; }
        public string Prenom { get => prenom; set => prenom = value; }
        public DateTime Datenaissance { get => datenaissance; set => datenaissance = value; }
        public string Pseudo { get => pseudo; set => pseudo = value; }
        public int Jetons { get => jetons; set => jetons = value; }
        public string Mail { get => mail; set => mail = value; }

        public Utilisateurs()
        {
        }

        public Utilisateurs(int id, string nom, string prenom, DateTime datenaissance, string pseudo, int jetons, string mail)
        {
            Id = id;
            Nom = nom;
            Prenom = prenom;
            Datenaissance = datenaissance;
            Pseudo = pseudo;
            Jetons = jetons;
            Mail = mail;
        }
    }
}
