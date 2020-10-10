<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${profile_owner.nickname}</title>
    <#include "head.ftl">
</head>
<body>
<#include "navbar.ftl">

<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="card mt-3">
            <article class="card-body">

                <div class="row mt-3">Логин : ${profile_owner.nickname}</div>
                <div class="row mt-3">Email : ${profile_owner.email}</div>

                <#if profile_owner.id == client_user.id || client_user.role=="ADMIN">
                    <div class="row mt-3">Token : ${profile_owner.token}</div>
                    <div class="row mt-3">About :
                        <form method="post" action="/details/">
                            <input value="<#if profile_owner.description??>${profile_owner.description}</#if>"
                                   name="text">
                            <button>Update</button>
                        </form>
                    </div>
                <#else>
                    <div class="row mt-3">About
                        : <#if profile_owner.description??>${profile_owner.description}</#if></div>
                </#if>
            </article>
        </div>
        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Devices</h4>
            </header>
            <article class="card-body">

                <#list profile_owner.devices as device>
                    <div class="card mt-3">
                        <header class="card-header">
                            <h4 class="card-title mt-2">${device.name}</h4>
                        </header>
                        <article class="card-body">

                            <div class="row mt-3">Description : <#if device.description??>${device.description}</#if></div>
                            <#if profile_owner.id == client_user.id || client_user.role=="ADMIN">
                                <div class="row mt-3">Token : ${device.token}</div>
                            </#if>
                            <div class="row mt-3"><a href="/device/${device.id}">more info</a></div>
                        </article>
                    </div>
                </#list>
                <div class="card mt-3">
                    <header class="card-header">
                        <h4 class="card-title mt-2">New device</h4>
                    </header>
                    <article class="card-body">
                        <form action="/device/" method="post">
                            <input name="name">
                            <button>new</button>
                        </form>
                    </article>
                </div>
            </article>
        </div>

    </div>
</div>
</body>
</html>

