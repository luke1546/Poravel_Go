<script setup>
import { ref, onMounted } from "vue";
import { RouterLink, useRouter } from 'vue-router'
import axios from 'axios';
import { useBoard } from "../../stores/counter"
import { jwtDecode } from "jwt-decode";

const { VITE_BACK_SERVER } = import.meta.env;

const name = ref(jwtDecode(sessionStorage.getItem("accessToken")).userId);

const pageNo = ref(1);

const boardList = ref({});
const pageInfo = ref({});

async function getBoardList() {
    boardList.value = (await axios.get(VITE_BACK_SERVER + "board/list/" + pageNo.value, {})).data;
    pageInfo.value = boardList.value.pg;
    boardList.value = boardList.value.list;
    console.dir(pageInfo.value);
}
getBoardList();

const router = useRouter();
function goDetail(i) {
    router.push("BoardDetail");
    useBoard().getNo(i);
}
function paging(i) {
    if (
        pageNo.value + i >= pageInfo.value.beginPage &&
        pageNo.value + i <= pageInfo.value.endPage
    ) {
        pageNo.value += i;
        getBoardList();
    }
}
const userId = ref(jwtDecode(sessionStorage.getItem("accessToken")).userId);
</script>

<template>
  <div>.
  </div>
  <div id="right_panel">
    <h3>자유게시판</h3>
   <router-link to="BoardWrite"><div href="#" class="user__edit">글쓰기</div></router-link>
  <div class="wrap-table">
    <table>
      <thead>
        <tr>
          <th scope="col" class="sticky-col" width="60">글번호</th>
          <th scope="col" width="500">제목</th>
          <th scope="col">글쓴이</th>
          <th scope="col" width="40">조회</th>
          <th scope="col" class="sticky-col sticky-col-right">추천</th>
        </tr>
      </thead>
                <tbody>
                    <tr v-for="(board, index) in boardList" :key="index">
                        <td class="pointer" @click="goDetail(board.boardNo)">
                            <p>{{ board.boardNo }}</p>
                        </td>
                        <td class="title pointer" @click="goDetail(board.boardNo)">
                            {{ board.subject }}
                        </td>

                        <td>
                            <div class="user__info">
                                <img
                                    class="user__photo"
                                    src="https://www.shutterstock.com/image-vector/cute-adorable-vector-illustration-creature-260nw-2313970719.jpg"
                                    alt=""
                                />
                                <div>
                                    <div style="line-height: 30px" class="user__name">
                                        {{ name }}🐦 |
                                        <span class="date">{{
                                            board.registerTime.slice(2, -3).replaceAll("-", ".")
                                        }}</span>
                                    </div>
                                    <div>
                                        <meter
                                            style="display: inline-block"
                                            id="fuel"
                                            min="0"
                                            max="100"
                                            low="33"
                                            high="66"
                                            optimum="80"
                                            value="100"
                                        >
                                            at 50/100
                                        </meter>
                                        151/151
                                    </div>
                                </div>
                            </div>
                        </td>

                        <td>{{ board.hit }}</td>
                        <td>{{ board.heart }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <button id="toggle" class="toggle2" type="button" @click="paging(-1)">
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
    <button id="toggle" class="toggle1" type="button" @click="paging(1)">
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
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@100;400;500;600&display=swap");
@import url("../../assets/css/output.css");
body {
    justify-content: center;
    color: #111827;
}

.wrap-table {
    display: grid;
    grid-template-columns: 1fr;
    border: 1px solid #e5e7eb;
    border-radius: 12px;
    overflow-x: auto;
    width: 100%;
    container-type: inline-size;
    container-name: table;
}

table {
    border-collapse: collapse;
    width: 100%;
    font-size: 14px;
}

th {
    font-weight: 700;
    color: #374151;
    font-size: 14px;
    padding: 12px 0;
    background: #f9fafb;
    border-bottom: 1px solid #e5e7eb;
    text-align: center;
}

td {
    text-align: center;
    padding: 10px 0;
    border-bottom: 1px solid #e5e7eb;
    background: #fff;
}

.user__photo {
    width: 48px;
    height: 48px;
    object-fit: cover;
    border-radius: 100px;
}

.user__info {
    display: flex;
    justify-content: center;
    gap: 12px;
}

.user__name {
    font-size: 14px;
    font-weight: 600;
    color: #111827;
}

meter {
    width: 100px;
}
meter::-webkit-meter-optimum-value {
    background: #10b981;
}

/* progress {
  display: block;
  width: 100px;
  height: 7px;
  -webkit-appearance: none;
  border-radius: 10px;
}

progress::-webkit-progress-bar {
  background-color: #eee;
  border-radius: 10px;
}

progress::-webkit-progress-value {
  background-color: #10b981;
  border-radius: 10px;
} */

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
}

#right_panel {
    margin: auto;
    width: 1000px;
    margin-top: 100px;
    position: relative;
}

.title {
    text-align: left;
}
a {
    text-decoration: none;
}
.title:hover {
    text-decoration: underline;
}

.date {
    color: gray;
    font-size: 12px;
}
.pointer:hover {
    cursor: pointer;
}

.toggle1 {
    left: calc(50% + 25px);
    transform: rotate(90deg);
}
.toggle2 {
    left: calc(50% - 25px);
    transform: rotate(270deg);
}

#toggle {
    z-index: 30;
    position: absolute;
    /* top: 45%; */
    width: 50px;
    transition: transform 0.5s ease-in-out;
    fill: gray;
}
h3{
    text-align: center;
    font-size: 30px;
    margin: 20px 0;
}
</style>
