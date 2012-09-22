<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page TEST</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
   
        
        <script type="text/javascript">
            
            $(document).ready( function(){
                $('#addmember').submit(function() {
                    return JSON.stringify($('#addmember').serializeObject());
                });
            });
            
        </script>
        
    </head>
    <body>
        <h1>TEST Web Services !</h1>
        
        
        <form id="addmember"  action="http://localhost:8080/scrumble-server-web/scrumble/members/add" method="post">
            <input type="text" name="firstname" placeholder="firstname" /><br />
            <input type="text" name="lastname" placeholder="lastname" /><br />
            <input type="text" name="login" placeholder="login" /><br />
            <input type="text" name="email" placeholder="email" /><br />
            <input type="submit" value="ADD"/>
        </form>
        
        
    </body>
</html>
