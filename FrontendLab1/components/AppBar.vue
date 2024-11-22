<template>
  <v-app-bar color="primary" class="navbar d-flex align-center">
    <!-- Icono de la tienda -->
    <v-btn icon color="white" href="/">
      <v-icon>mdi-store</v-icon>
    </v-btn>

    <!-- Botón para categorías -->
    <v-btn @click="abrirDrawer = !abrirDrawer">Categorías</v-btn>

    <!-- Contenedor para empujar los botones a la derecha -->
    <div class="ml-auto d-flex align-center">
      <!-- Botones visibles según autenticación -->
      <template v-if="isAuthenticated">
        <v-btn text class="mx-2" href="/pedidos">Mis Pedidos</v-btn>
        <v-btn icon color="white" href="/perfil"><v-icon>mdi-account-check</v-icon></v-btn>
        <v-btn icon color="white" href="/cart">
          <v-icon>mdi-cart-outline</v-icon>
          <span v-if="cartCount > 0" class="badge">{{ cartCount }}</span>
        </v-btn>
        <v-btn icon color="white" @click="logout" class="mx-4"><v-icon>mdi-logout</v-icon></v-btn>
      </template>
      <template v-else>
        <v-btn text class="mx-2" href="/login">Iniciar Sesión</v-btn>
        <v-btn text class="mx-2" href="/register">Registro</v-btn>
        <v-btn icon color="white" href="/cart">
          <v-icon>mdi-cart-outline</v-icon>
          <span v-if="cartCount > 0" class="badge">{{ cartCount }}</span>
        </v-btn>
      </template>

      <!-- Botón del carrito de compras -->

    </div>
  </v-app-bar>

  <!-- Drawer para las categorías -->
  <v-navigation-drawer v-model="abrirDrawer" app temporary>
    <v-list>
      <v-list-item v-for="categoria in categorias" :key="categoria.idCategoria"
        @click="filtrarPorCategoria(categoria.idCategoria)">
        <v-list-item-title>{{ categoria.nombre }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { useCart } from "@/composables/useCart.js";
import axios from "axios";

const { cartCount } = useCart();

export default {

  data() {
    return {
      abrirDrawer: false,
      categorias: [],
      isAuthenticated: false, // Se inicializa en falso
    };
  },
  mounted() {
    this.obtenerCategorias();
    this.actualizarEstadoAutenticacion(); // Actualiza el estado autenticado
  },
  watch: {
    // Observa los cambios en la ruta para actualizar el estado de autenticación
    '$route'(to, from) {
      this.actualizarEstadoAutenticacion();
    }
  },
  methods: {
    async obtenerCategorias() {
      const response = await axios.get("http://localhost:8090/api/categoria");
      this.categorias = response.data;
    },
    async filtrarPorCategoria(id) {
      const response = await axios.get(`http://localhost:8090/api/categoria/${id}`);
      console.log(response.data); // Maneja los productos filtrados
    },
    actualizarEstadoAutenticacion() {
      // Verifica el estado de autenticación desde localStorage
      this.isAuthenticated = localStorage.getItem("isAuthenticated") === "true";
    },

    logout() {
      if (!confirm("¿Estás seguro de que deseas cerrar sesión?")) {
        return;
      }
      localStorage.setItem("isAuthenticated", false);
      localStorage.removeItem("userId");
      this.actualizarEstadoAutenticacion();
      alert("Sesión cerrada exitosamente");
    }
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