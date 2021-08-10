using projetParis.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace projetParis.Services
{
    class HistoriqueTransacService
    { 



        public HistoriqueTransacService()
        {

        }

        public static string url = "https://grails-api.herokuapp.com/oracle/";
        public Object getHistoriqueByUser(string id)
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getHistoByUser/" + id).Result;
                Object historiqueTransac = response.Content.ReadAsAsync<IEnumerable<HistoriqueTransac>>().Result;
                return historiqueTransac;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.WriteLine("EXEPTION " + e.Message);
                throw e;
            }
        }

    }
}
