using projetParis.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace projetParis.Services
{
    class RegleServices
    {

        public static string url = "https://grails-api.herokuapp.com/api/";

        public Object getAllRegles()
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("regles").Result;

                /**System.Diagnostics.Debug.WriteLine("ito ilay json" + response.Content);               
                var matchss = response.Content.ReadAsStringAsync().Result;
                System.Diagnostics.Debug.WriteLine("ito ilay json22" + matchss.ToString()); **/
                Object regles = response.Content.ReadAsAsync<IEnumerable<Regles>>().Result;
                return regles;
            }
            catch (Exception e)
            {
                throw e;
            }
        }


        public RegleServices() 
        {
        }

    }


}
