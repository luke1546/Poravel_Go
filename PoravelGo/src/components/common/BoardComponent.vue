<script setup>
import { ref } from "vue";
const BoardList = ref([]);
BoardList.value.push(
    "data:image/png;base64,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"
);
BoardList.value.push(
    "https://mblogthumb-phinf.pstatic.net/MjAyMTA1MjJfMTQ3/MDAxNjIxNjk0MzAzMTc0.s2cL92k0nFa1FWlBgGEcJWxZfB4lcVKamP46OGc8ElEg.-IyOQTozSPhuT9i__MEfhniNOnWAu-D_rcjYnfb7iAcg.JPEG.190208/Internet%EF%BC%BF20210522%EF%BC%BF233751%EF%BC%BF4.jpeg?type=w800"
);

const arr = ref([]);
arr.value.push("12월 20일 pvp모드 open");
arr.value.push("12월 10일 화상채팅 open");
const condition = ref(1);
const like = ref(false);
const big = ref(false);
function grow() {
    big.value = !big.value;
}

function paging(i) {
    condition.value += i;
    if (condition.value >= BoardList.value.length) condition.value %= BoardList.value.length;
    if (condition.value < 0) condition.value += BoardList.value.length;
    big.value = !big.value;
}

function addLike() {
    like.value = !like.value;
    big.value = !big.value;
}
</script>

<template>
    <article class="movie-card" :class="{ big: big == true }" @click="grow()">
        <div v-for="(board, index) in BoardList" :key="index">
            <div class="icons" @click="addLike()">
                <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="white"
                    xmlns="http://www.w3.org/2000/svg"
                    :class="{ heart: like === true }"
                >
                    <path
                        d="M12.7439 22.3037L11.2939 20.9837C6.1439 16.3137 2.7439 13.2237 2.7439 9.45374C2.7439 6.36374 5.1639 3.95374 8.2439 3.95374C9.9839 3.95374 11.6539 4.76374 12.7439 6.03374C13.8339 4.76374 15.5039 3.95374 17.2439 3.95374C20.3239 3.95374 22.7439 6.36374 22.7439 9.45374C22.7439 13.2237 19.3439 16.3137 14.1939 20.9837L12.7439 22.3037Z"
                    ></path>
                </svg>
                <!-- <svg
                    width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M7.1439 21.3537C6.81056 21.4871 6.4939 21.4581 6.1939 21.2667C5.8939 21.0754 5.7439 20.7961 5.7439 20.4287V5.95374C5.7439 5.40374 5.9399 4.93274 6.3319 4.54074C6.7239 4.14874 7.19456 3.95307 7.7439 3.95374H17.7439C18.2939 3.95374 18.7649 4.14974 19.1569 4.54174C19.5489 4.93374 19.7446 5.4044 19.7439 5.95374V20.4287C19.7439 20.7954 19.5939 21.0747 19.2939 21.2667C18.9939 21.4587 18.6772 21.4877 18.3439 21.3537L12.7439 18.9537L7.1439 21.3537Z"
                        fill="white"></path>
                </svg>
                <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M9.7439 19.9537V17.9537H21.7439V19.9537H9.7439ZM9.7439 13.9537V11.9537H21.7439V13.9537H9.7439ZM9.7439 7.95374V5.95374H21.7439V7.95374H9.7439ZM5.7439 20.9537C5.1939 20.9537 4.7229 20.7577 4.3309 20.3657C3.9389 19.9737 3.74323 19.5031 3.7439 18.9537C3.7439 18.4037 3.9399 17.9327 4.3319 17.5407C4.7239 17.1487 5.19456 16.9531 5.7439 16.9537C6.2939 16.9537 6.7649 17.1497 7.1569 17.5417C7.5489 17.9337 7.74457 18.4044 7.7439 18.9537C7.7439 19.5037 7.5479 19.9747 7.1559 20.3667C6.7639 20.7587 6.29323 20.9544 5.7439 20.9537ZM5.7439 14.9537C5.1939 14.9537 4.7229 14.7577 4.3309 14.3657C3.9389 13.9737 3.74323 13.5031 3.7439 12.9537C3.7439 12.4037 3.9399 11.9327 4.3319 11.5407C4.7239 11.1487 5.19456 10.9531 5.7439 10.9537C6.2939 10.9537 6.7649 11.1497 7.1569 11.5417C7.5489 11.9337 7.74457 12.4044 7.7439 12.9537C7.7439 13.5037 7.5479 13.9747 7.1559 14.3667C6.7639 14.7587 6.29323 14.9544 5.7439 14.9537ZM5.7439 8.95374C5.1939 8.95374 4.7229 8.75774 4.3309 8.36574C3.9389 7.97374 3.74323 7.50307 3.7439 6.95374C3.7439 6.40374 3.9399 5.93274 4.3319 5.54074C4.7239 5.14874 5.19456 4.95307 5.7439 4.95374C6.2939 4.95374 6.7649 5.14974 7.1569 5.54174C7.5489 5.93374 7.74457 6.4044 7.7439 6.95374C7.7439 7.50374 7.5479 7.97474 7.1559 8.36674C6.7639 8.75874 6.29323 8.9544 5.7439 8.95374Z"
                        fill="white"></path>
                </svg> -->
            </div>

            <div class="disabled" :class="{ active: condition == index }">
                <img :src="board" alt="Avatar wallpaper" />
                <div class="content">
                    <h1>공지사항</h1>

                    <p class="synopsis">{{ arr[index] }}</p>
                </div>
                <button id="toggle" class="toggle1" type="button" @click="paging(1)">
                    <svg
                        id="chevron"
                        class="fill-primary-5"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                    >
                        <title>chevron</title>
                        <path d="M7.41,15.41L12,10.83L16.59,15.41L18,14L12,8L6,14L7.41,15.41Z" />
                    </svg>
                </button>
                <button
                    id="toggle"
                    :class="{ hide: big == false }"
                    class="toggle2"
                    type="button"
                    @click="paging(-1)"
                >
                    <svg
                        id="chevron"
                        class="fill-primary-5"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                    >
                        <title>chevron</title>
                        <path d="M7.41,15.41L12,10.83L16.59,15.41L18,14L12,8L6,14L7.41,15.41Z" />
                    </svg>
                </button>
            </div>
        </div>
    </article>
