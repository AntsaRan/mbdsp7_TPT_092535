using System;
using System.Collections;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Text;
using Microsoft.Ajax.Utilities;
using MongoDB.Bson.IO;
using projetParis.Model;

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

        public Object InsertMatch()
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
