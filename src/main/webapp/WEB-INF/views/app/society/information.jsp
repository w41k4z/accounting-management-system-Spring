<%@ page import="com.alain.accounting_management_system.model.Journal" %>
<%
    Journal[] general_ledger = (Journal[]) request.getAttribute("general_ledger");
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
                    <li class="active"><a href=""><i class="menu-icon fa fa-info"></i>Informations </a></li>
                    <li><a href=""><i class="menu-icon fa fa-file-alt"></i>Files </a></li>
                    <li class="menu-title">Accounting</li>
                    <li class="menu-item-has-children dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-stream"></i>Chart Of Account</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-th-large"></i><a href="/ela-admin/society/home-page/chart-of-account/general">General</a></li>
                            <li><i class="fa fa-th"></i><a href="/ela-admin/society/home-page/chart-of-account/third-party">Third Party</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-journal-whills"></i>Journal</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-quote-left"></i><a href="/ela-admin/society/home-page/journal/journal-code">Journal Code</a></li>
                            <li><i class="menu-icon fa fa-clipboard-list"></i><a href="">Transaction Record</a></li>
                        </ul>
                    </li>
                    <li><a href=""><i class="menu-icon fa fa-book-open"></i>General Ledger </a></li>
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
                    <div class="card-body">
                        <div class="py-5 text-center">
                            <h2>${account.getSocietyAccounts()[0].getSociety().getName()}</h2>
                        </div>

                        <div class="row g-5">
                            <div class="col-md-5 col-lg-4 order-md-last">
                                <h4 class="d-flex justify-content-between align-items-center mb-3">
                                    <span class="text-primary">Your cart</span>
                                    <span class="badge bg-primary rounded-pill">3</span>
                                </h4>
                                <ul class="list-group mb-3">
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">Product name</h6>
                                            <small class="text-muted">Brief description</small>
                                        </div>
                                        <span class="text-muted">$12</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">Second product</h6>
                                            <small class="text-muted">Brief description</small>
                                        </div>
                                        <span class="text-muted">$8</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">Third item</h6>
                                            <small class="text-muted">Brief description</small>
                                        </div>
                                        <span class="text-muted">$5</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                        <div class="text-success">
                                            <h6 class="my-0">Promo code</h6>
                                            <small>EXAMPLECODE</small>
                                        </div>
                                        <span class="text-success">−$5</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between">
                                        <span>Total (USD)</span>
                                        <strong>$20</strong>
                                    </li>
                                </ul>

                                <form class="card p-2">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Promo code">
                                        <button type="submit" class="btn btn-secondary">Redeem</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-7 col-lg-8">
                                <form class="needs-validation" novalidate>
                                    <div class="row g-3">
                                        <div class="col-sm-6">
                                            <label for="firstName" class="form-label">First name</label>
                                            <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                                            <div class="invalid-feedback">
                                                Valid first name is required.
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <label for="lastName" class="form-label">Last name</label>
                                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                                            <div class="invalid-feedback">
                                                Valid last name is required.
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="username" class="form-label">Username</label>
                                            <div class="input-group has-validation">
                                                <span class="input-group-text">@</span>
                                                <input type="text" class="form-control" id="username" placeholder="Username" required>
                                                <div class="invalid-feedback">
                                                    Your username is required.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="email" class="form-label">Email <span class="text-muted">(Optional)</span></label>
                                            <input type="email" class="form-control" id="email" placeholder="you@example.com">
                                            <div class="invalid-feedback">
                                                Please enter a valid email address for shipping updates.
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="address" class="form-label">Address</label>
                                            <input type="text" class="form-control" id="address" placeholder="1234 Main St" required>
                                            <div class="invalid-feedback">
                                                Please enter your shipping address.
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="address2" class="form-label">Address 2 <span class="text-muted">(Optional)</span></label>
                                            <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
                                        </div>

                                        <div class="col-md-5">
                                            <label for="country" class="form-label">Country</label>
                                            <select class="form-select" id="country" required>
                                                <option value="">Choose...</option>
                                                <option>United States</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please select a valid country.
                                            </div>
                                        </div>

                                        <div class="col-md-4">
                                            <label for="state" class="form-label">State</label>
                                            <select class="form-select" id="state" required>
                                                <option value="">Choose...</option>
                                                <option>California</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please provide a valid state.
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="zip" class="form-label">Zip</label>
                                            <input type="text" class="form-control" id="zip" placeholder="" required>
                                            <div class="invalid-feedback">
                                                Zip code required.
                                            </div>
                                        </div>
                                    </div>

                                    <hr class="my-4">

                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="same-address">
                                        <label class="form-check-label" for="same-address">Shipping address is the same as my billing address</label>
                                    </div>

                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="save-info">
                                        <label class="form-check-label" for="save-info">Save this information for next time</label>
                                    </div>

                                    <hr class="my-4">

                                    <h4 class="mb-3">Payment</h4>

                                    <div class="my-3">
                                        <div class="form-check">
                                            <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
                                            <label class="form-check-label" for="credit">Credit card</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required>
                                            <label class="form-check-label" for="debit">Debit card</label>
                                        </div>
                                        <div class="form-check">
                                            <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required>
                                            <label class="form-check-label" for="paypal">PayPal</label>
                                        </div>
                                    </div>

                                    <div class="row gy-3">
                                        <div class="col-md-6">
                                            <label for="cc-name" class="form-label">Name on card</label>
                                            <input type="text" class="form-control" id="cc-name" placeholder="" required>
                                            <small class="text-muted">Full name as displayed on card</small>
                                            <div class="invalid-feedback">
                                                Name on card is required
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="cc-number" class="form-label">Credit card number</label>
                                            <input type="text" class="form-control" id="cc-number" placeholder="" required>
                                            <div class="invalid-feedback">
                                                Credit card number is required
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="cc-expiration" class="form-label">Expiration</label>
                                            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                                            <div class="invalid-feedback">
                                                Expiration date required
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="cc-cvv" class="form-label">CVV</label>
                                            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                                            <div class="invalid-feedback">
                                                Security code required
                                            </div>
                                        </div>
                                    </div>

                                    <hr class="my-4">

                                    <button class="w-100 btn btn-primary btn-lg" type="submit">Continue to checkout</button>
                                </form>
                            </div>
                        </div>
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
