<script setup>
import { onMounted, ref, watch } from "vue";
import { usePokemonNo } from "@/stores/counter.js";
import { RouterLink, useRouter } from "vue-router";
import chat from "../common/ChatModal.vue";
import { useChatClose } from "../../stores/counter";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { jwtDecode } from "jwt-decode";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogout, getUserInfo } = memberStore;

const logout = async () => {
    if (sessionStorage.getItem("accessToken")) {
        await userLogout(jwtDecode(sessionStorage.getItem("accessToken")).userId);
    } else {
        isLogin.value = false;
    }
    sessionStorage.clear();
    router.push("/");
};

const cc = useChatClose();

const pokemonNo = usePokemonNo();

onMounted(() => {
    const mainTabs = document.querySelector(".main-tabs");
    const mainSliderCircle = document.querySelector(".main-slider-circle");
    const roundButtons = document.querySelectorAll(".round-button");

    const getColor = (color, variant) => {
        return colors[color][variant].value;
    };

    const handleActiveTab = (tabs, event, className) => {
        tabs.forEach((tab) => {
            tab.classList.remove(className);
        });
        if (!event.target.classList.contains(className)) {
            event.target.classList.add(className);
        }
    };

    mainTabs.addEventListener("click", (event) => {
        const root = document.documentElement;
        const targetColor = event.target.dataset.color;
        const targetTranslateValue = event.target.dataset.translateValue;

        if (event.target.classList.contains("round-button")) {
            mainSliderCircle.classList.remove("animate-jello");
            void mainSliderCircle.offsetWidth;
            mainSliderCircle.classList.add("animate-jello");
            root.style.setProperty("--translate-main-slider", targetTranslateValue);
            root.style.setProperty("--main-slider-color", getColor(targetColor, 50));
            root.style.setProperty("--background-color", getColor(targetColor, 100));
            handleActiveTab(roundButtons, event, "active");

            if (!event.target.classList.contains("gallery")) {
                root.style.setProperty("--filters-container-height", "0");
                root.style.setProperty("--filters-wrapper-opacity", "0");
            } else {
                root.style.setProperty("--filters-container-height", "3.8rem");
                root.style.setProperty("--filters-wrapper-opacity", "1");
            }
        }

        if (!condition.value) {
            root.style.setProperty("--filters-container-height", "3.8rem");
            root.style.setProperty("--filters-wrapper-opacity", "0");
        }
    });

    // const filterTabs = document.querySelector(".filter-tabs");
    // const filterButtons = document.querySelectorAll(".filter-button");

    // filterTabs.addEventListener("click", (event) => {
    //     const root = document.documentElement;
    //     const targetTranslateValue = event.target.dataset.translateValue;
    //     if (event.target.classList.contains("filter-button")) {
    //         root.style.setProperty("--translate-filters-slider", targetTranslateValue);
    //         handleActiveTab(filterButtons, event, "filter-active");
    //     }
    // });
});

const colors = {
    blue: {
        50: {
            value: "#e3f2fd",
        },
        100: {
            value: "#bbdefb",
        },
    },
    green: {
        50: {
            value: "#e8f5e9",
        },
        100: {
            value: "#c8e6c9",
        },
    },
    purple: {
        50: {
            value: "#f3e5f5",
        },
        100: {
            value: "#e1bee7",
        },
    },
    orange: {
        50: {
            value: "#ffe0b2",
        },
        100: {
            value: "#ffe0b2",
        },
    },
    red: {
        50: {
            value: "#ffebee",
        },
        100: {
            value: "#ffcdd2",
        },
    },
};

const condition = ref(false);

function changeCondition() {
    condition.value = !condition.value;
    cc.clsoeModal();
}

