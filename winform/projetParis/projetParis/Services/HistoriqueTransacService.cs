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
        public List<HistoriqueTransac> getHistoriqueByUser(string id)
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getHistoByUser/" + id).Result;
                //Object historiqueTransac = response.Content.ReadAsAsync<IEnumerable<HistoriqueTransac>>().Result;
                //List<MyStok> myDeserializedObjList = (List<MyStok>)Newtonsoft.Json.JsonConvert.DeserializeObject(sc, typeof(List<MyStok>));
                List<HistoriqueTransac> valiny = response.Content.ReadAsAsync<List<HistoriqueTransac>>().Result;
                return valiny;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.WriteLine("EXEPTION " + e.Message);
                throw e;
            }
        }
        public List<HistoUserName> getHistoriqueByUserName(string nom)
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getHistoByUserName?nom=" + nom).Result;
                //Object historiqueTransac = response.Content.ReadAsAsync<IEnumerable<HistoUserName>>().Result;
                List<HistoUserName> valiny = response.Content.ReadAsAsync<List<HistoUserName>>().Result;
                return valiny;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.WriteLine("EXEPTION " + e.Message);
                throw e;
            }
        }

    }
}
