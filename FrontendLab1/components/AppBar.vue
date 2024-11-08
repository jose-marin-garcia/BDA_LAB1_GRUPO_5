<template>
    <v-app-bar color="primary">
        <!-- Enlaces de navegación -->
        <v-spacer></v-spacer>
        <v-btn text class="mx-2" href="/productos">Productos</v-btn>
        <v-btn text class="mx-2" href="/pedidos">Mis Pedidos</v-btn>
        <v-btn text class="mx-2" href="/perfil">Mi Cuenta</v-btn>
        <!-- Botón de carrito de compras -->
        <v-btn icon color="white" href="/cart">
            <v-icon>mdi-cart</v-icon>
            <span v-if="cartCount > 0" class="badge">{{ cartCount }}</span>
        </v-btn>
        <v-spacer></v-spacer>
    </v-app-bar>
</template>

<script setup>
import { useCart } from '@/composables/useCart.js'

// Obtenemos el estado del carrito
const { cartCount } = useCart()
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
</style>