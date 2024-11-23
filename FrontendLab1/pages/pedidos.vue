<template>
  <div class="container mx-auto px-4">
    <h1 class="text-3xl font-bold mb-8">Pedidos</h1>

    <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
      <div
        v-for="order in Order"
        :key="order.idOrden"
        class="border border-gray-300 rounded-lg shadow-md p-4"
      >
      <p class="text-xl font-semibold mb-2">Fecha de pedido</p>
        <h2 class="text-xl font-semibold mb-2">{{ formatDate(order.fechaOrden) }}</h2>
        <p class="text-xl font-semibold mb-2">Estado</p>
        <span :class="['font-medium', getStateColor(order.estado)]">
           {{ order.estado }}
          </span>
        <p class="text-xl font-semibold mb-2">Costo Total</p>
        <div class="flex justify-between items-center mb-4">
          <span class="text-xl font-bold">${{ order.total }}</span>
        </div>
        <!-- Botón para abrir el modal -->
        <button @click="openModal(order.idOrden)" class="bg-blue-500 text-white px-4 py-2 rounded">
          Detalle de la Orden
        </button>
      </div>
    </div>

    <!-- Paginación -->
    <v-pagination
      v-model="currentPage"
      :length="totalPages"
      color="primary"
      class="mt-6"
    />

    <!-- Modal con los detalles de la orden -->
    <div v-if="isModalOpen" class="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
      <div class="bg-white p-6 rounded-lg w-1/2">
        <h2 class="text-xl font-semibold mb-4">Detalles de la Orden</h2>
        <div v-if="orderDetails">
          <p><strong>Producto:</strong> {{ orderDetails.idProducto }}</p>
          <p><strong>Cantidad:</strong> {{ orderDetails.cantidad }}</p>
          <p><strong>Precio Unitario:</strong> ${{ orderDetails.precioUnitario }}</p>
        </div>
        <button @click="closeModal" class="bg-red-500 text-white px-4 py-2 rounded mt-4">
          Cerrar
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';

const Order = ref([]); // Almacena las órdenes
const currentPage = ref(1); // Página actual
const totalPages = ref(1); // Número total de páginas
const API_URL = 'http://localhost:8090/api/orden'; // URL del backend Orden
const API_URL_2 = 'http://localhost:8090/api/detalleorden'; // URL del backend de DetalleOrden

// Estado del modal
const isModalOpen = ref(false);
const orderDetails = ref(null);

// Abrir el modal
const openModal = async (idOrden) => {
  try {
    const response = await axios.get(`${API_URL_2}/get/${idOrden}`);
    orderDetails.value = response.data; // Guarda DetalleOrden
    isModalOpen.value = true; // Abre el popup
  } catch (error) {
    console.error('Error fetching order details:', error);
  }
};


const closeModal = () => {
  isModalOpen.value = false;
  orderDetails.value = null; 
};

// Función para obtener las órdenes del cliente
const fetchOrders = async () => {
  const limit = 8; 
  const offset = (currentPage.value - 1) * limit;
  const idCliente = localStorage.getItem('userId'); // Obtener el ID del cliente desde localStorage

  if (!idCliente) {
    console.error('ID del cliente no encontrado en localStorage');
    return;
  }

  try {
    
    const response = await axios.get(`${API_URL}/${idCliente}/${limit}/${offset}`);
    console.log('Respuesta del servidor:', response.data); 

    
    Order.value = response.data;
    totalPages.value = Math.ceil(response.data.length / limit);
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

// Formatear la fecha en el formato deseado
const formatDate = (dateString) => {
  const date = new Date(dateString);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  return `${day}/${month}/${year} ${hours}:${minutes}`;
};

// Llamar la función cuando se monte el componente
onMounted(fetchOrders);

// Actualizar las órdenes cuando se cambie la página
watch(currentPage, fetchOrders);

const getStateColor = (estado) => {
  if (estado === "pendiente") return 'text-red-500'
  if (estado === "pagada") return 'text-orange-500'
  return 'text-green-500'
}


</script>


