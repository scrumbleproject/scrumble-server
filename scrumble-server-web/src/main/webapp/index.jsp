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
                $('#addmember').submit(function() {
                    $.ajax({
                        url:"http://localhost:8080/scrumble-server-web/scrumble/members/add",
                        type:"POST",
                        data: JSON.stringify($('#addmember').serializeObject()),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function() {
                            $("#result").html("Member cr&eacute;&eacute;e avec succ&egrave;s");
                        }
                    });
                    
                    return false;
                });
            });
            
        </script>
        
    </head>
    <body>
        <h1>TEST Web Services !</h1>
        
        <div id="result"></div>
        <form id="addmember"  action="" method="post">
            <input type="text" name="firstname" placeholder="firstname" /><br />
            <input type="text" name="lastname" placeholder="lastname" /><br />
            <input type="text" name="login" placeholder="login" /><br />
            <input type="text" name="email" placeholder="email" /><br />
            <input type="password" name="password" placeholder="password" /><br />
            <!--<input type="hidden" name="idMember"/><br />
            <input type="hidden" name="idRole"/><br />-->
            <input type="submit" value="ADD"/>
        </form>
        
        
    </body>
</html>
