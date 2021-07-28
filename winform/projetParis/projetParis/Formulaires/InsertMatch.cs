using projetParis.Model;
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
    public partial class InsertMatch : Form
    {
        public InsertMatch()
        {
            InitializeComponent();
            //dateTimePicker1.Format = DateTimePickerFormat.Custom;
            //dateTimePicker1.CustomFormat = "dd/MM/yyyy hh:mm:ss";

            dateTimePicker2.Format = DateTimePickerFormat.Time;
            dateTimePicker2.ShowUpDown = true;
            //DateTime myDate = dateTimePicker1.Value.Date +
                    //dateTimePicker2.Value.TimeOfDay;
            //System.Diagnostics.Debug.WriteLine("Ilay DATE Time Nampidirina "+myDate);

            EquipeService equipeService = new EquipeService();

            Object dataTable= equipeService.getAllEquipe();
            
            Object dataTable2 = equipeService.getAllEquipe();
            comboBoxEquipe1.DataSource = dataTable;
            comboBoxEquipe1.DisplayMember = "nom"; // Column Name
            comboBoxEquipe1.ValueMember = "id"; // Valeur
            comboBoxEquipe2.DataSource = dataTable2;
            comboBoxEquipe2.DisplayMember = "nom"; // Column Name
            comboBoxEquipe2.ValueMember = "id"; // Valeur
            System.Diagnostics.Debug.WriteLine("OBJECT GET EQUIPE  " + dataTable.ToString());

        }

        public delegate void UpdateDelegate(object sender, UpdateEventArgs args);
        public event UpdateDelegate UdpateEventHandler;

        public class UpdateEventArgs : EventArgs
        {
            public string Data { get; set; }
        }

      

        private void buttonValider_Click(object sender, EventArgs e)
        {
            DateTime date = dateTimePicker1.Value.Date+dateTimePicker2.Value.TimeOfDay;
            String dateString = date.ToString("yyyy-MM-ddTHH:mm:ss");
          
            EquipeService equipeService = new EquipeService();
            if (comboBoxEquipe1.Text == "" || comboBoxEquipe2.Text == "" || dateString == "" || textBoxLieu.Text == "") {
                MessageBox.Show("Veuillez remplir les champs vide");
            }
            else
            {                         
                string selectedValue1 = (string)comboBoxEquipe1.SelectedValue;
                string selectedValue2 = (string)comboBoxEquipe2.SelectedValue;
               
                bool test = equipeService.testInsertMatch(selectedValue1, selectedValue2, date);
                System.Diagnostics.Debug.WriteLine("Bool " + test);
                if (test) {
                    MatchService matchService = new MatchService();
                    String response = matchService.insertMatch(selectedValue1, selectedValue2, dateString, textBoxLieu.Text);
                    if (response == "0") { MessageBox.Show("une erreur s'est produite"); }
                    else { 
                        MessageBox.Show("Match inseré");
                        this.Close();
                        update();
                    }
                }
                else { MessageBox.Show("Un match doit avoir 2 equipes différentes et une date superieur a la date d'aujourd'hui"); }
            }
           
        }

        

        protected void update()
        {
            UpdateEventArgs args = new UpdateEventArgs();
            UdpateEventHandler.Invoke(this, args);
        }
    }
}
