import { createRouter, createWebHistory } from "vue-router";
import MyPage from "../components/user/MyPage.vue";
import MyPage_PokemonDetail from "../components/user/MyPage_PokemonDetail.vue";
import MainView from "../views/MainView.vue";
import AttractionInfo from "../views/AttractionInfo.vue";
import AttractionDetail from "../views/attractionDetail.vue";
import Chat from "../components/common/ChatModal.vue";
import BoardList from "../components/board/boardList.vue";
import BoardWrite from "../components/board/boardWrite.vue";
import test from "@/components/common/chattest.vue";
import BoardDetail from "@/components/board/BoardDetail.vue"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "main",
            component: MainView,
        },
        {
            path: "/regist",
            name: "regist",
            component: () => import("../views/RegistUser.vue"),
        },
        {
            path: "/findpass",
            name: "findpass",
            component: () => import("../views/FindPassword.vue"),
        },
        {
            path: "/mypage",
            name: "mypage",
            component: MyPage,
        },
        {
            path: "/mypagepokemondetail",
            name: "mypagepokemondetail",
            component: MyPage_PokemonDetail,
        },
        {
            path: "/attractioninfo",
            name: "attractioninfo",
            component: AttractionInfo,
        },
        {
            path: "/attractionDetail",
            name: "attractionDetail",
            component: AttractionDetail,
        },
        {
            path: "/chat",
            name: "chat",
            component: Chat,
        },
        {
            path: "/boardList",
            name: "boardList",
            component: BoardList,
        },
        {
            path: "/boardWrite",
            name: "boardWrite",
            component: BoardWrite,
        },
        {
            path: "/test",
            name: "test",
            component: test,
        },
        {
            path: "/boardDetail",
            name: "boardDetail",
            component: BoardDetail,
        },
    ],
});

export default router;
