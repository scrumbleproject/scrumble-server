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
                
                $('#addTask').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addTask').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/tasks/add",
                        type:"POST",
                        data: JSON.stringify($('#addTask').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function() {
                            $("#result").append("Task cr&eacute;&eacute; avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
                
                
                /*$('#addTaskUpdate').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addTaskUpdate').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/tasks/",
                        type:"PUT",
                        data: JSON.stringify($('#addTaskUpdate').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data) {
                            $("#result").append("Task modifi&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                
                //load a member for EDIT (PUT)
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/tasks/15",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idTask").val(reponse.idTask);
                        $("#title").val(reponse.title);
                        $("#estimation").val(reponse.estimation);
                    }
                );*/
                    
                
                $('#taskDelete').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#taskDelete').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/tasks/"+$('#taskDelete > #idTask').val(),
                        type:"DELETE",
                        success: function(data) {
                            $("#result").append("Task supprim&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a member for DELETE
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/tasks/15",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idTask").val(reponse.idTask);
                        $("#title").val(reponse.title);
                        $("#estimation").val(reponse.estimation);
                    }
                );
                    
                
            });
            
        </script>
    </head>
    <body>
        <h1>TEST Task !</h1>
        
        <div id="result"></div>
        <br/>TEST ajout :<br/>
        <form id="addTask"  action="" method="post">
            <input type="text" name="title" placeholder="title" /><br />
            <input type="text" name="estimation" placeholder="estimation" /><br />
            <input type="submit" value="Ajouter"/>
        </form>
        
        <br/>TEST modification :<br/>
        <form id="addTaskUpdate"  action="" method="post">
            
            <!--<input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <input type="hidden" id="idTask" name="idTask"/>-->
            <input type="submit" value="Modifier"/>
        </form>
        
        <br/>TEST suppression : <br/>
        <form id="taskDelete"  action="" method="post">
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <input type="hidden" id="idTask" name="idTask"/>
            <input type="submit" value="Supprimer"/>
        </form>
        
    </body>
</html>
