<#import "../parts/common.ftl" as common>
<#import "common.ftl" as view>
<#include "../parts/security.ftl">

<@common.page item.name>
    <div class="card-header mb-3">
        <h1 class="card-title">${item.name}</h1>
        <@view.view_nav "store/item" item.id 0></@view.view_nav>
    </div>
    <@view.item_view item inSC></@view.item_view>
</@common.page>