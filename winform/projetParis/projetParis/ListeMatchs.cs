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
    public partial class ListeMatchs : UserControl
    {
        public ListeMatchs()
        {
            InitializeComponent();
        }
        public bool isFirstAffichage = true;

        private void ListeMatchs_Load(object sender, EventArgs e)
        {
           

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonInsertMatch_Click(object sender, EventArgs e)
        {
            InsertMatch insertForm = new InsertMatch();
            insertForm.UdpateEventHandler += F2_updateEventHandler1;
            insertForm.ShowDialog();
        }

        private void F2_updateEventHandler1(object sender, InsertMatch.UpdateEventArgs args)
        {
            MatchService matchService = new MatchService();
            this.dataGridViewMatch.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewMatch.DataSource = matchService.getAllMatchs();
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            MatchService matchService = new MatchService();
            this.dataGridViewMatch.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewMatch.DataSource = matchService.getAllMatchs();
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
                this.dataGridViewMatch.Columns.Add(btnEdit);
                //Add Bouton Supprimer
                DataGridViewButtonColumn btnSuppr = new DataGridViewButtonColumn();
                btnSuppr.HeaderText = "Delete";
                btnSuppr.Name = "buttonSupprimer";
                btnSuppr.Text = "Supprimer";
                btnSuppr.UseColumnTextForButtonValue = true;
                this.dataGridViewMatch.Columns.Add(btnSuppr);
                this.isFirstAffichage = false;
            }
        }
        public void loadData()
        {
            MatchService matchService = new MatchService();
            this.dataGridViewMatch.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewMatch.DataSource = matchService.getAllMatchs();
            //Add Bouton Modifier
            this.addButton();
        }

    }
}
