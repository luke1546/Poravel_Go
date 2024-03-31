<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";

import UserLoginModal from "../auth/UserLoginModal.vue";

// login modal
const useModal = ref(false);
const openModal = () => {
    useModal.value = true;
    document.body.style.overflow = "hidden";
};
const closeModal = () => {
    useModal.value = false;
    document.body.style.removeProperty("overflow");
};

const getImageUrl = () => {
    return new URL(`@/assets/imgs/logo.png`, import.meta.url).href;
};
</script>

<template>
    <header class="header">
        <span>
            <RouterLink to="/"><img id="logo" :src="getImageUrl()" alt="" /></RouterLink>
            <span id="menuspace">
                <!-- <router-link to="attractioninfo">Contact</router-link>
                <router-link to="mypage">About</router-link>
                <router-link to="test">Test</router-link> -->
            </span>
        </span>

        <span id="login_space">
            <button id="login-click" @click="openModal">
                <img id="login" src="@/assets/icons/login.png" alt="" />
            </button>
        </span>

        <div class="modal-overlay" v-if="useModal" @click.self="closeModal">
            <UserLoginModal />
        </div>
    </header>
</template>

<style>
* {
    font-family: PokemonGSK2Mono;
}
</style>
<style scoped>
@font-face {
    font-family: "PokemonGSK2Mono";
    src: url("../../assets/font/maple.ttf") format("truetype");
    font-weight: bold;
}
img {
    width: 200px;
}
* {
    font-family: "PokemonGSK2Mono";
    overflow: hidden;
    /* background-color: #333; */
}

.header {
    position: fixed;
    width: 100%;
    padding: 1.5rem;
    background: transparent;
    color: black;
    text-align: center;
    font-size: 30px;
    display: flex;
    justify-content: space-between;
    color: black;
    z-index: 2;
}

a {
    text-decoration: none;
    color: black;
}

a:hover {
    background-color: transparent;
}

#login-click {
    border: none;
    z-index: 3;
    background-color: transparent;
    cursor: pointer;
}

#logo {
    float: left;
}

#login {
    width: 140px;
    height: 60px;
}

#login_space {
    float: right;
}

#menuspace > a {
    margin-left: 50px;
}
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.815);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
}
</style>
