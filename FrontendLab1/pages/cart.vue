<template>
  <div class="container mx-auto px-4">
    <h1 class="text-3xl font-bold mb-8">Carrito de compras</h1>
    <div v-if="cartItems.length > 0" class="bg-white rounded-lg shadow-md p-6">
      <div v-for="item in cartItems" :key="item.id_producto" class="cart-item">
        <p>{{ item.nombre }} - ${{ item.precio }}</p>

        <div class="quantity-controls">
          <v-btn icon @click="decreaseQuantity(item.id_producto)">
            <v-icon>mdi-minus</v-icon>
          </v-btn>
          <span>{{ item.quantity }}</span>
          <v-btn icon @click="increaseQuantity(item.id_producto)">
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </div>

        <p>Subtotal: ${{ item.precio * item.quantity }}</p>
      </div>
      <div class="mt-6 flex justify-between items-center">
        <span class="text-xl font-bold">Total: ${{ cartTotal }}</span>
        <v-btn color="primary" @click="checkout">Pagar</v-btn>
      </div>
    </div>
    <div v-else class="text-center py-12">
      <p class="text-xl text-gray-600">No has agregado ningún producto en el carrito</p>
    </div>
  </div>
</template>

<script setup>
import { useCart } from '@/composables/useCart'
import axios from 'axios'

const { cartItems, cartTotal, increaseQuantity, decreaseQuantity, clearCart } = useCart()
const API_URL = 'http://localhost:8080/api'

const total = computed(() => {
  return cartItems.value.reduce((sum, item) => sum + (item.product.price * item.quantity), 0)
})

const checkout = async () => {
  try {
    await axios.post(`${API_URL}/orden`, {
      id_cliente: 1,
      fecha: new Date(),
      total: cartTotal.value,
      detalles: cartItems.value.map(item => ({
        id_producto: item.id,
        cantidad: item.quantity,
        precio_unitario: item.price,
      }))
    })
    clearCart()
    alert('Orden enviada exitosamente.')
  } catch (error) {
    console.error('Error al enviar la orden:', error)
  }
}
</script>