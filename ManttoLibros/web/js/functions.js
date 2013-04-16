function disableTextFields(form, combo) {

    for (var i = 0; i < document.forms[form].elements.length; i++) {

        var currentType = document.forms[form].elements[i].type;
        //alert(currentType);
        if (currentType == 'text') {
            document.forms[form].elements[i].disabled = true;
        }
    }
}

function enableTextSearch(form, combo) {

    var whatIndex = document.forms[form].elements[combo].options.selectedIndex;
    var whatValue = document.forms[form].elements[combo].options[whatIndex].value;

    if (whatValue == '') {
        alert('Seleccione una opción de Busqueda');
    } else {
        disableTextFields(form, combo)
        document.forms[form].elements[whatValue].disabled = false;
    }
}


function validarFormularioCreate(form, camposOblig) {

    var campos = camposOblig.split(',');

    var res = true;

    for (var i = 0; i < campos.length; i++) {

        var value = document.forms[form].elements[campos[i]].value;

        if (value !== null && value !== '') {

            if (campos[i] == 'carnet') {

                if (value.length == 10) {
                    res = res && true;
                } else {
                    res = false;
                    alert('El carnet debe tener 10 caracteres de longitud');
                }
            } else {

                res = res && true;
            }
        } else {
            res = false;
            alert('El campo: ' + campos[i] + ' es requerido!!!');
        }


    }

    return res;
}
