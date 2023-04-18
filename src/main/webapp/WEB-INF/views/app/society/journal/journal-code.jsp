<%@ page import="com.alain.accounting_management_system.model.JournalCode" %>
<%
    JournalCode[] journalCodes = (JournalCode[]) request.getAttribute("journalCodes");
%>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Journal code</title>
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
                    <li><a href="/ela-admin/society/home-page/dashboard"><i class="menu-icon fa fa-laptop"></i>Dashboard </a></li>
                    <li class="menu-title">About</li>
                    <li><a href="/ela-admin/society/home-page/information"><i class="menu-icon fa fa-info"></i>Informations </a></li>
                    <li><a href=""><i class="menu-icon fa fa-file-alt"></i>Files </a></li>
                    <li class="menu-title">Accounting</li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-stream"></i>Chart Of Account</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-th-large"></i><a href="/ela-admin/society/home-page/chart-of-account/general">General</a></li>
                            <li><i class="menu-icon fa fa-th"></i><a href="/ela-admin/society/home-page/chart-of-account/third-party">Third Party</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-journal-whills"></i>Journal</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-quote-left"></i><a href="">Journal Code</a></li>
                            <li><i class="menu-icon fa fa-clipboard-list"></i><a href="/ela-admin/society/home-page/journal/transaction-record">Transaction Record</a></li>
                        </ul>
                    </li>
                    <li><a href="/ela-admin/society/home-page/general-ledger"><i class="menu-icon fa fa-book-open"></i>General Ledger </a></li>
                    <li><a href=""><i class="menu-icon fa fa-balance-scale"></i>Financial Statement </a></li>
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
                <div>
                    <div class="card">
                        <div class="card-body d-flex justify-content-between">
                            <h4 class="box-title">Journal code </h4>
                            <div>
                                <button class="btn btn-success" data-toggle="modal" data-target="#insertModal"><i class="fa fa-plus"></i></button>

                                <div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="mediumModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header d-flex justify-content-between">
                                                <h4 class="modal-title" id="mediumModalLabel">Choose your insert method</h4>
                                                <button class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="model-body row justify-content-around mt-3 p-3">
                                                <div class="col-md-5">
                                                    <div class="card br-0">
                                                        <div class="card-body">
                                                            <h5 class="card-title">Import</h5>
                                                            <hr>
                                                            <p>Supported file : csv, xls, xlsx</p>
                                                            <form action="" method="POST" enctype="multipart/form-data">
                                                                <input class="form-control form-control-sm" id="formFileSm" type="file" name="file">
                                                                <div class="mt-3 d-flex justify-content-end">
                                                                    <button type="submit" class="btn btn-primary">Import</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div><!-- /.card -->
                                                </div>

                                                <p class="col-md-2 text-center"> <strong>OR</strong> </p>

                                                <div class="col-md-5">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h4 class="card-title m-0 text-dark">Add manually</h4>
                                                            <hr>
                                                        </div>
                                                        <form action="/ela-admin/society/journal/create-journal-code" class="row g-3 px-3" method="POST">
                                                            <div class="col-auto col-lg-12">
                                                                <input type="text" class="form-control" placeholder="Code" name="code">
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
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <table id="bootstrap-data-table" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Code</th>
                                        <th>Entitled</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int i = 0; i < journalCodes.length; i++) { %>
                                    <tr>
                                        <td><%= (i+1) %>.</td>
                                        <td> <%= journalCodes[i].getCode() %> </td>
                                        <td><%= journalCodes[i].getEntitled() %></td>
                                        <td class="d-flex justify-content-center">
                                            <div class="btn-group">

                                                <button class="btn btn-warning" data-toggle="modal" data-target="#edit<%= journalCodes[i].getCode() + i %>"><i class="fa fa-edit"></i></button>
                                                <%-- Edit Modal --%>
                                                <div class="modal fade" id="edit<%= journalCodes[i].getCode() + i %>" tabindex="-1" role="dialog" aria-labelledby="mediumModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header d-flex justify-content-between">
                                                                <h5 class="modal-title" id="mediumModalLabel">Update journal code</h5>
                                                                <button class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <form action="/ela-admin/society/journal/update-journal-code" class="modal-body row g-3 px-3" method="POST">
                                                                <input type="hidden" name="journalCodeID" value="<%= journalCodes[i].getJournalCodeID() %>" />
                                                                <div class="col-auto col-lg-12">
                                                                    <input type="text" class="form-control" placeholder="Code" name="code" value="<%= journalCodes[i].getCode() %>">
                                                                </div>
                                                                <div class="col-auto col-lg-12">
                                                                    <input type="text" class="form-control" placeholder="Entitled" name="entitled" value="<%= journalCodes[i].getEntitled() %>">
                                                                </div>
                                                                <div class="col-auto ms-auto">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                    <button type="submit" class="btn btn-warning">Update</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%-- /.Edit Modal --%>

                                                <button class="btn btn-danger" data-toggle="modal" data-target="#suppress<%= journalCodes[i].getCode() + i %>"><i class="fa fa-times"></i></button>
                                                <%-- Suppress Modal --%>
                                                <div class="modal fade" id="suppress<%= journalCodes[i].getCode() + i %>" tabindex="-1" role="dialog" aria-labelledby="mediumModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
                                                        <form action="/ela-admin/society/journal/delete-journal-code" class="modal-content" method="POST">
                                                            <input type="hidden" name="journalCodeID" value="<%= journalCodes[i].getJournalCodeID() %>" />
                                                            <div class="modal-header">
                                                                <h3 class="modal-title text-danger text-center" id="mediumModalLabel">Are you sure to delete this ?</h3>
                                                            </div>
                                                            <div class="modal-footer d-flex justify-content-center">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                <button type="submit" class="btn btn-danger">Confirm</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <%-- /.Suppress Modal --%>
                                            </div>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div> <!-- /.card -->
                </div>
                <!-- /.div -->
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

    <%-- Data Table --%>
    <script src="/static/assets/js/lib/data-table/datatables.min.js"></script>
    <script src="/static/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
    <script src="/static/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
    <script src="/static/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
    <script src="/static/assets/js/lib/data-table/jszip.min.js"></script>
    <script src="/static/assets/js/lib/data-table/vfs_fonts.js"></script>
    <script src="/static/assets/js/lib/data-table/buttons.html5.min.js"></script>
    <script src="/static/assets/js/lib/data-table/buttons.print.min.js"></script>
    <script src="/static/assets/js/lib/data-table/buttons.colVis.min.js"></script>
    <script src="/static/assets/js/init/datatables-init.js"></script>
    
</body>
</html>
