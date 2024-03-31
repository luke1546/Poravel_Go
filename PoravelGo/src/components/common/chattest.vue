<template>
    <div id="app">
        유저이름:
        <input v-model="userName" type="text" />
        내용: <input v-model="message" type="text" @keyup="sendMessage" />
        <div v-for="(item, idx) in recvList" :key="idx">
            <h3>유저이름: {{ item.sender }}</h3>
            <h3>내용: {{ item.message }}</h3>
        </div>
    </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
    name: "ChatCall",
    setup() {
        const difTime = (timeValue) => {
            const time = new Date(timeValue);
            const today = new Date();
            const betweenTime = Math.floor((today.getTime() - time) / 1000 / 60);
            if (betweenTime < 1) return "방금전";
            if (betweenTime < 60) {
                return `${betweenTime}분전`;
            }
            const betweenTimeHour = Math.floor(betweenTime / 60);
            if (betweenTimeHour < 24) {
                return `${betweenTimeHour}시간전`;
            }
            const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
            if (betweenTimeDay < 365) {
                return `${betweenTimeDay}일전`;
            }
            return `${Math.floor(betweenTimeDay / 365)}년전`;
        };
    },
    data() {
        return {
            chatRoomId: "",
            userName: "",
            message: "",
            recvList: [],
        };
    },
    created() {
        // App.vue가 생성되면 소켓 연결을 시도합니다.
        this.connect();
    },
    methods: {
        created() {
            // App.vue가 생성되면 소켓 연결을 시도합니다.
            this.connect();
            // this.findAllRoom();
        },

        // room method ---------------------------------
        createRoom() {
            var params = new URLSearchParams();
            axios
                .post("/chat/createroom", params)
                .then((response) => {
                    alert("방 개설에 성공하였습니다.");
                    this.room_name = "";
                    this.findAllRoom();
                })
                .catch((response) => {
                    alert("채팅방 개설에 실패하였습니다.");
                });
        },

        findAllRooms() {
            axios.get("/chat/rooms").then((response) => {
                this.chatrooms = response.data;
            });
        },

        enterRoom(chattingRoomId) {
            var sender = prompt("대화명을 입력해 주세요.");
            if (sender !== "") {
                localStorage.setItem("wschat.userName", sender);
                localStorage.setItem("wschat.chatRoomId", chattingRoomId);
                location.href = "/chat/room/enter/" + chattingRoomId;
            }
        },

        // message -----------------------------------
        sendMessage(e) {
            if (e.keyCode === 13 && this.userName !== "" && this.message !== "") {
                this.send();
                this.message = "";
            }
        },

        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const msg = {
                    roomId: this.roomId,
                    sender: this.userName,
                    message: this.message,
                    type: "TALK",
                };
                this.stompClient.send("/pub/chat/sendMessage", JSON.stringify(msg), {});
            }
        },

        selectFile() {
            this.img = this.$refs.file.files[0];
            console.log(this.img);
        },

        sendPicture() {
            if (this.stompClient && this.stompClient.connected) {
                const formData = new FormData();
                formData.append("image", this.img);
                formData.append("thumbnail", this.img);
                formData.append("type", "direct");
                formData.append("userId", 1);
                formData.append("channelId", 1);
                axios.post("http://localhost:8000/chat-server/file", formData);
            }
        },

        // const sendMessage = () => {
        // 	if (userSeq.value !== '' && message.value !== '') {
        // 		send();
        // 		message.value = '';
        // 	}
        // };

        // const send = () => {
        // 	if (stompClient.value && stompClient.value.connected) {
        // 		const date = new Date(Date.now());
        // 		const msg = {
        // 			userSeq: userSeq.value,
        // 			content: message.value,
        // 			createtime: date,
        // 			chatRoomId: chatRoomId,
        // 		};
        // 		stompClient.value.send('/receive', JSON.stringify(msg), {});
        // 	}
        // };

        sendMessage(e) {
            if (e.keyCode === 13 && this.userName !== "" && this.message !== "") {
                this.send();
                this.message = "";
            }
        },
        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const currentTime = new Date(Date.now());
                const msg = {
                    sender: this.userName,
                    message: this.message,
                    time: currentTime,
                };
                this.stompClient.send("/pub/chat/sendMessage", JSON.stringify(msg), {});
            }
        },
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

                        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                        this.recvList.push(JSON.parse(res.body));
                    });
                },
                (error) => {
                    // 소켓 연결 실패
                    console.log("소켓 연결 실패", error);
                    this.connected = false;
                }
            );
        },
    },
};
</script>
