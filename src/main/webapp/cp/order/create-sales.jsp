<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Create A New Sales Order</title>
    <!-- Meta -->
    <%@include file="/cp/head/meta.html"%>

    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.html"%>

    <!-- select2 css -->
    <%@include file="/cp/head/select2-css.html"%>

    <!-- dropzone css -->
    <%@include file="/cp/head/dropzone-css.html"%>

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
                                <h4 class="mb-0 font-size-18">Create A New Sales Order</h4>

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
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">

                                    <h4 class="card-title">Basic Information</h4>
                                    <p class="card-title-desc">Fill all information below</p>

                                    <form>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label for="productname">Product Name</label>
                                                    <input id="productname" name="productname" type="text" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label for="manufacturername">Manufacturer Name</label>
                                                    <input id="manufacturername" name="manufacturername" type="text" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label for="manufacturerbrand">Manufacturer Brand</label>
                                                    <input id="manufacturerbrand" name="manufacturerbrand" type="text" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label for="price">Price</label>
                                                    <input id="price" name="price" type="text" class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label class="control-label">Category</label>
                                                    <select class="form-control select2">
                                                        <option>Select</option>
                                                        <option value="AK">Alaska</option>
                                                        <option value="HI">Hawaii</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Features</label>

                                                    <select class="select2 form-control select2-multiple" multiple="multiple" data-placeholder="Choose ...">
                                                        <option value="AK">Alaska</option>
                                                        <option value="HI">Hawaii</option>
                                                        <option value="CA">California</option>
                                                        <option value="NV">Nevada</option>
                                                        <option value="OR">Oregon</option>
                                                        <option value="WA">Washington</option>
                                                    </select>

                                                </div>
                                                <div class="form-group">
                                                    <label for="productdesc">Product Description</label>
                                                    <textarea class="form-control" id="productdesc" rows="5"></textarea>
                                                </div>

                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary mr-1 waves-effect waves-light">Save Changes</button>
                                        <button type="submit" class="btn btn-secondary waves-effect">Cancel</button>
                                    </form>

                                </div>
                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-3">Product Images</h4>

                                    <form action="/" method="post" class="dropzone">
                                        <div class="fallback">
                                            <input name="file" type="file" multiple="">
                                        </div>

                                        <div class="dz-message needsclick">
                                            <div class="mb-3">
                                                <i class="display-4 text-muted bx bxs-cloud-upload"></i>
                                            </div>

                                            <h4>Drop files here or click to upload.</h4>
                                        </div>
                                    </form>
                                </div>

                            </div> <!-- end card-->

                            <div class="card">
                                <div class="card-body">

                                    <h4 class="card-title">Meta Data</h4>
                                    <p class="card-title-desc">Fill all information below</p>

                                    <form>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label for="metatitle">Meta title</label>
                                                    <input id="metatitle" name="productname" type="text" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label for="metakeywords">Meta Keywords</label>
                                                    <input id="metakeywords" name="manufacturername" type="text" class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label for="metadescription">Meta Description</label>
                                                    <textarea class="form-control" id="metadescription" rows="5"></textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary mr-1 waves-effect waves-light">Save Changes</button>
                                        <button type="submit" class="btn btn-secondary waves-effect">Cancel</button>

                                    </form>

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
    <!-- select 2 plugin -->
    <%@include file="/cp/script/select-2-plugin.html"%>
    <!-- dropzone plugin -->
    <%@include file="/cp/script/dropzone-plugin.html"%>
    <!-- init js -->
    <%@include file="/cp/script/init-js.html"%>
    <!-- App js -->
    <%@include file="/cp/script/app-js.html"%>
</body>

</html>