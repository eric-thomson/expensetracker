import { createRouter, createWebHistory } from 'vue-router'
import ExpensesView from "@/views/ExpensesView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'expenses',
      component: ExpensesView,
    },
  ],
})

export default router
