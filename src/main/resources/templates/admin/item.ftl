<#import "common.ftl" as common>

<@common.page "Item Edit" 0>

    <#if message??><h5 class="alert alert-success my-2">${message}</h5></#if>

    <form class="need-validation" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if item??&&item.imgName??>
            <input type="hidden" name="imgName" value="${item.imgName}">
        </#if>
        <div class="form-row mb-2">
            <div class="col">
                <label for="name">Title</label>
                <input type="text" class="form-control" id="name"
                       name="name" placeholder="Edit title..." required
                        <#if item??>value="${item.name}"</#if>
                >
            </div>

            <div class="col">
                <label for="price">Price</label>

                <input type="number" class="form-control"
                       name="price" id="price"
                        <#if (item??)&&(item.price??)>value="${item.priceS}"</#if>
                >
            </div>
        </div>
        <div class="form-group input-group">
            <#if categories??>
                <#list categories as c>
                    <#if c??>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="category" id="cat_${c}" value="${c}">
                        <label class="form-check-label" for="cat_${c}">${c}</label>
                    </div>
                    </#if>
                </#list>
            </#if>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="category" id="newCatR" value="">
                <label class="form-check-label" for="newCatR">
                    <input class="form-control" type="text" placeholder="new cat" id="newCatInp">
                </label>
                <script>
                    var newCatR = document.getElementById('newCatR');
                    var newCatInp = document.getElementById('newCatInp');
                    newCatInp.onkeyup = newCatInp.onkeydown = newCatInp.onkeypress = function (ev) {
                        newCatR.value = newCatInp.value
                    }
                </script>
            </div>
        </div>

        <div class="form-group input-group">
            <input class="form-control" type="text" placeholder="New tag" id="tagInp">

        </div>
        <div class="form-group input-group" id="tagField">
        </div>
        <script>
            var tagInp = $('#tagInp');
            var tagField = $('#tagField');
            tagInp.keypress(function (ev) {
                if (ev.key === 'Enter') {
                    ev.preventDefault();
                    console.log(this);
                    var val = this.value;
                    this.value = '';
                    console.log(val);
                    appendTag(val, tagField);
                }
            });

            function createTag(tag) {
                return $('<span>', {class: 'alert alert-info p-1 m-1'})
                        .text(tag)
                        .append($('<input type="hidden" name="tags">').val(tag))
                        .append(
                                $('<span>', {class: 'text-danger m-1', style: "cursor: pointer    "})
                                        .text('x')
                                        .click(function () {
                                            this.parentElement.remove();
                                        }));
            }

            function appendTag(tag, field) {
                field.append(
                        createTag(tag)
                )
            }


        </script>
        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="fileInpAddon">Picture</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="fileInp" name="file"
                       aria-describedby="fileInpAddon" onchange="validateFileSize(this,2)">
                <label class="custom-file-label" for="fileInp">Choose file</label>
            </div>
        </div>

        <div class="form-group">
            <label for="txtArea">Description</label>
            <textarea class="form-control" id="txtArea" name="description" maxlength="4096" rows="6"
                      placeholder="Description"><#if item??&&(item.description??)>${item.description}</#if></textarea>
        </div>

        <div class="form-group input-group">
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Upload</button>
            </div>
        </div>
    </form>



</@common.page>