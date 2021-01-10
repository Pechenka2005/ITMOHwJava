<template>
    <div id="app">
        <Header :userId="userId" :users="users"/>
        <Middle :posts="posts" :users="users" :comments="comments"/>
        <Footer :countUsers="Object.keys(users).length" :countPosts="Object.keys(posts).length" />
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return this.$root.$data;
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }

            const users = Object.values(this.users).filter(u => u.login === login);
            if (users.length === 0) {
                this.$root.$emit("onEnterValidationError", "No such user");
            } else {
                this.userId = users[0].id;
                this.$root.$emit("onChangePage", "Index");
            }
        });
        this.$root.$on("onRegister", (login, name) => {
            let invalid = false;
            for (let i = 0; i < login.length; i++) {
                if (login[i] < 'a' || login[i] > 'z') {
                    invalid = true;
                    break;
                }
            }
            if(login.length < 3 || login.length > 16) {
                this.$root.$emit("onRegisterValidationError", "Login must be from 3 to 16 characters");
            } else if (invalid) {
                this.$root.$emit("onRegisterValidationError", "login contains characters that are not lowercase latin letters");
            } else if (name.length < 1 || name.length > 32){
                this.$root.$emit("onRegisterValidationError", "Name must be from 1 to 32 characters");
            } else {
                let arr = Object.values(this.users).map(value => value.login);
                if(arr.includes(login)) {
                    this.$root.$emit("onRegisterValidationError", "login already exist");
                } else {
                    const id = Math.max(...Object.keys(this.users)) + 1;
                    this.$root.$set(this.users, id, {
                        id, login, name, admin: false
                    });
                    this.$root.$emit("onChangePage", "Index");
                }
            }
        });

        this.$root.$on("onLogout", () => this.userId = null);

        this.$root.$on("onWritePost", (title, text) => {
            if (this.userId) {
                if (!title || title.length < 5) {
                    this.$root.$emit("onWritePostValidationError", "Title is too short");
                } else if (!text || text.length < 10) {
                    this.$root.$emit("onWritePostValidationError", "Text is too short");
                } else {
                    const id = Math.max(...Object.keys(this.posts)) + 1;
                    this.$root.$set(this.posts, id, {
                        id, title, text, userId: this.userId
                    });
                }
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        });

        this.$root.$on("onEditPost", (id, text) => {
            if (this.userId) {
                if (!id) {
                    this.$root.$emit("onEditPostValidationError", "ID is invalid");
                } else if (!text || text.length < 10) {
                    this.$root.$emit("onEditPostValidationError", "Text is too short");
                } else {
                    let posts = Object.values(this.posts).filter(p => p.id === parseInt(id));
                    if (posts.length) {
                        posts.forEach((item) => {
                            item.text = text;
                        });
                    } else {
                        this.$root.$emit("onEditPostValidationError", "No such post");
                    }
                }
            } else {
                this.$root.$emit("onEditPostValidationError", "No access");
            }
        });
    }
}
</script>

<style>
#app {

}
</style>
