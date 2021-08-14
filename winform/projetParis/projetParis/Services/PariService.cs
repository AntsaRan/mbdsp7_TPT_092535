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

        public List<Pari> getPariByIdUser(string id)
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getAllParisbyUser/" + id).Result;
                //Object historiqueTransac = response.Content.ReadAsAsync<IEnumerable<HistoriqueTransac>>().Result;
                //List<MyStok> myDeserializedObjList = (List<MyStok>)Newtonsoft.Json.JsonConvert.DeserializeObject(sc, typeof(List<MyStok>));
                List<Pari> valiny = response.Content.ReadAsAsync<List<Pari>>().Result;
                return valiny;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.WriteLine("EXEPTION " + e.Message);
                throw e;
            }
        }


        public List<PariUserName> getPariByNomUser(string id)
        {
            try
            {
                HttpClient clint = new HttpClient();
                clint.BaseAddress = new Uri(url);
                HttpResponseMessage response = clint.GetAsync("getAllParisbyUserName?nom=" + id).Result;
                //Object historiqueTransac = response.Content.ReadAsAsync<IEnumerable<HistoriqueTransac>>().Result;
                //List<MyStok> myDeserializedObjList = (List<MyStok>)Newtonsoft.Json.JsonConvert.DeserializeObject(sc, typeof(List<MyStok>));
                List<PariUserName> valiny = response.Content.ReadAsAsync<List<PariUserName>>().Result;
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
