using System;
using System.Collections.Generic;
using System.Text;

namespace projetParis.Model
{
    class SuperUser
    {
        private int id;
        public string pseudo;
        public string password;
        private string mail;



        public int Id { get => id; set => id = value; }
        public string Pseudo { get => pseudo; set => pseudo = value; }
        public string Password { get => password; set => password = value; }
        public string Mail { get => mail; set => mail = value; }

        public SuperUser()
        {
        }

        public SuperUser(int id, string pseudo, string password, string mail)
        {
            Id = id;
            Pseudo = pseudo;
            Password = password;
            Mail = mail;
        }

        public SuperUser(string pseudo, string password)
        {
            this.pseudo = pseudo;
            this.password = password;
        }

        public SuperUser(string pseudo, string password, string mail)
        {
            Pseudo = pseudo;
            Password = password;
            Mail = mail;
        }

        
    }
}
