<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title>KukuDuku | Log In</title>
<%@ include file="header.jsp"%>

<section class="vh-100" style="background-color: #666666;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/img1.jpg"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form:form action="${pageContext.request.contextPath}/authUser" method="POST">

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <img src="../images/Logo.png" width="150" >
                                    </div>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Login</h5>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="username" class="form-control form-control-lg" />
                                        <label class="form-label" for="username">Username</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="form2Example27" class="form-control form-control-lg" />
                                        <label class="form-label" for="form2Example27">Password</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit" value="login">Login</button>
                                    </div>

                                    <!--<a class="small text-muted" href="#!">Forgot password?</a>-->
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="registration" style="color: #393f81;">Register here</a>
                                    <br><a class="small text-muted">Account is not neccessary in order to shop</a>
                                    </p>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp"%>




