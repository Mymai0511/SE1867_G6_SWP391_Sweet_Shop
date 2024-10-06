document.addEventListener("DOMContentLoaded", function() {
    // Lấy tất cả các sidebar items
    const sidebarItems = document.querySelectorAll('.sidebar-item a');

    // Lấy URL hiện tại
    const currentUrl = window.location.pathname;
    const addUrl = "/addstaff"

    // Duyệt qua từng sidebar item
    sidebarItems.forEach(item => {
        // So sánh href của item với URL hiện tại
        if (item.getAttribute('href') === currentUrl || item.getAttribute('href') === addUrl ) {
            // Thêm class 'active' cho item hiện tại
            item.parentElement.classList.add('active');
        }

        // Thêm sự kiện click cho mỗi item
        item.addEventListener('click', function() {
            // Xóa class 'active' của tất cả các item
            sidebarItems.forEach(i => i.parentElement.classList.remove('active'));

            // Thêm class 'active' cho item được click
            this.parentElement.classList.add('active');
        });
    });
});