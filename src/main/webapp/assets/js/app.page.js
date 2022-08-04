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

        static showSuspendedConfirmAlert() {
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

        static showErrorAlert(title) {
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

    static getTempRowProduct(item) {
        let str = '';
        str += `
        <tr id="tr_${item.id}">
            <td><span class="select-tab unselected"></span></td>
            <td>
                <img style="width: 70px;" src="/assets/images/product/${item.urlImage}" alt="">
            </td>
            <td>${item.title}</td>
            <td>${item.sku}</td>
            <td>${item.category.name}</td>
            <td>${item.createdAt}</td>
            <td>${item.createdBy}</td>
            <td>${item.description}</td>
            <td>
                <span
                    class="badge badge-pill badge-soft-success font-size-12">${item.imported === true ? 'Imported' : 'unImported'}</span>
            </td>
        </tr>
    `;
        return str;
    }

    static getTempRowUser(item) {
        let str = '';
        str += `
        <tr id="tr_${item.id}">
            <td><span class="select-tab unselected"></span></td>
            <td>
                <div class="avatar-xs">
                    <img class="avatar-title rounded-circle" src="/assets/images/users/${item.userInfo.urlImage}" alt="">
                </div>
            </td>
            <td>${item.userInfo.fullName}</td>
            <td>${item.email}</td>
            <td>${item.userInfo.phone}</td>
            <td>${item.role.name}</td>
            <td scope="col">
                <span class="badge badge-soft-success font-size-11 m-1">${item.activated === true ? 'Active' : 'Block'}</span>
            </td>
        </tr>
    `;
        return str;
    }

    static getTempRowProductInCreateImportOrder(item) {
        let str = '';
        str += `
        <tr id="tr_${item.id}">
            <td><span class="select-tab unselected"></span></td>
            <td scope="col">
                <img style="width: 70px;" src="/assets/images/product/${item.urlImage}" alt="">
            </td>
            <td scope="col">${item.title}</td>
            <td scope="col">${item.sku}</td>
            <td scope="col">${item.category.name}<td scope="col">
                <span class="badge badge-pill badge-soft-success font-size-12">${item.imported === true ? "Imported" : "unImported"}</span>
            </td>
            <td scope="col">
                <button class="btn btn-outline-primary add-cart-item">
                    <i class="fas fa-plus"></i>
                    Add
                </button>
            </td>
        </tr>
    `;
        return str;
    }

    static getTempRowCartItemInCart(item) {
        let str = '';
        str += `
        <tr id="cart_tr_1">
            <td>1</td>
            <td>
                <h5 class="font-size-14 text-truncate title">${item.product.title}</h5>
            </td>
            <td>
                <input type="text" value="${item.price}" class="form-control price" style="width: 90px" value="0">                                        
            </td>
            <td>
                <div style="width: 120px;">
                    <div class="input-group  bootstrap-touchspin bootstrap-touchspin-injected">
                        <input type="text" value="${item.quantity}" class="form-control quantity">
                    </div>
                </div>
            </td>
            <td class="total-cart">${item.totalPrice}</td>
            <td>
                <a href="#" class="action-icon text-danger delete-cart-item">
                    <i class="mdi mdi-trash-can font-size-18"></i>
                </a>
            </td>
        </tr>
    `;
        return str;
    }
}

class Product {
    constructor(id, title, sku, urlImage, description, price, quantity, sold, available, createdAt, createdBy, updatedAt, updatedBy, imported, category) {
        this.id = id;
        this.title = title;
        this.sku = sku;
        this.urlImage = urlImage;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
        this.available = available;
        this.imported = imported;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.category = category;
    }
}

class User {
    constructor(id, email, password, activated, createdAt, createdBy, updatedAt, updatedBy, role, userInfo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.role = role;
        this.userInfo = userInfo;
    }
}

class UserInfo {
    constructor(id, fullName, phone, urlImage, locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.urlImage = urlImage;
        this.locationRegion = locationRegion;
    }
}

class Cart {
    constructor(id, grandTotal, quantityTotal, user, type, situation, unit) {
        this.id = id;
        this.grandTotal = grandTotal;
        this.quantityTotal = quantityTotal;
        this.user = user;
        this.type = type;
        this.situation = situation;
        this.unit = unit;
    }
}

class CartImport {
    constructor(userId, productId, quantity, price, typeId) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.typeId = typeId;
    }
}

class CarIem {
    constructor(id, price, quantity, totalPrice, cart, product) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cart = cart;
        this.product = product;
    }
}


class Category {
    constructor(id, name, code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}

class Role {
    constructor(id, name, code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}

class Type {
    constructor(id, name, code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}

class Situation {
    constructor(id, name, code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}

class Unit {
    constructor(id, name, code, locationRegion) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.locationRegion = locationRegion;
    }
}

class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}