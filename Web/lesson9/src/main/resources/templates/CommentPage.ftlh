<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="comm" type="ru.itmo.wp.domain.Comment" -->

<#import "commons.ftlh" as c>

<@c.page>
    <div>
        <#if post??>
            <@c.post post=post/>
            <#if user??>
                <form method="post" action="">
                    <input type="hidden" name="action" value="post/${post.id}"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text"></textarea>
                        </div>

                    </div>
                    <div class="button-field">
                        <input type="submit" value="Comment">
                    </div>
                </form>
            </#if>
            <div></div>
            <#if post.comments??>
                <#list post.comments as comment>
                    <div class="comment">
                        <div class="comment-header">${comment.user.login}</div>
                        <div style="margin-left: 1rem">${comment.text}</div>
                    </div>
                </#list>
            </#if>
        <#else>
            <div>No such post</div>
        </#if>
    </div>
</@c.page>
