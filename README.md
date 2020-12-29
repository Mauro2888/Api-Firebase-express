# Api-Firebase-express
Basic implementation with Firebase + Express

- npm install -g firebase-tools
- firebase login
- firebase init
- npm install express body-parser --save

#Import modules

- import * as admin from 'firebase-admin'
- import * as express from 'express'
- import * as bodyParser from 'body-parser'

#initialize app
- admin.initializeApp(functions.config().firebase);

