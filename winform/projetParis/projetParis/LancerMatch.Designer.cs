
namespace projetParis
{
    partial class LancerMatch
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
            this.dataGridViewMatchToStart = new System.Windows.Forms.DataGridView();
            this.buttonRefresh = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewMatchToStart)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewMatchToStart
            // 
            this.dataGridViewMatchToStart.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewMatchToStart.Location = new System.Drawing.Point(11, 93);
            this.dataGridViewMatchToStart.Name = "dataGridViewMatchToStart";
            this.dataGridViewMatchToStart.RowTemplate.Height = 25;
            this.dataGridViewMatchToStart.Size = new System.Drawing.Size(805, 484);
            this.dataGridViewMatchToStart.TabIndex = 7;
            this.dataGridViewMatchToStart.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewMatchToStart_CellContentClick);
            // 
            // buttonRefresh
            // 
            this.buttonRefresh.Location = new System.Drawing.Point(11, 51);
            this.buttonRefresh.Name = "buttonRefresh";
            this.buttonRefresh.Size = new System.Drawing.Size(105, 36);
            this.buttonRefresh.TabIndex = 6;
            this.buttonRefresh.Text = "Refresh";
            this.buttonRefresh.UseVisualStyleBackColor = true;
            this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 19F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(147, 51);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(206, 36);
            this.label1.TabIndex = 5;
            this.label1.Text = "Lancer un Match";
            // 
            // LancerMatch
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.dataGridViewMatchToStart);
            this.Controls.Add(this.buttonRefresh);
            this.Controls.Add(this.label1);
            this.Name = "LancerMatch";
            this.Size = new System.Drawing.Size(829, 715);
            this.Load += new System.EventHandler(this.LancerMatch_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewMatchToStart)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.DataGridView dataGridViewMatchToStart;
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.Label label1;
    }
}
