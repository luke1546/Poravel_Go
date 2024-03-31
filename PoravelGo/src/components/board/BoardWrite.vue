<script setup>
import { RouterLink, useRouter } from "vue-router";
import { useBoard } from "../../stores/counter";
import { onMounted, ref } from "vue";
import axios from "axios";
const router = useRouter();
const { VITE_BACK_SERVER } = import.meta.env;

const subject = ref(null);
const content = ref(null);

onMounted(() => {});

async function writeBoard() {
    await axios.post(VITE_BACK_SERVER + "board/register", {
        subject: subject.value.value,
        content: content.value.value,
        memberNo: 1,
    });
    router.push("boardList");
}
</script>

<template>
    <div>.</div>
    <div id="page-wrapper" class="clearfix">
        <form action="#" method="POST" id="file-form">
            <div class="field">
                <div>제목</div>
                <input type="text" name="title" id="title" placeholder="Title" ref="subject" />
            </div>
            <div class="field">
                <div>내용</div>
                <textarea
                    name="content"
                    id="content"
                    placeholder="Type your content here..."
                    ref="content"
                ></textarea>
            </div>
            <div class="field field1">
                <router-link to="BoardList"><button>취소</button></router-link>
                <button type="button" @click="writeBoard()">등록</button>
                <!-- <div id="messages">??</div> -->
            </div>
        </form>

        <!-- <div id="files">
      <h2>File Browser</h2>
      <ul id="file-list"></ul>
    </div> -->
    </div>
</template>

<style scoped>
*,
*:before,
*:after {
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

html {
    font-family: Helvetica, Arial, sans-serif;
    font-size: 100%;
    background: #333;
    color: #33383d;
    -webkit-font-smoothing: antialiased;
}

#page-wrapper {
    width: 700px;
    background: #fff;
    padding: 1.25rem;
    margin: 1rem auto;
    min-height: 300px;
    border-top: 5px solid #69c773;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.8);
    margin-top: 200px;
    border-radius: 30px;
}

h1 {
    margin: 0;
}

h2 {
    margin-top: 0;
    font-size: 0.9rem;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: #999;
}

p {
    font-size: 0.9rem;
    margin: 0.5rem 0 1.5rem 0;
}

a,
a:visited {
    color: #08c;
    text-decoration: none;
}

a:hover,
a:focus {
    color: #69c773;
    cursor: pointer;
}

a.delete-file,
a.delete-file:visited {
    color: #cc0000;
    margin-left: 0.5rem;
}

#file-form {
    width: 650px;
    float: left;
}

.field {
    margin-bottom: 1rem;
}
.field1 {
    text-align: right;
}

input,
textarea {
    width: 100%;
    padding: 0.5rem;
    font-size: 1rem;
    border: 1px solid #d9d9d9;
    border-radius: 10px;
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.1);
}

textarea {
    min-height: 300px;
}

button {
    display: inline-block;
    border-radius: 10px;
    border: none;
    font-size: 0.9rem;
    padding: 0.6rem 1em;
    background: #86b32d;
    border-bottom: 1px solid #5d7d1f;
    color: white;
    margin: 0 0.25rem;
    text-align: center;
}

button:hover {
    opacity: 0.75;
    cursor: pointer;
}

#files {
    width: 230px;
    float: right;
}

#files ul {
    margin: 0;
    padding: 0.5rem 1rem;
    height: 330px;
    overflow-y: auto;
    list-style: none;
    background: #f7f7f7;
    border: 1px solid #d9d9d9;
    border-radius: 3px;
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.1);
}

#files li {
    padding: 0.5rem 0;
}

#messages {
    display: inline-block;
    font-weight: bold;
    color: #69c773;
    margin-left: 1rem;
}

/* Clearfix Utils */

.clearfix:before,
.clearfix:after {
    display: table;
    line-height: 0;
    content: "";
}

.clearfix:after {
    clear: both;
}
</style>
