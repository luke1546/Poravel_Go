<script setup>
import axios from "axios";
import { ref, onMounted, watch } from "vue";
import { useAttractionNo } from "../stores/counter";
const { VITE_BACK_SERVER } = import.meta.env;


const { VITE_ATTRACTION_SERVICE_KEY, VITE_ATTRACTION_URL, VITE_LOCATION, VITE_DETAIL_KEYWORD } =
    import.meta.env;
const radioValue = ref(12);
const attractions = ref({});
const attractionNo = useAttractionNo();

const searchType = ref("keyword"); //검색형태

let pageNo = 1;
let numOfRows = 20;
const totalCount = ref(0);

async function getAttractionInfo() {
    attractions.value = (
        await axios.get(VITE_BACK_SERVER + "attraction/list", {
            params: {},
        })
    ).data;
    searchType.value = "list";
    // totalCount.value = attractions.value?.totalCount;
    console.log(totalCount.value);
    console.log(attractions.value);
}

async function getAttractionKeywordInfo() {
    const keyword = document.getElementById("search").value;
    attractions.value = (
        await axios.get(VITE_BACK_SERVER + "attraction/search", {
            params: {title : keyword},
        })
    ).data;

    searchType.value = "keyword";
    console.log(totalCount.value);
    console.log(attractions.value);
}
// async function getAttractionKeywordInfo() {
//     const keyword = document.getElementById("search").value;
//     attractions.value = (
//         await axios.get(VITE_ATTRACTION_URL + VITE_DETAIL_KEYWORD, {
//             params: {
//                 serviceKey: VITE_ATTRACTION_SERVICE_KEY,
//                 numOfRows,
//                 pageNo,
//                 MobileOS: "ETC",
//                 MobileApp: "AppTest",
//                 _type: "json",
//                 listYN: "Y",
//                 arrange: "A",
//                 keyword,
//                 contentTypeId: 12,
//             },
//         })
//     ).data?.response?.body;

//     searchType.value = "keyword";
//     totalCount.value = attractions?.value?.totalCount;
//     attractions.value = attractions?.value?.items?.item;
//     console.log(totalCount.value);
//     console.log(attractions.value);
// }

watch(
    () => searchType.value,
    () => {
        pageNo = 1;
    }
);

watch(
    () => radioValue.value,
    () => {
        getAttractionInfo();
    }
);

function paging(i) {
    console.dir(totalCount.value / numOfRows);
    if (pageNo + i > 0 && pageNo + i < totalCount.value / 20 + 1) pageNo += i;

    if (searchType.value === "keyword") {
        getAttractionKeywordInfo();
    } else if (searchType.value === "list") {
        getAttractionInfo();
    }
}

// attraction.title
// attraction.addr1
// attraction.firstimage
// attractionNo.getNo(attraction.contentid) 이미지클릭시이동
// <router-link to="/attractionDetail"></router-link>
const loading = ref(false);
onMounted(() => {
    loading.value = true;
});
getAttractionInfo();
</script>

<template>
    <div id="full" v-show="loading">
        <div class="cover">
            <h1>Discover what's out there.</h1>
            <div class="flex-form" @keyup.enter="getAttractionKeywordInfo()">
                <label for="from">
                    <i class="ion-location"></i>
                </label>
                <input id="search" type="search" placeholder="Where do you want to go?" />
                <input type="submit" value="Search" @click="getAttractionKeywordInfo()"/>
            </div>
        </div>

        <!-- radiobutton -->
    <fieldset class="taglist">
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="12" id="attraction" class="radio" v-model="radioValue">
        <label for="attraction" class="label" :class="{'active':radioValue==12}">#관광지</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="14" id="culture" class="radio" v-model="radioValue">
        <label for="culture" class="label" :class="{'active':radioValue==14}">#문화시설</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="15" id="festival" class="radio" v-model="radioValue">
        <label for="festival" class="label" :class="{'active':radioValue==15}">#축제공연행사</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="25" id="course" class="radio" v-model="radioValue">
        <label for="course" class="label" :class="{'active':radioValue==25}">#여행코스</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="28" id="leports" class="radio" v-model="radioValue">
        <label for="leports" class="label" :class="{'active':radioValue==28}">#레포츠</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="32" id="lodgment" class="radio" v-model="radioValue">
        <label for="lodgment" class="label" :class="{'active':radioValue==32}">#숙박</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="38" id="shopping" class="radio" v-model="radioValue">
        <label for="shopping" class="label" :class="{'active':radioValue==38}">#쇼핑</label>
      </div>
      <div class="btn">
        <input type="radio" name="dark-taglist-radio" value="39" id="restaurant" class="radio" v-model="radioValue">
        <label for="restaurant" class="label" :class="{'active':radioValue==39}">#음식점</label>
      </div>
    </fieldset>
        <!-- radiobutton -->

        <div class="masonry">
            <div class="grid" v-for="attraction in attractions">
                <!-- <div v-if="totalCount==0">검색결과가 없습니다.</div> -->
                <div v-if="attraction?.firstImage">
                    <img class="img fade" :src="attraction?.firstImage" />
                    <div class="grid__body">
                        <div class="relative">
                            <router-link to="/attractionDetail"
                                ><a
                                    class="grid__link"
                                    target="_blank"
                                    href="#"
                                    @click="attractionNo.getNo(attraction.contentId)"
                                ></a
                            ></router-link>
                            <h1 class="grid__title">{{ attraction.title }}</h1>
                            <p class="grid__author">{{ attraction.addr1 }}</p>
                        </div>
                        <div class="mt-auto">
                            <!-- <span class="grid__tag"></span> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="pageNav" style="text-align: center">
            <span class="dot" @click="paging(-1)"></span>
            <span class="dot" @click="paging(1)"></span>
        </div>
    </div>
</template>

<style scoped>
@import url("../assets/css/attractioninfo.css");
@import url("../assets/css/search.css");
@import url("https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css");

@import "https://unpkg.com/open-props" layer(design.system);

#full {
    position: relative;
    margin: 0;
    background: url("https://source.unsplash.com/G8rRItjrwkA") no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    background-size: cover;
    color: #0a0a0b;
    overflow: hidden;
    min-height: 125vh;
    text-align: center;
}
.masonry {
    display: inline-block;
    margin: 75px 0px;
    width: 70%;
}
.grid {
    transition: all 0.2s linear;
    text-align: left;
}
.grid:hover {
    transform: scale(1.2);
    z-index: 10;
}

.dot {
    /* position: ab; */
    cursor: pointer;
    height: 30px;
    width: 30px;
    margin: 0 5px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
    transition: all 0.2s linear;
}
.dot:hover {
    background-color: #717171;
    transform: scale(1.5);
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

.pageNav {
    position: absolute;
    left: 50%;
    bottom: 20px;
}

.label{
  color: white;
  background-color: #343a40;
  border-radius: 50px;
  padding-inline: var(--size-3);
  padding-block: var(--size-1);
  cursor: pointer;
}
.active{
  color: black;
  background-color: #91a7ff;
}
.radio{
  appearance: none;
  background: none;
  border: none;
  inline-size: 100%;
  block-size: 100%;
}
.radioGrid {
  display: grid;
  align-items: center;
  grid-auto-flow: column;
  grid-template-columns: auto 1fr;
}
.taglist {
    margin: auto;
  padding: 0;
  border: none;
  display: flex;
  flex-wrap: wrap;
  align-items: start;
  gap: var(--size-2);
  max-inline-size: var(--size-content-2);
}
/* radio */

.btn:hover{
    transform:scale(1.2);
     transition: transform .5s;
     object-fit:cover;
}
</style>
