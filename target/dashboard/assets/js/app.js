!function(t){"use strict";function s(e){1==t("#light-mode-switch").prop("checked")&&"light-mode-switch"===e?(t("#dark-mode-switch").prop("checked",!1),t("#rtl-mode-switch").prop("checked",!1),t("#bootstrap-style").attr("href","assets/css/bootstrap.min.css"),t("#app-style").attr("href","assets/css/app.min.css"),sessionStorage.setItem("is_visited","light-mode-switch")):1==t("#dark-mode-switch").prop("checked")&&"dark-mode-switch"===e?(t("#light-mode-switch").prop("checked",!1),t("#rtl-mode-switch").prop("checked",!1),t("#bootstrap-style").attr("href","assets/css/bootstrap-dark.min.css"),t("#app-style").attr("href","assets/css/app-dark.min.css"),sessionStorage.setItem("is_visited","dark-mode-switch")):1==t("#rtl-mode-switch").prop("checked")&&"rtl-mode-switch"===e&&(t("#light-mode-switch").prop("checked",!1),t("#dark-mode-switch").prop("checked",!1),t("#bootstrap-style").attr("href","assets/css/bootstrap.min.css"),t("#app-style").attr("href","assets/css/app-rtl.min.css"),sessionStorage.setItem("is_visited","rtl-mode-switch"))}function e(){document.webkitIsFullScreen||document.mozFullScreen||document.msFullscreenElement||(console.log("pressed"),t("body").removeClass("fullscreen-enable"))}var n;t("#side-menu").metisMenu(),t("#vertical-menu-btn").on("click",function(e){e.preventDefault(),t("body").toggleClass("sidebar-enable"),992<=t(window).width()?t("body").toggleClass("vertical-collpsed"):t("body").removeClass("vertical-collpsed")}),t("#sidebar-menu a").each(function(){var e=window.location.href.split(/[?#]/)[0];this.href==e&&(t(this).addClass("active"),t(this).parent().addClass("mm-active"),t(this).parent().parent().addClass("mm-show"),t(this).parent().parent().prev().addClass("mm-active"),t(this).parent().parent().parent().addClass("mm-active"),t(this).parent().parent().parent().parent().addClass("mm-show"),t(this).parent().parent().parent().parent().parent().addClass("mm-active"))}),t(".navbar-nav a").each(function(){var e=window.location.href.split(/[?#]/)[0];this.href==e&&(t(this).addClass("active"),t(this).parent().addClass("active"),t(this).parent().parent().addClass("active"),t(this).parent().parent().parent().addClass("active"),t(this).parent().parent().parent().parent().addClass("active"),t(this).parent().parent().parent().parent().parent().addClass("active"))}),t('[data-toggle="fullscreen"]').on("click",function(e){e.preventDefault(),t("body").toggleClass("fullscreen-enable"),document.fullscreenElement||document.mozFullScreenElement||document.webkitFullscreenElement?document.cancelFullScreen?document.cancelFullScreen():document.mozCancelFullScreen?document.mozCancelFullScreen():document.webkitCancelFullScreen&&document.webkitCancelFullScreen():document.documentElement.requestFullscreen?document.documentElement.requestFullscreen():document.documentElement.mozRequestFullScreen?document.documentElement.mozRequestFullScreen():document.documentElement.webkitRequestFullscreen&&document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT)}),document.addEventListener("fullscreenchange",e),document.addEventListener("webkitfullscreenchange",e),document.addEventListener("mozfullscreenchange",e),t(".right-bar-toggle").on("click",function(e){t("body").toggleClass("right-bar-enabled")}),t(document).on("click","body",function(e){0<t(e.target).closest(".right-bar-toggle, .right-bar").length||t("body").removeClass("right-bar-enabled")}),t(".dropdown-menu a.dropdown-toggle").on("click",function(e){return t(this).next().hasClass("show")||t(this).parents(".dropdown-menu").first().find(".show").removeClass("show"),t(this).next(".dropdown-menu").toggleClass("show"),!1}),t(function(){t('[data-toggle="tooltip"]').tooltip()}),t(function(){t('[data-toggle="popover"]').popover()}),window.sessionStorage&&((n=sessionStorage.getItem("is_visited"))?(t(".right-bar input:checkbox").prop("checked",!1),t("#"+n).prop("checked",!0),s(n)):sessionStorage.setItem("is_visited","light-mode-switch")),t("#light-mode-switch, #dark-mode-switch, #rtl-mode-switch").on("change",function(e){s(e.target.id)}),t(window).on("load",function(){t("#status").fadeOut(),t("#preloader").delay(350).fadeOut("slow")}),Waves.init()}(jQuery);

class App {
    static DOMAIN = location.origin;
    static BASER_URL = this.DOMAIN + "/api";
    static GET_PROVINCES = "https://vapi.vnappmob.com/api/province";

    static ERROR_400 = "Giao dịch không thành công, vui lòng kiểm tra lại dữ liệu.";
    static ERROR_401 = "Access Denied! Invalid credentials.";
    static ERROR_403 = "Access Denied! You are not authorized to perform this function.";
    static ERROR_404 = "An error occurred. Please try again later!";
    static ERROR_500 = "Lưu dữ liệu không thành công, vui lòng liên hệ quản trị hệ thống.";
    static SUCCESS_CREATED = "Successful data generation !";
    static SUCCESS_UPDATED = "Data update successful !";
    static SUCCESS_DEPOSIT = "Successful deposit transaction !";
    static SUCCESS_WITHDRAW = "Successful withdraw transaction !";
    static SUCCESS_TRANSFER = "Successful transfer transaction !";
    static SUCCESS_SUSPEND = "Succeeded client suspension !";

    static SweetAleart = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showSuspendedConfirmAlert(){
            return Swal.fire({
                title: 'Are you sure?',
                text: "Are you sure you want to delete the selected data?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: "Yes, delete it!"
            })
        }
    }

    static IziToast = class {
        static showSuccessAlert(title) {
            iziToast.success({
                title: 'SUCCESS',
                position: 'topRight',
                timeout: 2500,
                message: title
            })
        }

        static showErrorAlert(title){
            iziToast.error({
                title: 'ERROR',
                position: 'topRight',
                timeout: 2500,
                message: title
            })
        }
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    static removeFormatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "")
    }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}
