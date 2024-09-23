document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('userForm');
    const userTable = document.getElementById('userTable').getElementsByTagName('tbody')[0];

    function fetchUsers() {
        fetch('/api/users')
            .then(response => response.json())
            .then(data => {
                userTable.innerHTML = '';
                data.forEach(user => {
                    const row = userTable.insertRow();
                    row.innerHTML = `
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.fName}</td>
                        <td>${user.gender === 1 ? 'Male' : 'Female'}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.dob}</td>
                        <td><img src="${user.avatar}" alt="Avatar" width="50"></td>
                        <td>${user.address}</td>
                        <td>${user.status}</td>
                        <td>${user.role}</td>
                        <td>
                            <button onclick="editUser(${user.id})">Edit</button>
                            <button onclick="deleteUser(${user.id})">Delete</button>
                        </td>
                    `;
                });
            });
    }

    function handleFormSubmit(event) {
        event.preventDefault();
        const userId = document.getElementById('userId').value;
        const user = {
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
            fName: document.getElementById('fName').value,
            gender: document.getElementById('gender').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            dob: document.getElementById('dob').value,
            avatar: document.getElementById('avatar').value,
            address: document.getElementById('address').value,
            status: document.getElementById('status').value,
            role: document.getElementById('role').value
        };

        if (userId) {
            // Update user
            fetch(`/api/users/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
            .then(response => response.json())
            .then(() => {
                fetchUsers();
                form.reset();
                document.getElementById('userId').value = '';
            });
        } else {
            // Create new user
            fetch('/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
            .then(response => response.json())
            .then(() => {
                fetchUsers();
                form.reset();
            });
        }
    }

    function editUser(id) {
        fetch(`/api/users/${id}`)
            .then(response => response.json())
            .then(user => {
                document.getElementById('userId').value = user.id;
                document.getElementById('username').value = user.username;
                document.getElementById('password').value = user.password;
                document.getElementById('fName').value = user.fName;
                document.getElementById('gender').value = user.gender;
                document.getElementById('email').value = user.email;
                document.getElementById('phone').value = user.phone;
                document.getElementById('dob').value = user.dob;
                document.getElementById('avatar').value = user.avatar;
                document.getElementById('address').value = user.address;
                document.getElementById('status').value = user.status;
                document.getElementById('role').value = user.role;
            });
    }

    function deleteUser(id) {
        fetch(`/api/users/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            fetchUsers();
        });
    }

    form.addEventListener('submit', handleFormSubmit);
    fetchUsers();
});

// Kết nối tới database MySQL
const mysql = require('mysql2');
const db = mysql.createConnection({
  host: 'localhost',
  user: 'mduc',
  password: '1234',
  database: 'shopcake'
});

// API để thêm hoặc cập nhật khách hàng
app.post('/customer/save', (req, res) => {
  const { customerId, name, email, phone } = req.body;

  if (customerId) {
    // Cập nhật khách hàng
    db.query(
      'UPDATE user SET fName = ?, email = ?, phone = ? WHERE id = ?',
      [name, email, phone, customerId],
      (err, result) => {
        if (err) throw err;
        res.redirect('/user-list.html');
      }
    );
  } else {
    // Thêm khách hàng mới
    db.query(
      'INSERT INTO user (fName, email, phone) VALUES (?, ?, ?)',
      [name, email, phone],
      (err, result) => {
        if (err) throw err;
        res.redirect('/user-list.html');
      }
    );
  }
});
// API để lấy dữ liệu khách hàng dựa trên ID
app.get('/customer/:id', (req, res) => {
    const customerId = req.params.id;
    db.query('SELECT * FROM user WHERE id = ?', [customerId], (err, result) => {
      if (err) throw err;
      res.render('user-add.html', { customer: result[0] });
    });
  });
  
  // API để lấy danh sách khách hàng
app.get('/customers', (req, res) => {
    db.query('SELECT * FROM user', (err, results) => {
      if (err) throw err;
      res.json(results);
    });
  });

  // API để xóa khách hàng dựa trên ID
app.delete('/customer/delete/:id', (req, res) => {
    const customerId = req.params.id;
    db.query('DELETE FROM user WHERE id = ?', [customerId], (err, result) => {
      if (err) throw err;
      res.sendStatus(200);
    });
  });
  