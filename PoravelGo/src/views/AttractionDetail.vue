<script setup>
import axios from "axios";
import { ref } from "vue";
import { useAttractionNo } from "../stores/counter";
import { useLocation } from "../stores/counter";
import NaverMap from "../components/common/NaverMap.vue";

const { VITE_ATTRACTION_SERVICE_KEY, VITE_ATTRACTION_URL, VITE_DETAIL, VITE_DETAIL_IMG } =
    import.meta.env;

const attraction = ref({});
const attractionImgs = ref({});
const attractionNo = useAttractionNo();
const cur = ref(0);
const isMapLoad = ref(false);

async function getDetailInfo() {
    console.dir(attractionNo.no);
    attraction.value = (
        await axios.get(VITE_ATTRACTION_URL + VITE_DETAIL, {
            params: {
                serviceKey: VITE_ATTRACTION_SERVICE_KEY,
                MobileOS: "ETC",
                MobileApp: "AppTest",
                _type: "json",
                contentId: attractionNo.no,
                defaultYN: "Y",
                firstImageYN: "Y",
                areacodeYN: "Y",
                catcodeYN: "Y",
                addrinfoYN: "Y",
                mapinfoYN: "Y",
                overviewYN: "Y",
            },
        }).catch(() => {
            console.dir(1);
        }
        )
    ).data?.response?.body?.items?.item[0];
    const location = useLocation();
    location.lat = attraction.value.mapy;
    location.lng = attraction.value.mapx;
    isMapLoad.value = true;
}
async function getDetailImg() {
    attractionImgs.value = (
        await axios.get(VITE_ATTRACTION_URL + VITE_DETAIL_IMG, {
            params: {
                serviceKey: VITE_ATTRACTION_SERVICE_KEY,
                MobileOS: "ETC",
                MobileApp: "AppTest",
                _type: "json",
                contentId: attractionNo.no,
                imageYN: "Y",
                subImageYN: "Y",
                numOfRows: 10,
                pageNo: 1,
            },
        })
    ).data?.response?.body?.items?.item;
}
getDetailInfo();
getDetailImg();
function paging(no) {
    if (cur.value + no >= 0 && cur.value + no < attractionImgs.value.length) cur.value += no;
}

// 카카오지도
</script>

<template>
    <div class="container">
        <div class="top">
            <h1>{{ attraction.title }}</h1>
            <div class="addr">{{ attraction.addr1 }}</div>
            <!-- <h2 class="subTitle"><hr></h2> -->
            <hr style="margin: 20px" />
        </div>
        <div class="mid">
            <div v-for="(attractionImg, index) in attractionImgs">
                <img
                    class="curImg1"
                    :class="{ curImg2: index === cur }"
                    :src="attractionImg.originimgurl"
                    alt=""
                />
            </div>
            <div class="pageNav" style="text-align: center">
                <span class="dot" @click="paging(-1)"></span>
                <span class="dot" @click="paging(1)"></span>
            </div>
        </div>

        <div class="detail">
            <h2>상세정보</h2>
            <hr style="margin-bottom: 20px;"/>
            <div v-html="attraction.overview" style="margin-bottom: 40px;"></div>
            <div v-if="isMapLoad"><NaverMap /></div>
            <div class="infomation"></div>
        </div>

        <div id="map" style="width: 500px; height: 400px"></div>
    </div>
</template>

<style scoped>
.container {
    width: 55%;
    margin: auto;
}
.top {
    text-align: center;
    margin-top: 100px;
}
.mid {
    text-align: center;
}
.addr {
    color: gray;
}
.infomation {
    display: grid;
}
.curImg1 {
    display: none;
    height: 500px;
    margin-top: 50px;
}
.curImg2 {
    display: inline;
}
.detail {
    margin-top: 50px;
}

/* slide */
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
/* slide */
</style>
