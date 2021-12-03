async function add(){
    console.log('sxd');
    console.log($('#idDoctor').val());
    fetch("/api/appointment/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
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