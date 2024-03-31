<script setup>
import { useChatClose } from "../../stores/counter.js";
const cc = useChatClose();
const mode = ref(false);
const items = ref([{}]);
const activeNo = ref(0);
function changeMode() {
    mode.value = !mode.value;
}

function changeState() {
    cc.isClosed.value = !cc.isClosed.value;
}

function addRoom() {
    items.value.push({});
}

function activeChange(i) {
    activeNo.value = i;
}

const getDarkImageUrl = (name) => {
    return new URL(`../../assets/img/pokemon/${name}.gif`, import.meta.url).href;
};
</script>
<script>
import axios from "axios";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import chattest from "../common/chattest.vue";

import { ref, onMounted } from "vue";
import { useChatClose } from "../../stores/counter.js";
import { jwtDecode } from "jwt-decode";

export default {
    name: "App",

    setup() {
        const senderType = ref(1);
        const cc = useChatClose();

        // const mode = ref(false);
        chattest.connect();

        return {
            senderType,
        };
    },
    data() {
        const userId = ref();
        const sc = ref(null);
        sc.scrollTop = sc.scrollHeight;
        if (jwtDecode(sessionStorage.getItem("accessToken")).userId) {
            userId.value = ref(jwtDecode(sessionStorage.getItem("accessToken")).userId);
        }
        return {
            sc,
            userId,
            userName: userId.value,
            message: "",
            recvList: [],
        };
    },
    created() {
        // App.vue가 생성되면 소켓 연결을 시도합니다.
        this.connect();
        // this.findAllRoom();
    },
    methods: {
        connect() {
            const serverURL = "http://localhost/poravel/chat";
            let socket = new SockJS(serverURL);

            console.dir(socket);
            this.stompClient = Stomp.over(socket);

            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
            this.stompClient.connect(
                {},
                (frame) => {
                    // 소켓 연결 성공
                    this.connected = true;
                    console.log("소켓 연결 성공", frame);
                    // 서버의 메시지 전송 endpoint를 구독합니다.
                    // 이런형태를 pub sub 구조라고 합니다.
                    this.stompClient.subscribe("/sub/chat/room/null", (res) => {
                        console.log("구독으로 받은 메시지 입니다.", res.body);
                        console.log(res.headers["message-id"]);
                        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                        this.recvList.push(JSON.parse(res.body));
                    });
                    // 채팅방 입장 메세지
                    // "/pub/chat/sendMessage", JSON.stringify(msg), {};
                    // this.stompClient.send(
                    //     "/pub/chat/enterUser",
                    //     JSON.stringify({
                    //         roomId: this.roomId,
                    //         sender: this.userName,
                    //         type: "ENTER",
                    //     }),
                    //     {}
                    // );
                },
                (error) => {
                    // 소켓 연결 실패
                    console.log("소켓 연결 실패", error);
                    this.connected = false;
                }
            );
        },
        // room method ---------------------------------

        // message -----------------------------------
        sendMessage(e) {
            console.log(1223);
            if (e.keyCode === 13 && this.userName !== "" && this.message !== "") {
                this.send();
                this.message = "";
            }
        },

        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const msg = {
                    sender: this.userName,
                    message: this.message,
                };
                this.stompClient.send("/pub/chat/sendMessage", JSON.stringify(msg), {});
            }
        },
    },
};
</script>
<template>
    <!-- <div id="app">
        유저이름:
        <input v-model="userName" type="text" />
        내용: <input v-model="message" type="text" @keyup="sendMessage" />
        <div v-for="(item, idx) in recvList" :key="idx">
            <h3>유저이름: {{ item.sender }}</h3>
            <h3>내용: {{ item.message }}</h3>
        </div>
    </div> -->

    <div id="app" :class="{ 'dark-mode': mode === true }">
        <div id="top">
            <div class="user-settings">
                <div class="dark-light" @click="changeMode()">
                    <svg
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        stroke-width="1.5"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                    >
                        <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z" />
                    </svg>
                </div>
                <div><button id="clsChat" @click="cc.changeState()"></button></div>
            </div>
        </div>
        <div class="wrapper">
            <div class="conversation-area">
                <!-- <div v-for="(item, index) in recvList" :key="index">
                    <div
                        class="msg"
                        :class="{ active: activeNo == index }"
                        @click="activeChange(index)"
                    >
                        <img
                            class="msg-profile"
                            src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3364143/download+%2812%29.png"
                            alt=""
                        />
                        <div class="msg-detail">
                            <div class="msg-username" :class="{ whiteFont: mode === true }">
                                채팅 id
                            </div>
                            <div class="msg-content">
                                <span class="msg-date">20m</span>
                            </div>
                        </div>
                    </div>
                </div> -->

                <button class="add" @click="addRoom()"></button>
                <div class="overlay"></div>
            </div>
            <div class="chat-area">
                <div class="chat-area-header">
                    <div class="chat-area-title" :class="{ whiteFont: mode === true }">
                        이대영, 최소영
                    </div>
                    <div class="chat-area-group">
                        <span>+4</span>
                    </div>
                </div>
                <div class="chat-area-main" ref="sc">
                    <div v-for="(item, idx) in recvList" :key="idx">
                        <div class="chat-msg" :class="{ owner: item.sender == '1' }">
                            <div class="chat-msg-profile">
                                <img
                                    class="chat-msg-img"
                                    :src="getDarkImageUrl(item.sender)"
                                    alt=""
                                />
                                <div class="chat-msg-date">
                                    {{ new Date().getHours() + ":" + new Date().getMinutes() }}
                                </div>
                            </div>
                            <div class="chat-msg-content">
                                <div class="chat-msg-text">{{ item.message }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="chat-area-footer">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="1.5"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-video"
                    >
                        <path d="M23 7l-7 5 7 5V7z" />
                        <rect x="1" y="5" width="15" height="14" rx="2" ry="2" />
                    </svg>

                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="1.5"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-paperclip"
                    >
                        <path
                            d="M21.44 11.05l-9.19 9.19a6 6 0 01-8.49-8.49l9.19-9.19a4 4 0 015.66 5.66l-9.2 9.19a2 2 0 01-2.83-2.83l8.49-8.48"
                        />
                    </svg>
                    <input
                        id="msg"
                        type="text"
                        v-model="message"
                        @keyup="sendMessage"
                        autocomplete="off"
                        placeholder="Type something here..."
                    />
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="1.5"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-smile"
                    >
                        <circle cx="12" cy="12" r="10" />
                        <path d="M8 14s1.5 2 4 2 4-2 4-2M9 9h.01M15 9h.01" />
                    </svg>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
@import url("https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css");
@import url("@/assets/css/chat.css");
.whiteFont {
    color: white;
}
.chat-msg-img {
    width: 40px;
    overflow: visible;
}
</style>
