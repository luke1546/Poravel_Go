<script setup>
import { RouterView } from "vue-router";
import { useMemberStore } from "@/stores/member.js";
import { storeToRefs } from "pinia";
import { onMounted } from "vue";

import Header from "@/components/layout/TheHeader.vue";
import Nav from "@/components/layout/NavBar.vue";

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);

onMounted(() => {
    if (!sessionStorage.getItem("accessToken")) {
        isLogin.value = false;
    }
});
</script>

<template>
    <div id="container">
        <Header v-if="!isLogin" />
        <RouterView />
        <Nav v-if="isLogin" />
    </div>
</template>

<style scope></style>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    /* cursor: url(./assets/img/ic_mouse.svg), auto; */
}
body {
    background-color: rgba(255, 255, 192, 0.1);
}
/* scroll setting */
::-webkit-scrollbar {
    width: 5px;
    height: 8px;
}

::-webkit-scrollbar-thumb {
    background: #666;
    border-radius: 20px;
}

::-webkit-scrollbar-track {
    background: #ddd;
    border-radius: 20px;
}

:root {
    /* weather widget */
    --num: 20;
    --gradient: linear-gradient(to top, #a3bded 40%, #6991c7 100%);

    /* tab bar */
    --background-color: #bbdefb;
    --blue-50: #e3f2fd;
    --blue-100: #bbdefb;
    --blue-A700: #2962ff;
    --green-50: #e8f5e9;
    --green-100: #c8e6c9;
    --green-A700: #00c853;
    --purple-50: #f3e5f5;
    --purple-100: #e1bee7;
    --purple-A700: #aa00ff;
    --orange-50: #fff3e0;
    --orange-100: #ffe0b2;
    --orange-A700: #ff6d00;
    --orange-700: #f57c00;
    --grey-900: #212121;
    --white: #ffffff;
    --round-button-active-color: #212121;
    --translate-main-slider: 712%;
    --main-slider-color: #e8f5e9;
    --translate-filters-slider: 0;
    --filters-container-height: 3.8rem;
    --filters-wrapper-opacity: 0;

    /* chat page */
    --body-bg-color: #e5ecef;
    --theme-bg-color: #fff;
    --settings-icon-hover: #9fa7ac;
    --developer-color: #f9fafb;
    --input-bg: #f8f8fa;
    --input-chat-color: #a2a2a2;
    --border-color: #eef2f4;
    --body-font: "Manrope", sans-serif;
    --body-color: #273346;
    --settings-icon-color: #c1c7cd;
    --msg-message: #969eaa;
    --chat-text-bg: #f1f2f6;
    --theme-color: #0086ff;
    --msg-date: #c0c7d2;
    --button-bg-color: #f0f7ff;
    --button-color: var(--theme-color);
    --detail-font-color: #919ca2;
    --msg-hover-bg: rgba(238, 242, 244, 0.4);
    --active-conversation-bg: linear-gradient(
        to right,
        rgba(238, 242, 244, 0.4) 0%,
        rgba(238, 242, 244, 0) 100%
    );
    --overlay-bg: linear-gradient(
        to bottom,
        rgba(255, 255, 255, 0) 0%,
        rgba(255, 255, 255, 1) 65%,
        rgba(255, 255, 255, 1) 100%
    );
    --chat-header-bg: linear-gradient(
        to bottom,
        rgba(255, 255, 255, 1) 0%,
        rgba(255, 255, 255, 1) 78%,
        rgba(255, 255, 255, 0) 100%
    );
}
</style>
