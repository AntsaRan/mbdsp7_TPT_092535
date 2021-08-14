
namespace projetParis
{
    partial class ListePari
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
            this.dataGridViewPari = new System.Windows.Forms.DataGridView();
            this.buttonRefresh = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxNomUser = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.buttonSearchNomUser = new System.Windows.Forms.Button();
            this.textBoxIdUser = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.buttonSearchIdUser = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPari)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewPari
            // 
            this.dataGridViewPari.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewPari.Location = new System.Drawing.Point(9, 93);
            this.dataGridViewPari.Name = "dataGridViewPari";
            this.dataGridViewPari.RowTemplate.Height = 25;
            this.dataGridViewPari.Size = new System.Drawing.Size(805, 484);
            this.dataGridViewPari.TabIndex = 13;
            // 
            // buttonRefresh
            // 
            this.buttonRefresh.Location = new System.Drawing.Point(9, 56);
            this.buttonRefresh.Name = "buttonRefresh";
            this.buttonRefresh.Size = new System.Drawing.Size(105, 36);
            this.buttonRefresh.TabIndex = 12;
            this.buttonRefresh.Text = "Refresh";
            this.buttonRefresh.UseVisualStyleBackColor = true;
            this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 19F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(120, 54);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(174, 36);
            this.label1.TabIndex = 11;
            this.label1.Text = "Liste des Paris";
            // 
            // textBoxNomUser
            // 
            this.textBoxNomUser.Location = new System.Drawing.Point(689, 65);
            this.textBoxNomUser.Name = "textBoxNomUser";
            this.textBoxNomUser.Size = new System.Drawing.Size(47, 23);
            this.textBoxNomUser.TabIndex = 24;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Segoe UI Symbol", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label3.Location = new System.Drawing.Point(579, 65);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(113, 20);
            this.label3.TabIndex = 23;
            this.label3.Text = "Nom Utilisateur";
            // 
            // buttonSearchNomUser
            // 
            this.buttonSearchNomUser.Location = new System.Drawing.Point(742, 65);
            this.buttonSearchNomUser.Name = "buttonSearchNomUser";
            this.buttonSearchNomUser.Size = new System.Drawing.Size(72, 24);
            this.buttonSearchNomUser.TabIndex = 22;
            this.buttonSearchNomUser.Text = "Voir";
            this.buttonSearchNomUser.UseVisualStyleBackColor = true;
            this.buttonSearchNomUser.Click += new System.EventHandler(this.buttonSearchNomUser_Click);
            // 
            // textBoxIdUser
            // 
            this.textBoxIdUser.Location = new System.Drawing.Point(437, 66);
            this.textBoxIdUser.Name = "textBoxIdUser";
            this.textBoxIdUser.Size = new System.Drawing.Size(47, 23);
            this.textBoxIdUser.TabIndex = 21;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI Symbol", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(345, 66);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(95, 20);
            this.label2.TabIndex = 20;
            this.label2.Text = "ID Utilisateur";
            // 
            // buttonSearchIdUser
            // 
            this.buttonSearchIdUser.Location = new System.Drawing.Point(490, 65);
            this.buttonSearchIdUser.Name = "buttonSearchIdUser";
            this.buttonSearchIdUser.Size = new System.Drawing.Size(72, 25);
            this.buttonSearchIdUser.TabIndex = 19;
            this.buttonSearchIdUser.Text = "Voir";
            this.buttonSearchIdUser.UseVisualStyleBackColor = true;
            this.buttonSearchIdUser.Click += new System.EventHandler(this.buttonSearchIdUser_Click);
            // 
            // ListePari
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.textBoxNomUser);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.buttonSearchNomUser);
            this.Controls.Add(this.textBoxIdUser);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.buttonSearchIdUser);
            this.Controls.Add(this.dataGridViewPari);
            this.Controls.Add(this.buttonRefresh);
            this.Controls.Add(this.label1);
            this.Name = "ListePari";
            this.Size = new System.Drawing.Size(829, 715);
            this.Load += new System.EventHandler(this.ListePari_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPari)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewPari;
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxNomUser;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button buttonSearchNomUser;
        private System.Windows.Forms.TextBox textBoxIdUser;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button buttonSearchIdUser;
    }
}
