using projetParis.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace projetParis
{
    public partial class ListeUtilisateur : UserControl
    {
        public ListeUtilisateur()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void ListeUtilisateur_Load(object sender, EventArgs e)
        {

        }
        public void loadData()
        {
            UtilisateurService userservice = new UtilisateurService();
            this.dataGridViewUser.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewUser.DataSource = userservice.getAllUser();
            //this.addButton();
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            this.loadData();
        }
    }
}
