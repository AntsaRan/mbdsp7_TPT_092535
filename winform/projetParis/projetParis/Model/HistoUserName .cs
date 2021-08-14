using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class HistoUserName
    {

        private int id;
        private int idUtilisateur;
        private string nom;
        private string nomTransaction;
        private int idtransaction;
        private DateTime datetransaction;
        private int montant;

        public int Id { get => id; set => id = value; }
        public int IdUtilisateur { get => idUtilisateur; set => idUtilisateur = value; }
        public string Nom { get => nom; set => nom = value; }
        public string NomTransaction { get => nomTransaction; set => nomTransaction = value; }
        public int Idtransaction { get => idtransaction; set => idtransaction = value; }
        public DateTime Datetransaction { get => datetransaction; set => datetransaction = value; }
        public int Montant { get => montant; set => montant = value; }

        public HistoUserName()
        {
        }

        public HistoUserName(int id, int idUtilisateur, string nom, string nomTransaction, int idtransaction, DateTime datetransaction, int montant)
        {
            Id = id;
            IdUtilisateur = idUtilisateur;
            Nom = nom;
            NomTransaction = nomTransaction;
            Idtransaction = idtransaction;
            Datetransaction = datetransaction;
            Montant = montant;
        }
    }
}
