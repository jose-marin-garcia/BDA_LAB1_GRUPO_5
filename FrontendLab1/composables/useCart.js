// composables/useCart.js
import { ref, computed, watch } from 'vue'

// Recuperar carrito desde localStorage si existe
const savedCart = JSON.parse(localStorage.getItem('cartItems')) || []

// Singleton: Crear referencias únicas
const cartItems = ref(savedCart) // Estado del carrito compartido globalmente

// Contador de productos en el carrito
const cartCount = computed(() => cartItems.value.reduce((sum, item) => sum + item.quantity, 0))

// Calcular el total del carrito
const cartTotal = computed(() =>
  cartItems.value.reduce((sum, item) => sum + item.precio * item.quantity, 0)
)

// Función para agregar productos al carrito
const addToCart = (product) => {
  if (product.stock > 0) {
    const existingProduct = cartItems.value.find(item => item.id_producto === product.id_producto)
    if (existingProduct) {
      if (existingProduct.quantity < product.stock) {
        existingProduct.quantity += 1
        product.stock -= 1
      }
    } else {
      cartItems.value.push({ ...product, quantity: 1 })
      product.stock -= 1
    }
  }
}

// Aumentar la cantidad de un producto en el carrito
const increaseQuantity = (productId) => {
  const product = cartItems.value.find(item => item.id_producto === productId)
  if (product && product.quantity < product.stock) {
    product.quantity += 1
    product.stock -= 1
  }
}

// Disminuir la cantidad de un producto en el carrito
const decreaseQuantity = (productId) => {
  const product = cartItems.value.find(item => item.id_producto === productId)
  if (product && product.quantity > 1) {
    product.quantity -= 1
    product.stock += 1
  } else if (product) {
    // Eliminar el producto si la cantidad llega a 0
    removeItem(productId)
  }
}

// Eliminar un producto del carrito
const removeItem = (productId) => {
  const productIndex = cartItems.value.findIndex(item => item.id_producto === productId)
  if (productIndex !== -1) {
    cartItems.value[productIndex].stock += cartItems.value[productIndex].quantity // Devolver el stock
    cartItems.value.splice(productIndex, 1)
  }
}

// Función para vaciar el carrito después de pagar
const clearCart = () => {
  cartItems.value = []
}

// Guardar el carrito en localStorage cada vez que cambie
watch(cartItems, (newCart) => {
  localStorage.setItem('cartItems', JSON.stringify(newCart))
}, { deep: true })

// Exportar el composable
export const useCart = () => {
  return {
    cartItems,
    cartCount,
    cartTotal,
    addToCart,
    increaseQuantity,
    decreaseQuantity,
    removeItem,
    clearCart,
  }
}
