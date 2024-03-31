<script setup>
import { ref, onMounted, watch, computed } from "vue";
import feather from "feather-icons";
import axios from "axios";

const select = ref(1);

function changeSelect(i) {
    select.value = i;
}
// console.log(select.value)
const WeatherKey = import.meta.env.VITE_WEATHER_KEY;

const condition = ref(false);
const condition2 = ref(false);
const city = ref("seoul");

function changeCity() {
    city.value = document.getElementById("searchbox").value;
    mounted();
}

function slider() {
    condition.value = !condition.value;
}

watch(
    () => condition.value,
    () => {
        if (condition.value == true) {
            console.log("dfjsadlkfjlk");
            setTimeout(() => {
                condition2.value = condition.value;
            }, 200);
        } else {
            condition2.value = condition.value;
        }
    }
);

const now = new Date();
const week = ["일", "월", "화", "수", "목", "금", "토"];
let day = week[now.getDay()];

const info = ref({});
const temp = computed(() => info.value?.main?.temp);

const mounted = async () => {
    await axios
        .get(`https://api.openweathermap.org/data/2.5/weather?q=${city.value}&appid=${WeatherKey}`)
        .then(({ data }) => {
            info.value = data;
        });
    feather.replace();
};

onMounted(() => {
    mounted();
});

// searchbar
function selectsearch() {
    const el = document.querySelector(".navsearch");
    el.classList.toggle("active");
    if (el.classList.contains("active")) {
        document.querySelector(".searchbox").focus();
    }
}
// searchbar
</script>
<template>
    <div id="weather-contents">
        <h2 id="weather-text"></h2>
        <div class="container" :class="{ wSlider: condition === true }">
            <div class="weather-side">
                <div class="weather-gradient"></div>
                <div class="date-container">
                    <h2 class="date-dayname">
                        {{ now.getMonth() + 1 + "/" + now.getDate() + " " + day + "요일" }}
                    </h2>
                    <span class="location">{{ info.name }}</span>
                </div>
                <div class="weather-container">
                    <i class="weather-icon" data-feather="sun"></i>
                    <h1 class="weather-temp"></h1>
                    <h3 class="weather-desc">{{ Math.round(temp - 273.16) }}°C</h3>
                </div>
            </div>

            <div class="info-side" v-show="condition2">
                <div class="week-container">
                    <ul class="week-list">
                        <li :class="{ active: select == 1 }" @click="changeSelect(1)">
                            <i class="day-icon" data-feather="sun"></i
                            ><span class="day-name">Tue</span><span class="day-temp">9°C</span>
                        </li>
                        <li :class="{ active: select == 2 }" @click="changeSelect(2)">
                            <i class="day-icon" data-feather="cloud"></i
                            ><span class="day-name">Wed</span><span class="day-temp">11°C</span>
                        </li>
                        <li :class="{ active: select == 3 }" @click="changeSelect(3)">
                            <i class="day-icon" data-feather="cloud-snow"></i
                            ><span class="day-name">Thu</span><span class="day-temp">8°C</span>
                        </li>
                        <li :class="{ active: select == 4 }" @click="changeSelect(4)">
                            <i class="day-icon" data-feather="cloud-rain"></i
                            ><span class="day-name">Fry</span><span class="day-temp">11°C</span>
                        </li>
                        <div class="clear"></div>
                    </ul>
                </div>

                <div class="location-container">
                    <div class="navbar">
                        <div class="navsearch">
                            <input
                                id="searchbox"
                                class="searchbox"
                                placeholder="only english city"
                                @keyup.enter="changeCity()"
                            />
                            <div class="searchicon" @click="selectsearch()">
                                <ion-icon name="search" style="fill: rgb(255, 255, 255)"></ion-icon>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <button
                id="toggle"
                :class="{ toggle1: condition === true, toggle2: condition === false }"
                type="button"
                @click="slider()"
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
</template>

<style scoped>
@import url("../../../dist/output.css");
@import url("@/assets/css/colorful-mobile-navigation-bar.css");
.navbar {
    background-color: rgba(0, 0, 0, 0);
}

.locationsvg {
    display: inline;
}
.toggle1 {
    transform: rotate(270deg);
}
.toggle2 {
    transform: rotate(90deg);
}
#toggle {
    position: absolute;
    left: calc(100% - 45px);
    top: 45%;
    width: 50px;
    transition: transform 0.5s ease-in-out;
    fill: white;
}

/* 글자 자간 */
#weather-contents {
    display: flex;
    flex-direction: column;
    position: relative;
    /* right: 670px; */
    right: 35%;
    margin-top: 20px;
    margin-bottom: 10px;
}

#weather-text {
    text-align: center;
    margin-bottom: 10px;
}

