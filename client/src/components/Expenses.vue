<template>
  <v-card class="pa-4 ma-4">
    <v-data-table
        class="pa-4"
        :headers="headers"
        :items="filteredItems"
        :sort-by="[{ key: 'date', order: 'desc'}]"
    >
      <template v-slot:top>
        <v-toolbar flat color="primary-darken-1">
          <v-toolbar-title>model.Expense Tracker</v-toolbar-title>
          <v-spacer />
          <v-btn
            rounded
            size="large"
            @click="openExpenseDialog({}, true)"
            prepend-icon="mdi-plus"
          >
            Add model.Expense
          </v-btn>
        </v-toolbar>
      </template>

      <template v-slot:item.date="{value}">
        {{ formattedDate(value) }}
      </template>
      <template v-slot:item.price="{ value }">
        $ {{ value }}
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn
          rounded
          color="primary"
          size="small"
          >
          <v-icon
            size="large"
            @click="openExpenseDialog(item, false)">
            mdi-pencil
          </v-icon>
        </v-btn>
        <v-btn
            rounded
            size="small"
            class="ml-1"
            color="secondary"
        >
        <v-icon
            size="large"
            @click="deleteExpense(item.id)"
        >
          mdi-delete
        </v-icon>
        </v-btn>

      </template>
    </v-data-table>
  </v-card>
  <v-dialog
    v-model="showExpenseDialog"
    attach
  >
    <Expense
        :expense="expenseItem"
        :is-new="isNew"
        @createNew="createExpense"
        @saveExpense="saveExpense" />

  </v-dialog>
</template>

<script setup>
  import { ref, computed } from 'vue'
  import { formattedDate } from './helper.js'
  import Expense from "@/components/Expense.vue";

  const showExpenseDialog = ref(false)
  const expenseItem = ref({})
  const isNew = ref(false)
  const currentId = ref(4)

  const items = ref([
    {
      id: 1,
      date: new Date('12/28/2024'),
      category: 'Groceries',
      payee: 'Walmart',
      description: 'Baja Blast',
      price: '10.54'
    },
    {
      id: 2,
      date: new Date('12/27/2024'),
      category: 'Gifts',
      payee: 'Amazon',
      description: 'Bambu Lab 3D Printer',
      price: '134.43'
    },
    {
      id: 3,
      date: new Date('12/20/2024'),
      category: 'Groceries',
      payee: 'Walmart',
      description: '3 Watermelons for my pet hippo',
      price: '3.00'
    }
  ])
  const headers = ref([
      { title: 'Date', value: 'date', sortable: true },
      { title: 'Category', value: 'category', sortable: true },
      { title: 'Payee', value: 'payee', sortable: true },
      { title: 'Description', value: 'description', sortable: true },
      { title: 'Price', value: 'price', sortable: true},
      { title: 'Actions', value: 'actions', sortable: false }
  ])

  const filteredItems = computed(()=> {
    return items.value
  })

  const openExpenseDialog = (item, isNewExpense) => {
    isNew.value = isNewExpense
    expenseItem.value = item
    showExpenseDialog.value = true
  }
  const closeExpenseDialog = () => {
    showExpenseDialog.value = false
    expenseItem.value = {}
  }

  const createExpense = (newExpense) => {
    newExpense.id = currentId.value
    newExpense.date = new Date(newExpense.date)
    currentId.value = currentId.value + 1
    items.value.push(newExpense)
    closeExpenseDialog()
  }

  const saveExpense = (updatedExpense => {
    console.log("Saving model.Expense", updatedExpense)

    updatedExpense.date = new Date(updatedExpense.date)

    let newItems = []
    items.value.forEach((e) => {
      if (e.id === updatedExpense.id) {
         newItems.push(updatedExpense)
      } else {
        newItems.push(e)
      }
    })
    items.value = newItems
    closeExpenseDialog()
  })

  const deleteExpense = (itemId) => {
    items.value = items.value.filter((e) => {
      return e.id !== itemId
    })
  }
</script>

<style scoped>

</style>
