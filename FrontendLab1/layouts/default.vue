<template>
    <v-app>
        <AppBar v-if="!hideComponents" />
        <v-main>
            <nuxt-page />
        </v-main>
        <Footer v-if="!hideComponents" />
    </v-app>
</template>

<script setup>
import AppBar from '@/components/AppBar.vue'
import Footer from '@/components/Footer.vue'
import { useRoute } from 'vue-router';

const route = useRoute();
const hideComponents = ['login', 'register'].includes(route.name);

</script>

<style scoped>
</style>