<#import "../../parts/common.ftl" as common>
<#import "../common.ftl" as view>

<@common.page item.name>
    <div class="card-header mb-3">
        <h1 class="card-title">${item.name}</h1>
        <@view.view_nav "films" item.id 0></@view.view_nav>
    </div>
    <@view.item_view item></@view.item_view>
</@common.page>