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
  <div class="name">
    <h1>{{identity.name}} (희귀도: {{identity.rarity}})</h1>
  </div>

  <div class="status">
    <ul>
      <li>HP: {{identity.status.hp}}</li>
      <li>speed: {{identity.status.minSpeed}} ~ {{identity.status.maxSpeed}}</li>
    </ul>
  </div>

  <div class="resistances">
    <ul>
      <li>참격내성: {{identity.resistances.slashResistance}}</li>
      <li>관통내성: {{identity.resistances.pierceResistance}}</li>
      <li>타격내성: {{identity.resistances.bluntResistance}}</li>
    </ul>
  </div>

  <div class="sanity">
    <ul>
      <li>패닉: {{identity.sanity.panic}}</li>
      <li>정신력증가조건: {{identity.sanity.factorsIncreasingSanity}}</li>
      <li>정신력감소조건: {{identity.sanity.factorsDecreasingSanity}}</li>
    </ul>
  </div>

  <div class="offenseSkills">
    <h2>공격스킬</h2>
    <div class="offenseSkill1">
      <ul>
        <li>슬롯: {{identity.offenseSkills[0].slot}}</li>
        <li>이름: {{identity.offenseSkills[0].name}}</li>
        <li>공격레벨: {{identity.offenseSkills[0].level}}</li>
        <li>공격타입: {{identity.offenseSkills[0].offenseType}}</li>
        <li>죄악타입: {{identity.offenseSkills[0].sinType}}</li>
        <li>개수: {{identity.offenseSkills[0].amount}}</li>
        <li>기본위력: {{identity.offenseSkills[0].skillPower}}</li>
        <li>코인위력: {{identity.offenseSkills[0].coinPower}}</li>
        <li>코인개수: {{identity.offenseSkills[0].coinNumber}}</li>
        <li>가중치: {{identity.offenseSkills[0].weight}}</li>
        <li>효과: {{identity.offenseSkills[0].effect}}</li>


        <div class="offenseSkill1CoinEffects">
          <h3>코인별효과</h3>
          <ul>
            <li>코인: {{identity.offenseSkills[0].offenseSkillCoinEffects[0].coin}}</li>
            <li>효과: {{identity.offenseSkills[0].offenseSkillCoinEffects[0].effect}}</li>
          </ul>

        </div>


      </ul>
    </div>

    <div class="offenseSkill2">

    </div>

    <div class="offenseSkill3">

    </div>
  </div>

  <div class="defenseSkills">
    <h2>수비스킬</h2>
    <div class="defenseSkill1">
      <ul>
        <li>이름: {{identity.defenseSkills[0].name}}</li>
        <li>수비레벨: {{identity.defenseSkills[0].level}}</li>
        <li>타입: {{identity.defenseSkills[0].defenseType}}</li>
        <li>타입: {{identity.defenseSkills[0].sinType}}</li>
        <li>스킬위력: {{identity.defenseSkills[0].skillPower}}</li>
        <li>코인위력: {{identity.defenseSkills[0].coinPower}}</li>
        <li>효과: {{identity.defenseSkills[0].effect}}</li>
      </ul>

    </div>
  </div>

  <div class="passiveSkills">
    <h2>패시브스킬</h2>
    <div class="passiveSkill1">
      <ul>
        <li>이름: {{identity.passiveSkills[0].name}}</li>
        <li>타입: {{identity.passiveSkills[0].sinType}}</li>
        <li>{{identity.passiveSkills[0].amount}}개 {{identity.passiveSkills[0].passiveType}}</li>
        <li>효과:  {{identity.passiveSkills[0].effect}}</li>
      </ul>
    </div>

    <div class="passiveSkill2">
      <ul>
        <li>이름: {{identity.passiveSkills[1].name}}</li>
        <li>효과: {{identity.passiveSkills[1].effect}}</li>
      </ul>
    </div>

    <div class="passiveSkill3">
      <ul>
        <li>이름: {{identity.passiveSkills[2].name}}</li>
        <li>타입: {{identity.passiveSkills[2].sinType}}</li>
        <li>{{identity.passiveSkills[2].amount}}개 {{identity.passiveSkills[2].passiveType}}</li>
        <li>효과: {{identity.passiveSkills[2].effect}}</li>
      </ul>
    </div>
  </div>




</template>


<style scoped>

</style>