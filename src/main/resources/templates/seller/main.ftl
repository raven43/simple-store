<#import "../parts/common.ftl" as common>

<@common.page "Seller">

<div class="p-2">
    <h1>Seller Page</h1>
    <#if page??>
        <table CLASS="table table-hover table-bordered table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>User</td>
                <td>Date</td>
                <td>Item</td>
                <td>Number</td>
                <td>Status</td>
                <td>Cost</td>
            </tr>
            </thead>
            <tbody>
                <#list page.content as order>
                <tr>
                    <td>${order.id}</td>
                    <td>${order.user.username}</td>
                    <td>${order.date?datetime}</td>
                    <td>${order.item.name}</td>
                    <td>${order.number}</td>
                    <td>${order.status?string}</td>
                    <td>${order.getCost()?if_exists}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </#if>

</div>

</@common.page>