.clear {
    clear: both;
}
.container {
    float: left;
    /* position: relative;
	left: -200px;
	margin: 30px; */
    border-radius: 25px;
    -webkit-box-shadow: 0 0 70px -10px rgba(0, 0, 0, 0.2);
    box-shadow: 0 0 70px -10px rgba(0, 0, 0, 0.2);
    background-color: #222831;
    color: #ffffff;
    height: 210px;
    width: 200px;
    /* transition: background-color 0.6s ease; */
    transition: all 0.2s linear;
}

.weather-side {
    position: relative;
    height: 100%;
    border-radius: 25px;
    width: 200px;
    -webkit-box-shadow: 0 0 20px -10px rgba(0, 0, 0, 0.2);
    box-shadow: 0 0 20px -10px rgba(0, 0, 0, 0.2);
    -webkit-transition: -webkit-transform 300ms ease;
    transition: -webkit-transform 300ms ease;
    -o-transition: transform 300ms ease;
    transition: transform 300ms ease;
    transition: transform 300ms ease, -webkit-transform 300ms ease;
    -webkit-transform: translateZ(0) scale(1.02) perspective(1000px);
    transform: translateZ(0) scale(1.02) perspective(1000px);
    float: left;
    margin-right: 20px;
}

.weather-side:hover {
    -webkit-transform: scale(1.1) perspective(1500px) rotateY(10deg);
    transform: scale(1.1) perspective(1500px) rotateY(10deg);
}

.weather-gradient {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background-image: var(--gradient);
    border-radius: 25px;
    opacity: 0.8;
}

.date-container {
    position: absolute;
    top: 25px;
    left: 25px;
}

.date-dayname {
    margin: 0;
}

.date-day {
    display: block;
}

.location {
    display: inline-block;
    margin-top: 10px;
}

.location-icon {
    display: inline-block;
    height: 0.8em;
    width: auto;
    margin-right: 5px;
}

.weather-container {
    position: absolute;
    bottom: 25px;
    left: 25px;
}

.weather-icon.feather {
    height: 40px;
    width: auto;
}

.weather-temp {
    margin: 0;
    font-weight: 700;
    font-size: 2rem;
}

.weather-desc {
    margin: 0;
}

.info-side {
    position: relative;
    float: left;
    height: 100%;
    padding-top: 25px;
}

.today-info {
    padding: 15px;
    margin: 0 25px 25px 25px;
    box-shadow: 0 0 50px -5px rgba(0, 0, 0, 0.25);
    border-radius: 10px;
}

.today-info > div:not(:last-child) {
    margin: 0 0 10px 0;
}

.today-info > div .title {
    float: left;
    font-weight: 700;
}

.today-info > div .value {
    float: right;
}

.week-list {
    list-style-type: none;
    padding: 0;
    -webkit-box-shadow: 0 0 50px -5px rgba(0, 0, 0, 0.25);
    box-shadow: 0 0 50px -5px rgba(0, 0, 0, 0.25);
    border-radius: 10px;
}

.week-list > li {
    float: left;
    padding: 15px;
    cursor: pointer;
    -webkit-transition: 200ms ease;
    -o-transition: 200ms ease;
    transition: 200ms ease;
    border-radius: 10px;
}

.week-st > li:hover {
    -webkit-transform: scale(1.1);
    -ms-transform: scale(1.1);
    transform: scale(1.1);
    background: #fff;
    color: #222831;
    -webkit-box-shadow: 0 0 40px -5px rgba(0, 0, 0, 0.2);
    box-shadow: 0 0 40px -5px rgba(0, 0, 0, 0.2);
}

.week-list > li.active {
    background: #fff;
    color: #222831;
    border-radius: 10px;
}

.week-list > li .day-name {
    display: block;
    margin: 8px 0 0 0;
    text-align: center;
}

.week-list > li .day-icon {
    display: block;
    width: auto;
    margin: 0 auto;
}

.week-list > li .day-temp {
    display: block;
    text-align: center;
    margin: 10px 0 0 0;
    font-weight: 700;
}

.location-button {
    outline: none;
    width: 100%;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    border: none;
    border-radius: 25px;
    padding: 10px;
    background-image: var(--gradient);
    color: #ffffff;
    font-weight: 700;
    -webkit-box-shadow: 0 0 30px -5px rgba(0, 0, 0, 0.25);
    box-shadow: 0 0 30px -5px rgba(0, 0, 0, 0.25);
    cursor: pointer;
    -webkit-transition: -webkit-transform 200ms ease;
    transition: -webkit-transform 200ms ease;
    -o-transition: transform 200ms ease;
    transition: transform 200ms ease;
    transition: transform 200ms ease, -webkit-transform 200ms ease;
}

.location-button:hover {
    -webkit-transform: scale(0.95);
    -ms-transform: scale(0.95);
    transform: scale(0.95);
}

.location-button .feather {
    height: 1em;
    width: auto;
    margin-right: 5px;
}

.wSlider {
    width: 520px;
}
</style>
