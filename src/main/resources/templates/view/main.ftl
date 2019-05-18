<#import "../parts/common.ftl" as common>
<#import "common.ftl" as view>

<@common.page "Store">

    <div class="card-header mb-3">
        <h1 class="card-title">Store</h1>

        <ul class="list-group">
            <#list categories as c>
                <#if c??>
                <li class="list-group-item">
                    <a class="nav-link" href="/store/${c?url}">${c}</a>
                </li>
                </#if>
            </#list>
        </ul>
    </div>


</@common.page>