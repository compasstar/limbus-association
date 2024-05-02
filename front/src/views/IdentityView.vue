<script setup lang="ts">

import {defineProps, ref} from "vue";
import axios from "axios";

const props = defineProps({
  identityId: {
    type: [Number, String],
    require: true,
  },
});

const initialIdentity: Identity = {
  sinner: "",
  name: "",
  rarity: 0,
  status: {
    hp: 100,
    minSpeed: 0,
    maxSpeed: 0,
    defenseLevel: 0
  },
  resistances: {
    slashResistance: 0,
    pierceResistance: 0,
    bluntResistance: 0
  },
  sanity: {
    panic: "",
    factorsIncreasingSanity: "",
    factorsDecreasingSanity: ""
  },
  offenseSkills: [{
    slot: 1,
    name: "Offensive Skill",
    level: 1,
    offenseType: "Physical",
    sinType: "Greed",
    amount: 30,
    skillPower: 60,
    coinPower: 20,
    coinNumber: 5,
    weight: 10,
    effect: "Deals 100 damage to the target.",
    offenseSkillCoinEffects: [
      {
        coin: 1,
        effect: "Inflicts bleeding on the target."
      },
      {
        coin: 2,
        effect: "Increases damage by 50%."
      }
    ]
  }],
  defenseSkills: [{
    name: "Defensive Skill",
    level: 1,
    defenseType: "Physical",
    sinType: "Greed",
    skillPower: 50,
    coinPower: 20,
    coinNumber: 1,
    effect: "Reduces incoming damage by 30%."
  }],
  passiveSkills: [{
    name: "Passive Skill",
    sinType: "Greed",
    passiveType: "Buff",
    amount: 10,
    effect: "Increases defense by 20%."
  }]
};
const identity = ref(initialIdentity);

interface Identity {
  sinner: string;
  name: string;
  rarity: number;
  status: Status;
  resistances: Resistances;
  sanity: Sanity;
  offenseSkills: OffenseSkill[];
  defenseSkills: DefenseSkill[];
  passiveSkills: PassiveSkill[];
}
interface PassiveSkill {
  name: string;
  sinType?: string;
  passiveType?: string;
  amount?: number;
  effect: string;
}
interface DefenseSkill {
  name: string;
  level: number;
  defenseType: string;
  sinType: string;
  skillPower: number;
  coinPower: number;
  coinNumber: number;
  effect: string;
}
interface OffenseSkill {
  slot: number;
  name: string;
  level: number;
  offenseType: string;
  sinType: string;
  amount: number;
  skillPower: number;
  coinPower: number;
  coinNumber: number;
  weight: number;
  effect: string;
  offenseSkillCoinEffects: OffenseSkillCoinEffect[];
}
interface OffenseSkillCoinEffect {
  coin: number;
  effect: string;
}
interface Sanity {
  panic: string;
  factorsIncreasingSanity: string;
  factorsDecreasingSanity: string;
}
interface Resistances {
  slashResistance: number;
  pierceResistance: number;
  bluntResistance: number;
}
interface Status {
  hp: number;
  minSpeed: number;
  maxSpeed: number;
  defenseLevel: number;
}

const re = (text:String) => {
  return text.replaceAll("\\n", "</br>");
}

axios.get(`/api/identities/${props.identityId}`)
    .then((response) => {
      identity.value = response.data;
    });
</script>


<template>
  <div class="name">
    <h1>{{identity.name}} (희귀도: {{identity.rarity}})</h1>
  </div>

  <div class="status-and-resistances">
    <div class="status-container">
      <h3>스테이터스</h3>
      <ul class="status">
        <li>HP: {{identity.status.hp}}</li>
        <li>speed: {{identity.status.minSpeed}} ~ {{identity.status.maxSpeed}}</li>
      </ul>
    </div>

    <div class="resistances-container">
      <h3>내성 정보</h3>
      <ul class="resistances">
        <li>참격내성: {{identity.resistances.slashResistance}}</li>
        <li>관통내성: {{identity.resistances.pierceResistance}}</li>
        <li>타격내성: {{identity.resistances.bluntResistance}}</li>
      </ul>
    </div>
  </div>

  <div class="skill">
    <h2>공격스킬</h2>
    <div class="offenseSkill" v-for="(offenseSkill) in identity.offenseSkills">
      <div class="slot">
        스킬{{offenseSkill.slot}}
      </div>
      <div class="content">
        <div>{{offenseSkill.name}} x{{offenseSkill.amount}} ({{offenseSkill.offenseType}})</div>
        <div>위력 {{offenseSkill.skillPower}} + {{offenseSkill.coinPower}} x {{offenseSkill.coinNumber}}(코인개수)</div>
        <div>공격레벨{{offenseSkill.level}} 가중치 {{offenseSkill.weight}} [{{offenseSkill.sinType}}]</div>
        <div class="effect mt-2" v-html="re(offenseSkill.effect)"></div>
        <div class="coin-effect" v-for="(offenseSkillCoinEffect) in offenseSkill.offenseSkillCoinEffects">
          <div>- 코인{{offenseSkillCoinEffect.coin}}: {{offenseSkillCoinEffect.effect}}</div>
        </div>
      </div>
    </div>

    <div class="defenseSkill" v-for="(defenseSkill) in identity.defenseSkills">
      <div class="slot">
        수비
      </div>
      <div class="content">
        <div>{{defenseSkill.name}}</div>
        <div>위력 {{defenseSkill.skillPower}} + {{defenseSkill.coinPower}} x {{defenseSkill.coinNumber}}</div>
        <ul>
          <li>수비레벨: {{defenseSkill.level}}</li>
          <li>타입: {{defenseSkill.defenseType}}</li>
          <li>타입: {{defenseSkill.sinType}}</li>
          <li>스킬위력: {{defenseSkill.skillPower}}</li>
          <li>코인위력: {{defenseSkill.coinPower}}</li>
          <li>효과: {{defenseSkill.effect}}</li>
        </ul>
      </div>
    </div>
  </div>




  <div class="passiveSkills" v-for="(passiveSkill) in identity.passiveSkills">
    <h2>패시브스킬</h2>
    <div class="passiveSkill1">
      <ul>
        <li>이름: {{passiveSkill.name}}</li>
        <li>타입: {{passiveSkill.sinType}}</li>
        <li>{{passiveSkill.amount}}개 {{passiveSkill.passiveType}}</li>
        <p>효과: <li v-html="re(passiveSkill.effect)"></li></p>
      </ul>
    </div>
  </div>

  <div class="sanity">
    <ul>
      <li>패닉: {{identity.sanity.panic}}</li>
      <p>정신력증가조건<li v-html="re(identity.sanity.factorsIncreasingSanity)"></li></p>
      <p>정신력감소조건<li v-html="re(identity.sanity.factorsDecreasingSanity)"></li></p>
    </ul>
  </div>
</template>


<style scoped lang="scss">
ul {
  list-style: none;
}

.name {
  text-align: center;
}

.status-and-resistances {
  display: flex;
  justify-content: space-around;
  text-align: center;
  .status {
    display: flex;
  }
  .resistances {
    display: flex;
    justify-content: space-around;
  }
}

.skill {
  border-style: solid;

  .offenseSkill {
    display: flex;
    border-style: solid;
  }

  .defenseSkill {
    display: flex;
    border-style: solid;
  }


  .slot {
    align-content: center;
    text-align: center;
    width: 10%;
    margin-left: 1rem;

    border-style: solid;
  }
  .content {
    width: 90%;
    .effect {
      font-size: 0.8rem;
    }
    .coin-effect {
      font-size: 0.8rem;
    }
  }

}




</style>