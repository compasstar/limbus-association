<script setup lang="ts">

import {defineProps, ref} from "vue";
import axios from "axios";

const props = defineProps({
  identityId: {
    type: [Number, String],
    require: true,
  },
});

const identity = ref({});

axios.get(`/api/identities/${props.identityId}`)
    .then((response) => { identity.value = response.data;
    console.log(`${props.identityId}`);
    console.log(identity.value);
    });
</script>


<template>
  <h1>Hello World!</h1>

  <div>{{ identity.name }}</div>
  <div>
    <div class="status">
      <ul>
        <li>{{identity.status.hp}}</li>
        <li>{{identity.status.minSpeed}}</li>
        <li>{{identity.status.maxSpeed}}</li>
      </ul>
    </div>
    <div class="resistances">
      <ul>
        <li>{{identity.resistances.slashResistance}}</li>
        <li>{{identity.resistances.pierceResistance}}</li>
        <li>{{identity.resistances.bluntResistance}}</li>
      </ul>
    </div>
  </div>

  <div class="offenseSkills">
    <div class="offenseSkill">
      <div class="slot">
        {{identity.offenseSkills.slot}}
      </div>
      <div class="content">

      </div>

    </div>

  </div>
</template>


<style scoped>

</style>