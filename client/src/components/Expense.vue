<template>
  <v-card
    class="justify-center pa-4"
    width="400px"
  >
    <v-text-field placeholder="Date" v-model="date"></v-text-field>
    <v-text-field placeholder="Category" v-model="category"></v-text-field>
    <v-text-field placeholder="Payee" v-model="payee"></v-text-field>
    <v-text-field placeholder="Description" v-model="description"></v-text-field>
    <v-text-field placeholder="Price" v-model="price"></v-text-field>
    <template v-slot:actions>
      <v-btn
        class="ms-auto"
        :text="props.isNew === true ? 'Create Expense' : 'Save Expense'"
        @click="props.isNew === true ? createNewExpense() : saveCurrentExpense()">
      </v-btn>
    </template>
  </v-card>
</template>

<script setup>

import { ref, onMounted } from 'vue'

const emit = defineEmits(['createNew', 'saveExpense'])

const props = defineProps({
  isNew: {
    type: Boolean,
    required: false,
    default: true
  },
  expense: {
    type: Object,
    required: false,
    default: {}
  }
})

onMounted(() => {
  loadProps()
})

const loadProps = () => {
  date.value = props?.expense?.date
  category.value = props?.expense?.category
  payee.value = props?.expense?.payee
  description.value = props?.expense?.description
  price.value = props?.expense?.price
}

const date = ref('')
const category = ref('')
const payee = ref('')
const description = ref('')
const price = ref('')

const createNewExpense = () => {
  let newExpense = {
      date: date.value,
      category: category.value,
      payee: payee.value,
      description: description.value,
      price: price.value
  }
  emit('createNew', newExpense)
}

const saveCurrentExpense = () => {
  let currentExpense = {
      id: props.expense?.id,
      date: date.value,
      category: category.value,
      payee: payee.value,
      description: description.value,
      price: price.value
  }
  console.log("Emitting saveExpense", currentExpense)
  emit('saveExpense', currentExpense)
}

</script>

<style scoped>

</style>
