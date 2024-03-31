import { localAxios } from "@/util/http-commons";

const local = localAxios();
// chat manage
async function userList(roomId, success, fail) {
    await local.get(`/chat/userlist`).then(success).catch(fail);
}

async function isDuplicateName(param, success, fail) {
    await local.get(`/chat/duplicateName`).then(success).catch(fail);
}

// chatroom manage

async function createRoom(param, success, fail) {
    await local.post(`/chat/createroom`).then(success).catch(fail);
}

async function roomDetail(roomId, success, fail) {
    await local.get(`/chat/room`).then(success).catch(fail);
}

async function confirmPwd(roomId, success, fail) {
    await local.post(`/chat/confirmPwd/${roomId}`).then(success).catch(fail);
}

async function delChatRoom(roomId, success, fail) {
    await local.get(`/chat/delRoom/${roomId}`).then(success).catch(fail);
}

async function chUserCnt(roomId, success, fail) {
    await local.get(`/chat/delRoom/${roomId}`).then(success).catch(fail);
}

export { userList, isDuplicateName, createRoom, roomDetail, confirmPwd, delChatRoom, chUserCnt };
