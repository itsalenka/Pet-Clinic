var header = 'Bearer ' + localStorage.getItem("jwt");
var id;


async function Add(){
    fetch("api/pet/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        },
        body: JSON.stringify({
            idUser: id,
            name: $('#nameP').val(),
            type: $('#typeP option:selected').text(),
            breed: $('#breedP').val(),
            gender: $('#genderP option:selected').text(),
            bday: $('#bdayP').val().toString()
        })
    }).then(res => res.json()).then(res => {
        if(res.status == 400){
            $('#mesPet').text("Enter correct data");
        }else {
            if (res.error) {
                $('#mesPet').text(res.error);
            } else {
                $('#mesPet').text("Successful");
                $(':input','#form')
                    .not(':button, :submit, :reset, :hidden')
                    .val('')
                    .removeAttr('checked')
                    .removeAttr('selected');
               // getPets();
            }
        }
    });
}

async function getPets(){
    document.getElementById("listP").innerHTML = "";
    document.getElementById("historyPet").innerHTML = "";
    fetch("api/pet/user/" + id, {
        method: 'Get',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        let str = 'Not Found';
        if(!res.error) {
            str = '<table>' +
                '    <thead>' +
                '    <tr>' +
                '        <th>Name</th>' +
                '        <th>Type</th>' +
                '        <th>Breed</th>' +
                '        <th>Gender</th>' +
                '        <th>Bday</th>' +
                '        <th>History</th>' +
                '        <th>Delete</th>' +
                '    </tr>' +
                '    </thead>' +
                '    <tbody>';
            res.forEach(obj => {
                str += '<tr>' +
                    '<td>' + obj.name + '</td>' +
                    '<td>' + obj.type + '</td>' +
                    '<td>' + obj.breed + '</td>' +
                    '<td>' + obj.gender + '</td>' +
                    '<td>' + obj.bday + '</td>' +
                    '<td><button onclick="historyPet(' + obj.id + ')">Open</button> </td>' +
                    '<td><button onclick="deletePet(' + obj.id + ')">X</button> </td>' +
                    '</tr>';
            });
            str += '</tbody></table>';
        }
        document.getElementById("listP").innerHTML = str;
    });
}

async function deletePet(id){
    console.log(id);
    fetch("/api/pet/" + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        getPets();
    });
}

async function historyPet(id){
    document.getElementById("historyPet").innerHTML = "";
    fetch("api/pet/history/" + id, {
        method: 'Get',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header
        }
    }).then(res => res.json()).then(res => {
        let str = 'Not Found';
        if(!res.error) {
            str = '<table>' +
                '    <thead>' +
                '    <tr>' +
                '        <th>Дата</th>' +
                '        <th>Жалобы</th>' +
                '        <th>Диагноз</th>' +
                '    </tr>' +
                '    </thead>' +
                '    <tbody>';
            res.forEach(obj => {
                str += '<tr>' +
                    '<td>' + obj.date + '</td>' +
                    '<td>' + obj.complaints + '</td>' +
                    '<td>' + obj.diagnosis + '</td>' +
                    '</tr>';
            });
            str += '</tbody></table>';
        }
        document.getElementById("historyPet").innerHTML = str;
    });
}