const getImageUrl = () => {
    return new URL(`../../assets/img/pokemon/${pokemonNo.no}.gif`, import.meta.url).href;
};
</script>
<template>
    <!-- partial:index.partial.html -->
    <nav class="amazing-tabs">
        <div class="filters-container" :class="{ plus: cc.isClosed === false }">
            <div v-if="cc.isClosed">
                <chat />
            </div>
        </div>
        <div class="main-tabs-container">
            <div class="main-tabs-wrapper">
                <ul class="main-tabs">
                    <li :class="{ plus: condition === false }">
                        <router-link to="/">
                            <button
                                class="round-button active"
                                data-translate-value="-15%"
                                data-color="purple"
                            >
                                <span class="avatar">
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        height="1em"
                                        viewBox="0 0 576 512"
                                    >
                                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                        <path
                                            d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"
                                        />
                                    </svg>
                                </span>
                            </button>
                        </router-link>
                    </li>
                    <li :class="{ plus: condition === false }">
                        <button
                            @click="logout"
                            class="round-button active"
                            data-translate-value="84%"
                            data-color="orange"
                        >
                            <span class="avatar">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="1em"
                                    viewBox="0 0 512 512"
                                >
                                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                    <path
                                        d="M502.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-128-128c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 224 192 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l210.7 0-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l128-128zM160 96c17.7 0 32-14.3 32-32s-14.3-32-32-32L96 32C43 32 0 75 0 128L0 384c0 53 43 96 96 96l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32l0-256c0-17.7 14.3-32 32-32l64 0z"
                                    />
                                </svg>
                            </span>
                        </button>
                    </li>
                    <li :class="{ plus: condition === false }">
                        <router-link to="mypage">
                            <button
                                class="round-button active"
                                data-translate-value="192%"
                                data-color="green"
                            >
                                <span class="avatar">
                                    <img :src="getImageUrl()" alt="user avatar" />
                                </span>
                            </button>
                        </router-link>
                    </li>
                    <li :class="{ plus: condition === false }">
                        <router-link to="mypage">
                            <button
                                class="round-button active"
                                data-translate-value="296%"
                                data-color="red"
                            >
                                <span class="avatar">
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        height="1em"
                                        viewBox="0 0 576 512"
                                    >
                                        <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                        <path
                                            d="M565.6 36.2C572.1 40.7 576 48.1 576 56V392c0 10-6.2 18.9-15.5 22.4l-168 64c-5.2 2-10.9 2.1-16.1 .3L192.5 417.5l-160 61c-7.4 2.8-15.7 1.8-22.2-2.7S0 463.9 0 456V120c0-10 6.1-18.9 15.5-22.4l168-64c5.2-2 10.9-2.1 16.1-.3L383.5 94.5l160-61c7.4-2.8 15.7-1.8 22.2 2.7zM48 136.5V421.2l120-45.7V90.8L48 136.5zM360 422.7V137.3l-144-48V374.7l144 48zm48-1.5l120-45.7V90.8L408 136.5V421.2z"
                                        />
                                    </svg>
                                </span>
                            </button>
                        </router-link>
                    </li>

                    <li :class="{ plus: condition === false }">
                        <button
                            class="round-button gallery"
                            style="--round-button-active-color: #2962ff"
                            data-translate-value="400%"
                            data-color="blue"
                            @click="cc.changeState()"
                        >
                            <svg
                                fill="currentColor"
                                xmlns="http://www.w3.org/2000/svg"
                                height="1em"
                                viewBox="0 0 512 512"
                            >
                                <path
                                    d="M160 368c26.5 0 48 21.5 48 48v16l72.5-54.4c8.3-6.2 18.4-9.6 28.8-9.6H448c8.8 0 16-7.2 16-16V64c0-8.8-7.2-16-16-16H64c-8.8 0-16 7.2-16 16V352c0 8.8 7.2 16 16 16h96zm48 124l-.2 .2-5.1 3.8-17.1 12.8c-4.8 3.6-11.3 4.2-16.8 1.5s-8.8-8.2-8.8-14.3V474.7v-6.4V468v-4V416H112 64c-35.3 0-64-28.7-64-64V64C0 28.7 28.7 0 64 0H448c35.3 0 64 28.7 64 64V352c0 35.3-28.7 64-64 64H309.3L208 492z"
                                />
                            </svg>
                        </button>
                    </li>
                    <li :class="{ plus: condition === false }">
                        <router-link to="attractioninfo">
                            <button
                                class="round-button"
                                style="--round-button-active-color: #aa00ff"
                                data-translate-value="507%"
                                data-color="purple"
                            >
                                <!-- <svg fill="currentColor" viewBox="0 0 16 16">
                                    <path
                                        d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"
                                    />
                                </svg> -->
                                <svg
                                    fill="currentColor"
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="1em"
                                    viewBox="0 0 512 512"
                                >
                                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                    <path
                                        d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                                    />
                                </svg>
                            </button>
                        </router-link>
                    </li>
                    <li :class="{ plus: condition === false }">
                        <router-link to="boardList">
                            <button
                                class="round-button"
                                style="--round-button-active-color: #ff6d00"
                                data-translate-value="608%"
                                data-color="orange"
                            >
                                <!-- <svg  viewBox="0 0 16 16">
                                <path
                                    d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"
                                />
                            </svg> -->
                                <svg
                                    fill="currentColor"
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="1em"
                                    viewBox="0 0 384 512"
                                >
                                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                    <path
                                        d="M280 64h40c35.3 0 64 28.7 64 64V448c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128C0 92.7 28.7 64 64 64h40 9.6C121 27.5 153.3 0 192 0s71 27.5 78.4 64H280zM64 112c-8.8 0-16 7.2-16 16V448c0 8.8 7.2 16 16 16H320c8.8 0 16-7.2 16-16V128c0-8.8-7.2-16-16-16H304v24c0 13.3-10.7 24-24 24H192 104c-13.3 0-24-10.7-24-24V112H64zm128-8a24 24 0 1 0 0-48 24 24 0 1 0 0 48z"
                                    />
                                </svg>
                            </button>
                        </router-link>
                    </li>

                    <li>
                        <button
                            class="round-button"
                            style="--round-button-active-color: #00c853"
                            data-translate-value="712%"
                            data-color="green"
                            @click="changeCondition()"
                        >
                            <svg fill="currentColor" viewBox="0 0 16 16">
                                <path
                                    d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
                                />
                                <path
                                    d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
                                />
                            </svg>
                        </button>
                    </li>
                </ul>
                <div class="main-slider" aria-hidden="true">
                    <div class="main-slider-circle">&nbsp;</div>
                </div>
            </div>
        </div>
    </nav>
    <!-- partial -->
