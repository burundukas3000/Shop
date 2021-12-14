<title>KukuDuku | Shop</title>
<%@ include file="header.jsp"%>
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
                <img src="../images/clothes.png" height="150" width="150" alt="Card image cap">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/products/category/clothes" class="btn btn-primary">CLOTHES</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img src="../images/toys.png" height="150"  width="150" alt="Card image cap">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/products/category/toys" class="btn btn-primary">TOYS</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <img src="../images/accessories.png" height="150" width="150" alt="Card image cap">
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/products/category/accessories" class="btn btn-primary">ACCESSORIES</a>
                </div>
            </div>
        </div>
    </div>


</div>

<!--content ends here-->
</body>
<%@ include file="footer.jsp"%>