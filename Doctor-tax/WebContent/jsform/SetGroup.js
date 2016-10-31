$( function() {
    $( "#dialogDelete" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickDelete());
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnDelete" ).on( "click", function() {
      $( "#dialogDelete" ).dialog( "open" );
    });
  } );

$( function() {
    $( "#dialogReset" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickReset());
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnReset" ).on( "click", function() {
      $( "#dialogReset" ).dialog( "open" );
    });
  } );

$( function() {
    $( "#dialogSave" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickSave());
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnSave" ).on( "click", function() {
      $( "#dialogSave" ).dialog( "open" );
    });
  } );//modal

$(document).ready(function(){
	genTable();
});

function genTable(){
	$.ajax({
		type: 'GET',
		url: './SetGroupSrvl',
		data: 
		 {"method":"genTable"} ,
		success: function(data){
			$('#inputId').val(data.max);
			$('#tb').append(data.html);
		}
	})
}

function clickRow(id){
	alert(id);
}