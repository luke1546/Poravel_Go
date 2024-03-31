<script setup>
import Weather from "../components/common/WeatherComponent.vue";
import { onMounted } from "vue";
import BoardComponent from "../components/common/BoardComponent.vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);

onMounted(() => {
    const slider = document.querySelector(".slider");

    function activate(e) {
        const items = document.querySelectorAll(".item");
        if (e.target.matches(".next")) {
            slider.append(items[0]);
        }
        if (e.target.matches(".prev")) {
            slider.prepend(items[items.length - 1]);
        }
    }

    document.addEventListener("click", activate, false);
});
</script>

<template>
    <!-- <router-view/> -->
    <div class="container">
        <div class="main">
            <ul class="slider">
                <li
                    class="item"
                    style="
                        background-image: url('https://image.kr.canon/pds/gallery/1437925600491_TMF7xEL0vq.jpg');
                    "
                >
                    <div class="content">
                        <h2 class="title">"Lossless Youths"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
                <li
                    class="item"
                    style="
                        background-image: url('https://a.cdn-hotels.com/gdcs/production124/d899/558b1095-0fac-4386-a709-45dd82929979.jpg');
                    "
                >
                    <div class="content">
                        <h2 class="title">"Estrange Bond"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
                <li
                    class="item"
                    style="background-image: url('https://content.skyscnr.com/m/22bd5e72543472f4/original/sokcho-korea-sea-pixabay-237482.jpg?crop=1224px:647px&quality=100&position=attention');">
                    <div class="content">
                        <h2 class="title">"The Gate Keeper"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
                <li
                    class="item"
                    style="background-image: url('https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_2048,h_1360,f_auto/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/bu0em9mc2glkcaui7ou7/%ED%9B%84%EC%BF%A0%EC%98%A4%EC%B9%B4%EC%B6%9C%EB%B0%9C2%EC%9D%BC%EC%98%A4%EC%9D%B4%ED%83%80%EB%B0%8F%EA%B5%AC%EB%A7%88%EB%AA%A8%ED%86%A0%EB%B2%84%EC%8A%A4%ED%88%AC%EC%96%B4-%ED%81%B4%EB%A3%A9Klook%ED%95%9C%EA%B5%AD.jpg');">
                    <div class="content">
                        <h2 class="title">"Last Trace Of Us"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
                <li
                    class="item"
                    style="background-image: url('https://www.kkday.com/ko/blog/wp-content/uploads/korea_gunsan_oknyeointersection_1.jpg');">
                    <div class="content">
                        <h2 class="title">"Urban Decay"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
                <li class="item" style="background-image: url('https://knto.or.kr/humanframe/theme/kto/assets/image/main/main_visual_05.png');">
                    <div class="content">
                        <h2 class="title">"The Migration"</h2>
                        <p class="description">
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Tempore fuga
                            voluptatum, iure corporis inventore praesentium nisi. Id laboriosam
                            ipsam enim.
                        </p>
                        <button>Read More</button>
                    </div>
                </li>
            </ul>
            <nav class="nav">
                <ion-icon class="btn prev" name="arrow-back-outline"></ion-icon>
                <ion-icon class="btn next" name="arrow-forward-outline"></ion-icon>
            </nav>
        </div>
            <div v-if="isLogin" class="weather"><Weather /></div>
            <div v-if="isLogin" class="notify"><BoardComponent /></div>
        </div>
</template>

<style scoped>
.weather{
    z-index: 2;
    position: absolute;
    left: calc(200px);
    top: 0px;
    opacity: 0.7;
}
.notify{
    z-index: 2;
    position: absolute;
    right: 10px;
    top: 10px;
}
.weather:hover{
    opacity: 1;
}
.addInfo {
    display: grid;
    place-items: center;
    grid-template-columns: 1fr 1fr;
    margin: 100px;
}
.container {
    display: grid;
    place-items: center;
    overflow: hidden;
}

