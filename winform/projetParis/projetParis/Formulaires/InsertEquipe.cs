using projetParis.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace projetParis.Formulaires
{
    public partial class InsertEquipe : Form
    {
        public InsertEquipe()
        {
            InitializeComponent();
        }
       

        private void InsertEquipe_Load(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void buttonValider_Click(object sender, EventArgs e)
        {
            EquipeService equipeService = new EquipeService();
            String val = equipeService.insertEquipe(textBoxName.Text, textBoxLogo.Text);
            MessageBox.Show(val);
        }
    }
}
