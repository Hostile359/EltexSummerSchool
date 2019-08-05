<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script src="http://code.jquery.com/jquery-3.4.1.js"> </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <title>Title</title>
    </head>
    <script type="text/javascript">
            $(document).ready(function(){
                $.get("/Servlet-1/get_users",
                    function(data){
                    console.log(data);
                        var str = "";
                        for(var i = 0; i < data.length; i++) {
                            console.log(data[i]);
                            str += data[i].id + ")" + data[i].name + "<br>";
                        }
                        $(".users").html(str);
                    }
                );
            });
    </script>
    <body>
        <div class="users"></div>
    </body>
</html>