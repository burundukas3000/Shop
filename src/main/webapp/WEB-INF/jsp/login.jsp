<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>KukuDuku | Log In</title>
<%@ include file="header.jsp"%>

<section class="vh-100" style="background-color: #666666;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="../images/login1.png"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form:form action="${pageContext.request.contextPath}/authUser"
                                           method="POST" class="form-horizontal">

                                    <c:if test="${param.error != null}">

                                        <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                            Invalid username and password.</div>

                                    </c:if>
                                    <c:if test="${param.logout != null}">

                                        <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                            Invalid username and password.</div>

                                    </c:if>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Login</h5>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="username" class="form-control form-control-lg" />
                                        <label class="form-label" for="username" name="username">Username</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="password" class="form-control form-control-lg" />
                                        <label class="form-label" for="password" name="password" >Password</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit" value="login">Login</button>
                                    </div>

                                    <!--<a class="small text-muted" href="#!">Forgot password?</a>-->
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="../registration" style="color: #393f81;">Register here</a>
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




