<!DOCTYPE html>
<html lang="en">
<head>
    <title>device ${device.name}</title>
    <#include "head.ftl">
</head>
<body>
<#include "navbar.ftl">

<div class="row justify-content-center">
    <div class="col-md-8">

        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Device ${device.name}</h4>
            </header>
            <article class="card-body">
                <div class="row mt-3">Owner : ${device.owner.nickname}</div>
                <#if device.owner.id == client_user.id || client_user.role=="ADMIN">
                    <div class="row mt-3">Token : ${device.token}</div>
                    <div class="row mt-3">Description :
                        <form method="post" action="/device-details/${device.id}">
                            <input value="<#if device.description??>${device.description}</#if>" name="text">
                            <button>Update</button>
                        </form>
                    </div>
                <#else>
                    <div class="row mt-3">Description : <#if device.description??>${device.description}</#if></div>
                </#if>
            </article>
        </div>
        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Sensors</h4>
            </header>
            <article class="card-body">

                <#list device.sensors as sensor>
                    <div class="card mt-3">
                        <header class="card-header">
                            <h4 class="card-title mt-2">${sensor.name}</h4>
                        </header>
                        <article class="card-body">
                            <div class="row mt-3">value : ${sensor.value}</div>
                            <div class="row mt-3">unit : ${sensor.unit}</div>
                            <div class="row mt-3">type : ${sensor.sensorType}</div>
                        </article>
                    </div>
                </#list>
            </article>
        </div>
    </div>
</div>
</body>
</html>