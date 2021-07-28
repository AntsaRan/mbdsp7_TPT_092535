using Newtonsoft.Json;
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

        public SuperUser login(String pseudo, String password)
        {
            try
            {
                 SuperUser superUser = new SuperUser(pseudo, password);
                 HttpClient clint = new HttpClient();
                 clint.BaseAddress = new Uri(url);
                 // System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Avant nom: " + equipe.nom+ " logo :"+equipe.logo);
                 var json = JsonConvert.SerializeObject(superUser); // or JsonSerializer.Serialize if using System.Text.Json
                 var stringContent = new StringContent(json, UnicodeEncoding.UTF8, "application/json");
                 //System.Diagnostics.Debug.WriteLine("ito ilay json SUPERUSEER Avant"+json);
                 //System.Diagnostics.Debug.WriteLine("ito ilay json SUPERUSEER Apres" + stringContent);
                 HttpResponseMessage response = clint.PostAsync("authentificationAdmin", stringContent).Result;
                 SuperUser valiny = response.Content.ReadAsAsync<SuperUser>().Result;
                 //System.Diagnostics.Debug.WriteLine("Nahita Valiny " + valiny.Pseudo);
                 //System.Diagnostics.Debug.WriteLine("ito ilay Objet Super admin raha misy Avant nom: " + response.Content.ToString());
                 return valiny; 
             


            }
            catch (Exception e)
            {
                throw e;
            }
        }
    }
}
