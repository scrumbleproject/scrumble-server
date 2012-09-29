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
                
                $('#addProject').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addProject').serializeObject()));
                    
                    $.ajax({
                        url:"http://192.168.0.13:8080/scrumble-server-web/scrumble/projects/add",
                        type:"POST",
                        data: JSON.stringify($('#addProject').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function() {
                            $("#result").append("Project cr&eacute;&eacute; avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
                
                /*
                $('#addProjectUpdate').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addProjectUpdate').serializeObject()));
                    
                    $.ajax({
                        url:"http://192.168.0.13:8080/scrumble-server-web/scrumble/projects/",
                        type:"PUT",
                        data: JSON.stringify($('#addProjectUpdate').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data) {
                            $("#result").append("Project modifi&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                
                //load a member for EDIT (PUT)
                $.getJSON("http://192.168.0.13:8080/scrumble-server-web/scrumble/projects/2",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idProject").val(reponse.idProject);
                        $("#title").val(reponse.title);
                        $("#description").val(reponse.description);
                    }
                );*/
                    
                
                $('#projectDelete').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#projectDelete').serializeObject()));
                    
                    $.ajax({
                        url:"http://192.168.0.13:8080/scrumble-server-web/scrumble/projects/"+$('#projectDelete > #idProject').val(),
                        type:"DELETE",
                        success: function(data) {
                            $("#result").append("Project supprim&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a member for DELETE
                $.getJSON("http://192.168.0.13:8080/scrumble-server-web/scrumble/projects/2",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idProject").val(reponse.idProject);
                        $("#title").val(reponse.title);
                        $("#description").val(reponse.description);
                    }
                );
                    
                
            });
            
        </script>
    </head>
    <body>
        <h1>TEST Project !</h1>
        
        <div id="result"></div>
        <br/>TEST ajout :<br/>
        <form id="addProject"  action="" method="post">
            <input type="text" name="title" placeholder="title" /><br />
            <input type="text" name="description" placeholder="description" /><br />
            <input type="submit" value="Ajouter"/>
        </form>
        
        <br/>TEST modification :<br/>
        <form id="addProjectUpdate"  action="" method="post">
            <!--<input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="description" name="description" placeholder="description" /><br />
            <input type="hidden" id="idProject" name="idProject"/>-->
            <input type="submit" value="Modifier"/>
        </form>
        
        <br/>TEST suppression : <br/>
        <form id="projectDelete"  action="" method="post">
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="description" name="description" placeholder="description" /><br />
            <input type="hidden" id="idProject" name="idProject"/>
            <input type="submit" value="Supprimer"/>
        </form>
        
    </body>
</html>
