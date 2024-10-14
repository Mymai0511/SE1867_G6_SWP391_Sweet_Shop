document.addEventListener("DOMContentLoaded", function () {
    // Lấy tham chiếu đến bảng và các hàng trong tbody
    const table = document.getElementById('staffTable');
    const tbody = table.querySelector('tbody');

    // Lấy các dropdown item
    const sortNameAsc = document.querySelector('.dropdown-menu li:nth-child(1) a');
    const sortNameDesc = document.querySelector('.dropdown-menu li:nth-child(2) a');
    const sortDobOldest = document.querySelector('.dropdown-menu li:nth-child(3) a');
    const sortDobYoungest = document.querySelector('.dropdown-menu li:nth-child(4) a');

    02

    // Hàm sắp xếp theo cột
    function sortTable(columnIndex, isAscending, isDate = false) {
        const rowsArray = Array.from(tbody.querySelectorAll('tr'));

        rowsArray.sort((rowA, rowB) => {
            const cellA = rowA.cells[columnIndex].textContent.trim();
            const cellB = rowB.cells[columnIndex].textContent.trim();

            if (isDate) {
                // Convert string to date for sorting
                const dateA = new Date(cellA);
                const dateB = new Date(cellB);
                return isAscending ? dateA - dateB : dateB - dateA;
            } else {
                // Compare strings
                return isAscending ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
            }
        });

        // Xóa các hàng cũ và thêm các hàng đã sắp xếp
        tbody.innerHTML = '';
        rowsArray.forEach(row => tbody.appendChild(row));
    }

    // Sắp xếp theo tên (từ A-Z)
    sortNameAsc.addEventListener('click', () => {
        sortTable(1, true);
    });

    // Sắp xếp theo tên (từ Z-A)
    sortNameDesc.addEventListener('click', () => {
        sortTable(1, false);
    });

    // Sắp xếp theo ngày sinh (oldest first)
    sortDobOldest.addEventListener('click', () => {
        sortTable(2, true, true); // true: tăng dần, isDate: true vì là ngày tháng
    });

    // Sắp xếp theo ngày sinh (youngest first)
    sortDobYoungest.addEventListener('click', () => {
        sortTable(2, false, true); // false: giảm dần, isDate: true vì là ngày tháng
    });
});