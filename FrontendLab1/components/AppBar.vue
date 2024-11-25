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
      <!-- Menú desplegable para autenticación -->
      <v-menu offset-y>
        <template v-slot:activator="{ props }">
          <v-btn text v-bind="props">
            <v-icon>mdi-account-circle</v-icon>
            <span>{{ isAuthenticated ? userName : 'Ingresar' }}</span>
          </v-btn>
        </template>
        <v-list>
          <template v-if="isAuthenticated">
            <v-list-item>
              <v-btn text href="/pedidos">Mis Pedidos</v-btn>
            </v-list-item>
            <v-list-item>
              <v-btn text href="/perfil">Mi Cuenta</v-btn>
            </v-list-item>
            <v-list-item>
              <v-btn text @click="logout">Cerrar Sesión</v-btn>
            </v-list-item>
          </template>
          <template v-else>
            <v-list-item>
              <v-btn text href="/login">Iniciar Sesión</v-btn>
            </v-list-item>
            <v-list-item>
              <v-btn text href="/register">Registro</v-btn>
            </v-list-item>
          </template>
        </v-list>
      </v-menu>

      <!-- Botón del carrito de compras -->
      <v-btn icon color="white" href="/cart">
        <v-icon>mdi-cart-outline</v-icon>
        <span v-if="cartCount > 0" class="badge">{{ cartCount }}</span>
      </v-btn>

      <!-- Botón para obtener el producto con mayor variabilidad de precios -->
      <v-btn text class="mx-2" @click="fetchVariablePriceProduct">
        <v-icon class="mr-2">mdi-database</v-icon>SQL
      </v-btn>
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

  <!-- Popup (Modal) para mostrar el producto -->
  <v-dialog v-model="isModalOpen" max-width="500">
    <v-card>
      <v-card-title class="text-h5">
        Producto con la mayor variabilidad de precios en venta en las órdenes de compra, considerando las últimas 100 órdenes
      </v-card-title>
      <v-card-text>
        <!-- Mostrar detalles del producto -->
        <div v-if="producto">
          <p><strong>Nombre:</strong> {{ producto.nombre }}</p>
          <p><strong>Descripción:</strong> {{ producto.descripcion }}</p>
          <p><strong>Precio:</strong> ${{ producto.precio }}</p>
          <p><strong>Stock:</strong> {{ producto.stock }}</p>
          <p><strong>Estado:</strong> {{ producto.estado }}</p>
        </div>
        <div v-else>
          <p>No se encontraron datos del producto.</p>
        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" text @click="isModalOpen = false">Cerrar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
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
      userName: "",
      isAuthenticated: false, // Se inicializa en falso
      producto: null, // Producto obtenido del backend
      isModalOpen: false,
    };
  },
  computed: {
    userName() {
      return localStorage.getItem("userName") || ""; // Obtiene siempre el valor actualizado de localStorage
    }
  },
  mounted() {
    this.obtenerCategorias();
    this.actualizarEstadoAutenticacion(); // Actualiza el estado autenticado
    this.userName = localStorage.getItem("userName") || ""; 
  },
  watch: {
    // Observa los cambios en la ruta para actualizar el estado de autenticación
    '$route'(to, from) {
      this.actualizarEstadoAutenticacion();
    },
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
      localStorage.removeItem("jwtToken");
      localStorage.removeItem("userName");
      this.actualizarEstadoAutenticacion();
      alert("Sesión cerrada exitosamente");
    },

    async fetchVariablePriceProduct() {
      const API_URL = "http://localhost:8090/api/producto";
      try {
        const response = await axios.get(`${API_URL}/getVariablePriceProduct`);
        console.log("Datos recibidos:", response.data);
        this.producto = response.data; // Actualiza los datos del producto
        this.isModalOpen = true; // Abre el modal
      } catch (error) {
        console.error("Error fetching variable price product:", error);
        alert("No se pudo obtener el producto con precio variable.");
      }
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

.v-card-title {
  word-wrap: break-word;
  white-space: normal; 
}
</style>
