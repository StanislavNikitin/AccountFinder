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
        .result_text {
            font-size: 12px;
        }
        .result_text #success_text {
            color: limegreen;
        }
        .result_text #fail_text {
            color: red;
        }
        .invisible {
            display: none;
        }
        div .account-info{
            margin-left: 10px;
        }
        .account-info a{
            margin-left: 10px;
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
                <input id="submitButton" class="btn btn-default" value="Send"><!-- type="submit"-->
                <input value="EAACEdEose0cBAEM9UTwrmvl6ImV8bvDNh3VgvW8dW4bTYzWW3mep6nacKFSJv59tjENxoEbMxqgJ4dGS0pnjiCNRWQgODqeVlhxRuMpjFZAT66Pqh0fJQXup5mliPHrtM2uPU68K5UfzC8h87PneSmW8sfJdtk9REGSejGwZDZD" name="facebook_token" class="form-control" placeholder="Access token" aria-describedly="basic-addon2">
                <div id="result-status">
                    <img id="process" src="img/progress.gif">
                    <p class="result_text" id="success_text"></p>
                </div>
            </div>
        </form>

    </div>
</div>

<div id="resultPanel" class="panel panel-info">
    <div class="panel-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-5">
                    <img src="img/facebook.jpg" width="32" height="32"/>
                    <div id="facebookresults"></div>
                </div>
                <div class="col-sm-5">
                    <img src="img/twitter.jpg" width="32" height="32"/>
                    <div id="twitterresults"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--script src="main.js"></script-->
<script type="application/javascript" >
    console.log("Page is loaded");
    var $resPanel = $('#resultPanel');

    $resPanel.hide();
    $('#result-status').hide()

    $('#submitButton').on('click', function () {
        var $inputs = $('#toFIndForm :input');
        var values = $inputs.serialize();
        values += "&action=find";
        console.log(values);
        $fb = $("#facebookresults");
        $tw = $("#twitterresults");

        $('#result-status').show()
        $('#process').show()

        $.ajax({
            url: "",
            data: values,
            success: function(data, status){

                $('#process').hide()

                if(data && data.data && data.data.length > 0){

                    data.data.facebook && data.data.facebook.length > 0 ? drawResults(data.data.facebook, $fb) : $fb.empty();
                    data.data.twitter && data.data.twitter.length > 0 ? drawResults(data.data.twitter, $tw) : $tw.empty();

                    $('p.result-text').html("Success");

                    $resPanel.show();
                }
                else{
                    $('p.result-text').html("Fail")
                       // $(this).attr('id', 'fail_text')
                       // $(this).text("fail")

                    $resPanel.hide();
                }
            },
            dataType: "json"
        });

        function drawResults(data, container) {
            container.empty();

            data.forEach(function(item){
                container.append('<div class="account-info">')
                container.append('<img src="' + ((item.cover == "") ? 'img/unknow.gif': item.cover) + '" width=64 height=64>')
                container.append('<a target="_blank" href="' + item.link + '">'+item.name+'</a></div>')
                container.append('</div>')
            })
        }

    })

</script>
</body>
</html>
