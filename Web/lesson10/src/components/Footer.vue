<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov |
        number of registered users: {{ countUsers }} |
        number of posts : {{ countPosts }}
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["countUsers", "countPosts"]

}
</script>

<style scoped>

</style>