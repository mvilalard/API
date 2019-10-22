var express = require('express');
var app = express();
var mysql = require('mysql');
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "pockethealth"
});

app.get('/patient/:id', function (req, res) {
    console.log('GET PATIENT');
    con.query("SELECT * from patient where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/patient/:id/heights', function (req, res) {
    console.log('GET heights');
    con.query("SELECT * from heights where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/patient/:id/weights', function (req, res) {
    console.log('GET weights');
    con.query("SELECT * from weights where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/patient/:id/appointment', function (req, res) {
    console.log('GET appointment');
    con.query("SELECT * from appointment where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/doctor', function (req, res) {
    console.log('GET doctor');
    con.query("SELECT * from doctor", function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/doctor/:id', function (req, res) {
    console.log('GET Doctor');
    con.query("SELECT * from doctor where doctorID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/patient/:id/recalls', function (req, res) {
    console.log('GET patient recalls');
    con.query("SELECT * from recall where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/vaccine', function (req, res) {
    console.log('GET vaccine');
    con.query("SELECT * from vaccine", function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/patient/:id/ordinance', function (req, res) {
    console.log('GET patient ordinance');
    con.query("SELECT * from ordinance where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});


app.get('/patient/:id/prescription', function (req, res) {
    console.log('GET patient prescription');
    con.query("SELECT * from prescription where patientID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/ordinance/:id/prescription', function (req, res) {
    console.log('GET ordinance prescription');
    con.query("SELECT * from prescription where ordinanceID = "+req.params.id, function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/medicament/', function (req, res) {
    console.log('GET medicament');
    con.query("SELECT * from medicament", function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

app.get('/connection/:forename/:name', function (req, res) {
    console.log('Connection try');
    con.query("SELECT patientID from patient where name = '"+req.params.name+"' and forename = '"+req.params.forename+"'", function (err, result, fields) {
      if (err) throw err;
      console.log(result);
      res.send(result);
    });
});

var server = app.listen(5000, function () {
    console.log('Server is running..');
});
