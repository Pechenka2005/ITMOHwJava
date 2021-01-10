<template>
    <div class="datatable">
        <div class="caption">Submissions</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <StringTableUser  v-for="user in viewUsers" :user="user" :key="user.id"/>
            </tbody>
        </table>
    </div>
</template>

<script>
    import StringTableUser from "@/components/middle/StringTableUser";

    export default {
        name: "AllUsers",
        components: {StringTableUser},
        props: ["users"],
        computed: {
            viewUsers : function () {
                return Object.values(this.users).sort((a, b) => a.id - b.id);
            }
        }
    }
</script>

<style scoped>

</style>