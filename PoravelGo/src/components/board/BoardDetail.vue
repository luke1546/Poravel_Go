<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useBoard } from "../../stores/counter"
import { RouterLink, useRouter } from 'vue-router'
import { jwtDecode } from "jwt-decode";

const { VITE_BACK_SERVER } = import.meta.env;
const router = useRouter();
const board = ref({});

async function getBoard() {
    board.value = (
        await axios.get(VITE_BACK_SERVER + "board/" + useBoard().no, {
            params: {},
        })
    ).data;
}
getBoard();

async function deleteBoard() {
    await axios.delete(VITE_BACK_SERVER + "board/" + useBoard().no, {});
    router.push("boardList");
}

const userId = ref(jwtDecode(sessionStorage.getItem("accessToken")).userId);
</script>

<template>
    <div>.</div>
    <div class="container">
        <div class="top">
            <hr class="topline" />
            <h1>{{ board.subject }}</h1>
            <div class="titleInfo">
                <div class="user__info">
                    <img
                        class="user__photo"
                        src="https://www.shutterstock.com/image-vector/cute-adorable-vector-illustration-creature-260nw-2313970719.jpg"
                        alt=""
                    />
                    <div>
                        <div style="line-height: 30px" class="user__name">
                            {{ board.userId }}🐦 | {{ board.registerTime }}
                        </div>
                        <div>
                            <progress
                                style="display: inline-block"
                                value="100"
                                max="100"
                            ></progress>
                            151/151
                        </div>
                    </div>
                </div>
                <div class="writingInfo">
                    <div class="hitIcon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                            <path
                                d="M288 80c-65.2 0-118.8 29.6-159.9 67.7C89.6 183.5 63 226 49.4 256c13.6 30 40.2 72.5 78.6 108.3C169.2 402.4 222.8 432 288 432s118.8-29.6 159.9-67.7C486.4 328.5 513 286 526.6 256c-13.6-30-40.2-72.5-78.6-108.3C406.8 109.6 353.2 80 288 80zM95.4 112.6C142.5 68.8 207.2 32 288 32s145.5 36.8 192.6 80.6c46.8 43.5 78.1 95.4 93 131.1c3.3 7.9 3.3 16.7 0 24.6c-14.9 35.7-46.2 87.7-93 131.1C433.5 443.2 368.8 480 288 480s-145.5-36.8-192.6-80.6C48.6 356 17.3 304 2.5 268.3c-3.3-7.9-3.3-16.7 0-24.6C17.3 208 48.6 156 95.4 112.6zM288 336c44.2 0 80-35.8 80-80s-35.8-80-80-80c-.7 0-1.3 0-2 0c1.3 5.1 2 10.5 2 16c0 35.3-28.7 64-64 64c-5.5 0-10.9-.7-16-2c0 .7 0 1.3 0 2c0 44.2 35.8 80 80 80zm0-208a128 128 0 1 1 0 256 128 128 0 1 1 0-256z"
                            />
                        </svg>
                        {{ board.hit }}
                    </div>
                    <div class="likeIcon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                            <path
                                d="M123.6 391.3c12.9-9.4 29.6-11.8 44.6-6.4c26.5 9.6 56.2 15.1 87.8 15.1c124.7 0 208-80.5 208-160s-83.3-160-208-160S48 160.5 48 240c0 32 12.4 62.8 35.7 89.2c8.6 9.7 12.8 22.5 11.8 35.5c-1.4 18.1-5.7 34.7-11.3 49.4c17-7.9 31.1-16.7 39.4-22.7zM21.2 431.9c1.8-2.7 3.5-5.4 5.1-8.1c10-16.6 19.5-38.4 21.4-62.9C17.7 326.8 0 285.1 0 240C0 125.1 114.6 32 256 32s256 93.1 256 208s-114.6 208-256 208c-37.1 0-72.3-6.4-104.1-17.9c-11.9 8.7-31.3 20.6-54.3 30.6c-15.1 6.6-32.3 12.6-50.1 16.1c-.8 .2-1.6 .3-2.4 .5c-4.4 .8-8.7 1.5-13.2 1.9c-.2 0-.5 .1-.7 .1c-5.1 .5-10.2 .8-15.3 .8c-6.5 0-12.3-3.9-14.8-9.9c-2.5-6-1.1-12.8 3.4-17.4c4.1-4.2 7.8-8.7 11.3-13.5c1.7-2.3 3.3-4.6 4.8-6.9c.1-.2 .2-.3 .3-.5z"
                            />
                        </svg>
                        0
                    </div>
                    <div class="commentIcon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                            <path
                                d="M225.8 468.2l-2.5-2.3L48.1 303.2C17.4 274.7 0 234.7 0 192.8v-3.3c0-70.4 50-130.8 119.2-144C158.6 37.9 198.9 47 231 69.6c9 6.4 17.4 13.8 25 22.3c4.2-4.8 8.7-9.2 13.5-13.3c3.7-3.2 7.5-6.2 11.5-9c0 0 0 0 0 0C313.1 47 353.4 37.9 392.8 45.4C462 58.6 512 119.1 512 189.5v3.3c0 41.9-17.4 81.9-48.1 110.4L288.7 465.9l-2.5 2.3c-8.2 7.6-19 11.9-30.2 11.9s-22-4.2-30.2-11.9zM239.1 145c-.4-.3-.7-.7-1-1.1l-17.8-20c0 0-.1-.1-.1-.1c0 0 0 0 0 0c-23.1-25.9-58-37.7-92-31.2C81.6 101.5 48 142.1 48 189.5v3.3c0 28.5 11.9 55.8 32.8 75.2L256 430.7 431.2 268c20.9-19.4 32.8-46.7 32.8-75.2v-3.3c0-47.3-33.6-88-80.1-96.9c-34-6.5-69 5.4-92 31.2c0 0 0 0-.1 .1s0 0-.1 .1l-17.8 20c-.3 .4-.7 .7-1 1.1c-4.5 4.5-10.6 7-16.9 7s-12.4-2.5-16.9-7z"
                            />
                        </svg>
                        {{ board.heart }}
                    </div>
                </div>
            </div>
            <hr />
        </div>
        <div class="content">
            {{ board.content }}
        </div>
        <hr />
        <div class="modify">
            <div><button class="register">수정</button></div>
            <div><button class="register" @click="deleteBoard()">삭제</button></div>
        </div>
        <div class="lower">
            <div class="commentContainer">
                <textarea class="comment" name="" id="" cols="30" rows="10"></textarea>
                <button class="register">등록</button>
            </div>
            <hr />
        </div>
    </div>
