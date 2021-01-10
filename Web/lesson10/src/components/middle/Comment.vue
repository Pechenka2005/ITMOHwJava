<template>
    <div class="comment">
        <header class="comment-header">
            userId: {{ comment.userId}}
        </header>
        <div class="comment-middle">
            {{ comment.text }}
        </div>
    </div>
</template>

<script>
    export default {
        name: "Comment",
        props: ["comment"]
    }
</script>

<style scoped>

</style>