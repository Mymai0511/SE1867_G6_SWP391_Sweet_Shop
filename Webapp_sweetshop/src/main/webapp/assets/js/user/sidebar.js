document.addEventListener("DOMContentLoaded", function() {
    // Làm nổi bật mục sidebar dựa trên URL hiện tại
    const sidebarLinks = document.querySelectorAll(".sidebar-link");
    const currentPage = window.location.pathname;

    // Duyệt qua tất cả các liên kết trong sidebar
    sidebarLinks.forEach(link => {
        // Kiểm tra xem liên kết có khớp với trang hiện tại không
        if (link.getAttribute("href") === currentPage) {
            // Thêm class 'active' vào liên kết tương ứng
            link.classList.add("active");

            // Nếu liên kết nằm trong một dropdown, mở dropdown cha
            const parentDropdown = link.closest(".collapse");
            if (parentDropdown) {
                parentDropdown.classList.add("show");

                // Nếu cần, cũng đánh dấu liên kết cha là 'active'
                const parentLink = parentDropdown.previousElementSibling;
                if (parentLink) {
                    parentLink.classList.add("active");
                }
            }
        }
    });

    // Xử lý trạng thái của dropdown sau khi tải lại trang
    const dropdownLinks = document.querySelectorAll("[data-bs-toggle='collapse']");

    // Duyệt qua tất cả các liên kết có chức năng dropdown
    dropdownLinks.forEach(link => {
        const dropdown = document.querySelector(link.getAttribute("data-bs-target"));

        // Kiểm tra xem dropdown có đang mở hay không
        if (dropdown && dropdown.classList.contains("show")) {
            link.classList.remove("collapsed");
            link.classList.add("active");
        }

        // Thêm sự kiện click để thay đổi trạng thái của dropdown
        link.addEventListener("click", function() {
            const isCollapsed = link.classList.contains("collapsed");

            // Xóa class 'active' khỏi các dropdown khác
            dropdownLinks.forEach(otherLink => {
                if (otherLink !== link) {
                    otherLink.classList.add("collapsed");
                    otherLink.classList.remove("active");
                }
            });

            // Thay đổi class 'active' cho liên kết đã click
            if (!isCollapsed) {
                link.classList.remove("active");
            } else {
                link.classList.add("active");
            }
        });
    });
});