</template>

<style scoped>
@import url("../../../dist/output.css");
/* Global CSS and Sass mixins defined in codepen.io/jsulpis/pen/ExdxLgM */

.movie-card {
    --transition-duration: 700ms;
    color: white;
    position: relative;
    border-radius: 0.6em;
    overflow: hidden;
    font-size: 120%;
    width: 50px;
    height: 50px;
    aspect-ratio: 16/9;
    box-shadow: var(--shadow-lg), var(--shadow-lg), var(--shadow-lg);
    transition: transform var(--transition-duration);
    opacity: 0.7;
    transition: all 0.2s linear;
}

.big {
    height: min(17em, 75vh);
    width: min(30em);
    max-width: 90vw;
    opacity: 1;
}

.hide {
    display: none;
}

.movie-card::after {
    content: "";
    position: absolute;
    inset: 0;
    background: radial-gradient(circle at 80% -150%, transparent 60%, rgba(0, 0, 0, 0.5));
    transition: box-shadow var(--transition-duration);
    mix-blend-mode: overlay;
}

img {
    height: 100%;
    width: 100%;
    transition: transform var(--transition-duration);
    object-fit: cover;
    object-position: center;
}

.content {
    z-index: 1;
    position: absolute;
    bottom: min(2.5em, 6vmin);
    left: min(3em, 8vmin);
    right: min(3em, 8vmin);
    text-align: left;
    transition: transform var(--transition-duration);
}
.content > * {
    position: relative;
    will-change: transform;
}

h1 {
    font-size: 2.4em;
    color: inherit;
    margin: 0;
}

.synopsis {
    font-size: 1.2em;
    line-height: 1.4;
    margin-block: 0.75em;
    transition-delay: calc(var(--transition-duration) / 8);
    transition-property: opacity, transform;
    transition-duration: calc(var(--transition-duration) / 2);
}

.icons {
    position: absolute;
    top: 5%;
    right: 5%;
    z-index: 50;
    /* display: flex; */
    /* gap: 0.5em; */
}
.icons svg {
    height: 1.2em;
    width: auto;
    transition-property: opacity, transform;
    transition-duration: calc(var(--transition-duration) / 2);
    transition-delay: 0s;
}
.icons svg:hover {
    cursor: pointer;
}

.movie-card:hover {
    transform: scale(1.03);
}
.movie-card:hover img {
    transform: scale(1.03);
}
.movie-card:hover .synopsis {
    transition-duration: var(--transition-duration);
    transition-delay: calc(var(--transition-duration) / 3);
}
.movie-card:hover .icons svg:nth-child(1) {
    transition-delay: calc(var(--transition-duration) / 1.8);
}
.movie-card:hover .icons svg:nth-child(2) {
    transition-delay: calc(var(--transition-duration) / 1.6);
}
.movie-card:hover .icons svg:nth-child(3) {
    transition-delay: calc(var(--transition-duration) / 1.4);
}

.movie-card:not(:hover) .synopsis {
    opacity: 0;
    transform: translateY(1em);
}
.movie-card:not(:hover) .icons svg {
    opacity: 0;
    transform: translateY(50%);
}
.movie-card:not(:hover) .content {
    transform: translateY(calc(100% - 4.5em));
}

.toggle1 {
    left: calc(100% - 45px);
    transform: rotate(90deg);
}
.toggle2 {
    transform: rotate(270deg);
}

#toggle {
    z-index: 30;
    position: absolute;
    top: 45%;
    width: 50px;
    transition: transform 0.5s ease-in-out;
    fill: white;
}

.disabled {
    display: none;
}

.active {
    display: block;
}

.heart {
    fill: red;
}
p {
    color: green;
}
h1 {
    color: green;
}
</style>