.main {
    position: relative;
    width: 100%;
    height: 100vh;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.3);
}

.item {
    width: 200px;
    height: 200px;
    list-style-type: none;
    position: absolute;
    top: 70%;
    transform: translateY(-50%);
    z-index: 1;
    background-position: center;
    background-size: cover;
    border-radius: 20px;
    box-shadow: 0 20px 30px rgba(255, 255, 255, 0.3) inset;
    transition: transform 0.1s, left 0.75s, top 0.75s, width 0.75s, height 0.75s;
}

.item:nth-child(1),
.item:nth-child(2) {
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    transform: none;
    border-radius: 0;
    box-shadow: none;
    opacity: 1;
}

.item:nth-child(3) {
    left: 50%;
}
.item:nth-child(4) {
    left: calc(50% + 220px);
}
.item:nth-child(5) {
    left: calc(50% + 440px);
}
.item:nth-child(6) {
    left: calc(50% + 660px);
    opacity: 0;
}

.content {
    width: min(30vw, 400px);
    position: absolute;
    top: 50%;
    left: 3rem;
    transform: translateY(-50%);
    font: 400 0.85rem helvetica, sans-serif;
    color: white;
    text-shadow: 0 3px 8px rgba(0, 0, 0, 0.5);
    opacity: 0;
    display: none;
}

.content .title {
    font-family: "arial-black";
    text-transform: uppercase;
}

.content .description {
    line-height: 1.7;
    margin: 1rem 0 1.5rem;
    font-size: 0.8rem;
}

.content button {
    width: fit-content;
    background-color: rgba(0, 0, 0, 0.1);
    color: white;
    border: 2px solid white;
    border-radius: 0.25rem;
    padding: 0.75rem;
    cursor: pointer;
}

.item:nth-of-type(2) .content {
    display: block;
    animation: show 0.75s ease-in-out 0.3s forwards;
}

@keyframes show {
    0% {
        filter: blur(5px);
        transform: translateY(calc(-50% + 75px));
    }
    100% {
        opacity: 1;
        filter: blur(0);
    }
}

.nav {
    position: absolute;
    bottom: 2rem;
    left: 50%;
    transform: translateX(-50%);
    z-index: 5;
    user-select: none;
}

.nav .btn {
    background-color: rgba(255, 255, 255, 0.5);
    color: rgba(0, 0, 0, 0.7);
    border: 2px solid rgba(0, 0, 0, 0.6);
    margin: 0 0.25rem;
    padding: 0.75rem;
    border-radius: 50%;
    cursor: pointer;
    z-index: 1;
}

.nav .btn:hover {
    background-color: rgba(255, 255, 255, 0.3);
}

@media (width > 650px) and (width < 900px) {
    .content .title {
        font-size: 1rem;
    }
    .content .description {
        font-size: 0.7rem;
    }
    .content button {
        font-size: 0.7rem;
    }

    .item {
        width: 160px;
        height: 270px;
    }

    .item:nth-child(3) {
        left: 50%;
    }
    .item:nth-child(4) {
        left: calc(50% + 170px);
    }
    .item:nth-child(5) {
        left: calc(50% + 340px);
    }
    .item:nth-child(6) {
        left: calc(50% + 510px);
        opacity: 0;
    }
}

@media (width < 650px) {
    .content .title {
        font-size: 0.9rem;
    }
    .content .description {
        font-size: 0.65rem;
    }
    .content button {
        font-size: 0.7rem;
    }

    .item {
        width: 130px;
        height: 220px;
    }

    .item:nth-child(3) {
        left: 50%;
    }
    .item:nth-child(4) {
        left: calc(50% + 140px);
    }
    .item:nth-child(5) {
        left: calc(50% + 280px);
    }
    .item:nth-child(6) {
        left: calc(50% + 420px);
        opacity: 0;
    }
}
</style>
