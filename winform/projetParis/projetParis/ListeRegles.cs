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
    public partial class ListeRegles : UserControl
    {
        public ListeRegles()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void ListeRegles_Load(object sender, EventArgs e)
        {

        }

        public void loadData()
        {
            RegleServices regleServices = new RegleServices();
            this.dataGridViewRegles.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewRegles.DataSource = regleServices.getAllRegles();
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            this.loadData();
        }
    }
}
