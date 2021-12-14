<title>KukuDuku | Shop</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="../CSS/style.css">
<link rel="stylesheet" href="css/style.css">
<div class="home-content-wrapper">
    <div class="overlay-box">
        <div class="container">
            <div class="welcome-text">Welcome to <br/> KukuDuku shop </div>
        </div>
    </div>
</div>
<div class="main-background">
    <div class="little-overlay-box">
        <div class="container">
            <div class="main-text">CATEGORIES</div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <img class="card-img-top" src="../images/ball.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Clothes and accessories</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="${pageContext.request.contextPath}/products/category/clothes" class="btn btn-primary">View Clothes</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img class="card-img-top" src="../images/ball.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Toys</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img class="card-img-top" src="../images/ball.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
    </div>


</div>

<!--content ends here-->
</body>
<%@ include file="footer.jsp"%>