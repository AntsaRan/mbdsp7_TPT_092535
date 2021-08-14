using projetParis.Model;
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
    public partial class HistoriqueUser : UserControl
    {
        public HistoriqueUser()
        {
            InitializeComponent();
        }

        private void buttonVoir_Click(object sender, EventArgs e)
        {
            if(textBoxIdUser.Text==null || textBoxIdUser.Text == "") { MessageBox.Show("Id User Necessaire"); }
            else
            {
                this.loadDataByIdUser(textBoxIdUser.Text);
            }
        }

        public void loadDataByIdUser(string idUser)
        {
            HistoriqueTransacService historiqueTransacService = new HistoriqueTransacService();
            this.dataGridViewHisto.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            List<HistoriqueTransac> val = new List<HistoriqueTransac>();
            val= historiqueTransacService.getHistoriqueByUser(idUser);
            if (val == null)
            {
                MessageBox.Show("Pas de résultat pour l'Id "+idUser);
            }
            dataGridViewHisto.DataSource = val;
            //this.addButton();
        }

        public void loadDataByIdUserName(string nomUser)
        {
            HistoriqueTransacService historiqueTransacService = new HistoriqueTransacService();
            this.dataGridViewHisto.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            List<HistoUserName> val = new List<HistoUserName>();
            val = historiqueTransacService.getHistoriqueByUserName(nomUser);
            if (val == null)
            {
                MessageBox.Show("Pas de résultat pour le nom " + nomUser);
            }
            dataGridViewHisto.DataSource = val;
            //this.addButton();
        }

        private void HistoriqueUser_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBoxNomUser.Text == null || textBoxNomUser.Text == "") { MessageBox.Show("Nom User Necessaire"); }
            else
            {
                System.Diagnostics.Debug.WriteLine("MAKATOO "+ textBoxNomUser.Text);
                this.loadDataByIdUserName(textBoxNomUser.Text);
            }
        }

        private void textBoxIdUser_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void textBoxNomUser_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
