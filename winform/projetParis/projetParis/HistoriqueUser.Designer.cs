
namespace projetParis
{
    partial class HistoriqueUser
    {
        /// <summary> 
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur de composants

        /// <summary> 
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas 
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewHisto = new System.Windows.Forms.DataGridView();
            this.buttonVoir = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxIdUser = new System.Windows.Forms.TextBox();
            this.textBoxNomUser = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.buttonSearchNomUser = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewHisto)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewHisto
            // 
            this.dataGridViewHisto.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewHisto.Location = new System.Drawing.Point(10, 91);
            this.dataGridViewHisto.Name = "dataGridViewHisto";
            this.dataGridViewHisto.RowTemplate.Height = 25;
            this.dataGridViewHisto.Size = new System.Drawing.Size(805, 484);
            this.dataGridViewHisto.TabIndex = 13;
            // 
            // buttonVoir
            // 
            this.buttonVoir.Location = new System.Drawing.Point(491, 60);
            this.buttonVoir.Name = "buttonVoir";
            this.buttonVoir.Size = new System.Drawing.Size(72, 25);
            this.buttonVoir.TabIndex = 12;
            this.buttonVoir.Text = "Voir";
            this.buttonVoir.UseVisualStyleBackColor = true;
            this.buttonVoir.Click += new System.EventHandler(this.buttonVoir_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 19F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(10, 49);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(314, 36);
            this.label1.TabIndex = 11;
            this.label1.Text = "Historique d\'un Utilisateur";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI Symbol", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(346, 61);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(95, 20);
            this.label2.TabIndex = 14;
            this.label2.Text = "ID Utilisateur";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // textBoxIdUser
            // 
            this.textBoxIdUser.Location = new System.Drawing.Point(438, 61);
            this.textBoxIdUser.Name = "textBoxIdUser";
            this.textBoxIdUser.Size = new System.Drawing.Size(47, 23);
            this.textBoxIdUser.TabIndex = 15;
            this.textBoxIdUser.TextChanged += new System.EventHandler(this.textBoxIdUser_TextChanged);
            // 
            // textBoxNomUser
            // 
            this.textBoxNomUser.Location = new System.Drawing.Point(690, 60);
            this.textBoxNomUser.Name = "textBoxNomUser";
            this.textBoxNomUser.Size = new System.Drawing.Size(47, 23);
            this.textBoxNomUser.TabIndex = 18;
            this.textBoxNomUser.TextChanged += new System.EventHandler(this.textBoxNomUser_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Segoe UI Symbol", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label3.Location = new System.Drawing.Point(580, 60);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(113, 20);
            this.label3.TabIndex = 17;
            this.label3.Text = "Nom Utilisateur";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // buttonSearchNomUser
            // 
            this.buttonSearchNomUser.Location = new System.Drawing.Point(743, 60);
            this.buttonSearchNomUser.Name = "buttonSearchNomUser";
            this.buttonSearchNomUser.Size = new System.Drawing.Size(72, 24);
            this.buttonSearchNomUser.TabIndex = 16;
            this.buttonSearchNomUser.Text = "Voir";
            this.buttonSearchNomUser.UseVisualStyleBackColor = true;
            this.buttonSearchNomUser.Click += new System.EventHandler(this.button1_Click);
            // 
            // HistoriqueUser
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.textBoxNomUser);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.buttonSearchNomUser);
            this.Controls.Add(this.textBoxIdUser);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.dataGridViewHisto);
            this.Controls.Add(this.buttonVoir);
            this.Controls.Add(this.label1);
            this.Name = "HistoriqueUser";
            this.Size = new System.Drawing.Size(829, 715);
            this.Load += new System.EventHandler(this.HistoriqueUser_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewHisto)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewHisto;
        private System.Windows.Forms.Button buttonVoir;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxIdUser;
        private System.Windows.Forms.TextBox textBoxNomUser;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button buttonSearchNomUser;
    }
}
