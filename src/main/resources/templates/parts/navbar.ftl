<#include "security.ftl">

<header class="navbar-dark   bg-dark mb-3">
    <nav class="navbar navbar-expand-md  mb-3 mx-auto" style="max-width: 820px">
        <a class="navbar-brand" href="/">SimpleStore</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">

            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="/">Home</a>
                <a class="nav-item nav-link" href="/store">Catalog</a>
                <#if isAdmin>
                <a class="nav-item nav-link" href="/admin">Admin</a>
                </#if>
                <#if isSeller>
                <a class="nav-item nav-link" href="/seller">Seller</a>
                </#if>

            <#if isAuthorize>
                <a class="nav-item nav-link outline" href="/profile">
                    <img src="<#if user.imgName??>${user.imgName}<#else>/file/default.png</#if>" class="icon-xs-r">
                    <span class="font-weight-bold">${user.username}</span>
                </a>
                <form class="form-inline ml-2 float-right" action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-danger" type="submit">Sign Out</button>
                </form>
            <#else>
                <div class="form-inline mr-2 ">
                    <a class="btn btn-outline-success" href="/login">Log in</a>
                </div>
                <div class="form-inline mr-2 float-right">
                    <a class="btn btn-outline-info" href="/register">Register</a>
                </div>
            </#if>
            </div>
        </div>
    </nav>
</header>
