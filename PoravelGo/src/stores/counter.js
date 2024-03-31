import { ref } from "vue";
import { defineStore } from "pinia";

export const useAttractionNo = defineStore(
    "AttractionNo",
    () => {
        const no = ref(0); //state
        function getNo(x) {
            //actions
            no.value = x;
        }
        return { no, getNo };
    },
    { persist: true }
);

export const usePokemonNo = defineStore(
    "pokemonNo",
    () => {
        const no = ref(1); //state
        return { no };
    },
    { persist: true }
);

export const useLocation = defineStore(
    "location",
    () => {
        const lat = ref(37.5670135); //state
        const lng = ref(126.978374); //state
        return { lat, lng };
    },
    { persist: true }
);

export const useChatClose = defineStore(
    "close",
    () => {
        const isClosed = ref(false);
        function changeState() {
            isClosed.value = !isClosed.value;
        }
        function clsoeModal() {
            isClosed.value = false;
        }
        return { isClosed, changeState, clsoeModal };
    },
    { persist: true } //새로고침해도 isclosed변수가 유지됨
);

export const useBoard = defineStore(
    "boardNo",
    () => {
        const no = ref(1); //state
        function getNo(i) {
            no.value = i;
        }
        return { no, getNo };
    },
    { persist: true }
);