</template>

<style scoped>
.container {
    width: 1200px;
    margin: auto;
    margin-top: 100px;
}
.titleInfo {
    display: grid;
    grid-template-columns: 1fr 1fr;
}
.user__photo {
    width: 48px;
    height: 48px;
    object-fit: cover;
    border-radius: 100px;
}
.user__info {
    display: flex;
    gap: 12px;
    margin-bottom: 10px;
}
.user__name {
    font-size: 14px;
    font-weight: 600;
    color: #111827;
}
progress {
    display: block;
    width: 100px;
    height: 7px;
    -webkit-appearance: none;
    border-radius: 10px;
}

progress::-webkit-progress-bar {
    background-color: #eee;
    border-radius: 10px;
}

progress::-webkit-progress-value {
    background-color: #10b981;
    border-radius: 10px;
}
.writingInfo {
    justify-self: self-end;
    display: flex;
    gap: 16px;
}
.content {
    margin: 50px 0;
}
.commentContainer {
    width: 100%;
    height: 250px;
    border: 1px solid rgb(0, 0, 0, 0.25);
    border-radius: 15px;
    margin: 50px 0;
}

.comment {
    width: 96%;
    height: 150px;
    border: 1px solid rgb(0, 0, 0, 0.25);
    border-radius: 15px;
    margin: 20px 20px;
    padding-top: 10px;
    padding-left: 10px;
}
.register {
    width: 80px;
    display: block;
    padding: 8px 12px;
    border-radius: 6px;
    background: #6366f1;
    color: #fff;
    text-align: center;
    font-weight: 500;
    text-decoration: none;
    margin-left: calc(100% - 90px);
}
.register:hover {
    background: #3d3eaa;
}
.modify {
    display: flex;
    justify-content: flex-end;
    gap: 90px;
    margin-top: 10px;
}
a {
    text-decoration: none;
}
</style>
