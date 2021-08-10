using projetParis.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace projetParis.Services
{
    class UtilisateurService
    {

        public static string url = "https://grails-api.herokuapp.com/oracle/";
        public Object getAllUser()
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getAllUser").Result;
                Object user = response.Content.ReadAsAsync<IEnumerable<Utilisateurs>>().Result;
                return user;
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        public UtilisateurService()
        {

        }
    }
}
