using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace projetParis
{
    public partial class Accueil : Form
    {
        public Accueil()
        {
            InitializeComponent();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            listeEquipes1.BringToFront();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            listeMatchs1.BringToFront();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            listeRegles1.loadData();
            listeRegles1.BringToFront();
        }

        private void pictureBox5_Click(object sender, EventArgs e)
        {

        }

        private void listeEquipes1_Load(object sender, EventArgs e)
        {

        }

        private void Accueil_Load(object sender, EventArgs e)
        {
            listeEquipes1.BringToFront();
        }
    }
}
