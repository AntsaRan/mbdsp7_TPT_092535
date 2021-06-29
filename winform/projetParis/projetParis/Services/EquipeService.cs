using System;
using System.Collections.Generic;
using System.Text;
using System.Net.Http;
using System.Net.Http.Formatting;
using projetParis.Model;

namespace projetParis.Services
{
    class EquipeService
    {
        public static string url = "https://grails-api.herokuapp.com/api/";
        public Object getAllEquipe() {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("equipes").Result;
                Object equipe = response.Content.ReadAsAsync<IEnumerable<Equipe>>().Result;
                return equipe;
            }
            catch(Exception e)
            {
                throw e;
            }
        }
        public EquipeService() {
        }
    }

}
