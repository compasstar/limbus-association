export interface Identity {
    sinner: string;
    name: string;
    englishName: string;
    rarity: number;
    status: Status;
    resistances: Resistances;
    sanity: Sanity;
    offenseSkills: OffenseSkill[];
    defenseSkills: DefenseSkill[];
    passiveSkills: PassiveSkill[];
}

export interface OffenseSkill {
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
    effect: string[];
    offenseSkillCoinEffects: OffenseSkillCoinEffect[];
}

export interface OffenseSkillCoinEffect {
    coin: number;
    effect: string[];
}

export interface DefenseSkill {
    name: string;
    level: number;
    defenseType: string;
    sinType: string;
    skillPower: number;
    coinPower: number;
    coinNumber: number;
    effect: string[];
    weight: number;
}

export interface PassiveSkill {
    support: boolean;
    name: string;
    sinType?: string;
    passiveType?: string;
    amount?: number;
    effect: string[];
}

export interface Sanity {
    panic: string[];
    factorsIncreasingSanity: string[];
    factorsDecreasingSanity: string[];
}

export interface Resistances {
    slashResistance: number;
    pierceResistance: number;
    bluntResistance: number;
}

export interface Status {
    hp: number;
    minSpeed: number;
    maxSpeed: number;
    defenseLevel: number;
}

export interface Name {
    name: string;
    englishName: string;
}
