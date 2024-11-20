<template>
    <v-app-bar color="primary" class="navbar">
        <!-- Enlaces de navegación -->
        <v-btn icon color="white"><v-icon>mdi-store</v-icon></v-btn>
        <v-btn @click="abrirDrawer = !abrirDrawer">Categorías</v-btn>
        <v-btn text class="mx-2" href="/pedidos">Mis Pedidos</v-btn>
        <v-btn text class="mx-2" href="/perfil">Mi Cuenta</v-btn>
        <v-btn text class="mx-2" href="/register">Registro</v-btn>
        <!-- Botón de carrito de compras -->
        <v-btn icon color="white" href="/cart">
            <v-icon>mdi-cart-outline</v-icon>
            <span v-if="cartCount > 0" class="badge">{{ cartCount }}</span>
        </v-btn>
        <v-spacer></v-spacer>
    </v-app-bar>
    <v-navigation-drawer v-model="abrirDrawer" app temporary>
    <v-list>
      <v-list-item
        v-for="categoria in categorias"
        :key="categoria.idCategoria"
        @click="filtrarPorCategoria(categoria.idCategoria)"
      >
        <v-list-item-title>{{ categoria.nombre }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { useCart } from '@/composables/useCart.js'
import axios from 'axios'

// Obtenemos el estado del carrito
const { cartCount } = useCart()

const API_URL = 'http://localhost:8090/api'

export default {
  data() {
    return {
      abrirDrawer: false,
      categorias: [],
    };
  },
  mounted() {
    this.obtenerCategorias();
  },
  methods: {
    async obtenerCategorias() {
      const response = await axios.get(`${API_URL}/categoria`);
      this.categorias = response.data;
    },
    async filtrarPorCategoria(id) {
      const response = await axios.get(`${API_URL}/categoria/${id}`);
      console.log(response.data); // Aquí puedes manejar los productos filtrados
    },
  },
};

</script>

<style scoped>
.badge {
    background-color: red;
    color: white;
    border-radius: 50%;
    padding: 0 8px;
    font-size: 12px;
    position: absolute;
    top: 8px;
    right: 8px;
}

.navbar {
    display: grid;
    width: 100%;
}

.v-toolbar__content {
    display: grid;
    width: 100%;
}
</style>