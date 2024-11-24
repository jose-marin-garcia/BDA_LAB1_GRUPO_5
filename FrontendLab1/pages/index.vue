<template>
  <div class="container mx-auto px-4">
    <h1 class="text-3xl font-bold mb-8 mt-4">Productos</h1>
    <input 
      v-model="searchQuery"
      type="text" 
      placeholder="Buscar productos..." 
      class="p-2 border border-gray-300 rounded mb-6 mr-4"
      style="width: 800px"
    />
    <v-btn icon @click="searchProducts" color="primary">
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
      <div v-for="product in products" :key="product.idProducto" class="border border-gray-300 rounded-lg shadow-md p-4">
        <h2 class="text-xl font-semibold mb-2">{{ product.nombre }}</h2>
        <p class="text-gray-600 mb-2">{{ product.descripcion }}</p>
        <div class="flex justify-between items-center mb-4">
          <span class="text-xl font-bold">${{ product.precio }}</span>
          <span :class="['font-medium', getStockColorClass(product.stock)]">
            Stock: {{ product.stock }}
          </span>
        </div>
        <button @click="() => {
          addToCart(product);
          agregando();
          show();
          product.stock--;
        }" class="w-full bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:bg-gray-400"
          :disabled="product.stock === 0">
          {{ product.stock === 0 ? 'Agotado' : agregarCarrito }}
        </button>
      </div>
    </div>
    
    <!-- Pagination Component -->
    <v-pagination v-model="currentPage" :length="totalPages" color="primary" class="mt-6" />
    
    <div 
      v-if="visible"
      class="fixed inset-0 flex items-end justify-center z-50 mb-[10%]" 
      @click="hide"
    >
      <div 
        class="bg-gray-800 text-white p-4 rounded text-center shadow-lg"
        style="background: rgba(0, 0, 0, 0.7); min-width: 300px;"
      >
        {{ feedBackMessage }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useCart } from '@/composables/useCart.js';
import axios from 'axios';

const feedBackMessage = 'Producto agregado al carrito';
const visible = ref(false);
const agregarCarrito = ref('Agregar a carrito');
const products = ref([]);
const currentPage = ref(1);
const totalPages = ref(1);
const searchQuery = ref('');
const API_URL = 'http://localhost:8090/api';
const { addToCart, cartItems } = useCart();

// Sincronizar el stock de los productos con los del carrito
const syncStockWithCart = () => {
  products.value.forEach((product) => {
    const cartItem = cartItems.value.find((item) => item.idProducto === product.idProducto);
    if (cartItem) {
      product.stock -= cartItem.quantity;
    }
  });
};

const fetchProducts = async () => {
  const limit = 8; // Número de productos por página
  const offset = (currentPage.value - 1) * limit;

  try {
    const response = await axios.get(`${API_URL}/producto`, {
      params: {
        limit: limit,
        offset: offset,
        search: searchQuery.value,
      },
    });
    products.value = response.data.products;
    totalPages.value = Math.ceil(response.data.totalCount / limit);

    // Sincronizar el stock con el carrito después de cargar los productos
    syncStockWithCart();
  } catch (error) {
    console.error('Error fetching products:', error);
  }
};

watch(currentPage, fetchProducts);

const searchProducts = () => {
  currentPage.value = 1;
  fetchProducts();
};

const agregando = () => {
  agregarCarrito.value = 'Cargando...';
  setTimeout(() => {
    agregarCarrito.value = 'Agregar a carrito';
  }, 200);
}

const show = () => {
  visible.value = true;
  setTimeout(() => {
    visible.value = false;
  }, 600);
};

onMounted(fetchProducts);

const getStockColorClass = (stock) => {
  if (stock === 0) return 'text-red-500';
  if (stock <= 5) return 'text-orange-500';
  return 'text-green-500';
};
</script>

