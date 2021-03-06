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
        #result_text {
            font-size: 12px;
        }
        #result_text.success_text {
            color: limegreen;
        }
        #result_text.fail_text {
            color: red;
        }
        .invisible {
            display: none;
        }
        div.account-info{
            margin-left: 10px;
            margin-top: 5px;
        }
        a.linkto{
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
        <p>
            <% Object isValid = request.getSession().getAttribute("valid_token");
                if (isValid != null && isValid.equals("true")){ %>
                You authorized in Facebook
            <% } else {%>
                Please, authorize in Facebook!
            <% } %>
        </p>
        <form id ="toFIndForm" role="form" class="form-inline">
            <div class="form-group">
                <input name="login" type="text" class="form-control" placeholder="Username" value="paulmillr"
                       aria-describedby="basic-addon2">
                <input name="email" type="text" class="form-control" placeholder="E-mail" value="paulmillr"
                       aria-describedby="basic-addon2">
                <input id="submitButton" class="btn btn-default" value="Send"><!-- type="submit"-->
                <div id="result-status">
                    <img id="process" src="img/progress.gif">
                    <p id="result_text"></p>
                </div>
            </div>
        </form>

    </div>
</div>
<script type="application/javascript">
    var link = document.createElement('a')
    var currUrl = document.URL.substring(0, document.URL.indexOf("index") + 5)
    var redirect_uri = "https://www.facebook.com/dialog/oauth?client_id=720825914723269&redirect_uri=" + currUrl + "&response_type=code"
    link.href = redirect_uri
    link.text = "AUTHORIZATION"
    document.body.appendChild(link)
    //<a href="https://www.facebook.com/dialog/oauth?client_id=720825914723269&redirect_uri="+document.URL+"&response_type=code"> ATHORIZATION</a>
</script>


<div id="resultPanel" class="panel panel-info">
    <div class="panel-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-5">
                    <img src="img/facebook.jpg" width="32" height="32"/>
                    <div id="facebookresults"></div>
                </div>
                <div class="col-sm-5">
                    <!--<img src="img/twitter.jpg" width="32" height="32"/> -->
                    <div id="twitterresults"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            /*document.getElementById('status').innerHTML = 'Please log ' +
                    'into this app.';*/
            console.log(' The person is logged into Facebook, but not your app  ', response);
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            /*document.getElementById('status').innerHTML = 'Please log ' +
                    'into Facebook.';*/
            console.log('The person is not logged into Facebook, so we\'re not sure if', response)
        }
        console.log('connect failed  ', response)
    }

    // This function is called when someone finishes with the Login
    // Button.  See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }

    window.fbAsyncInit = function() {
        FB.init({
            appId      : '720825914723269',
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.2' // use version 2.2
        });

        // Now that we've initialized the JavaScript SDK, we call
        // FB.getLoginStatus().  This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide.  They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        //    your app or not.
        //
        // These three cases are handled in the callback function.

        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });

    };

    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful.  See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function(response) {
            console.log('Successful login for: ' + response.name);
            //document.getElementById('status').innerHTML =*/
                    console.log('Thanks for logging in, ' + response.name + '!');
        });
    }
</script>
<!--script src="main.js"></script-->
<script type="application/javascript" >

    function getAbsolutePath() {
        var loc = window.location;
        var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
        return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
    }

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
        $('#result_text').hide()

        $.ajax({
            url: "index",
            data: values,
            success: function(data, status){

                $('#process').hide()
                $('#result_text').show()

                if(data && data.data && data.success == true){

                    data.data.facebook && data.data.facebook.length > 0 ? drawResults(data.data.facebook, $fb) : $fb.empty();
                    data.data.twitter && data.data.twitter.length > 0 ? drawResults(data.data.twitter, $tw) : $tw.empty();


                    $('#result_text').removeClass("fail_text").addClass("success_text")
                    $('#result_text').text("Found " + data.count + " matches")

                    $resPanel.show();
                }
                else if (data.success == false){

                    $('#result_text').removeClass("success_text").addClass("fail_text")
                    $('#result_text').text(data.error)

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
                container.append('<a class="linkto" target="_blank" href="' + item.link + '">'+item.name+'</a></div>')
                container.append('</div>')
            })
        }

    })

</script>
</body>
</html>
