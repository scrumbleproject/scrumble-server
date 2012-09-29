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
                            $("#result").append("Task cr&eacute;&eacute;e avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
                
                $('#addTaskUpdate').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addTaskUpdate').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/tasks/",
                        type:"PUT",
                        data: JSON.stringify($('#addTaskUpdate').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data) {
                            $("#result").append("Task modifi&eacute;e avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a task for EDIT (PUT)
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/tasks/1",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idTask").val(reponse.idTask);
                        //$("#idRole").val(reponse.idRole);
                        $("#title").val(reponse.title);
                        //$("#lastname").val(reponse.lastname);
                        $("#estimation").val(reponse.estimation);
                        $("#idUserStory").val(reponse.idUserStory);
                        //$("#password").val(reponse.password);
                        
                    }
                );
                    
                  
                $('#TaskDelete').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#TaskDelete').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/tasks/"+$('#TaskDelete > #idTask').val(),
                        type:"DELETE",
                        success: function(data) {
                            $("#result").append("Task supprim&eacute;e avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a task for DELETE
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/tasks/11",
                    function(reponse) {
                        // assigner les valeurs
                        $("#TaskDelete > #idTAsk").val(reponse.idTask);
                        //$("#idRole").val(reponse.idRole);
                        $("#TaskDelete > #title").val(reponse.title);
                        $("#TaskDelete > #estimation").val(reponse.estimation);
                        $("#TaskDelete > #idUserStory").val(reponse.idUserStory);
                        //$("#memberDelete > #email").val(reponse.email);
                        //$("#memberDelete > #password").val(reponse.password);
                        
                    }
                );
                    
                
            });
            
        </script>
        
    </head>
    <body>
        <h1>TEST Web Services !</h1>
        
        <div id="result"></div>
        <form id="addTask"  action="" method="post">
            <input type="text" name="title" placeholder="title" /><br />
            <input type="text" name="estimation" placeholder="estimation" /><br />
            <input type="text" name="idUserStory" placeholder="idUserStory" /><br />
            <!--<input type="text" name="email" placeholder="email" /><br />
            <input type="password" name="password" placeholder="password" /><br />-->
            <!--<input type="hidden" name="idMember" value=""/>
            <input type="hidden" name="idRole" value=""/>-->
            <input type="submit" value="ADD"/>
        </form>
        <form id="addTaskUpdate"  action="" method="post">
            <input type="text" id="idTask" name="title" placeholder="idTask" /><br />
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <input type="text" id="idUserStory" name="idUserStory" placeholder="idUserStory" /><br />
            <!--<input type="text" id="email" name="email" placeholder="email" /><br />
            <input type="password" id="password" name="password" placeholder="password" /><br />
            <input type="hidden" id="idTask" name="idTask"/>
            <!--<input type="hidden" id="idRole" name="idRole"/>-->
            <input type="submit" value="Modifier"/>
        </form>
        <form id="TaskDelete"  action="" method="post">
            <input type="text" id="idTask" name="title" placeholder="idTask" /><br />
            <input type="text" id="title" name="title" placeholder="title" /><br />
            <input type="text" id="estimation" name="estimation" placeholder="estimation" /><br />
            <!--<input type="text" id="login" name="login" placeholder="login" /><br />
            <input type="text" id="email" name="email" placeholder="email" /><br />
            <input type="password" id="password" name="password" placeholder="password" /><br />
            <input type="hidden" id="idTask" name="idTask"/>
            <!--<input type="hidden" id="idRole" name="idRole"/>-->
            <input type="submit" value="Delete"/>
        </form>
        
        
        
        
    </body>
</html>
