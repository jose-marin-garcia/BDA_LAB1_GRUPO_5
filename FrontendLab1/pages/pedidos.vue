<template>



</template>

<script>
import axios from 'axios';
const Order = ref([])
const API_URL = 'http://localhost:8080/api'
const currentPage = ref(1)
const totalPages = ref(1)

const fetchOrders = async () => {
    const limit = 8; // Número de productos por página
    const offset = (currentPage.value - 1) * limit;
    const idCliente = localStorage.getItem('userId'); // Obtener el ID desde localStorage
    console.log(idCliente);
  try {
    const response = await axios.get(`${API_URL}/orden`, {
      params: {
        limit: limit,
        offset: offset
      }
    });
    Order.value = response.data.Order;
    totalPages.value = Math.ceil(response.data.totalCount / limit);
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};


</script>