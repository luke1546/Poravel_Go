<script setup>
import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;

const loginUser = ref({
    userId: "",
    userPwd: "",
});

async function login() {
    console.dir(1);
    await userLogin(loginUser.value);
}
watch(
    () => isLogin.value,
    (newValue, oldValue) => {
        console.dir(isLogin.value);
        let token = sessionStorage.getItem("accessToken");
        getUserInfo(token);
    }
);
</script>

<template>
    <!-- Modal dialog -->
    <div>
        <div id="modal-container" class="six modal-active">
            <div class="modal-background">
                <div class="modal">
                    <div><img src="@/assets/img/Logo.png" id="modallogo" /></div>
                    <form class="input-form">
                        <div class="input-group">
                            <label for="id" class="userinfo userlabel">Username :</label>
                            <input
                                type="text"
                                class="userinfo"
                                id="id"
                                v-model="loginUser.userId"
                                placeholder="아이디를 입력해주세요"
                            />
                        </div>
                        <div class="input-group">
                            <label for="password" class="userinfo userlabel">Password :</label>
                            <input
                                type="password"
                                id="password"
                                class="userinfo"
                                v-model="loginUser.userPwd"
                                placeholder="비밀번호를 입력해주세요"
                            />
                        </div>
                        <button type="button" class="loginbtn usersub" @click="login()">
                            Login
                        </button>
                    </form>
                    <div>
                        <button class="usersub">password</button>
                        <button class="usersub">regist</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style setup>
div > .usersub {
    vertical-align: bottom;
    position: relative;
    top: 10px;
    left: 18%;
    clear: auto;
    margin: 23px;
    cursor: pointer;
    border: none;
    z-index: 3;
    background-color: transparent;
    font-size: 20px;
}

#modallogo {
    float: left;
    margin-top: 20px;
    margin-left: 30px;
    width: 200px;
}

.input-form {
    position: absolute;
    height: 100%;
    width: 100%;
    z-index: 2;
}

/* login image */
#loginimg {
    width: 130px;
}

.userinfo {
    font-size: 25px;
    width: 40%;
}

/* input */
#id,
#password {
    width: 350px;
    height: 45px;
    border: none;
    border-radius: 20px;
    padding-left: 15px;
    margin-left: 50px;
    outline-color: yellow;
    font-size: 20px;
}

.input-group {
    position: relative;
    top: 180px;
    align-items: center;
    margin-bottom: 30px;
}

/* submit button & animation */
.loginbtn {
    position: absolute;
    margin: 5px 5px 5px 5px;
    border: none;
    position: relative;
    cursor: pointer;
    border: none;
    background-color: transparent;
    transition: 0.3s;
    opacity: 100%;
    top: 220px;
    right: -260px;
}

.loginbtn:hover {
    transform: scale(1.2);
}

.loginbtn:hover::after {
    content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 3px solid red;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1s infinite;
}

/* login modal */
#modal-container {
    position: absolute;
    display: flex;
    height: 50vh;
    width: 50vh;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) scale(1);
    align-items: center;
    justify-content: center;
}

#modal-container.six .modal-background .modal {
    background-color: transparent;
    animation: modalFadeIn 0.3s 0.5s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}
#id,
#password {
    opacity: 0;
    background-color: transparent;
    animation: formFadeIn 0.4s 0.7s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

.userlabel {
    opacity: 0;
    animation: fadeIn 0.4s 0.7s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

.usersub {
    opacity: 0;
    font-size: 20px;
    animation: fadeIn 0.4s 0.7s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

#modallogo {
    opacity: 0;
    animation: fadeIn 0.4s 0.7s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

/* 배경 색상 */
#modal-container .modal-background {
    display: table-cell;
    background: none;
    text-align: center;
    vertical-align: middle;
}

#modal-container .modal-background .modal {
    display: inline-block;
    border-radius: 3px;
    position: relative;
    width: 800px;
    height: 500px;
    z-index: 2;
}

/* login button animation */
@keyframes ring {
    0% {
        width: 30px;
        height: 30px;
        opacity: 1;
    }
    100% {
        width: 150px;
        height: 150px;
        opacity: 0;
    }
}

/* modal animation */
@keyframes modalFadeIn {
    0% {
        background-color: transparent;
    }
    100% {
        background-color: white;
    }
}

@keyframes formFadeIn {
    0% {
        background-color: transparent;
    }
    100% {
        opacity: 1;
        background-color: rgb(206, 206, 206);
    }
}

@keyframes fadeIn {
    0% {
    }
    100% {
        opacity: 1;
    }
}
</style>
