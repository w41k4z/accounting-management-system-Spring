<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Log In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="/static/assets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/assets/fonts/fontawesome-5/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
    <link rel="stylesheet" href="/static/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="/static/assets/css/lib/parsley.css">
    <link rel="stylesheet" href="/static/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>
<body class="bg-dark">

    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="/app" id="logo">
                        <img class="align-content" src="/static/images/logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <form action="/app/account/authenticate" method="POST" id="form">
                        <div class="form-group">
                            <label>Email address</label>
                            <input type="email" class="form-control" placeholder="Email" name="email" data-parsley-trigger="focusout" required="" data-parsley-checkemail data-parsley-checkemail-message="Email address already in use">
                        </div>
                        <div class="form-group mt-3">
                            <label>Password</label>
                            <input type="password" class="form-control" placeholder="Password" data-parsley-minlength="8" data-parsley-maxlength="16" data-parsley-trigger="keyup" name="password" required="">
                        </div>
                        <button type="submit" class="btn btn-success btn-flat mt-3">Sign in</button>
                        <div class="register-link mt-3 text-center">
                            <p>Don't have account ? <a href="/app/account/register"> Sign Up Here</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="/static/assets/js/lib/parsley/parsley.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#form').parsley();
        });
    </script>

</body>
</html>
