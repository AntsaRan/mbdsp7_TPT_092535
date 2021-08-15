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
    public partial class LancerMatch : UserControl
    {
        public LancerMatch()
        {
            InitializeComponent();
        }
        public bool isFirstAffichage = true;
        public int forButtonLancer = 0;

        private void LancerMatch_Load(object sender, EventArgs e)
        {
           /* MatchService matchService = new MatchService();
            this.dataGridViewMatchToStart.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewMatchToStart.DataSource = matchService.getAllMatchs();
            //Add Bouton Modifier
            if (isFirstAffichage)
            {
                DataGridViewButtonColumn btnEdit = new DataGridViewButtonColumn();
                btnEdit.HeaderText = "Edit";
                btnEdit.Name = "buttonEdit";
                btnEdit.Text = "Modifier";
                btnEdit.UseColumnTextForButtonValue = true;
                this.dataGridViewMatchToStart.Columns.Add(btnEdit);
                //Add Bouton Supprimer
                DataGridViewButtonColumn btnSuppr = new DataGridViewButtonColumn();
                btnSuppr.HeaderText = "Delete";
                btnSuppr.Name = "buttonSupprimer";
                btnSuppr.Text = "Supprimer";
                btnSuppr.UseColumnTextForButtonValue = true;
                this.dataGridViewMatchToStart.Columns.Add(btnSuppr);
                this.isFirstAffichage = false;
            }*/
        }

        private void dataGridViewMatchToStart_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
           
            
        }
        public void loadData()
        {
            MatchService matchService = new MatchService();
            this.dataGridViewMatchToStart.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewMatchToStart.DataSource = matchService.getMatchsToStart();
            this.addButton();
            this.forButtonLancer += 1;
            System.Diagnostics.Debug.WriteLine("forButtonLancer " + forButtonLancer);

        }

        public void addButton()
        {
            if (isFirstAffichage && dataGridViewMatchToStart.Rows.Count != 0)
            {
                DataGridViewButtonColumn btnEdit = new DataGridViewButtonColumn();
                btnEdit.HeaderText = "Lancer";
                btnEdit.Name = "buttonlancer";
                btnEdit.Text = "Lancer";
                btnEdit.UseColumnTextForButtonValue = true;
                this.dataGridViewMatchToStart.Columns.Add(btnEdit);
                
                this.isFirstAffichage = false;
            }
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            Cursor = Cursors.WaitCursor; // change cursor to hourglass type
            this.loadData();
            Cursor = Cursors.Arrow; // change cursor to hourglass type
        }

        private void dataGridViewMatchToStart_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            int variable = 10;
            if (forButtonLancer>1) variable = 0;
            System.Diagnostics.Debug.WriteLine("VARIABLE "+variable);
            if (e.ColumnIndex == variable)
            {
                Cursor = Cursors.WaitCursor; // change cursor to hourglass type
                DataGridViewRow row = this.dataGridViewMatchToStart.Rows[e.RowIndex];
                string id = row.Cells["id"].Value.ToString();
                string date = row.Cells["date"].Value.ToString();
                string lieu = row.Cells["lieu"].Value.ToString();

                MatchService matchService = new MatchService();
                matchService.lancerMatch(id);

                // if (response == null || response=="") { MessageBox.Show("une erreur est survenue , veuillez réesayer"); }
                //else { MessageBox.Show("Le match " + id + " pour le " + date + " est lancé"); }
                MessageBox.Show("Le match a " + lieu + " pour le " + date + " a commencé");
                this.loadData();
                Cursor = Cursors.Arrow; // change cursor to normal type
            } 
        }
    }
    
}
