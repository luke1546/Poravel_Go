<script setup>
import { onMounted } from "vue";
import { useLocation } from "../../stores/counter.js";
const location = useLocation();

const getImageUrl = () => {
    return new URL(`../../assets/img/pin2.png`, import.meta.url).href;
};

onMounted(() => {
    // 네이버 지도 API 로드
    const script = document.createElement("script");
    script.src = "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fyyx1x1l5l";
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);

    script.onload = () => {
        var position = new naver.maps.LatLng(location.lat, location.lng);

        // 네이버 지도 생성
        var map = new window.naver.maps.Map("map", {
            center: position,
            zoom: 14,
        });
        var urlMarker = new naver.maps.Marker({
            position,
            map: map,
            title: "urlMarker",
            icon: {
                content: '<div div class= "pin" ></div> <div class="pulse"></div>',
                size: new naver.maps.Size(22, 35),
                anchor: new naver.maps.Point(11, 35),
            },
            shape: {
                coords: [
                    11, 0, 9, 0, 6, 1, 4, 2, 2, 4, 0, 8, 0, 12, 1, 14, 2, 16, 5, 19, 5, 20, 6, 23,
                    8, 26, 9, 30, 9, 34, 13, 34, 13, 30, 14, 26, 16, 23, 17, 20, 17, 19, 20, 16, 21,
                    14, 22, 12, 22, 12, 22, 8, 20, 4, 18, 2, 16, 1, 13, 0,
                ],
                type: "poly",
            },
        });
    };
});
</script>

<template>
    <div id="map"></div>
</template>

<style scope>
/* @import url("https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"); */
@import url("./../../assets/css/marker.css");
#map {
    width: 100%;
    height: 400px;
}
</style>
