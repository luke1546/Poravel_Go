<script setup>
import axios from "axios";
import { ref } from "vue";
import { usePokemonNo } from "../../stores/counter.js";

const pokemonData = ref({});
const pokemon = ref({});

const pokemonNo = usePokemonNo();
const getTypeImgUrl = (name) => {
    return new URL(`../../assets/img/pokemon/types/${name}.svg`, import.meta.url).href;
};
async function getPokemonInfo() {
    pokemon.value = (await axios.get(`https://pokeapi.co/api/v2/pokemon/${pokemonNo.no}`)).data;
    const speciesResponse = await axios.get(
        `https://pokeapi.co/api/v2/pokemon-species/${pokemonNo.no}`
    );

    const pokemonImg = pokemon.value.sprites.other["official-artwork"]["front_default"]; //이미지
    const koreanName = speciesResponse.data.names.find((name) => name.language.name === "ko"); //한국어 이름
    const description = speciesResponse.data["flavor_text_entries"].find(
        (desc) => desc.language.name === "ko"
    ); //한국어 설명

    const genera = speciesResponse.data["genera"].find((gene) => gene.language.name === "ko"); //속
    pokemonData.value = {
        id: pokemon.value.id,
        pokemonImg,
        koreanName: koreanName.name,
        description: description.flavor_text,
        speciesResponse,
        genera: genera.genus,
        height: pokemon.value.height / 10,
        weight: pokemon.value.weight / 10,
        types: pokemon.value.types,
    };
}
getPokemonInfo();
</script>

<template>
    <div style="margin-bottom: 200px">　</div>
    <div class="characterBox">
        <div class="character">
            <img :src="pokemonData.pokemonImg" alt="" />
        </div>

        <div style="margin: 40px">
            <div class="top">
                <div class="pokemonNo">No. {{ pokemonData.id }}</div>
                <div class="name">{{ pokemonData.koreanName }}</div>
                <div class="description">
                    {{ pokemonData.description }}
                </div>
            </div>
            <div class="status">
                <div>
                    <div class="type">타입</div>
                    <span v-for="type in pokemonData.types"
                        ><img class="typeImg" :src="getTypeImgUrl(type.type.name)" alt=""
                    /></span>
                </div>
                <div>
                    <div class="weight">키</div>
                    <div class="">{{ pokemonData.height }}m</div>
                </div>
                <div>
                    <div class="weight">분류</div>
                    <div class="">{{ pokemonData.genera }}</div>
                </div>
                <div>
                    <div class="weight">성별</div>
                    <div class="">♂ ♀</div>
                </div>
                <div>
                    <div class="weight">몸무게</div>
                    <div class="">{{ pokemonData.weight }}kg</div>
                </div>
                <div></div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.characterBox {
    display: grid;
    grid-template-columns: 1fr 1fr;
    border: 1px solid black;
    border-radius: 10px;
    width: 60%;
    /* height: 800px; */
    margin: auto;
    background-color: ivory;
    text-align: center;
}
.status {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    margin-top: 30px;
    border: 1px solid lightgray;
    border-radius: 10px;
    height: 200px;
}
.status > div {
    margin: 20px;
}
.status > div > div:first-child {
    color: gray;
    margin-bottom: 10px;
}
.pokemonNo {
    color: gray;
}
.name {
    font-size: 40px;
    margin: 0;
    padding: 0;
}
.description {
    margin-top: 20px;
    text-align: left;
}

.status {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}
.character > img {
    width: 250px;
}
.character {
    margin-top: 17%;
}
.top {
    /* text-align: left; */
}
.typeImg {
    width: 30px;
}
</style>
