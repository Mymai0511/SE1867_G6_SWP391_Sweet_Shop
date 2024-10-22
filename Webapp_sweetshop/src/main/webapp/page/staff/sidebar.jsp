<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        /* Sidebar Styling */
        section {
            background-color: #EBA827;
            height: 100%;
            padding: 20px;
        }

        /* Fix Home and Dashboard links */
        .sticky-links {
            position: sticky;
            top: 0;
            background-color: #EBA827;
            z-index: 100;
            padding-bottom: 10px;
        }

        .sidebar a {
            display: block;
            padding: 10px;
            color: #722211;
            text-decoration: none;
            margin-bottom: 12px;
        }

        .sidebar a:hover {
            background-color: #ddd;
            border-radius: 10px;
        }
        section a.active {
            background-color: #fff;
            color: #722211;
            font-weight: bold;
            border-radius: 10px;
        }
    </style>
</head>

<body>
<section class="sidebar">
    <!-- Sticky Section for Home and Dashboard -->
    <div class="sticky-links">
        <a href="/home" class="home-link active"><i class="fas fa-home"></i> Home</a>
        <a href="/dashboard" class="dashboard-link"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        <a href="/products" class=""><i class="fas fa-box"></i> Products</a>
        <a href="/orders" class=""><i class="fas fa-shopping-cart"></i> Orders</a>
        <a href="/customers" class=""><i class="fas fa-users"></i> Customers</a>
    </div>
</section>
</body>

</html>
