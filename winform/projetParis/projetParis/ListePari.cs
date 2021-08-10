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

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            this.loadData();
        }
    }
}
