

    //xem trước hình ảnh được tải lên cho ảnh đại diện
    function previewImage(event) {
    const file = event.target.files[0];
    const imgError = document.getElementById('fileError');
    if (file) {
    if (file.type !== 'image/jpeg') {
    imgError.style.display = 'block';
    imgError.innerText = 'Only JPG files are allowed.';
    document.getElementById('profileImage').src = "../../assets/avatar/11.png"; // Reset image
} else {
    imgError.style.display = 'none';
    const reader = new FileReader();
    reader.onload = function(e) {
    document.getElementById('profileImage').src = e.target.result;
}
    reader.readAsDataURL(file);
}
}
}

    // kiểm tra tính hợp lệ
    function validateField(field) {
    let isValid = true;
    // lấy giá trị từ trường dữ liệu và loại bỏ khoảng trắng đầu và cuối
    const value = document.getElementById(field).value.trim();
    const errorElement = document.getElementById(field + 'Error');

    switch(field) {
    case 'fullname':
    // Kiểm tra chuỗi có rỗng ko
    if (value === "") {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Full Name cannot be blank or contain only spaces.';
    isValid = false;
} else if (!/^[a-zA-Z\s]{1,30}$/.test(value)) {
    // Kiểm tra tên chỉ chứa chữ cái và tối đa 30 ký tự
    errorElement.style.display = 'block';
    errorElement.innerText = 'Full Name must be letters only and max 30 characters.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'dob':
    const today = new Date();
    const selectedDate = new Date(value);
    if (selectedDate >= today) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Date of Birth must be before today.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'gender':
    if (!value) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Please select Gender.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'status':
    if (!value) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Please select Status.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'address':
    // Kiểm tra chuỗi có rỗng ko
    if (value === "") {
        errorElement.style.display = 'block';
        errorElement.innerText = 'Full Name cannot be blank or contain only spaces.';
        isValid = false;
    } else if (value.length > 200) {
        errorElement.style.display = 'block';
        errorElement.innerText = 'Address cannot exceed 200 characters.';
        isValid = false;
    } else {
     errorElement.style.display = 'none';
    }
    break;

    case 'mobno':
    if (!/^(0[0-9]{9})$/.test(value)) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Mobile number must start with 0 and be 10 digits long.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'email':
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
        errorElement.style.display = 'block';
        errorElement.innerText = 'Email format is invalid.';
        isValid = false;
    } else {
        errorElement.style.display = 'none';
    }
    break;

    case 'uname':
        // Kiểm tra chuỗi có rỗng ko
        if (value === "") {
            errorElement.style.display = 'block';
            errorElement.innerText = 'Full Name cannot be blank or contain only spaces.';
            isValid = false;
        } else if (/\s/.test(value) || value.length > 15) {
            errorElement.style.display = 'block';
            errorElement.innerText = 'User Name cannot contain whitespace and must be max 15 characters.';
            isValid = false;
        } else {
            errorElement.style.display = 'none';
        }
        break;


    case 'pass':
    if (value.length < 8) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Password must be at least 8 characters long.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    case 'rpass':
    const password = document.getElementById('pass').value;
    if (value !== password) {
    errorElement.style.display = 'block';
    errorElement.innerText = 'Passwords do not match.';
    isValid = false;
} else {
    errorElement.style.display = 'none';
}
    break;

    default:
    break;
}

    return isValid;
}

    //chuyển đổi giữa hiển thị và ẩn mật khẩu khi người dùng chọn "Show Password"
    function togglePasswordVisibility() {
    const passwordFields = [document.getElementById('pass'), document.getElementById('rpass')];
    passwordFields.forEach(field => {
    field.type = field.type === 'password' ? 'text' : 'password';
});
}

    //kiểm tra toàn bộ các trường trong biểu mẫu khi người dùng nhấn nút submit
    function validateForm() {
    let isValid = true;
    const fields = ['fullname', 'dob', 'gender', 'status', 'address', 'mobno', 'email', 'uname', 'pass', 'rpass'];
    fields.forEach(field => {
    isValid = validateField(field) && isValid;
});
    return isValid;
}
