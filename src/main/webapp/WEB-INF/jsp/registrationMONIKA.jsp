 <title>Kukuduku | UserRegistration</title>
 <%@ include file="header.jsp"%>
 <link rel="stylesheet" href="../css/registrationMonika.css">


<div class="container">
    <div class="header">
        <h2> Create an acount</h2>
    </div>
    <form class="form" id="form">
        <div class="form-control">
             <label>User name</label>
            <input type="text" placeholder="monika254" id="username">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <div class="form-control">
            <label>First name</label>
            <input type="text" placeholder="Monika" id="firstname">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <div class="form-control">
            <label>Last name</label>
            <input type="text" placeholder="Sam" id="lastname">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <div class="form-control">
            <label>Email adress</label>
            <input type="email" placeholder="monika254@gmail.com" id="email">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <div class="form-control">
            <label>Password</label>
            <input type="password" placeholder="password" id="password">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <div class="form-control">
            <label>Password check</label>
            <input type="password" placeholder="password two" id="password2">
            <i class="fas fa-check-circle"></i>
            <i class="fas fa-exclamation-circle"></i>
            <small>Error message</small>
        </div>
        <button>Register</button>
    </form>
</div>


 <%@ include file="footer.jsp"%>