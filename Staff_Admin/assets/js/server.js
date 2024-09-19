// Kết nối tới database MySQL
const mysql = require('mysql2');
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'your_password',
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
