using projetParis.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;

namespace projetParis.Services
{
    class SuperUserService
    {
        public static string url = "https://grails-api.herokuapp.com/oracle/";

        public String login(String pseudo, String password)
        {
            try
            {
                /* SuperUser equipe = new SuperUser(nom, logo);
                 HttpClient clint = new HttpClient();
                 clint.BaseAddress = new Uri(url);
                 // System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Avant nom: " + equipe.nom+ " logo :"+equipe.logo);
                 var json = JsonConvert.SerializeObject(equipe); // or JsonSerializer.Serialize if using System.Text.Json
                 var stringContent = new StringContent(json, UnicodeEncoding.UTF8, "application/json");
                 //System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Apres"+json);
                 HttpResponseMessage response = clint.PostAsync("equipe", stringContent).Result;
                 return response.Content.ToString(); */
                return "a";


            }
            catch (Exception e)
            {
                throw e;
            }
        }
    }
}
