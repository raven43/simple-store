<#include "../parts/security.ftl">

<#macro list_item item>

    <div class="card flex-row flex-wrap m-2">
        <div class="card-header border-0">
            <a class="d-block" href="/store/item/${item.id}">
                <img src="<#if item.imgName??>${item.imgName}<#else >/file/default.png</#if>" class="icon-md">
            </a>
        </div>
        <div class="card-block p-2">
            <div>
                <h4 class="card-title d-flex flex-row">
                    <a href="/store/item/${item.id}" class="nav-link">${item.name}</a>
                <#if isAdmin>
                <a href="/admin/item?id=${item.id}" class="btn btn-outline-info">Edit</a>
                </#if>

                </h4>
            </div>
            <div class="card-text">
                <nav class="text-secondary">
                    <#if item.tags?? >
                        <#list item.tags as tag>
                            <a class="nav-link p-0 m-1" href="#">
                                ${tag}
                            </a>
                            <#sep > </#list>
                    </#if>
                </nav>
            </div>
        </div>
    </div>

</#macro>

<#macro item_view item inSC>

    <div class="row m-0">
        <div class="col-auto">
            <#if item.imgName??>
                <img src="${item.imgName}" class="icon-lg">
            <#else >
            <img src="/file/default.png" class="icon-lg">
            </#if>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header py-2 d-flex justify-content-between">
                    <h4 class="card-title m-0">
                        Price: ${item.price}
                        <#if isAdmin>
                        <a href="/admin/item?id=${item.id}" class="btn btn-outline-info">Edit</a>
                        </#if>
                    </h4>
                    <div class="btn-group">
                        <#if isAuthorize>
                            <input class="btn btn-outline-success"
                                   id="itemNumber" type="number" min="1" max="99"
                                   value="1">
                            <button class="btn btn-outline-success" id="buy">Buy</button>
                        </#if>
                    </div>
                </div>
                <script>

                    $('#buy').click(function (ev) {
                        ev.preventDefault();
                        var itemNumber = $('#itemNumber').val();
                        addToShopCart(${item.id}, '${_csrf.token}', itemNumber, function () {
                            location.reload();
                            //$('#buy').toggleClass('btn-outline-success').toggleClass('btn-success');
                        });
                    })
                </script>
                <div class="card-body">
                    <h5 class="text-muted"><#if inSC>In shop cart</#if></h5>

                    <#if item.description??>
                        <div class="card-text mb-3">${item.description}</div>                     </#if>

                    <#if item.tags??>
                        <div class="card-text">
                            <#list item.tags as tag>${tag}<#sep > </#list>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>

</#macro>

<#macro view_nav type id tab>

    <ul class="nav nav-tabs card-header-tabs">
        <li class="nav-item">
            <a class="nav-link${(tab==0)?string(" active","")}" href="/${type}/${id}">View</a>
        </li>
        <li class="nav-item">
            <a class="nav-link${(tab==1)?string(" active","")}" href="/${type}/${id}/comments">Comments</a>
        </li>
    </ul>

</#macro>

<#macro comment comment>

    <div class="row my-2 mx-0" id="comment${comment.id}">
        <div class="col-auto">
            <#if comment.user.imgName??>
                <a href="/profile/${comment.user.id}"><img src="${comment.user.imgName}" class="icon-sm-r"></a>
            <#else >
            <img src="/file/default.png" class="icon-sm-r">
            </#if>
        </div>
        <div class="col">
            <div class="card">
                <h5 class="card-header py-2">
                    <a href="/profile/${comment.user.id}">${comment.user.username}</a>
                    <#if isAuthorize&&(isAdmin||(user.id==comment.user.id))>
                    <span id="del${comment.id}" style="text-align: right; cursor: pointer"
                          onclick="deleteComment(${comment.id},'${_csrf.token}')">❌</span>
                    </#if>
                </h5>
                <script>
                </script>
                <div class="card-body">
                    <p class="card-text">${comment.text}</p>
                </div>
                <div class="card-footer w-100 text-muted py-2">
                    ${comment.date?datetime}
                </div>
            </div>
        </div>
    </div>
</#macro>