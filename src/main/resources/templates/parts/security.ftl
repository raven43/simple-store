<#assign
    isAuthorize = Session.SPRING_SECURITY_CONTEXT??
>

<#if isAuthorize>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    isSeller = user.isSeller()
    >

<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    isSeller = false
    >
</#if>