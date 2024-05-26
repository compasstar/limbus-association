<script setup lang="ts">

import {onMounted, ref, type Ref} from "vue";
import axios from "axios";
import type {Identity} from "@/types/Identity";

const sinners = ["이상", "파우스트", "돈키호테", "로슈", "뫼르소", "홍루", "히스클리프", "이스마엘", "로쟈", "싱클레어", "오티스", "그레고르"];

const identities: Ref<Identity[]> = ref([]);
onMounted(() => {
  axios.get(`/api/identities`)
      .then((response) => {
        identities.value = response.data.data;
      });
})

const searchInput = ref('');
const search = () => {
  axios.get(`/api/identities/search`, {
    params: {
      name: searchInput.value,
    }
  })
      .then((response) => {
        identities.value = response.data.data;
      })
}

</script>

<template>
  <h2>수감자 목록</h2>

  <div class="sinners">
    <el-button v-for="(sinner) in sinners">{{sinner}}</el-button>
  </div>

  <div class="mt-3">
    <el-input v-model="searchInput" style="width: 240px" placeholder="수감자 검색"/>
    <Search class="search-input" @click="search()"></Search>
  </div>

  <ul>
    <li v-for="(identity) in identities">
      <router-link v-bind:to="`/identities/${identity.englishName}`">{{ identity.name }}</router-link>
    </li>
  </ul>


</template>

<style scoped lang="scss">
.sinners {
  display: grid;
  grid-template-columns: 100px 100px 100px 100px 100px 100px;
}

.search-input {
  position: relative;
  top: 3px;
  left: -27px;
  width: 1.2rem;
  height: 1.2rem;
}

.el-button{
  width: 100px;
  height: 40px;
}
</style>