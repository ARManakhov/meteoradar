<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>device ${device.name}</title>
</head>
<body>
<div>Device : ${device.name}</div>
<div>Owner : ${device.owner.nickname}</div>
<#if device.owner.id == client_user.id || client_user.role=="ADMIN">
    <div>Token : ${device.token}</div>
    <div>Description :
        <form method="post" action="/device-details/${device.id}">
            <input value="<#if device.description??>${device.description}</#if>" name="text">
            <button>Update</button>
        </form>
    </div>
<#else>
    <div>Description : <#if device.description??>${device.description}</#if></div>
</#if>
<div>
    Sensors :
</div>
</body>
</html>