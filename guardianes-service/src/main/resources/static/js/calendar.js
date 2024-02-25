// Obtener la fecha actual
var currentDate = new Date();

// Obtener el primer día del próximo mes
var nextMonthFirstDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);

// Obtener el último día del próximo mes
var nextMonthLastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 2, 0);

var nextMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);

var weekend_strtday = 0;
var weekend_endday =  6;

// Inicialización de componentes
$('#datepicker').datepicker({
    format: 'dd/mm/yy',
    language: 'es',
    maxViewMode: 0, // Solo mostrar los días del mes
    autoclose: true, // Cerrar cuando se selecciona una fecha
    multidate: true,
    weekStart: 1, // Lunes
    beforeShowDay: function(date){
        var day = date.getDay();
        // Deshabilitar los fines de semana y preseleccionarlos visualmente
        if (day === 0 || day === 6) {
            return {
                enabled: false,
                classes: 'weekend'
            };
        } else {
            return {
                enabled: true
            };
        }
    },
    defaultViewDate: { year: nextMonth.getFullYear(), month: nextMonth.getMonth(), day: 1 }
}).datepicker('setStartDate', nextMonthFirstDay).datepicker('setEndDate', nextMonthLastDay); // Establecer fechas de inicio y fin


$('#dateForm').submit(function(event) {
    var selectedDates = $('#datepicker').datepicker('getDates');
    console.log(selectedDates); // Imprime las fechas seleccionadas

    $.ajax({
        url: '/guardianes/calendars',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ fechas: selectedDates }),
        success: function(response) {
            console.log('Fechas enviadas al servidor con éxito', response);
        },
        error: function(error) {
            console.error('Error al enviar las fechas al servidor:', error);
        }
    });

    event.preventDefault(); // Evitar que se envíe el formulario de forma convencional
});
