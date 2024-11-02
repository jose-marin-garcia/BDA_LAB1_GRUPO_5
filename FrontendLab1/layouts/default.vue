<template>
    <v-app>
      <v-app-bar app color="primary" dark>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
        <v-toolbar-title>Mi E-commerce</v-toolbar-title>
      </v-app-bar>
      <v-main>
        <nuxt-page />
      </v-main>
      <v-footer app color="primary" dark>
        <v-col class="text-center white--text">
          <a href="https://github.com/tu-repo" target="_blank" class="white--text">Repositorio del Proyecto</a>
        </v-col>
      </v-footer>
    </v-app>
  </template>
  