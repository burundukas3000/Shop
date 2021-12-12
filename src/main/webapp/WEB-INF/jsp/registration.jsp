<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>
<head>
    <title>Kukuduku | UserRegistration</title>
    <%@ include file="header.jsp"%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .error { color: red }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#username").change(function() {
                $.ajax({
                    url : 'registration/validateuser',
                    data : {
                        username : $("#username").val()
                    },
                    success : function(responseText) {
                        $("#errmsg").text(responseText);
                        if (responseText != "") {
                            $("#username").focus();
                        }
                    }
                });
            });
        });
    </script>
    <script>
        function validateForm() {
            valid = true;

            if (document.contactForm.username.value == "" ) {
                alert("User Name must be filled out");
                valid = false;
            }

            if (document.contactForm.firstName.value == "" ) {
                alert("Name must be filled out");
                valid = false;
            }

            else if (document.contactForm.lastName.value == "" ) {
                alert("Last Name must be filled out");
                valid = false;
            }

            else if (document.contactForm.email.value == "" ) {
                alert("email must be filled out");
                valid = false;
            }

            else if (document.contactForm.password.value == "" ) {
                alert("Password must be filled out");
                valid = false;
            }
            return valid;
        }
    </script>
</head>
<body>

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

                                <form:form name="contactForm" method="post" action="${pageContext.request.contextPath}/registration/processform"
                                           modelAttribute="user" class="form-horizontal" onsubmit="validateForm()">

                                <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 5px;">User Registration</h5>

                                <c:if test="${errorWarning != null}">

                                <!--User name-->
                                <div class="form-outline mb-4">
                                    </c:if><span class="error" id="errmsg"></span>
                                    <form:input path="username" class="form-control form-control-lg"  id="username"/>
                                    <label class="form-label" for="userName">User Name *</label>
                                    <form:errors path="username" cssClass="error" />
                                </div>

                                <!--First name/ Last name-->
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        </c:if><span class="error" id="errmsg"></span>
                                        <form:input path="firstName" class="form-control form-control-lg" id="firstName"/>
                                        <label class="form-label" for="firstName">First Name *</label>
                                        <form:errors path="firstName" cssClass="error" />
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        </c:if><span class="error" id="errmsg"></span>
                                        <form:input path="lastName" class="form-control form-control-lg" id="lastName"/>
                                        <label class="form-label" for="lastName">Last Name *</label>
                                        <form:errors path="lastName" cssClass="error" />
                                    </div>
                                </div>


                                <!--Email-->
                                <div class="form-outline mb-4">
                                    </c:if><span class="error" id="errmsg"></span>
                                    <form:input path="email" class="form-control form-control-lg" id="email"/>
                                    <label class="form-label" for="email">Email address *</label>
                                    <form:errors path="email" cssClass="error" />
                                </div>

                                <!--Password-->
                                <div class="form-outline mb-4">
                                    </c:if><span class="error" id="errmsg"></span>
                                    <form:input path="password" class="form-control form-control-lg" id="password"/>
                                    <label class="form-label" for="email">Password *</label>
                                    <form:errors path="password" cssClass="error" />
                                </div>

                                <div class="pt-1 mb-4">
                                    <button class="btn btn-dark btn-lg btn-block" type="submit" value="Send Deatils" >Register</button>



                                    <!--<a class="small text-muted" href="#!">Forgot password?</a>-->
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Already have an account?
                                        <a href="loginopt" style="color: #393f81;"> Login here</a></p>
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

