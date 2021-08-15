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
    public partial class ListePari : UserControl
    {
        public ListePari()
        {
            InitializeComponent();
        }

        private void ListePari_Load(object sender, EventArgs e)
        {

        }

        public void loadData()
        {
            PariService pariservice = new PariService();
            this.dataGridViewPari.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewPari.DataSource = pariservice.getAllpari();
            //this.addButton();
        }

        public void loadDataByIdUser(string idUser)
        {
            PariService pariservice = new PariService();
            this.dataGridViewPari.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            List<Pari> val = new List<Pari>();
            val = pariservice.getPariByIdUser(idUser);
            if (val == null)
            {
                MessageBox.Show("Pas de résultat pour l'Id " + idUser);
            }
            dataGridViewPari.DataSource = val;
            //this.addButton();
        }

        public void loadDataByIdUserName(string nomUser)
        {
            PariService pariservice = new PariService();
            this.dataGridViewPari.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            List<PariUserName> val = new List<PariUserName>();
            val = pariservice.getPariByNomUser(nomUser);
            if (val == null)
            {
                MessageBox.Show("Pas de résultat pour le nom " + nomUser);
            }
            dataGridViewPari.DataSource = val;
            //this.addButton();
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            this.loadData();
        }

        private void buttonSearchIdUser_Click(object sender, EventArgs e)
        {
            if (textBoxIdUser.Text == null || textBoxIdUser.Text == "") { MessageBox.Show("Id User Necessaire"); }
            else
            {
                Cursor = Cursors.WaitCursor; // change cursor to hourglass type    
                this.loadDataByIdUser(textBoxIdUser.Text);
                Cursor = Cursors.Arrow; // change cursor to hourglass type    
            }
        }

        private void buttonSearchNomUser_Click(object sender, EventArgs e)
        {
            if (textBoxNomUser.Text == null || textBoxNomUser.Text == "") { MessageBox.Show("Nom User Necessaire"); }
            else
            {
                Cursor = Cursors.WaitCursor; // change cursor to hourglass type    
                System.Diagnostics.Debug.WriteLine("MAKATOO " + textBoxNomUser.Text);
                this.loadDataByIdUserName(textBoxNomUser.Text);
                Cursor = Cursors.Arrow; // change cursor to hourglass type    
            }
        }
    }
}
