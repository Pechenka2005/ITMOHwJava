<template>
    <div>
        <Post v-for="post in sortedPosts" :post="post" :name="post.user.name" :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/middle/Post";
export default {
    name: "Index",
    components: {Post},
    props: ["posts"],
    computed: {
        sortedPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        }
    }
}
</script>

<style scoped>



</style>