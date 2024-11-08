<template>
    <v-app>
        <AppBar />
        <v-main>
            <nuxt-page />
        </v-main>
        <Footer />
    </v-app>
</template>

<script setup>
import AppBar from '@/components/AppBar.vue'
import Footer from '@/components/Footer.vue'
</script>

<style scoped>
</style>