
namespace projetParis
{
    partial class ConnexionPage
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ConnexionPage));
            this.panelBack = new System.Windows.Forms.Panel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.buttonConnexion = new System.Windows.Forms.Button();
            this.textBoxMdp = new System.Windows.Forms.TextBox();
            this.textBoxPseudo = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.labelSlogan = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.panelBack.SuspendLayout();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // panelBack
            // 
            this.panelBack.AutoSize = true;
            this.panelBack.BackColor = System.Drawing.Color.Black;
            this.panelBack.Controls.Add(this.panel1);
            this.panelBack.Controls.Add(this.labelSlogan);
            this.panelBack.Controls.Add(this.pictureBox1);
            this.panelBack.Location = new System.Drawing.Point(2, 1);
            this.panelBack.Name = "panelBack";
            this.panelBack.Size = new System.Drawing.Size(797, 655);
            this.panelBack.TabIndex = 0;
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.White;
            this.panel1.Controls.Add(this.buttonConnexion);
            this.panel1.Controls.Add(this.textBoxMdp);
            this.panel1.Controls.Add(this.textBoxPseudo);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Location = new System.Drawing.Point(174, 197);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(483, 325);
            this.panel1.TabIndex = 2;
            // 
            // buttonConnexion
            // 
            this.buttonConnexion.BackColor = System.Drawing.Color.Gold;
            this.buttonConnexion.Font = new System.Drawing.Font("Comic Sans MS", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.buttonConnexion.Location = new System.Drawing.Point(260, 250);
            this.buttonConnexion.Name = "buttonConnexion";
            this.buttonConnexion.Size = new System.Drawing.Size(141, 36);
            this.buttonConnexion.TabIndex = 5;
            this.buttonConnexion.Text = "Connexion";
            this.buttonConnexion.UseVisualStyleBackColor = false;
            this.buttonConnexion.Click += new System.EventHandler(this.buttonConnexion_Click);
            // 
            // textBoxMdp
            // 
            this.textBoxMdp.Location = new System.Drawing.Point(74, 172);
            this.textBoxMdp.Multiline = true;
            this.textBoxMdp.Name = "textBoxMdp";
            this.textBoxMdp.PlaceholderText = "Votre mot de passe";
            this.textBoxMdp.Size = new System.Drawing.Size(327, 46);
            this.textBoxMdp.TabIndex = 3;
            this.textBoxMdp.TextChanged += new System.EventHandler(this.textBoxMdp_TextChanged);
            // 
            // textBoxPseudo
            // 
            this.textBoxPseudo.Location = new System.Drawing.Point(74, 66);
            this.textBoxPseudo.Multiline = true;
            this.textBoxPseudo.Name = "textBoxPseudo";
            this.textBoxPseudo.PlaceholderText = "Votre pseudo";
            this.textBoxPseudo.Size = new System.Drawing.Size(327, 46);
            this.textBoxPseudo.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Comic Sans MS", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(74, 143);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(128, 26);
            this.label2.TabIndex = 1;
            this.label2.Text = "Mot de passe";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Comic Sans MS", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(74, 37);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(72, 26);
            this.label1.TabIndex = 0;
            this.label1.Text = "Pseudo";
            // 
            // labelSlogan
            // 
            this.labelSlogan.AutoSize = true;
            this.labelSlogan.Font = new System.Drawing.Font("Comic Sans MS", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.labelSlogan.ForeColor = System.Drawing.Color.White;
            this.labelSlogan.Location = new System.Drawing.Point(248, 152);
            this.labelSlogan.Name = "labelSlogan";
            this.labelSlogan.Size = new System.Drawing.Size(327, 30);
            this.labelSlogan.TabIndex = 1;
            this.labelSlogan.Text = "On parie que vous allez gagner";
            this.labelSlogan.Click += new System.EventHandler(this.labelSlogan_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(364, 48);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(97, 101);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // ConnexionPage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 650);
            this.Controls.Add(this.panelBack);
            this.MaximizeBox = false;
            this.Name = "ConnexionPage";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ConnexionPage";
            this.Load += new System.EventHandler(this.ConnexionPage_Load);
            this.panelBack.ResumeLayout(false);
            this.panelBack.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panelBack;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label labelSlogan;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TextBox textBoxMdp;
        private System.Windows.Forms.TextBox textBoxPseudo;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttonConnexion;
    }
}

