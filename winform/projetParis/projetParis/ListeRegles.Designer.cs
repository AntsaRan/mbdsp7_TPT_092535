
namespace projetParis
{
    partial class ListeRegles
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
            this.buttonRefresh = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridViewRegles = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewRegles)).BeginInit();
            this.SuspendLayout();
            // 
            // buttonRefresh
            // 
            this.buttonRefresh.Location = new System.Drawing.Point(9, 62);
            this.buttonRefresh.Name = "buttonRefresh";
            this.buttonRefresh.Size = new System.Drawing.Size(108, 35);
            this.buttonRefresh.TabIndex = 5;
            this.buttonRefresh.Text = "Refresh";
            this.buttonRefresh.UseVisualStyleBackColor = true;
            this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 19F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(155, 56);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(196, 36);
            this.label1.TabIndex = 4;
            this.label1.Text = "Liste des Regles";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // dataGridViewRegles
            // 
            this.dataGridViewRegles.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewRegles.Location = new System.Drawing.Point(11, 116);
            this.dataGridViewRegles.Name = "dataGridViewRegles";
            this.dataGridViewRegles.RowTemplate.Height = 25;
            this.dataGridViewRegles.Size = new System.Drawing.Size(807, 482);
            this.dataGridViewRegles.TabIndex = 7;
            // 
            // ListeRegles
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.dataGridViewRegles);
            this.Controls.Add(this.buttonRefresh);
            this.Controls.Add(this.label1);
            this.Name = "ListeRegles";
            this.Size = new System.Drawing.Size(829, 715);
            this.Load += new System.EventHandler(this.ListeRegles_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewRegles)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView dataGridViewRegles;
    }
}
