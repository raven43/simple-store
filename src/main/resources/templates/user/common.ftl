<#import "../parts/common.ftl" as common>

<#macro page title tab>

    <@common.page title>
        <div class="card-header mb-3">
            <h1 class="card-title">${title}</h1>
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link${(tab==0)?string(" active","")}" href="/profile">Main</a>
                </li>
                <li class="nav-item disabled">
                    <a class="nav-link${(tab==1)?string(" active","")} disabled" href="/profile/im">Messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link${(tab==2)?string(" active","")}" href="/profile/edit">Edit</a>
                </li>
            </ul>
        </div>
        <div class="px-3">
        <#nested >
        </div>
    </@common.page>

</#macro>

<#macro user_item_view user>

</#macro>

<#macro chat_view chat>

</#macro>

<#macro chat_item_view chat>

</#macro>

<#macro message_incoming message>

</#macro>

<#macro message_outgoing message>

</#macro>