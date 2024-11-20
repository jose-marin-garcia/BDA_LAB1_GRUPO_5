<template>
  <div class="container mx-auto px-4">
    <h1 class="text-3xl font-bold mb-8">Productos</h1>
    <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
      <div v-for="product in products" :key="product.id" class="border border-gray-300 rounded-lg shadow-md p-4">
        <h2 class="text-xl font-semibold mb-2">{{ product.nombre }}</h2>
        <p class="text-gray-600 mb-2">{{ product.descripcion }}</p>
        <div class="flex justify-between items-center mb-4">
          <span class="text-xl font-bold">${{ product.precio }}</span>
          <span :class="['font-medium', getStockColorClass(product.stock)]">
            Stock: {{ product.stock }}
          </span>
        </div>
        <button @click="() => {
          addToCart(product); console.log(cartItems.value);
        }" class="w-full bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:bg-gray-400"
          :disabled="product.stock === 0">
          {{ product.stock === 0 ? 'Agotado' : 'Agregar a carrito' }}
        </button>
      </div>
    </div>

    <!-- Pagination Component -->
    <v-pagination v-model="currentPage" :length="totalPages" color="primary" class="mt-6" />

  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useCart } from '@/composables/useCart.js'
import axios from 'axios'

const products = ref([])
const currentPage = ref(1)
const totalPages = ref(1)
const API_URL = 'http://localhost:8090/api'
const { addToCart } = useCart()

const fetchProducts = async () => {
  const limit = 8; // Número de productos por página
  const offset = (currentPage.value - 1) * limit;
  try {
    const response = await axios.get(`${API_URL}/producto`, {
      params: {
        limit: limit,
        offset: offset
      }
    });
    products.value = response.data.products;
    totalPages.value = Math.ceil(response.data.totalCount / limit);
  } catch (error) {
    console.error('Error fetching products:', error);
  }
};

watch(currentPage, fetchProducts)

onMounted(fetchProducts)

const getStockColorClass = (stock) => {
  if (stock === 0) return 'text-red-500'
  if (stock <= 5) return 'text-orange-500'
  return 'text-green-500'
}

</script>
