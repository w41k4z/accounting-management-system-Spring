<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/assets/fonts/fontawesome-5/css/all.min.css">
    <link rel="stylesheet" href="/static/assets/css/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="/static/assets/css/style.css">

</head>

 <style>
    .logout {
        transition: all 0.5s ease;
        font-size: larger;
    }
    .logout:hover {
        cursor: pointer;
        color: red;
    }
 </style>

<body>
    <!-- Left Panel -->
    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">
            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/ela-admin/society/home-page/dashboard"><i class="fa fa-laptop me-5"></i>Dashboard </a></li>
                    <li class="menu-title">About</li>
                    <li><a href=""><i class="fa fa-info me-5"></i>Informations </a></li>
                    <li><a href=""><i class="fa fa-file-alt me-5"></i>Files </a></li>
                    <li class="menu-title">Accounting</li>
                    <li class="menu-item-has-children dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-stream"></i>Chart Of Account</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-th-large"></i><a href="/ela-admin/society/home-page/chart-of-account/general">General</a></li>
                            <li><i class="fa fa-th"></i><a href="/ela-admin/society/home-page/chart-of-account/third-party">Third Party</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-journal-whills"></i>Journal</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-quote-left"></i><a href="">Journal Code</a></li>
                            <li><i class="fa fa-clipboard-list"></i><a href="">Transaction Record</a></li>
                        </ul>
                    </li>
                    <li><a href=""><i class="fa fa-book-open me-5"></i>General Ledger </a></li>
                    <li><a href=""><i class="fa fa-balance-scale me-5"></i>Financial Statement </a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside>
    <!-- /#left-panel -->
    <!-- Right Panel -->
    <div id="right-panel" class="right-panel">
        <!-- Header-->
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header">
                    <a class="navbar-brand" href="./"><img src="/static/images/logo.png" alt="Logo"></a>
                    <a class="navbar-brand hidden" href="./"><img src="/static/images/logo2.png" alt="Logo"></a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <a href="/ela-admin/society/disconnect" class="logout dropdown float-right">
                        ${account.getSocietyAccounts()[0].getSociety().getName()} <i class="fa fa-power-off"></i>
                    </a>

                </div>
            </div>
        </header>
        <!-- /#header -->
        <!-- Content -->
        <div class="content">
            <!-- Animated -->
            <div class="animated fadeIn">
                <div class="row">
                    <div class="col-xl-8">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="box-title">Third Party </h4>
                            </div>
                            <div class="card-body">
                                <div class="table-stats order-table ov-h">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Type</th>
                                                <th>Name</th>
                                                <th>Entitled</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1.</td>
                                                <td> FO </td>
                                                <td> John </td>
                                                <td> FRNS: John </td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a class="btn btn-warning" href="">Modify</a>
                                                        <a class="btn btn-danger" href="">Delete</a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>2.</td>
                                                <td> CT </td>
                                                <td> David </td>
                                                <td> CLT: David </td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a class="btn btn-warning" href="">Modify</a>
                                                        <a class="btn btn-danger" href="">Delete</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div> <!-- /.table-stats -->
                            </div>
                        </div> <!-- /.card -->
                    </div>  <!-- /.col-lg-8 -->

                    <div class="col-xl-4">
                        <div class="row">
                            <div class="col-lg-6 col-xl-12">
                                <div class="card br-0">
                                    <div class="card-body">
                                        <h5 class="card-title">Import</h5>
                                        <hr>
                                        <form action="">
                                            <input class="form-control form-control-sm" id="formFileSm" type="file">
                                            <div class="mt-3 d-flex justify-content-end">
                                                <button type="submit" class="btn btn-primary">Import</button>
                                            </div>
                                        </form>
                                    </div>
                                </div><!-- /.card -->
                            </div>

                            <div class="col-lg-6 col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title m-0 text-dark">Add manually</h4>
                                        <hr>
                                    </div>
                                    <form class="row g-3 px-3">
                                        <div class="col-lg-12">
                                            <div class="form-floating">
                                                <select id="account" class="form-select" name="accountNumber">
                                                    <option value="">402000</option>
                                                    <option value="">41000</option>
                                                </select>
                                                <label for="account">Account related</label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="form-floating">
                                                <select id="type" class="form-select" name="type">
                                                    <option value="">FO</option>
                                                    <option value="">CT</option>
                                                </select>
                                                <label for="type">Type</label>
                                            </div>
                                        </div>
                                        <div class="col-auto col-lg-12">
                                            <input type="text" class="form-control" placeholder="Name" name="name">
                                        </div>
                                        <div class="col-auto col-lg-12">
                                            <input type="text" class="form-control" placeholder="Entitled" name="entitled">
                                        </div>
                                        <div class="col-auto ms-auto">
                                            <button type="submit" class="btn btn-primary mb-3">Confirm</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> <!-- /.col-md-4 -->
                </div>
                <!-- /.orders -->
            <!-- /#add-category -->
            </div>
            <!-- .animated -->
        </div>
        <!-- /.content -->
        <div class="clearfix"></div>
        <!-- Footer -->
        <footer class="site-footer">
            <div class="footer-inner bg-white">
                <div class="row">
                    <div class="col-sm-6">
                        Copyright &copy; 2023 Society fundation
                    </div>
                </div>
            </div>
        </footer>
        <!-- /.site-footer -->
    </div>
    <!-- /#right-panel -->

    <!-- Scripts -->
    <script src="/static/assets/js/lib/jquery/jquery-3.6.4.min.js"></script>
    <script src="/static/assets/css/lib/bootstrap/v4.1.3/js/bootstrap.min.js"></script>
    <script src="/static/assets/js/lib/jquery/jquery.matchHeight.js"></script>
    <script src="/static/assets/js/main.js"></script>
</body>
</html>
