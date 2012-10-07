<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page TEST</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script type="text/javascript">
            
            $.fn.serializeObject = function()
            {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function() {
                    if (o[this.name] !== undefined) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                
                return o;
            };
            
            $(document).ready( function(){
                
                $('#addUserStory').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addUserStory').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/userstories/add",
                        type:"POST",
                        data: JSON.stringify($('#addUserStory').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function() {
                            $("#result").append("UserStory cr&eacute;&eacute;e avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
                
                
                $('#addUserStoryUpdate').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addUserStoryUpdate').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/userstories/",
                        type:"PUT",
                        data: JSON.stringify($('#addUserStoryUpdate').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data) {
                            $("#result").append("UserStory modifi&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                
                //load a member for EDIT (PUT)
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/userstories/1",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idUserstory").val(reponse.idUserstory);
                        //$("#idRole").val(reponse.idRole);
                        $("#title").val(reponse.title);
                        $("#importance").val(reponse.importance);
                        $("#estimation").val(reponse.estimation);
                        $("#demonstration").val(reponse.demonstration);
                        $("#note").val(reponse.note);
                        $("#category").val(reponse.category);
                    }
                );
                    
                /*
                $('#userstoryDelete').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#userstoryDelete').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/userstories/"+$('#userstoryDelete > #idUserstory').val(),
                        type:"DELETE",
                        success: function(data) {
                            $("#result").append("UserStory supprim&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a member for DELETE
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/userstories/1",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idUserstory").val(reponse.idUserstory);
                        $("#title").val(reponse.title);
                        $("#importance").val(reponse.importance);
                        $("#estimation").val(reponse.estimation);
                        $("#demonstration").val(reponse.demonstration);
                        $("#note").val(reponse.note);
                        $("#category").val(reponse.category);
                    }
                );*/
                    
                
            });
            
        </script>
    </head>
    <body>
        <h1>TEST UserStory !</h1>
        
        <div id="result"></div>
        <br/>TEST ajout : OK !<br/>
        <form id="addUserStory"  action="" method="post">
            <input type="text" name="title" placeholder="title" /><br />
            <input type="text" name="importance" placeholder="importance" /><br />
            <input type="text" name="estimation" placeholder="estimation" /><br />
            <input type="text" name="demonstration" placeholder="demonstration" /><br />
            <input type="text" name="note" placeholder="note" /><br />
            <input type="text" name="category" placeholder="category" /><br />
            <input type="submit" value="Ajouter"/>
        </form>
        
        <br/>TEST modification : OK !<br/>
        <form id="addUserStoryUpdate"  action="" method="post">
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="importance" name="importance" placeholder="importance" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <input type="text" id="demonstration" name="demonstration" placeholder="demonstration" /><br />
            <input type="text" id="note" name="note" placeholder="note" /><br />
            <input type="text" id="category" name="category" placeholder="category" /><br />
            <input type="hidden" id="idUserstory" name="idUserstory"/>
            <input type="submit" value="Modifier"/>
        </form>
        
        <br/>TEST suppression : OK !<br/>
        <!--<form id="userstoryDelete"  action="" method="post">
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="importance" name="importance" placeholder="importance" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <input type="text" id="demonstration" name="demonstration" placeholder="demonstration" /><br />
            <input type="text" id="note" name="note" placeholder="note" /><br />
            <input type="text" id="category" name="category" placeholder="category" /><br />
            <input type="hidden" id="idUserstory" name="idUserstory"/>
            <input type="submit" value="Supprimer"/>
        </form>        -->
        
    </body>
</html>
