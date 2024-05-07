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



axios.get(`/api/identities/${props.englishName}`)
    .then((response) => {
      identity.value = response.data;
    });

const supportPassives = computed(() => {
  return identity.value.passiveSkills.filter(passiveSkill => passiveSkill.support);
});
const nonSupportPassives = computed(() => {
  return identity.value.passiveSkills.filter(passiveSkill => !passiveSkill.support);
});

const searchName = ref("")
const searchIdentitiesInit: string[] = [];
const searchIdentities = ref(searchIdentitiesInit);
const search = function() {
  axios.get(`/api/identities/search?name=${searchName.value}`)
      .then((response) => {
        searchIdentities.value = response.data.englishNames;
        console.log(searchIdentities.value);
      })
}

</script>


<template>
  <div class="name">
    <el-text>[{{identity.sinner}}]</el-text>
    <el-text type="primary" size="large">{{identity.name}}</el-text>
    <el-text type="danger"> ({{identity.rarity}})</el-text>
  </div>


  <el-descriptions title="스테이터스" direction="vertical" :column="3" border>
    <el-descriptions-item label="hp">{{identity.status.hp}}</el-descriptions-item>
    <el-descriptions-item label="속도">{{identity.status.minSpeed}} ~ {{identity.status.maxSpeed}}</el-descriptions-item>
    <el-descriptions-item label="수비레벨">{{identity.status.defenseLevel}}</el-descriptions-item>
  </el-descriptions>

  <el-descriptions title="내성정보" direction="vertical" :column="3" border>
    <el-descriptions-item label="참격내성">{{identity.resistances.slashResistance}}</el-descriptions-item>
    <el-descriptions-item label="관통내성">{{identity.resistances.pierceResistance}}</el-descriptions-item>
    <el-descriptions-item label="타격내성">{{identity.resistances.bluntResistance}}</el-descriptions-item>
  </el-descriptions>



  <div class="skill">
    <el-text class="title2" size="large">스킬</el-text>
    <div class="offenseSkill" v-for="(offenseSkill) in identity.offenseSkills">
      <el-card class="slot">
        스킬{{offenseSkill.slot}}
      </el-card>
      <el-card class="content">
        <div>{{offenseSkill.name}} x{{offenseSkill.amount}} ({{offenseSkill.offenseType}})</div>
        <div>위력 {{offenseSkill.skillPower}} + {{offenseSkill.coinPower}} x {{offenseSkill.coinNumber}}(코인개수)</div>
        <div>공격레벨 {{offenseSkill.level}} 가중치 {{offenseSkill.weight}} [{{offenseSkill.sinType}}]</div>
        <div class="effect mt-2" v-html="re(offenseSkill.effect)"></div>
        <div class="coin-effect" v-for="(offenseSkillCoinEffect) in offenseSkill.offenseSkillCoinEffects">
          <div>- 코인{{offenseSkillCoinEffect.coin}}: {{offenseSkillCoinEffect.effect}}</div>
        </div>
      </el-card>
    </div>

    <div class="defenseSkill" v-for="(defenseSkill) in identity.defenseSkills">
      <el-card class="slot">
        수비
      </el-card>
      <el-card class="content">
        <div>{{defenseSkill.name}} ({{defenseSkill.defenseType}})</div>
        <div>위력 {{defenseSkill.skillPower}} + {{defenseSkill.coinPower}} x {{defenseSkill.coinNumber}}</div>
        <div>수비레벨 {{defenseSkill.level}} 가중치 {{defenseSkill.weight}} [{{defenseSkill.sinType}}]</div>
        <div class="effect mt-2" v-html="re(defenseSkill.effect)"></div>
      </el-card>
    </div>
  </div>


  <div class="passiveSkills">
    <el-text class="title2" size="large">패시브</el-text>
    <div class="passiveSkill" v-for="(passiveSkill) in nonSupportPassives">
      <el-card class="slot">
        <div>패시브</div>
      </el-card>
      <el-card class="content">
        <div>{{passiveSkill.name}}</div>
        <div v-if="passiveSkill.amount">{{passiveSkill.sinType}} x{{passiveSkill.amount}} {{passiveSkill.passiveType}}</div>
        <div class="effect" v-html="re(passiveSkill.effect)"></div>
      </el-card>
    </div>
    <div class="passiveSkill" v-for="(passiveSkill) in supportPassives">
      <el-card class="slot">
        <div>서포트<br>패시브</div>
      </el-card>
      <el-card class="content">
        <div>{{passiveSkill.name}}</div>
        <div v-if="passiveSkill.amount">{{passiveSkill.sinType}} x{{passiveSkill.amount}} {{passiveSkill.passiveType}}</div>
        <div class="effect" v-html="re(passiveSkill.effect)"></div>
      </el-card>
    </div>
  </div>



  <div class="sanity-container">
    <el-text class="title2" size="large">정신력</el-text>
    <div class="sanity">
      <el-card class="slot">
        패닉<br>유형
      </el-card>
      <el-card class="content effect">
        {{identity.sanity.panic}}
      </el-card>
    </div>
    <div class="sanity">
      <el-card class="slot text-info">
        정신력<br>증가<br>조건
      </el-card>
      <el-card class="content effect" v-html="re(identity.sanity.factorsIncreasingSanity)"></el-card>
    </div>
    <div class="sanity">
      <el-card class="slot text-danger">
        정신력<br>감소<br>조건
      </el-card>
      <el-card class="content effect" v-html="re(identity.sanity.factorsDecreasingSanity)"></el-card>
    </div>
  </div>


    <div class="icons"></div>
    <el-input v-model="searchName" placeholder="수감자 이름"></el-input>
    <el-button type="primary" @click="search">검색</el-button>
    <div class="result"></div>

    <div v-for="(searchIdentity) in searchIdentities">
      <div>{{searchIdentity}}</div>
    </div>



</template>


<style scoped lang="scss">
ul {
  list-style: none;
}

.name {
  text-align: center;
}

.el-descriptions {
  margin-top: 2rem;
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
  margin-top: 2rem;
}



.title2 {
  font-size: 24px;
}



.offenseSkill {
  display: flex;
}

.defenseSkill {
  display: flex;
}

.passiveSkills {
  margin-top: 2rem;
  .passiveSkill {
    display: flex;
  }
}

.sanity-container {
  margin-top: 2rem;
  .sanity {
    display: flex;
    .content {
      padding: 1rem;
    }
  }
}

.slot {
  width: 10%;
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