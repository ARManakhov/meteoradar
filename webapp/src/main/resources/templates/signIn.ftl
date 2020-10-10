<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <#include "head.ftl">
</head>
<body>
<#include "navbar.ftl">


<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Login</h4>
            </header>
            <article class="card-body">
                <form method="post" action="/signIn">
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Email</label>
                            <input type="text" name="email" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col form-row">
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </div>
                    </div>
                </form>
            </article>
            <div class="border-top card-body text-center">Don't have an account ? <a href="/signUp">Registration</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>