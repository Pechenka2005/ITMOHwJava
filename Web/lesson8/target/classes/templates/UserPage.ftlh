
<#import "commons.ftlh" as c>

<@c.page>
    <div class="user datatable">
        <#if userInfo??>
            <div>id : ${userInfo.id}</div>
            <div>login : ${userInfo.login}</div>
            <div>creationTime : ${userInfo.creationTime}</div>
        <#else>
            <div>No such user</div>
        </#if>
    </div>
</@c.page>
