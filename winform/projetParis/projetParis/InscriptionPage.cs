using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace projetParis
{
    public partial class InscriptionPage : Form
    {
        public InscriptionPage()
        {
            InitializeComponent();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void InscriptionPage_Load(object sender, EventArgs e)
        {
            comboBoxMois.DisplayMember = "Text";
            comboBoxMois.ValueMember = "Value";
            comboBoxMois.Items.Add(new { Text = "Janvier", Value = "01" });
            comboBoxMois.Items.Add(new { Text = "Fevrier", Value = "02" });
            comboBoxMois.Items.Add(new { Text = "Mars", Value = "03" });
            comboBoxMois.Items.Add(new { Text = "Avril", Value = "04" });
            comboBoxMois.Items.Add(new { Text = "Mai", Value = "05" });
            comboBoxMois.Items.Add(new { Text = "Juin", Value = "06" });
            comboBoxMois.Items.Add(new { Text = "Juillet", Value = "07" });
            comboBoxMois.Items.Add(new { Text = "Aout", Value = "08" });
            comboBoxMois.Items.Add(new { Text = "Septembre", Value = "09" });
            comboBoxMois.Items.Add(new { Text = "Octobre", Value = "10" });
            comboBoxMois.Items.Add(new { Text = "Novembre", Value = "11" });
            comboBoxMois.Items.Add(new { Text = "Decembre", Value = "12" });
            comboBoxMois.SelectedIndex = 0;

            for(int i=0; i<31; i++) { }

        }
    }
}
