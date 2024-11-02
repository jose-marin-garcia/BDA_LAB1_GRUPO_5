<template>
    <div class="container mx-auto px-4">
      <h1 class="text-3xl font-bold mb-8">Carrito de compras</h1>
      <div v-if="cartItems.length > 0" class="bg-white rounded-lg shadow-md p-6">
        <div v-for="item in cartItems" :key="item.id" class="flex items-center py-4 border-b">
          <img :src="item.product.image" :alt="item.product.name" class="w-24 h-24 object-cover mr-6">
          <div class="flex-grow">
            <h2 class="text-xl font-semibold">{{ item.product.name }}</h2>
            <p class="text-gray-600">${{ item.product.price }}</p>
          </div>
          <div class="flex items-center">
            <button @click="updateQuantity(item, -1)" class="px-2 py-1 border rounded">-</button>
            <span class="mx-4">{{ item.quantity }}</span>
            <button @click="updateQuantity(item, 1)" class="px-2 py-1 border rounded">+</button>
          </div>
          <button @click="removeItem(item)" class="ml-6 text-red-500 hover:text-red-600">Remove</button>
        </div>
        <div class="mt-6 flex justify-between items-center">
          <span class="text-xl font-bold">Total: ${{ total }}</span>
          <button @click="checkout" class="bg-green-500 text-white px-6 py-3 rounded hover:bg-green-600">
            Checkout
          </button>
        </div>
      </div>
      <div v-else class="text-center py-12">
        <p class="text-xl text-gray-600">No has agregado ningún producto en el carrito</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, onMounted } from 'vue'
  import axios from 'axios'
  
  const cartItems = ref([])
  const API_URL = 'http://localhost:8080/api'
  
  const total = computed(() => {
    return cartItems.value.reduce((sum, item) => sum + (item.product.price * item.quantity), 0)
  })
  
  onMounted(async () => {
    await fetchCart()
  })
  
  const fetchCart = async () => {
    try {
      const response = await axios.get(`${API_URL}/cart`)
      cartItems.value = response.data
    } catch (error) {
      console.error('Error fetching cart:', error)
    }
  }
  
  const updateQuantity = async (item, change) => {
    const newQuantity = item.quantity + change
    if (newQuantity < 1) return
    
    try {
      await axios.put(`${API_URL}/cart/update`, {
        productId: item.product.id,
        quantity: newQuantity
      })
      await fetchCart()
    } catch (error) {
      console.error('Error updating quantity:', error)
    }
  }
  
  const removeItem = async (item) => {
    try {
      await axios.delete(`${API_URL}/cart/${item.product.id}`)
      await fetchCart()
    } catch (error) {
      console.error('Error removing item:', error)
    }
  }
  
  const checkout = async () => {
    try {
      await axios.post(`${API_URL}/orders/create`)
      cartItems.value = []
      // Show success notification and redirect
    } catch (error) {
      console.error('Error creating order:', error)
    }
  }
  </script>