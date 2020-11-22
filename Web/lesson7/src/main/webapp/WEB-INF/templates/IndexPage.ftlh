<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <div class="noData"> no data </div>
        <template>
            <div class="article">
                <div class="title"></div>
                <div class="body">

                </div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
                </ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        2 days ago
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </div>
        </template>
    </div>



<script>
    $(function () {
        const $noData = $(".articles .noData");
        $noData.text("Loading...");

        ajax("findAll", function(response) {
            response["articles"].forEach(function(article) {
                $noData.hide();
                const $tempArticle = $($(".articles template").prop("content")).clone();
                $tempArticle.find(".title").text(article["title"]);
                $tempArticle.find(".body").text(article["text"]);
                $(".articles").append($tempArticle);
            });
        })

    })


</script>
</@c.page>
