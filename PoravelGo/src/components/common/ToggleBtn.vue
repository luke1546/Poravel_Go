<script setup></script>

<template>
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
</template>

<style scoped>
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
</style>
