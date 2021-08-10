using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class HistoriqueTransac
    {
        private int id;
        private int idUtilisateur;
        private string idTransaction;
        private DateTime dateTransaction;
        private int montant;
        private int idPari;

        public int Id { get => id; set => id = value; }
        public int IdUtilisateur { get => idUtilisateur; set => idUtilisateur = value; }
        public string IdTransaction { get => idTransaction; set => idTransaction = value; }
        public DateTime DateTransaction { get => dateTransaction; set => dateTransaction = value; }
        public int Montant { get => montant; set => montant = value; }
        public int IdPari { get => idPari; set => idPari = value; }

        public HistoriqueTransac()
        {
        }

        public HistoriqueTransac(int id, int idUtilisateur, string idTransaction, DateTime dateTransaction, int montant, int idPari)
        {
            Id = id;
            IdUtilisateur = idUtilisateur;
            IdTransaction = idTransaction;
            DateTransaction = dateTransaction;
            Montant = montant;
            IdPari = idPari;
        }
    }
}
