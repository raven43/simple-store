<#import "../parts/common.ftl" as common>
<#import "common.ftl" as profile>
<#include "../parts/security.ftl">
<@profile.page user.username 0>

    <img src="<#if user.imgName??>${user.imgName}<#else >/file/default.png</#if>" class="icon-md">

    <h1 class="d-inline-block">${user.username}</h1>
    <#if shopCart?? && (shopCart?size>0)>
    <h5>Shop Cart</h5>
    <table class="table table-striped table-bordered table-hover table">
        <thead>
        <tr>
            <td>Item name</td>
            <td>Price</td>
            <td>Number</td>
            <td>Cost</td>
            <td>Del</td>
        </tr>
        </thead>
        <tbody>
        <#list shopCart as pos>
        <tr>
            <td>
                <a href="/store/item/${pos.item.id}">${pos.item.name}</a>
            </td>
            <td>${pos.item.price}</td>
            <td>${pos.number}</td>
            <td>${pos.getCost()}</td>
            <td>
                <button class="btn btn-outline-danger" id="p${pos.id}">x
                </button>
                <script>
                    $('#p${pos.id}').click(function () {
                        var self = this;
                        deleteFromShopCart(${pos.id},'${_csrf.token}',function (v) {
                            console.log("HERE");
                            console.log(self.parentElement.parentElement);
                            self.parentElement.parentElement.remove();
                            location.reload();
                        });
                    });
                </script>
            </td>
        </tr>
        </#list>

        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td></td>
            <td>Summ:</td>
            <td>${summ}</td>
            <td></td>
        </tr>

        </tfoot>
    </table>

    </#if>

<#--<#if  !shopCart?? || shopCart?size==0>-->

<div class="d-flex justify-content-center">
    <button class="btn btn-info" id="q">Proceed to checkout</button>
</div>
<script>
    $('#q').click(function () {
        $('#al').toggle('slow');
    });
</script>
<h3 class="m-2 alert alert-info" style="display: none;" id="al">
    Manager will contact you soon!
</h3>
<#--</#if>-->



</@profile.page>