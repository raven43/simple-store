<#import "../parts/common.ftl" as common>

<#macro page title tab>
    <@common.page title>
        <@admin_nav tab></@admin_nav>
        <div class="px-3">
            <#nested>
        </div>
    </@common.page>

</#macro>

<#macro admin_nav tab>
    <div class="card-header mb-3">
        <ul class="nav nav-tabs card-header-tabs">
            <li class="nav-item">
                <a class="nav-link${(tab==0)?string(" active","")}" href="/admin/item">Edit item</a>
            </li>
            <li class="nav-item">
                <a class="nav-link${(tab==1)?string(" active","")}" href="/admin/users">Edit Users</a>
            </li>
        </ul>
        <#nested>
    </div>
</#macro>

<#macro user_list_item usr>

    <div class="row my-2 mx-0">
        <div class="col-auto">
            <a href="/admin/users/${usr.id}">
                <img src="<#if usr.imgName??>${usr.imgName}<#else>/file/default.png</#if>" class="icon-sm">
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h5 class="card-header py-2">
                    <a href="/admin/users/${usr.id}">${usr.username}</a>
                </h5>
                <div class="card-body w-100 text-muted py-2">
                <#list usr.authorities as role>${role}<#sep >, </#list>
                </div>
            </div>
        </div>
    </div>

</#macro>

<#macro user_edit_view usr roles>

    <div class="row my-2">
        <div class="col-auto">
            <a href="/admin/users/${usr.id}">
                <img src="<#if usr.imgName??>${usr.imgName}<#else>/file/default.png</#if>" class="icon-lg">
            </a>

        </div>
        <div class="col">
            <div class="card">
                <h3 class="card-header py-2">
                    <a href="/profile/${usr.id}">${usr.username}</a>
                </h3>
                <div class="card-body w-100 py-2">

                    <form method="post">
                        <div class="form-group">
                            <#list roles as role>
                                <div class="form-check">
                                    <input id="role_${role}" name="authorities[]"
                                           class="form-check-input<#if (role=="CLIENT")> disabled</#if>" type="checkbox"
                                           value="${role}" ${usr.authorities?seq_contains(role)?string("checked","")}
                                    >
                                    <label class="form-check-label" for="role_${role}">
                                        ${role}
                                    </label>
                                </div>
                            </#list>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                <#list usr.authorities as role>${role}<#sep >, </#list>
                </div>
            </div>
        </div>
    </div>

</#macro>


