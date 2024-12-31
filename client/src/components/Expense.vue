<template>
  <v-card
    class="pa-4 ma-auto"
    width="400px"
  >
    <v-form
      v-model="expenseForm"
      @submit.prevent="onSubmit"
    >
      <v-text-field placeholder="Date" v-model="date" :rules="[dateMaskRule]" persistent-hint hint="MM/DD/YYYY"></v-text-field>
      <v-text-field placeholder="Category" v-model="category"></v-text-field>
      <v-text-field placeholder="Payee" v-model="payee"></v-text-field>
      <v-text-field placeholder="Description" v-model="description"></v-text-field>
      <v-text-field placeholder="Price" v-model="price"></v-text-field>
      <v-btn
          :disabled="!expenseForm"
          :loading="loading"
          color="success"
          size="large"
          type="submit"
          variant="elevated"
          block
          @click="props.isNew === true ? createNewExpense() : saveCurrentExpense()">
        {{ props.isNew === true ? 'Create model.Expense' : 'Save model.Expense' }}
      </v-btn>

    </v-form>
  </v-card>
</template>

<script setup>

import { ref, onMounted } from 'vue'
import {formattedDate} from "@/components/helper.js";

const emit = defineEmits(['createNew', 'saveExpense'])

const date = ref('')
const category = ref('')
const payee = ref('')
const description = ref('')
const price = ref('')
const expenseForm = ref(false)
const loading = ref(false)

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

const dateMaskRule = (val) => {
  const re = new RegExp("[0-9]{2}\/[0-9]{2}\/[0-9]{4}")
  if (re.test(val)) {
    return true
  }
  return "MM/DD/YYYY"
}

const onSubmit = () => {
  if (!expenseForm.value) return
  console.log('expenseForm.value is true')
  loading.value = true
}
const loadProps = () => {
  date.value = formattedDate(props?.expense?.date)
  category.value = props?.expense?.category
  payee.value = props?.expense?.payee
  description.value = props?.expense?.description
  price.value = props?.expense?.price
}

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
