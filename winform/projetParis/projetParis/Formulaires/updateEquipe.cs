﻿using projetParis.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace projetParis.Formulaires
{
    public partial class updateEquipe : Form
    {
        string id;    
        public updateEquipe(ListeEquipes listeEquipe,string id1,string nom,string logo)
        {
            

            InitializeComponent();
            this.textBoxName.Text = nom;
            this.textBoxLogo.Text = logo;
            this.id = id1;
        }
        public delegate void UpdateDelegate(object sender, UpdateEventArgs args);
        public event UpdateDelegate UdpateEventHandler;

        public class UpdateEventArgs : EventArgs
        {
            public string Data { get; set; }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonValider_Click(object sender, EventArgs e)
        {
            EquipeService equipeService = new EquipeService();
            if (textBoxName.Text == "" || textBoxLogo.Text == "")
            {
                MessageBox.Show("Veuillez remplir les champs vide");
            }
            else
            {
                String val = equipeService.updateEquipe(this.id,textBoxName.Text, textBoxLogo.Text);
                var confirmResult = MessageBox.Show("Equipe modifiée",
                                     "Message",
                                     MessageBoxButtons.OK);
                if (confirmResult == DialogResult.OK)
                {
                    //System.Diagnostics.Debug.WriteLine("METY ILAY OK BUTTON");
                    this.Close();
                    update();
                }
            }
        }
        protected void update()
        {
            UpdateEventArgs args = new UpdateEventArgs();
            UdpateEventHandler.Invoke(this, args);
        }
    }
}
