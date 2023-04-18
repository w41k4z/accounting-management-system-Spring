<%@ page import="com.alain.accounting_management_system.model.Journal" %>
<%@ page import="com.alain.accounting_management_system.utils.MoneyFormatter" %>
<%
    Journal[] journals = (Journal[]) request.getAttribute("journals");
%>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Transaction record</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/assets/fonts/fontawesome-5/css/all.min.css">
    <link rel="stylesheet" href="/static/assets/css/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="/static/assets/css/style.css">
    <link rel="stylesheet" href="/static/assets/css/lib/datatable/dataTables.bootstrap.min.css">

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
                        <a href="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-stream"></i>Chart Of Account</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-th-large"></i><a href="/ela-admin/society/home-page/chart-of-account/general">General</a></li>
                            <li><i class="fa fa-th"></i><a href="/ela-admin/society/home-page/chart-of-account/third-party">Third Party</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-journal-whills"></i>Journal</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-quote-left"></i><a href="/ela-admin/society/home-page/journal/journal-code">Journal Code</a></li>
                            <li><i class="menu-icon fa fa-clipboard-list"></i><a href="">Transaction Record</a></li>
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
                <div class="card">
                    <div class="card-header">
                        <form action="/ela-admin/society/home-page/journal/transaction-record" class="d-flex justify-content-center">
                            <input type="date" name="date" /><button>Search</button>
                        </form>
                        <div class="d-flex justify-content-end">
                            <button class="btn btn-success" data-toggle="modal" data-target="#insertModal"><i class="fa fa-plus"></i></button>
                            <div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="mediumModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-md" role="document">
                                    <div class="modal-content">
                                        <div class="model-body row justify-content-center">
                                            <div class="br-0">
                                                <div class="card-body">
                                                    <h5 class="card-title">Import</h5>
                                                    <hr>
                                                    <p>Supported file : csv, xls, xlsx</p>
                                                    <form action="/ela-admin/society/journal/import-journal" method="POST" enctype="multipart/form-data">
                                                        <input class="form-control form-control-sm" id="formFileSm" type="file" name="file">
                                                        <div class="mt-2 d-flex justify-content-end">
                                                            <button type="submit" class="btn btn-primary">Import</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div><!-- /.card -->
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
                                    <th>Date</th>
                                    <th>Piece Num</th>
                                    <th>Part Ref</th>
                                    <th>Account</th>
                                    <th>Third Party</th>
                                    <th>Debit</th>
                                    <th>Credit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(int i = 0; i < journals.length; i++) { %>
                                <tr>
                                    <th><%= (i+1) %>.</th>
                                    <td><%= journals[i].getJournalDate() %></td>
                                    <td><%= journals[i].getPieceNumber() %></td>
                                    <td><%= journals[i].getPartReference() %></td>
                                    <td><%= journals[i].getGeneralAccount() %></td>
                                    <td><%= journals[i].getThirdPartyAccount() %></td>
                                    <td class="text-end"><%= MoneyFormatter.display(journals[i].getDebit()) %></td>
                                    <td class="text-end"><%= MoneyFormatter.display(journals[i].getCredit()) %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
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
