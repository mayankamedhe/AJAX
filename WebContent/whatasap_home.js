/**
 * Sample javascript file. Read the contents and understand them, 
 * then modify this file for your use case.
 */

var myTable2;
$(document).ready(function() {
  ho();
});

function ho() {
  document.getElementById("content").innerHTML = "<table id=\"myyTable\" class=\"display\"> <thead>" + 
  "        <tr> <th>uid</th> <th>last_timestamp</th> <th>num_msgs</th> </tr>" + 
  "        </thead>" + 
  "    </table>";
  myTable2 = $("#myyTable").DataTable({
        columns: [{data:"uid"}, {data:"last_timestamp"}, {data:"num_msgs"}]
    });
    myTable2.ajax.url("AllConversations").load();
    $('#myyTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            myTable2.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        
        var uid1 = myTable2.row(this).data()["uid"];

        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {

            obj = JSON.parse(this.responseText).data; 
        
            var table="<table><tr><th>post_id</th><th>thread_id</th><th>uid</th><th>timestamp</th><th>text</th></tr>";
            for(x in obj){
              table = table+"<tr><td>" + obj[x].post_id +"</td><td>" + obj[x].thread_id + "</td>" + "<td>" + obj[x].uid + "</td><td>" +obj[x].timestamp + "</td><td>" +obj[x].text +"</td></tr>";
            }
            table = table+"</table>";
            table = table+ 
        "Message:<input type=\"text\" id=\"nmsg\" name=\"msg\"/>  \n" + 
        "<input type=\"hidden\" name=\"other_id\" value="+uid1+">"+
        "<button type=\"submit\" onclick=\"UpdMessage()\">send new message</button>  \n";
            document.getElementById("content").innerHTML = table;
           
          }
        };
        xhttp.open("GET", "ConversationDetail?other_id="+uid1, true);
        xhttp.send();
    } );
};
  
function Search_fun(){
   var uid1 = document.getElementById("sear").value;
    
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {

        obj = JSON.parse(this.responseText).data; 
    
        var table="<table><tr><th>post_id</th><th>thread_id</th><th>uid</th><th>timestamp</th><th>text</th></tr>";
        for(x in obj){
          table = table+"<tr><td>" + obj[x].post_id +"</td><td>" + obj[x].thread_id + "</td>" + "<td>" + obj[x].uid + "</td><td>" +obj[x].timestamp + "</td><td>" +obj[x].text +"</td></tr>";
        }
        table = table+"</table>";
        table = table+ 
    "Message:<input type=\"text\" id=\"nmsg\" name=\"msg\"/><br/><br/>  \n" + 
    "<input type=\"hidden\" name=\"other_id\" value="+uid1+">"+
    "<button type=\"submit\" onclick=\"UpdMessage()\">send new message</button>  \n";
        document.getElementById("content").innerHTML = table;
       
      }
    };
    xhttp.open("GET", "ConversationDetail?other_id="+uid1, true);
    xhttp.send();
};

function show(uidd){

    
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {

        obj = JSON.parse(this.responseText).data; 
    
        var table="<table><tr><th>post_id</th><th>thread_id</th><th>uid</th><th>timestamp</th><th>text</th></tr>";
        for(x in obj){
          table = table+"<tr><td>" + obj[x].post_id +"</td><td>" + obj[x].thread_id + "</td>" + "<td>" + obj[x].uid + "</td><td>" +obj[x].timestamp + "</td><td>" +obj[x].text +"</td></tr>";
        }
        table = table+"</table>";
      table = table+ 
    "Message:<input type=\"text\" id=\"nmsg\" name=\"msg\"/><br/><br/>  \n" + 
    "<input type=\"hidden\" name=\"other_id\" value="+uidd+">"+
    "<button type=\"submit\" onclick=\"UpdMessage()\">send new message</button>  \n";
        document.getElementById("content").innerHTML = table;
       
      }
    };
    xhttp.open("GET", "ConversationDetail?other_id="+uidd, true);
    xhttp.send();
};

function UpdMessage(){
   var uid = document.getElementById("other_id").value;
   var msg = document.getElementById("nmsg").value;
   console.log(uid);
   console.log(msg);

  if (msg == null || msg == "") {
      return;
  }
     xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {

        obj = JSON.parse(this.responseText);
        status = obj.status;
        
        if(status == false){
          alert("failure");
      ho();
    }else{
      alert("Success");
      show(uid);
        }
       }
     }
     xhttp.open("GET", "NewMessage?other_id="+uid1+"&msg="+msg, true);
     xhttp.send();
};

function loadTableAsync() {
    myTable.ajax.url("Home").load();
    document.getElementById("content").innerHTML = "";
}

//function Form_create(){
//  document.getElementById("form").innerHTML = "<form action=\"CreateConversation\" method=\"post\"> " + 
//  "             ID:<input id=\"autocomplete-5\" type=\"text\" name=\"other_id\"/><br/><br/> " + 
//  "             <button type=\"submit\" onclick=\"CreateConversation\">Submit</button> " + 
//  "             </form> ";
//  var xhttp;    
//
//  xhttp = new XMLHttpRequest();
//  xhttp.open("GET", "AutoCompleteUser?other_id="+str, true);
//  xhttp.send();
//  
//  parse_json();
//}


function Form_create() {
  document.getElementById("form").innerHTML = "<form action=\"CreateConversation\" method=\"post\"> " + 
  "             ID:<input id=\"autocomplete-5\" type=\"text\" name=\"other_id\"/><br/><br/> " + 
  "             <button type=\"submit\" onclick=\"CreateConversation\">Submit</button> " + 
  "             </form> ";
  console.log("Inside parse");
    var xhttp;    
    var other_id = document.getElementById("autocomplete-5").value ;
    console.log("Inside parse");
      
//    if (str_sent == "") {
//
//      document.getElementById("txtHint").innerHTML = "";
//
//      return;
//
//    }
    xhttp = new XMLHttpRequest();
    var output;
    xhttp.onreadystatechange = function() {
//      console.log("this_response==="+this.responseText);
      
      if (this.readyState == 4 && this.status == 200) {

        //document.getElementById("txtHint").innerHTML = this.responseText;
        var myObj = JSON.parse(this.responseText);
        console.log("s"+myObj);
        console.log("res=" + this.responseText);
        output = $.map(myObj.data, function(item)
        {
          return 
          {
            label:item.label;
            value:item.value;
          }
        });
        console.log("end"+output);
      }
      $(function(){
          $( "#autocomplete-5" ).autocomplete({ 
              source: output
           });});
      
    };

    xhttp.open("GET", "AutoCompleteUser?other_id="+other_id, true);

    xhttp.send();
    
    console.log("output=="+output);
//    console.log("other_id->"+other_id);
//    System.out.println("giving sutocomp-" + other_id);
    
  }

//$(function() {
//    
// });