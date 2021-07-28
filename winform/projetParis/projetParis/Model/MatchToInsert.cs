using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class MatchToInsert
    {
       
        public string idEquipe1;
        public string idEquipe2;
        public string date;
        public string lieu;

        public MatchToInsert(string equipe1, string equipe2, string date, string lieu)
        {
            this.idEquipe1 = equipe1;
            this.idEquipe2 = equipe2;
            this.date = date;
            this.lieu = lieu;
        }
    }
}
