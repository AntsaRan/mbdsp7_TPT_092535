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
    public partial class ListeEquipes : UserControl
    {
        public ListeEquipes()
        {
            InitializeComponent();
        }
        public bool isFirstAffichage =true;
        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            EquipeService equipeService = new EquipeService();
            this.dataGridViewEquipe.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewEquipe.DataSource = equipeService.getAllEquipe();
            //Add Bouton Modifier
            if (isFirstAffichage) {
                DataGridViewButtonColumn btnEdit = new DataGridViewButtonColumn();
                btnEdit.HeaderText = "Edit";
                btnEdit.Name = "buttonEdit";
                btnEdit.Text = "Modifier";
                btnEdit.UseColumnTextForButtonValue = true;
                this.dataGridViewEquipe.Columns.Add(btnEdit);
                //Add Bouton Supprimer
                DataGridViewButtonColumn btnSuppr = new DataGridViewButtonColumn();
                btnSuppr.HeaderText = "Delete";
                btnSuppr.Name = "buttonSupprimer";
                btnSuppr.Text = "Supprimer";
                btnSuppr.UseColumnTextForButtonValue = true;
                this.dataGridViewEquipe.Columns.Add(btnSuppr);
                this.isFirstAffichage = false;
            };
           
        }

        private void dataGridViewEquipe_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex==3)
            {
                DataGridViewRow row = this.dataGridViewEquipe.Rows[e.RowIndex];
                String id = row.Cells["id"].Value.ToString();
                MessageBox.Show(id +" Row Clicked");
                
            }
        }
    }
}
