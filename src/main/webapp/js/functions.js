$(document).ready(function () {
    $('.collapsible').collapsible();
});

$(document).ready(function () {
    $('.sidenav').sidenav();
});

$(document).ready(function () {
    $('select').formSelect();
});



const mascaraMoeda = (event) => {
   const onlyDigits = event.target.value
      .split("")
      .filter(s => /\d/.test(s))
      .join("")
      .padStart(3, "0")

const digitsFloat = onlyDigits.slice(0, -2) + "." + onlyDigits.slice(-2)
    event.target.value = maskCurrency(digitsFloat)
}

const maskCurrency = (valor, locale = 'pt-BR', currency = 'BRL') => {
    return new Intl.NumberFormat(locale, {
        style: 'currency',
        currency
    }).format(valor)
}



