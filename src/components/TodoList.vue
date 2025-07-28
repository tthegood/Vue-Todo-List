<template>
  <section>
    <h2>My Todos</h2>
    <form @submit.prevent="addTodo">
      <label for="new-todo" class="visually-hidden">New todo</label>
      <input id="new-todo" v-model="newText" placeholder="What needs doing?" />
      <button :disabled="!newText.trim()">Add</button>
    </form>

    <ul>
      <li v-for="todo in todos" :key="todo.id" :class="{ completed: todo.done }">
        <input
          type="checkbox"
          :id="`cb-${todo.id}`"
          :checked="todo.done"
          @change="toggleDone(todo)"
        />
        <label :for="`cb-${todo.id}`">{{ todo.text }}</label>
      </li>
    </ul>

    <p class="status">
      <span v-if="remaining === 0">🎉 All done! 🎉</span>
      <span v-else>{{ remaining }} of {{ todos.length }} left</span>
    </p>
  </section>
</template>

<script>
import { ref, computed, onMounted } from 'vue';

export default {
  setup() {
    const todos = ref([]);
    const newText = ref('');

    // Load from backend
    async function loadTodos() {
      const res = await fetch('/api/todos');
      todos.value = await res.json();
    }

    onMounted(loadTodos);

    // Create
    async function addTodo() {
      const res = await fetch('/api/todos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: newText.value })
      });
      const created = await res.json();
      todos.value.push(created);
      newText.value = '';
    }

    // Toggle done
    async function toggleDone(todo) {
      todo.done = !todo.done; // optimistic
      await fetch(`/api/todos/${todo.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(todo)
      });
    }

    const remaining = computed(() =>
      todos.value.filter(t => !t.done).length
    );

    return { todos, newText, addTodo, toggleDone, remaining };
  }
};
</script>
