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
                
                $('#addMember').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addMember').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/members/add",
                        type:"POST",
                        data: JSON.stringify($('#addMember').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function() {
                            $("#result").append("Member cr&eacute;&eacute;e avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
                
                $('#addMemberUpdate').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#addMemberUpdate').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/members/",
                        type:"PUT",
                        data: JSON.stringify($('#addMemberUpdate').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function(data) {
                            $("#result").append("Member modifi&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a member for EDIT (PUT)
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/members/1",
                    function(reponse) {
                        // assigner les valeurs
                        $("#idMember").val(reponse.idMember);
                        //$("#idRole").val(reponse.idRole);
                        $("#firstname").val(reponse.firstname);
                        $("#lastname").val(reponse.lastname);
                        $("#login").val(reponse.login);
                        $("#email").val(reponse.email);
                        $("#password").val(reponse.password);
                        
                    }
                );
                    
                  
                $('#memberDelete').submit(function() {
                    
                    $("#result").html(JSON.stringify($('#memberDelete').serializeObject()));
                    
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/members/"+$('#memberDelete > #idMember').val(),
                        type:"DELETE",
                        success: function(data) {
                            $("#result").append("Member supprim&eacute; avec succ&egrave;s<br/>");
                        }
                    });
                    
                    return false;
                });
                
                //load a member for DELETE
                $.getJSON("http://localhost:8080/scrumble-server-web/scrumble/members/11",
                    function(reponse) {
                        // assigner les valeurs
                        $("#memberDelete > #idMember").val(reponse.idMember);
                        //$("#idRole").val(reponse.idRole);
                        $("#memberDelete > #firstname").val(reponse.firstname);
                        $("#memberDelete > #lastname").val(reponse.lastname);
                        $("#memberDelete > #login").val(reponse.login);
                        $("#memberDelete > #email").val(reponse.email);
                        $("#memberDelete > #password").val(reponse.password);
                        
                    }
                );
                    
                
            });
            
        </script>
        
    </head>
    <body>
        <h1>TEST Web Services !</h1>
        
        <div id="result"></div>
        <form id="addMember"  action="" method="post">
            <input type="text" name="firstname" placeholder="firstname" /><br />
            <input type="text" name="lastname" placeholder="lastname" /><br />
            <input type="text" name="login" placeholder="login" /><br />
            <input type="text" name="email" placeholder="email" /><br />
            <input type="password" name="password" placeholder="password" /><br />
            <!--<input type="hidden" name="idMember" value=""/>
            <input type="hidden" name="idRole" value=""/>-->
            <input type="submit" value="ADD"/>
        </form>
        <form id="addMemberUpdate"  action="" method="post">
            <input type="text" id="firstname" name="firstname" placeholder="firstname" /><br />
            <input type="text" id="lastname" name="lastname" placeholder="lastname" /><br />
            <input type="text" id="login" name="login" placeholder="login" /><br />
            <input type="text" id="email" name="email" placeholder="email" /><br />
            <input type="password" id="password" name="password" placeholder="password" /><br />
            <input type="hidden" id="idMember" name="idMember"/>
            <!--<input type="hidden" id="idRole" name="idRole"/>-->
            <input type="submit" value="Modifier"/>
        </form>
        <form id="memberDelete"  action="" method="post">
            <input type="text" id="firstname" name="firstname" placeholder="firstname" /><br />
            <input type="text" id="lastname" name="lastname" placeholder="lastname" /><br />
            <input type="text" id="login" name="login" placeholder="login" /><br />
            <input type="text" id="email" name="email" placeholder="email" /><br />
            <input type="password" id="password" name="password" placeholder="password" /><br />
            <input type="hidden" id="idMember" name="idMember"/>
            <!--<input type="hidden" id="idRole" name="idRole"/>-->
            <input type="submit" value="Delete"/>
        </form>
        
        
    </body>
</html>
