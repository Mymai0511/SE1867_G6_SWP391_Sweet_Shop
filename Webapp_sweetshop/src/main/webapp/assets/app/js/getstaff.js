// Hàm để lấy và sắp xếp các hàng của bảng dựa trên cột và thứ tự
function sortTableByColumn(columnIndex, isAscending) {
    var table = document.getElementById("user-list-table");
    var rows = Array.from(table.rows).slice(1); // Lấy các hàng trừ hàng đầu tiên (header)

    rows.sort(function (rowA, rowB) {
        var cellA = rowA.cells[columnIndex].textContent.trim();
        var cellB = rowB.cells[columnIndex].textContent.trim();

        if (columnIndex === 3) { // Nếu là cột Date of Birth
            // Chuyển đổi ngày sang định dạng có thể so sánh
            cellA = new Date(cellA);
            cellB = new Date(cellB);
        }

        if (cellA < cellB) {
            return isAscending ? -1 : 1;
        } else if (cellA > cellB) {
            return isAscending ? 1 : -1;
        } else {
            return 0;
        }
    });

    // Đặt lại các hàng đã sắp xếp vào bảng
    rows.forEach(function (row) {
        table.tBodies[0].appendChild(row);
    });

    // Cập nhật lại thông tin trang và phân trang
    const totalRows = table.querySelectorAll("tbody tr").length;
    createPagination();
    displayPage(1);
}

// Khi tài liệu đã tải xong
document.addEventListener("DOMContentLoaded", function () {
    const rowsPerPage = 10; // Số hàng muốn hiển thị mỗi trang
    const table = document.getElementById("user-list-table");
    const rows = table.querySelectorAll("tbody tr");
    const totalRows = rows.length;
    const totalPages = Math.ceil(totalRows / rowsPerPage);
    const pagination = document.querySelector(".pagination");

    // Hiển thị trang
    function displayPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        // Ẩn tất cả các hàng
        rows.forEach((row) => {
            row.style.display = "none";
        });

        // Hiển thị các hàng cho trang hiện tại
        rows.forEach((row, index) => {
            if (index >= start && index < end) {
                row.style.display = "";
            }
        });

        // Cập nhật thông tin trang
        document.getElementById("page-info-text").innerText = `Showing ${start + 1} to ${Math.min(end, totalRows)} of ${totalRows} entries`;
    }

    // Tạo điều khiển phân trang
    function createPagination() {
        pagination.innerHTML = "";

        // Nút Previous
        const prevBtn = document.createElement("li");
        prevBtn.className = "page-item";
        prevBtn.innerHTML = `<a class="page-link" href="#">Previous</a>`;
        prevBtn.addEventListener("click", function (e) {
            e.preventDefault();
            const activePage = document.querySelector(".pagination .active");
            if (activePage) {
                const currentPage = parseInt(activePage.dataset.page);
                const newPage = Math.max(1, currentPage - 1);
                goToPage(newPage);
            }
        });
        pagination.appendChild(prevBtn);

        // Nút số trang
        for (let i = 1; i <= totalPages; i++) {
            const li = document.createElement("li");
            li.className = `page-item ${i === 1 ? 'active' : ''}`;
            li.dataset.page = i;
            li.innerHTML = `<a class="page-link" href="#">${i}</a>`;
            li.addEventListener("click", function (e) {
                e.preventDefault();
                const page = parseInt(this.dataset.page);
                goToPage(page);
            });
            pagination.appendChild(li);
        }

        // Nút Next
        const nextBtn = document.createElement("li");
        nextBtn.className = "page-item";
        nextBtn.innerHTML = `<a class="page-link" href="#">Next</a>`;
        nextBtn.addEventListener("click", function (e) {
            e.preventDefault();
            const activePage = document.querySelector(".pagination .active");
            if (activePage) {
                const currentPage = parseInt(activePage.dataset.page);
                const newPage = Math.min(totalPages, currentPage + 1);
                goToPage(newPage);
            }
        });
        pagination.appendChild(nextBtn);
    }

    // Chuyển đến trang cụ thể
    function goToPage(page) {
        document.querySelector(".pagination .active").classList.remove("active");
        document.querySelector(`.pagination [data-page="${page}"]`).classList.add("active");
        displayPage(page);
    }

    // Thêm sự kiện vào các lựa chọn trong menu thả xuống
    document.querySelectorAll('.dropdown-item').forEach(function (item) {
        item.addEventListener('click', function (event) {
            event.preventDefault();  // Ngăn chặn việc chuyển trang
            var sortType = this.getAttribute('data-sort');  // Lấy giá trị từ thuộc tính data-sort

            switch (sortType) {
                case 'nameAsc':
                    sortTableByColumn(1, true);  // Sắp xếp Name từ A-Z
                    break;
                case 'nameDesc':
                    sortTableByColumn(1, false); // Sắp xếp Name từ Z-A
                    break;
                case 'dobAsc':
                    sortTableByColumn(3, true);  // Sắp xếp Date of Birth từ cũ nhất
                    break;
                case 'dobDesc':
                    sortTableByColumn(3, false); // Sắp xếp Date of Birth từ mới nhất
                    break;
            }
        });
    });

    // Khởi tạo phân trang
    createPagination();
    displayPage(1); // Hiển thị trang đầu tiên ban đầu
});