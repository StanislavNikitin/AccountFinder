<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Account Finder</title>
    <style>
        .header h1{
            color: deepskyblue;
            font-size: 25px;
            text-align: center;
            margin-top: 16px;
        }

        .header  hr {
            border: none;
            background-color: deepskyblue;
            color: deepskyblue; /* Цвет линии для IE6-7 */
            height: 1px;
        }
    </style>
    <!--link rel="stylesheet" type="text/css" href="main.css"-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>

<body>
<div class="header">
    <h1>Account Finder V_0.5</h1>
    <hr>
</div>

<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">Enter github.com account's info</h3>
    </div>
    <div class="panel-body">

        <form id ="toFIndForm" role="form" class="form-inline">
            <div class="form-group">
                <input name="login" type="text" class="form-control" placeholder="Username"
                       aria-describedby="basic-addon2">
                <input name="email" type="text" class="form-control" placeholder="E-mail"
                       aria-describedby="basic-addon2">
                <input id="submitButton" class="btn btn-default" value="Submit"><!-- type="submit"-->
            </div>
        </form>


    </div>
</div>
<div id="resultPanel" class="panel panel-info">
    <div class="panel-body">
    </div>
</div>

<!--script src="main.js"></script-->
<script type="application/javascript" >
    console.log("Page is loaded");
    var $resPanel = $('#resultPanel');

    $resPanel.hide();

    $('#submitButton').on('click', function () {
        var $inputs = $('#toFIndForm :input');
        var values = $inputs.serialize();
        values += "&action=find";
        console.log(values);

        $.ajax({
            url: "Finder",
            data: values,
            success: function(data, status){
                console.log(data);
                if(data && data.data.length > 0){
                    drawResults(data.data);
                    $resPanel.show();
                }
                else{
                    $resPanel.hide();
                }
            },
            dataType: "json"
        });

        function drawResults(data) {
            $panel = $("#resultPanel > div.panel-body");
            $panel.empty();
            console.log(data);
            data.forEach(function(item){
                $panel.append('<div class="account-info"><p>' +item.name+' '+item.link+ '</p></div>')
            })
        }

    })

</script>
</body>
</html>
