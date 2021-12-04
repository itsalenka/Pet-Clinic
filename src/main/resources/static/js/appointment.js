var header = 'Bearer ' + localStorage.getItem("jwt")

function errorPage(mes){return `<div><h3>${mes}</h3><h4><a href="/login">Pls, sing in</a></h4></div>`;
}

async function add(){
    fetch("/api/appointment/doctor/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        },
        body: JSON.stringify({
            weight: $('#weight').val().toString(),
            temp: $('#temp').val().toString(),
            history: $('#history').val(),
            anamnesis: $('#anamnesis').val(),
            complaints: $('#complaints').val(),
            condition: $('#condition').val(),
            diagnostics: $('#diagnostics').val(),
            diagnosis: $('#diagnosis').val(),
            purpose: $('#purpose').val(),
            idDoctor: $('#idDoctor').val(),
            idPet: $('#idPet').val(),
        })
    }).then(res => res.json()).then(res => {
        if(res.status == 401)
            $('#div').html(errorPage("You are not authorized"));
        else {
            if (res.status == 403)
                $('#div').html(errorPage("Not enough access rights"));
            else {
                if(res.error){
                    $('#error').text(res.error);
                }else {
                     document.location.href = "/doctor";
                }
            }
        }
    });
}

async function load() {
    fetch("/api/appointment/doctor/check", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        if (res.status == 401)
            $('#div').html(errorPage("You are not authorized"));
        else {
            if (res.status == 403)
                $('#div').html(errorPage("Not enough access rights"));
        }
    });
}

async function loadInfo(){
    fetch("/api/appointment/get/" + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        if(res.status == 401)
            $('#div').html(errorPage("You are not authorized"));
        else {
                $('#username').text(res.username);
                $('#doctorname').text(res.doctorname);
                $('#bday').val(res.bday);
                $('#gender').val(res.gender);
        }
    });
}

async function checkGet(){
    fetch("/api/appointment/get/check", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        console.log(res.status);
        if (res.status == 401)
            $('#div').html(errorPage("You are not authorized"));
    });
}