</template>
<style scoped>
button {
    border: none;
    cursor: pointer;
    background-color: transparent;
    outline: none;
}
nav.amazing-tabs {
    background-color: var(--grey-900);
    border-radius: 1rem;
    user-select: none;
    padding-top: 1rem;
    width: 1000px;
    margin: auto;
    position: fixed;
    top: calc(100% - 150px);
    left: calc(100% - 700px);
    z-index: 40;
    background: transparent;
}

.main-tabs-container {
    padding: 0 1rem 1rem 1rem;
    z-index: -1;
}
.main-tabs-wrapper {
    position: relative;
}
ul.main-tabs,
ul.filter-tabs {
    list-style-type: none;
    display: flex;
}
ul.main-tabs li {
    display: inline-flex;
    position: relative;
    padding: 0.8rem;
    z-index: 1;
}
.avatar,
.avatar img {
    width: 2.5rem;
    pointer-events: none;
}
.round-button {
    height: 3.6rem;
    width: 3.6rem;
    border-radius: 50%;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    color: var(--grey-900);
    transition: color 0.2s ease-in-out;
}
.round-button:hover,
.round-button.active {
    color: var(--round-button-active-color);
}
.round-button svg {
    pointer-events: none;
    height: 2rem;
    width: 2rem;
    transform: translate(0, 0);
}
.main-slider {
    pointer-events: none;
    position: absolute;
    top: 1.4px;
    left: 13.5px;
    padding: 1rem;
    z-index: 0;
    transition: transform 0.4s ease-in-out;
    transform: translateX(var(--translate-main-slider));
}
.main-slider-circle {
    height: 3rem;
    width: 3rem;
    border-radius: 50%;
    transition: background-color 0.4s ease-in-out;
    background-color: var(--main-slider-color);
}
.animate-jello {
    animation: jello-horizontal 0.8s both;
}
@keyframes jello-horizontal {
    0% {
        transform: scale3d(1, 1, 1);
    }
    30% {
        transform: scale3d(1.25, 0.75, 1);
    }
    40% {
        transform: scale3d(0.75, 1.25, 1);
    }
    50% {
        transform: scale3d(1.15, 0.85, 1);
    }
    65% {
        transform: scale3d(0.95, 1.05, 1);
    }
    75% {
        transform: scale3d(1.05, 0.95, 1);
    }
    100% {
        transform: scale3d(1, 1, 1);
    }
}
.filters-container {
    min-width: 730px;
    position: absolute;
    top: -490px;
    right: 344px;
    border-radius: 20px;
    box-shadow: 0.2px 0.2px 35px 2px rgb(134, 134, 134);
    overflow: hidden;
    transition: max-height 0.4s ease-in-out;
}
.filters-wrapper {
    position: relative;
    transition: opacity 0.2s ease-in-out;
    opacity: var(--filters-wrapper-opacity);
}
.plus {
    visibility: hidden;
    opacity: 0;
}
li {
    transition: 0.8s ease-in-out;
}
</style>
