<#import "parts/common.ftl" as common>
<#import "view/common.ftl" as view>
<@common.wide_page "Simple Store">

    <main class="container">
        <div class="card-deck">
            <#if page??&&page.content??>
                <#list page.content as item>
                <div class="card">
                    <a href="/store/item/${item.id}">

                        <img class="card-img-top"
                             src="<#if item.imgName??>${item.imgName}<#else>/file/default.png</#if>"
                        >
                    </a>
                    <div class="card-body">
                        <a href="/store/item/${item.id}"><h5 class="card-title">${item.name}</h5></a>
                        <p class="card-text">
                            <#if item.description??>
                                <#if (item.description?length>64)>
                                    ${item.description?substring(0,63)}...
                                <#else>
                                    ${item.description}
                                </#if>
                            </#if>
                        </p>
                        <p class="card-footer">
                            <h5>price: ${item.price?if_exists}</h5>
                        </p>
                    </div>
                </div>
                </#list>
            </#if>
        </div>
    </main>

    <#--<div class="card">-->
        <#--<div class="card-header">-->
            <#--<button class="btn btn-info m-1" id="get">get</button>-->
            <#--<button class="btn btn-info m-1" id="categories">categories</button>-->
            <#--<button class="btn btn-info m-1" id="tags">tags</button>-->
            <#--<button class="btn btn-info m-1" id="test">test</button>-->
            <#--<button class="btn btn-secondary m-1" id="post">post</button>-->
        <#--</div>-->
        <#--<form class="form-row" id="itemForm">-->
            <#--<input class="col form-control" type="text" name="name" placeholder="name">-->
            <#--<input class="col form-control" type="text" name="description" placeholder="description">-->
            <#--<input class="col form-control" type="text" name="category" placeholder="category">-->
            <#--<input class="col form-control" type="text" name="tags" placeholder="tags">-->
            <#--<input class="col form-control" type="text" name="tags" placeholder="tags">-->
            <#--<input class="col form-control" type="text" name="tags" placeholder="tags">-->
        <#--</form>-->
        <#--<h5> Search</h5>-->
        <#--<form class="form-row" id="searchForm">-->
            <#--<input class="col form-control" type="text" name="category" placeholder="category">-->
            <#--<input class="col form-control" type="text" name="tags" placeholder="tags">-->
            <#--<input class="col form-control" type="text" name="tags" placeholder="tags">-->
        <#--</form>-->
        <#--<pre class="card-body">-->

        <#--</pre>-->
    <#--</div>-->

    <#--<script>-->
        <#--var token = '${_csrf.token}';-->
        <#--var out = document.getElementsByClassName('card-body')[0];-->
        <#--var itemForm = document.getElementById('itemForm');-->
        <#--var searchForm = document.getElementById('searchForm');-->

        <#--function get() {-->
            <#--fetch("/rest")-->
                    <#--.then(function (v) {-->
                        <#--return v.json();-->
                    <#--})-->
                    <#--.then(function (v) {-->
                        <#--console.log(v);-->
                        <#--out.innerText = JSON.stringify(v, null, 4);-->
                    <#--})-->
                    <#--.catch(function (reason) {-->
                        <#--console.log(reason);-->
                    <#--})-->
        <#--}-->

        <#--function categories() {-->
            <#--fetch("/rest/categories")-->
                    <#--.then(function (v) {-->
                        <#--return v.json();-->
                    <#--})-->
                    <#--.then(function (v) {-->
                        <#--console.log(v);-->
                        <#--out.innerText = JSON.stringify(v, null, 4);-->
                    <#--})-->
                    <#--.catch(function (reason) {-->
                        <#--console.log(reason);-->
                    <#--})-->
        <#--}-->
        <#--function tags() {-->
            <#--var params = new URLSearchParams(new FormData(searchForm)).toString();-->
            <#--console.log("/rest/tags?"+params);-->
            <#--fetch("/rest/tags?"+params)-->
                    <#--.then(function (v) {-->
                        <#--return v.json();-->
                    <#--})-->
                    <#--.then(function (v) {-->
                        <#--console.log(v);-->
                        <#--out.innerText = JSON.stringify(v, null, 4);-->
                    <#--})-->
                    <#--.catch(function (reason) {-->
                        <#--console.log(reason);-->
                    <#--})-->
        <#--}-->
        <#--function test() {-->
            <#--fetch("/rest/test")-->
                    <#--.then(function (v) {-->
                        <#--return v.json();-->
                    <#--})-->
                    <#--.then(function (v) {-->
                        <#--console.log(v);-->
                        <#--out.innerText = JSON.stringify(v, null, 4);-->
                    <#--})-->
                    <#--.catch(function (reason) {-->
                        <#--console.log(reason);-->
                    <#--})-->
        <#--}-->

        <#--function post() {-->

            <#--var data = new FormData(itemForm);-->
            <#--console.log('form_data:');-->
            <#--console.log(data);-->
            <#--fetch('/rest', {-->
                <#--method: 'POST',-->
                <#--credentials: "same-origin",-->
                <#--headers: {-->
                    <#--// 'Content-Type': 'multipart/form-data',-->
                    <#--'X-CSRF-TOKEN': token-->
                <#--},-->
                <#--body: data-->
            <#--})-->
                    <#--.then(function (value) {-->
                        <#--console.log(value);-->
                    <#--})-->
                    <#--.catch(function (reason) {-->
                        <#--console.log(reason);-->
                    <#--});-->
        <#--}-->

        <#--document.getElementById('get').onclick = get;-->
        <#--document.getElementById('categories').onclick = categories;-->
        <#--document.getElementById('tags').onclick = tags;-->
        <#--document.getElementById('test').onclick = test;-->
        <#--document.getElementById('post').onclick = post;-->
    <#--</script>-->

</@common.wide_page>