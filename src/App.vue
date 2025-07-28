<template>
  <main>
    <header>
      <h1>Accessible Todo List</h1>
      <button
        @click="toggleDark"
        :aria-pressed="darkMode.toString()"
        aria-label="Toggle dark mode"
        class="theme-toggle"
      >
        {{ darkMode ? '🌞 Light Mode' : '🌙 Dark Mode' }}
      </button>
    </header>
    <TodoList />
  </main>
</template>

<script>
import { ref, onMounted, watch } from 'vue'
import TodoList from './components/TodoList.vue'

export default {
  components: { TodoList },
  setup() {
    const darkMode = ref(false)

    function applyClass(isDark) {
      document.documentElement.classList.toggle('dark', isDark)
    }

    function toggleDark() {
      darkMode.value = !darkMode.value
    }

    onMounted(() => {
      const saved = localStorage.getItem('darkMode')
      if (saved !== null) {
        darkMode.value = JSON.parse(saved)
      } else {
        darkMode.value = window.matchMedia('(prefers-color-scheme: dark)').matches
      }
      applyClass(darkMode.value)
    })

    watch(darkMode, (val) => {
      localStorage.setItem('darkMode', JSON.stringify(val))
      applyClass(val)
    })

    return { darkMode, toggleDark }
  }
}
</script>

<style>
/* ------------------------------------------------------------
   Theme variables
------------------------------------------------------------ */
:root {
  /* Light theme */
  --bg-color: #f0f2f5;
  --text-color: #333333;
  --card-bg: #ffffff;
  --card-shadow: rgba(0, 0, 0, 0.06);
  --input-bg: #ffffff;
  --input-border: #bbbbbb;
  --button-bg: #007acc;
  --button-hover: #005fa3;
  --button-disabled: #aaccee;
  --status-color: #333333;
  --todo-border: #eeeeee;
  --todo-completed-color: #888888;
}

html.dark {
  /* Dark theme */
  --bg-color: #1e1e1e;
  --text-color: #dddddd;
  --card-bg: #252526;
  --card-shadow: rgba(0, 0, 0, 0.7);
  --input-bg: #3c3c3c;
  --input-border: #555555;
  --button-bg: #0a84ff;
  --button-hover: #0060df;
  --button-disabled: #3a7bd5;
  --status-color: #cccccc;
  --todo-border: #444444;
  --todo-completed-color: #777777;
}

/* Apply background & text */
body {
  background-color: var(--bg-color);
  color: var(--text-color);
  margin: 0;
}

/* ------------------------------------------------------------
   Component styles (scoped) 
------------------------------------------------------------ */
main {
  max-width: 480px;
  margin: 2rem auto;
  padding: 1.5rem;
  background: var(--card-bg);
  border-radius: 8px;
  box-shadow: 0 4px 12px var(--card-shadow);
  font-family: 'Segoe UI', Roboto, sans-serif;
}

header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

h1 {
  margin: 0;
  font-size: 1.75rem;
  color: var(--text-color);
}

/* Toggle button */
.theme-toggle {
  background: none;
  border: 1px solid var(--input-border);
  border-radius: 4px;
  padding: 0.5rem;
  cursor: pointer;
  font-size: 1.25rem;
  transition: background 0.2s, color 0.2s;
}
.theme-toggle:hover {
  background: var(--input-border);
}
.theme-toggle[aria-pressed="true"] {
  background: var(--button-bg);
  color: white;
}
</style>
