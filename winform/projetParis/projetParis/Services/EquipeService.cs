using System;
using System.Collections.Generic;
using System.Text;
using System.Net.Http;
using System.Net.Http.Formatting;
using projetParis.Model;
using Newtonsoft.Json;

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
        public String insertEquipe(String nom, String logo)
        {
            try
            {
                Equipe equipe = new Equipe(nom, logo);
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Avant nom: " + equipe.nom+ " logo :"+equipe.logo);
                var json = JsonConvert.SerializeObject(equipe); // or JsonSerializer.Serialize if using System.Text.Json
                var stringContent = new StringContent(json, UnicodeEncoding.UTF8, "application/json");
                System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Apres"+json);
                HttpResponseMessage response = clint.PostAsync("equipe", stringContent).Result;
                return response.Content.ToString();


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
