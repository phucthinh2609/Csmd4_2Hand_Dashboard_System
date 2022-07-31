<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Stock Details</title>
    <!-- Meta -->
    <%@include file="/cp/head/meta.html"%>

    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.html"%>

    <!-- select2 css -->
    <%@include file="/cp/head/select2-css.html"%>

    <!-- bootstrap-datepicker css -->
    <%@include file="/cp/head/bootstrap-datepicker-css.html"%>

    <!-- DataTables -->
    <%@include file="/cp/head/data-tables.html"%>

    <!-- Responsive datatable examples -->
    <%@include file="/cp/head/responsive-datatable-examples.html"%>

    <!-- Bootstrap Css --> <!-- Icons Css --> <!-- App Css-->
    <%@include file="/cp/head/main.html"%>

</head>

<body data-sidebar="dark">

    <!-- Begin page -->
    <div id="layout-wrapper">

        <%@include file="/cp/layout/page-topbar.jsp"%>
        <div class="vertical-menu">

            <div data-simplebar="" class="h-100">

                <!--- Sidemenu -->
                <%@include file="/cp/layout/sidebar-menu.jsp"%>
                <!-- Sidebar -->
            </div>
        </div>
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="main-content">

            <div class="page-content">
                <div class="container-fluid">

                    <!-- start page title -->
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box d-flex align-items-center justify-content-between">
                                <h4 class="mb-0 font-size-18">Stock Details</h4>

                                <div class="page-title-right">
                                    <ol class="breadcrumb m-0">
                                        <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboards</a></li>
                                        <li class="breadcrumb-item active">Dashboard</li>
                                    </ol>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- end page title -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-3">Items</h4>

                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" data-toggle="tab" href="#all-order" role="tab">
                                                All Items
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" data-toggle="tab" href="#processing" role="tab">
                                                Processing
                                            </a>
                                        </li>
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content p-3">
                                        <div class="tab-pane active" id="all-order" role="tabpanel">
                                            <form>
                                                <div class="row">

                                                    <div class="col-xl col-sm-6">
                                                        <div class="form-group mt-3 mb-0">
                                                            <label>Date :</label>
                                                            <input type="text" class="form-control" id="orderid-input" placeholder="Select date" data-date-format="dd M, yyyy" data-date-orientation="bottom auto" data-provide="datepicker" data-date-autoclose="true">
                                                        </div>
                                                    </div>

                                                    <div class="col-xl col-sm-6">
                                                        <div class="form-group mt-3 mb-0">
                                                            <label>Coin</label>
                                                            <select class="form-control select2-search-disable">
                                                                <option value="BTC" selected="">Bitcoin</option>
                                                                <option value="ETH">Ethereum</option>
                                                                <option value="LTC">litecoin</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="col-xl col-sm-6">
                                                        <div class="form-group mt-3 mb-0">
                                                            <label>Type</label>
                                                            <select class="form-control select2-search-disable">
                                                                <option value="BU" selected="">Buy</option>
                                                                <option value="SE">Sell</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="col-xl col-sm-6">
                                                        <div class="form-group mt-3 mb-0">
                                                            <label>Status</label>
                                                            <select class="form-control select2-search-disable">
                                                                <option value="CO" selected="">Completed</option>
                                                                <option value="PE">Pending</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="col-xl col-sm-6 align-self-end">
                                                        <div class="mt-3">
                                                            <button type="button" class="btn btn-primary w-md">Search</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>

                                            <div class="table-responsive mt-5">
                                                <table class="table table-hover datatable dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                    <thead>
                                                    <tr>
                                                        <th scope="col">Date</th>
                                                        <th scope="col">Type</th>
                                                        <th scope="col">Coin</th>
                                                        <th scope="col">Value</th>
                                                        <th scope="col">Value in USD</th>
                                                        <th scope="col">Status</th>
                                                    </tr>

                                                    </thead>

                                                    <tbody>
                                                    <tr>
                                                        <td>03 Mar, 2020</td>
                                                        <td>Buy</td>
                                                        <td>Bitcoin</td>
                                                        <td>1.00952 BTC</td>
                                                        <td>$ 9067.62</td>
                                                        <td>
                                                            <span class="badge badge-success font-size-10">Completed</span>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>04 Mar, 2020</td>
                                                        <td>Sell</td>
                                                        <td>Ethereum</td>
                                                        <td>0.00413 ETH</td>
                                                        <td>$ 2123.01</td>
                                                        <td>
                                                            <span class="badge badge-success font-size-10">Completed</span>
                                                        </td>
                                                    </tr>


                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="processing" role="tabpanel">
                                            <div>
                                                <div class="table-responsive mt-4">
                                                    <table class="table table-hover datatable dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col">Date</th>
                                                            <th scope="col">Type</th>
                                                            <th scope="col">Coin</th>
                                                            <th scope="col">Value</th>
                                                            <th scope="col">Value in USD</th>
                                                            <th scope="col">Status</th>
                                                        </tr>

                                                        </thead>

                                                        <tbody>
                                                        <tr>
                                                            <td>03 Mar, 2020</td>
                                                            <td>Buy</td>
                                                            <td>Bitcoin</td>
                                                            <td>1.00952 BTC</td>
                                                            <td>$ 9067.62</td>
                                                            <td>
                                                                <span class="badge badge-success font-size-10">Completed</span>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>04 Mar, 2020</td>
                                                            <td>Sell</td>
                                                            <td>Ethereum</td>
                                                            <td>0.00413 ETH</td>
                                                            <td>$ 2123.01</td>
                                                            <td>
                                                                <span class="badge badge-success font-size-10">Completed</span>
                                                            </td>
                                                        </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->
                </div>
                <!-- container-fluid -->
            </div>
            <!-- End Page-content -->

            <%@include file="/cp/layout/footer.jsp"%>
        </div>
        <!-- end main content-->

    </div>
    <!-- END layout-wrapper -->

    <!-- JAVASCRIPT -->
    <%@include file="/cp/script/javascript.html"%>

    <!-- select2 -->
    <%@include file="/cp/script/select-2-plugin.html"%>
    <!-- bootstrap-datepicker js -->
    <%@include file="/cp/script/bootstrap-datepicker-js.html"%>

    <!-- Required datatable js -->
    <%@include file="/cp/script/required-datatable-js.html"%>

    <!-- Responsive examples -->
    <%@include file="/cp/script/responsive-examples.html"%>

    <!-- init js -->
    <%@include file="/cp/script/init-js.html"%>

    <!-- App js -->
    <%@include file="/cp/script/app-js.html"%>
</body>

</html>