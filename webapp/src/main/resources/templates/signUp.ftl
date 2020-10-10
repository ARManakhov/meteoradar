<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
    <#include "head.ftl">
</head>
<body>
<#include "navbar.ftl">

<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Registration</h4>
            </header>
            <article class="card-body">
                <form method="post" action="/sign_up">
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Login</label>
                            <input type="text" name="nickname" class="form-control" placeholder="">
                        </div>
                        <div class="col form-group">
                            <label>Email</label>
                            <input type="text" name="email" class="form-control" placeholder=" ">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" placeholder="">
                        </div>
                        <div class="col form-group">
                            <label>Repeat password</label>
                            <input type="password" name="rePassword" class="form-control" placeholder=" ">
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Register</button>
                    </div>
                </form>
            </article>
            <div class="border-top card-body text-center">Already have an account? <a href="/sign_in">Login</a></div>
        </div>
    </div>
</div>
</body>
</html>