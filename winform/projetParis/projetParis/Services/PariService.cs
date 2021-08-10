using projetParis.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace projetParis.Services
{
    class PariService
    {
        public PariService() { }

        public static string url = "https://grails-api.herokuapp.com/oracle/";
        public Object getAllpari()
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getAllParis").Result;
                Object pari = response.Content.ReadAsAsync<IEnumerable<Pari>>().Result;
                return pari;
            }
            catch (Exception e)
            {
                throw e;
            }
        }
    }
}
