using projetParis.Formulaires;
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
            this.addButton();

        }

        private void dataGridViewEquipe_CellClick(object sender, DataGridViewCellEventArgs e)
        {
           
            if (e.ColumnIndex==1)
            {
                EquipeService equipeService = new EquipeService();
                DataGridViewRow row = this.dataGridViewEquipe.Rows[e.RowIndex];
                String id = row.Cells["id"].Value.ToString();
                string response = equipeService.deleteEquipe(id);
                dataGridViewEquipe.DataSource = equipeService.getAllEquipe();
                this.dataGridViewEquipe.Refresh();
                this.dataGridViewEquipe.Update();
                MessageBox.Show("Team with id: "+id+" deleted");
                
            }
            if (e.ColumnIndex == 0)
            {
                DataGridViewRow row = this.dataGridViewEquipe.Rows[e.RowIndex];
                string id = row.Cells["id"].Value.ToString();
                string nom = row.Cells["nom"].Value.ToString();
                string logo = row.Cells["logo"].Value.ToString();
                
                
                updateEquipe updateEquipe = new updateEquipe(this, id, nom, logo);
                updateEquipe.UdpateEventHandler += F2_updateEventHandler2;
                updateEquipe.ShowDialog();
               
               
            }
        }

        private void ListeEquipes_Load(object sender, EventArgs e)
        {
            EquipeService equipeService = new EquipeService();
            this.dataGridViewEquipe.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewEquipe.DataSource = equipeService.getAllEquipe();
            //Add Bouton Modifier
            this.addButton();
        }

        private void buttonInsertEquipe_Click(object sender, EventArgs e)
        {

            InsertEquipe insertForm = new InsertEquipe(this);
            insertForm.UdpateEventHandler += F2_updateEventHandler1;
            insertForm.ShowDialog();
            
        }

        public void addButton()
        {
            if (isFirstAffichage)
            {
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
                //btnSuppr.ReadOnly = true;
                btnSuppr.UseColumnTextForButtonValue = true;
                //this.dataGridViewEquipe.Columns.Add(btnSuppr);
                this.isFirstAffichage = false;
            }
        }

        private void F2_updateEventHandler1(object sender , InsertEquipe.UpdateEventArgs args)
        {
            EquipeService equipeService = new EquipeService();
            this.dataGridViewEquipe.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewEquipe.DataSource = equipeService.getAllEquipe();
        }
        private void F2_updateEventHandler2(object sender, updateEquipe.UpdateEventArgs args)
        {
            EquipeService equipeService = new EquipeService();
            this.dataGridViewEquipe.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewEquipe.DataSource = equipeService.getAllEquipe();
        }
    }
}
