<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${profile_owner.nickname}</title>
</head>
<body>
<div>Логин : ${profile_owner.nickname}</div>
<div>Email : ${profile_owner.email}</div>

<#if profile_owner.id == client_user.id || client_user.role=="ADMIN">
    <div>Token : ${profile_owner.token}</div>
    <div>About :
        <form method="post" action="/details/">
            <input value="<#if profile_owner.description??>${profile_owner.description}</#if>" name="text">
            <button>Update</button>
        </form>
    </div>
<#else>
    <div>About : <#if profile_owner.description??>${profile_owner.description}</#if></div>
</#if>

<div>devices:</div>
<button>New Device</button>
</body>
</html>