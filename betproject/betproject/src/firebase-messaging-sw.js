// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.8.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.8.0/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in the
// messagingSenderId.

firebase.initializeApp({
    apiKey: "AIzaSyB1XqvkjClNswR56VveJGcu6DWTKc7hGco",
    authDomain: "mbds-5e91b.firebaseapp.com",
    projectId: "mbds-5e91b",
    storageBucket: "mbds-5e91b.appspot.com",
    messagingSenderId: "1003946694596",
    appId: "1:1003946694596:web:7178143c417a66c740f691",
    measurementId: "G-KNQR6ERP5Q" 
});
const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function(payload) {

    console.log("Notification back" + payload.data.title);
    return self.registration.showNotification(payload.data.title,  { body: payload.data.body});
});