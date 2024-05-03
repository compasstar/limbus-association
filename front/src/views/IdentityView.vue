<script setup lang="ts">

import {computed, defineProps, ref} from "vue";
import axios from "axios";

const props = defineProps({
  englishName: {
    type: [String],
    require: true,
  }
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
    effect: "Reduces incoming damage by 30%.",
    weight: 1,
  }],
  passiveSkills: [{
    support: false,
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
  support: boolean;
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
  weight: number;
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

// axios.get(`/api/identities/${props.identityId}`)
//     .then((response) => {
//       identity.value = response.data;
//     });

axios.get(`/api/identities/${props.englishName}`)
    .then((response) => {
      identity.value = response.data;
      console.log(identity.value);
    });

const supportPassives = computed(() => {
  return identity.value.passiveSkills.filter(passiveSkill => passiveSkill.support);
});
const nonSupportPassives = computed(() => {
  return identity.value.passiveSkills.filter(passiveSkill => !passiveSkill.support);
});




</script>


<template>
  <div class="name">
    <h1>[{{identity.sinner}}] {{identity.name}} (희귀도: {{identity.rarity}})</h1>
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
        <div>공격레벨 {{offenseSkill.level}} 가중치 {{offenseSkill.weight}} [{{offenseSkill.sinType}}]</div>
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
        <div>{{defenseSkill.name}} ({{defenseSkill.defenseType}})</div>
        <div>위력 {{defenseSkill.skillPower}} + {{defenseSkill.coinPower}} x {{defenseSkill.coinNumber}}</div>
        <div>수비레벨 {{defenseSkill.level}} 가중치 {{defenseSkill.weight}} [{{defenseSkill.sinType}}]</div>
        <div class="effect mt-2" v-html="re(defenseSkill.effect)"></div>
      </div>
    </div>
  </div>


  <h2>패시브</h2>
  <div class="passiveSkills">
    <div class="passiveSkill" v-for="(passiveSkill) in nonSupportPassives">
      <div class="slot">
        <div>패시브</div>
      </div>
      <div class="content">
        <div>{{passiveSkill.name}}</div>
        <div v-if="passiveSkill.amount">{{passiveSkill.sinType}} x{{passiveSkill.amount}} {{passiveSkill.passiveType}}</div>
        <div class="effect" v-html="re(passiveSkill.effect)"></div>
      </div>
    </div>
    <div class="passiveSkill" v-for="(passiveSkill) in supportPassives">
      <div class="slot">
        <div>서포트<br>패시브</div>
      </div>
      <div class="content">
        <div>{{passiveSkill.name}}</div>
        <div v-if="passiveSkill.amount">{{passiveSkill.sinType}} x{{passiveSkill.amount}} {{passiveSkill.passiveType}}</div>
        <div class="effect" v-html="re(passiveSkill.effect)"></div>
      </div>
    </div>
  </div>


  <h2>정신력</h2>
  <div class="sanity-container">
    <div class="sanity">
      <div class="slot">
        패닉<br>유형
      </div>
      <div class="content effect">
        {{identity.sanity.panic}}
      </div>
    </div>
    <div class="sanity">
      <div class="slot text-info">
        정신력<br>증가<br>조건
      </div>
      <div class="content effect" v-html="re(identity.sanity.factorsIncreasingSanity)"></div>
    </div>
    <div class="sanity">
      <div class="slot text-danger">
        정신력<br>감소<br>조건
      </div>
      <div class="content effect" v-html="re(identity.sanity.factorsDecreasingSanity)"></div>
    </div>
  </div>



  <div class="search">
    <div class="icons"></div>
    <div><input type="text" placeholder="수감자 검색"></div>
    <div class="result"></div>
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
}

.passiveSkill {
  display: flex;
  border-style: solid;
}

.sanity {
  display: flex;
  border-style: solid;
}

.slot {
  align-content: center;
  text-align: center;
  width: 10%;
  margin-left: 1rem;

  border-style: solid;
  border-color: black;
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





</style>