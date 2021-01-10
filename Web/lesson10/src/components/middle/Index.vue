<template>
    <div>
        <Post v-for="post in posts" :post="post" :comments="viewComments(post)" :name="users[post.userId].name" :key="post.id"/>
    </div>

</template>

<script>

import Post from "@/components/middle/Post";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "comments", "users"],
    methods: {
        viewComments: function (post) {
            if(post.id in this.comments) {
                return this.comments[post.id];
            }
            return 0;
        }
    }
}
</script>

<style scoped>

</style>