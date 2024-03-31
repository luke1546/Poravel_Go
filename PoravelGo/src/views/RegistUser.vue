<script>
export default {
    data: () => ({
        name: "",

        id: "",

        password: "",
        passwordRules: [
            (v) => !!v || "비밀번호을 입력해주세요",
            (v) => (v && v.length >= 5) || "비밀번호는 5글자 이상 작성해주세요",
            (v) => /(?=.*\d)/.test(v) || "숫자를 포함해야합니다",
            (v) => /([!@$%])/.test(v) || "특수문자를 포함해야합니다 [!@#$%]",
        ],

        checkPassword: "",
        checkPasswordRules: [
            (v) => !!v || "비밀번호을 확인해주세요",
            (v) => (v && v.length >= 5) || "비밀번호는 5글자 이상 작성해주세요",
            (v) => /(?=.*\d)/.test(v) || "숫자를 포함해야합니다",
            (v) => /([!@$%])/.test(v) || "특수문자를 포함해야합니다 [!@#$%]",
        ],

        email: "",
        emailRules: [
            (v) => !!v || "이메일을 입력해주세요",
            (v) => /.+@.+\..+/.test(v) || "이메일 형식을 확인해주세요",
        ],

        birthday: "",
        birthdayRules: [
            (v) => !!v || "생일을 입력해주세요",
            (v) => (v && v.length <= 10) || "닉네임을 10글자를 넘을 수 없습니다.",
        ],
    }),

    computed: {
        passwordConfirmationRule() {
            return () => this.password !== this.confirmPassword || "패스워드가 일치하지 않습니다";
        },
    },
    methods: {
        signUpSubmit() {
            const validate = this.$refs.form.validate();
            if (validate) {
                let saveData = {};
                saveData.email = this.email;
                saveData.password = this.password;
                saveData.nickname = this.nickname;
                try {
                    this.$axios
                        .post("/api/member", JSON.stringify(saveData), {
                            headers: {
                                "Content-Type": `application/json`,
                            },
                        })
                        .then((response) => {
                            console.log(response);
                            if (response.data.errorCode === 400) {
                                alert(response.data.errorMessage);
                            } else {
                                alert("회원가입이 완료되었습니다. 로그인 화면으로 돌아갑니다");
                                this.$router.push({ path: "./login" });
                            }
                        })
                        .catch((error) => {
                            console.log(error.response);
                        });
                } catch (error) {
                    console.error(error);
                }
            }
        },
        linkToLogin() {
            this.$router.push({ path: "/" });
        },
    },
};
</script>

<template>
    <div id="registpage">
        <form @submit.prevent="submitRegist">
            <label for="id">ID</label>
            <br />
            <input id="id" v-model="id" type="text" />
            <br />

            <label for="name">Name</label>
            <br />
            <input id="name" v-model="name" type="text" />
            <br />

            <label for="password">PASSWORD</label>
            <br />
            <input id="password" v-model="password" :rules="passwordRules" type="password" />
            <br />

            <label for="checkPassword">CHECK PASSWORD</label>
            <br />
            <input
                id="checkPassword"
                v-model="checkPassword"
                :rules="checkPasswordRules.concat(passwordConfirmationRule)"
                type="password"
            />
            <br />

            <label for="email">EMAIL</label>
            <br />
            <input id="email" v-model="email" :rules="emailRules" />
            <br />

            <label for="birthday">BIRTH_DAY</label>
            <br />
            <input id="birthday" v-model="birthday" :rules="birthdayRules" type="text" />
            <button class="loginbutton" type="submit">
                <img id="loginimg" src="@/assets/icons/login.png" />
            </button>
        </form>
    </div>
</template>

<style scoped>
/* form */
#loginpage {
    display: grid;
    place-items: center;
    width: 100%;
    height: 70%;
    border: solid 0.7px rgb(180, 172, 172);
    border-radius: 10px;
}

label {
    font-size: 18px;
}

/* input */
input {
    padding: 5px;
    margin: 15px;
    width: 350px;
    height: 40px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(233, 233, 233);
}

/* submit button & animation */
.loginbutton {
    position: absolute;
    margin: 70px 40px 0px 80px;
    border: none;
    cursor: pointer;
    background-color: transparent;
    transition: 0.3s;
    /* z-index: 1; */
    opacity: 100%;
}
.loginbutton:hover {
    transform: scale(1.2);
}

.loginbutton:hover::after {
    content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 6px solid red;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1.5s infinite;
}

@keyframes gradient1 {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}

@keyframes gradient2 {
    0% {
        background-position: 100% 50%;
    }
    50% {
        background-position: 0% 50%;
    }
    100% {
        background-position: 100% 50%;
    }
}

@keyframes ring {
    0% {
        width: 30px;
        height: 30px;
        opacity: 1;
    }
    100% {
        width: 300px;
        height: 300px;
        opacity: 0;
    }
}

/* login image */
#loginimg {
    width: 130px;
}
</style>
