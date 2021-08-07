"use strict";
const nodemailer = require("nodemailer");

// async..await is not allowed in global scope, must use a wrapper
function main(req, res) {
    // Generate test SMTP service account from ethereal.email
    // Only needed if you don't have a real mail account for testing
    if (req) {
        var mail = req.body.mail;
        var nom = req.body.nom;
        var msg = req.body.msg;

        console.log(JSON.stringify(req.body.msg) + " MESSAGE ");

        //  let testAccount = await nodemailer.createTestAccount();

        // create reusable transporter object using the default SMTP transport
        var transporter = nodemailer.createTransport({
            service: 'gmail',
            auth: {
                user: 'mbdstpt0@gmail.com',
                pass: 'mbdstpt2020'
            }
        });
        // send mail with defined transport object
        var mailOptions = {
            from: mail, // sender address
            to: "rivomahefamaharo@gmail.com", // list of receiverst
            subject: "customer message", // Subject line
            text: msg, // plain text body
            html: "<p> Nom :" + nom + "</p>"
                + "<p>email:" + mail + "</p>"
                + "<p>" + msg + "</p>", // html body
        };

        transporter.sendMail(mailOptions, function (error, info) {
            if (error) {
                console.log(error);
            } else {
                console.log('Email sent: ' + info.response);
                res.json("ok");
            }
        });
        //console.log("Message sent: %s", info.messageId);
        // Message sent: <b658f8ca-6296-ccf4-8306-87d57a0b4321@example.com>

        // Preview only available when sending through an Ethereal account
        //console.log("Preview URL: %s", nodemailer.getTestMessageUrl(info));
        // Preview URL: https://ethereal.email/message/WaQKMgKddxQDoou...
    }
}

module.exports = { main };
