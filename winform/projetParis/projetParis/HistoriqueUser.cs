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
            dataGridViewHisto.DataSource = historiqueTransacService.getHistoriqueByUser(idUser);
            //this.addButton();
        }

        private void HistoriqueUser_Load(object sender, EventArgs e)
        {

        }
    }
}
