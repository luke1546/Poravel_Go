<script setup>
import { ref, onMounted, onBeforeMount, onUpdated, onBeforeUpdate } from "vue";
import { usePokemonNo } from "../../stores/counter.js";
import axios from "axios";

const { VITE_BACK_SERVER } = import.meta.env;
const pokemons = ref([[], []]);
const checked = ref(false);

const numOfRows = 16;
// console.log(rootStyles.getPropertyValue("--num"))

function toggle() {
    checked.value = !checked.value;
}
// https://pokeapi.co/api/v2/pokemon/1

let index = 0;

var gotPokemon = new Array(151);
async function getBoardList() {
    gotPokemon.fill(false);
    const pokemons = (await axios.get(VITE_BACK_SERVER + "pokemon/" + 151)).data;
    pokemons.forEach((element) => {
        gotPokemon[element.pokemonNo] = true;
        console.dir(element.pokemonNo);
    });
}

getBoardList();

onBeforeUpdate(() => {
    console.dir(pokemons.value);
});

function getPokemonInfo() {
    for (let i = index * numOfRows + 1; i < (index + 1) * numOfRows + 1; i++) {
        pokemons.value[0].push(i);
    }
}
getPokemonInfo();

function paging(n) {
    index += n;
    if (index + n < 0) index += 10;
    else if (index + n >= 10) index -= 10;
    pokemons.value[0].length = 0;
    getPokemonInfo();
}

// showSlides(1);
const getImageUrl = (name) => {
    return new URL(`../../assets/img/pokemon/${name}.gif`, import.meta.url).href;
};

const getDarkImageUrl = (name) => {
    return new URL(`../../assets/img/blackpokemon/${name}.png`, import.meta.url).href;
};

const piniaPokemonNo = usePokemonNo();
function getPokemonNo(no) {
    piniaPokemonNo.no = no;
}
</script>

<template>
    <div class="layer-1 layer z-40" :class="{ move1: checked === true }">
        <div class="profile">
            <h2>마이페이지</h2>
            <p>반가워요! 🌞</p>
        </div>

        <div class="schedule-table flex flex-col">
            <h2>내 포켓몬</h2>
            <div class="characters mySlides fade">
                <div class="character" v-for="(pokemon, index) in pokemons[0]" :key="index">
                    <img
                        v-if="!gotPokemon[index]"
                        class="staticImg dark"
                        :src="getDarkImageUrl(pokemon)"
                        alt=""
                        @click="getPokemonNo(pokemon)"
                    />
                    <img
                        v-else
                        class="staticImg"
                        :src="getImageUrl(pokemon)"
                        alt=""
                        @click="getPokemonNo(pokemon)"
                    />
                </div>
            </div>

            <div class="pageNav" style="text-align: center">
                <span class="dot" @click="paging(-1)"></span>
                <span class="dot" @click="paging(1)"></span>
            </div>
        </div>
        <div class="calories flex flex-col">
            <h2>내 정보😊</h2>

            <div class="representativeCharacter">
                <router-link to="/mypagepokemondetail"
                    ><img :src="getImageUrl(piniaPokemonNo.no)" alt="" style="margin: auto"
                /></router-link>
            </div>
        </div>

        <div class="challenges relative flex flex-col">
            <h2>메모장✈️</h2>
            <div class="wrapper flex grow overflow-hidden">
                <div class="contents flex grow overflow-scroll snap-mandatory snap-x">
                    <div
                        id="challenge-1"
                        class="snap-start my-auto grow shrink-0 max-w-full text-center h-min"
                    >
                        ㅇㄴㄹㄴㅇㄹ
                    </div>
                    <!-- <div
                        id="challenge-2"
                        class="snap-start my-auto grow shrink-0 max-w-full text-center h-min">
                        <span></span>
                    </div>

                    <div
                        id="challenge-3"
                        class="snap-start my-auto grow shrink-0 max-w-full text-center h-min">
                        <span></span>
                    </div> -->
                </div>
            </div>
            <nav class="block mx-auto">
                <a class="h-2 w-2 inline-block bg-primary-3 rounded-full" href="#challenge-1"> </a>
                <a class="h-2 w-2 inline-block bg-primary-2 rounded-full" href="#challenge-2"> </a>
                <a class="h-2 w-2 inline-block bg-primary-3 rounded-full" href="#challenge-3"> </a>
            </nav>
        </div>
    </div>

    <div class="layer layer-2" :class="{ move2: checked === true }">
        <div class="exercise-table">
            <h2>진행중인 미션</h2>

            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/담양.jpg" alt="" />
                    <div class="flex justify-between grow">
                        <span class="mission">다음 관광지 배경 인증샷 찍기📷</span>
                    </div>

                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="lex flex-col gap-2">
                <div class="missionContainer">
                    <div><img class="missionImg" src="@/assets/img/거제.jpg" alt="" /></div>
                    <div class="flex justify-between grow">
                        유료 관광지 혹은 유로체험💎 최소 1곳 이상 방문하고 영수증 첨부하기🧾
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/경기.jpg" alt="" />
                    <div class="flex justify-between grow">
                        <span>해당 구조물이🗽 근처에 있는 카페에 방문하여 인증샷 찍기📷</span>
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/광화문.jpg" alt="" />
                    <div class="flex justify-between grow">
                        <span>🏨숙박업소 최소 1곳 이상 방문하고 영수증 첨부하기🧾</span>
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/천주산.jfif" alt="" />
                    <div class="flex justify-between grow">
                        <span
                            >개인 SNS에📱 필수 #해시태그와 선택미션😳 1가지 이상 수행한 내용으로
                            100자 이상 후기글 쓰기❣️</span
                        >
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/양평.jpg" alt="" />
                    <div class="flex justify-between grow">
                        <span>해당 지역 특산물🍝 먹고 가게에 리뷰 남기기⭐️</span>
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
            <div class="flex flex-col gap-2">
                <div class="missionContainer">
                    <img class="missionImg" src="@/assets/img/부산.jpg" alt="" />
                    <div class="flex justify-between grow">
                        <span>국토종주 자전거길🚲︎ 여행 인증수첩 완성하기📒</span>
                    </div>
                    <div href="#" class="user__edit">보상받기</div>
                </div>
            </div>
        </div>
        <button
            id="toggle"
            :class="{ toggle1: checked === true, toggle2: checked === false }"
            type="button"
            @click="toggle()"
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
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300;400;500;600;700&display=swap");
@import url("../../../dist/output.css");
@import url("../../assets/css/mypage.css");

.dot {
    /* position: ab; */
    cursor: pointer;
    height: 15px;
    width: 15px;
    margin: 0 2px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
}
.dot:hover {
    background-color: #717171;
}
/* Fading animation */
.fade {
    animation-name: fade;
    animation-duration: 1.5s;
}

@keyframes fade {
    from {
        opacity: 0.4;
    }
    to {
        opacity: 1;
    }
}
.representativeCharacter {
    margin: 40px;
}
.missionImg {
    width: 200px;
    height: 120px;
    border-radius: 20px;
}

.missionContainer {
    display: grid;
    grid-template-columns: 2fr 4fr 1fr;
    background-color: rgb(217, 232, 233);
    align-items: center;
    justify-items: center;
    margin: 10px 0;
    padding: 20px 0;
    border-radius: 15px;
}

.user__edit {
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
    margin-bottom: 10px;
}
.user__edit:hover {
    background: #3d3eaa;
    cursor: pointer;
}
.dark {
    height: 10vh;
    filter: grayscale(100%);
}
</style>
