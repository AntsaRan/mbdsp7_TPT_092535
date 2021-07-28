using System;
using System.Collections;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Text;
using Microsoft.Ajax.Utilities;
using projetParis.Model;
using Newtonsoft.Json;

namespace projetParis.Services
{
    class MatchService
    {

        public static string url = "https://grails-api.herokuapp.com/api/";

        public Object getAllMatchs()
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("matches").Result;
            
                /**System.Diagnostics.Debug.WriteLine("ito ilay json" + response.Content);               
                var matchss = response.Content.ReadAsStringAsync().Result;
                System.Diagnostics.Debug.WriteLine("ito ilay json22" + matchss.ToString()); **/
                Object matchs = response.Content.ReadAsAsync<IEnumerable<Match>>().Result;
                return matchs;
            }
            catch(Exception e)
            {
                throw e;
            }
        }

      
        /*-----------------------------ETO IZAO---------------------------*/
        public String insertMatch(string idEquipe1, string idEquipe2, string date,string lieu)
        {
            try
            {
                //System.Diagnostics.Debug.WriteLine("IDEQUIPE1 " + idEquipe1);
                //System.Diagnostics.Debug.WriteLine("IDEQUIPE2 " + idEquipe2);
                MatchToInsert match = new MatchToInsert(idEquipe1,idEquipe2,date,lieu);
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                // System.Diagnostics.Debug.WriteLine("ito ilay json EQUIPE Avant nom: " + equipe.nom+ " logo :"+equipe.logo);
                var json = JsonConvert.SerializeObject(match); // or JsonSerializer.Serialize if using System.Text.Json
                var stringContent = new StringContent(json, UnicodeEncoding.UTF8, "application/json");
                System.Diagnostics.Debug.WriteLine("ito ilay json Match Apres"+json);
                HttpResponseMessage response = clint.PostAsync("match", stringContent).Result;
               
             
                Match valiny = response.Content.ReadAsAsync<Match>().Result;
                if (valiny != null) {
                    //System.Diagnostics.Debug.WriteLine("Id inseree" + valiny.Id);
                    return valiny.Id;
                }
               
                //System.Diagnostics.Debug.WriteLine("Nahita Valiny " + valiny.Pseudo);
                //System.Diagnostics.Debug.WriteLine("ito ilay Objet Super admin raha misy Avant nom: " + response.Content.ToString());
                return "0";

            }
            catch (Exception e)
            {
                throw e;
            }
        }

        public MatchService()
        {

        }
    }
}
