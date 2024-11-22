<template>
  <div class="register">
    <h1 class="title text-2xl">Sign Up</h1>
    <v-form @submit.prevent="registrarUsuario" class="form">
      <v-row dense>
        <v-col cols="6">
          <v-text-field v-model="nombre" label="Nombre" outlined required placeholder="Nombre"></v-text-field>
        </v-col>

        <v-col cols="6">
          <v-text-field id="address-input" v-model="direccion" label="Dirección" outlined required placeholder="Dirección"></v-text-field>
        </v-col>

        <v-col cols="6">
          <v-text-field v-model="email" label="Email" outlined required type="email" placeholder="Email"></v-text-field>
        </v-col>

        <v-col cols="6">
          <v-text-field v-model="telefono" label="Teléfono" outlined required type="tel"
            placeholder="Teléfono"></v-text-field>
        </v-col>

        <v-col cols="6">
          <v-text-field v-model="password" label="Contraseña" outlined required type="password"
            placeholder="Contraseña"></v-text-field>
        </v-col>

        <v-col cols="6">
          <v-text-field v-model="passwordRepeat" label="Repite la contraseña" outlined required type="password"
            placeholder="Contraseña"></v-text-field>
        </v-col>
      </v-row>

      <v-btn class="form-submit" type="submit" color="primary">
        Registrarse
      </v-btn>
    </v-form>
  </div>
</template>



<script>
import axios from 'axios';
export default {
  mounted() {
    this.initializeAutocomplete();
  },
  data: () => ({
    nombre: "",
    direccion: "",
    email: "",
    telefono: "",
    password: "",
    passwordRepeat: "",
  }),
  methods: {
    registrarUsuario() {
      if (this.password !== this.passwordRepeat) {
        alert('Las contraseñas no coinciden');
        return;
      }
      axios.post('http://localhost:8090/api/cliente/register', {
        nombre: this.nombre,
        direccion: this.direccion,
        email: this.email,
        telefono: this.telefono,
        password: this.password,
      }).then(response => {
        console.log(response);
        this.$router.push('/login');
      }).catch(error => {
        console.log(error);
      });
    },
    initializeAutocomplete() {
      const input = document.getElementById("address-input");
      const autocomplete = new google.maps.places.Autocomplete(input,
      { 
        types: ["geocode"],
        componentRestrictions: { country: "cl" },
        fields: ["address_components", "formatted_address", "geometry"],
      });

      autocomplete.addListener("place_changed", () => {
        const place = autocomplete.getPlace();

        if (!place.geometry) {
          console.error("Place not found");
        }
        console.log("Selected Address:", place.formatted_address);
        console.log("Latitude:", place.geometry.location.lat());
        console.log("Longitude:", place.geometry.location.lng());
        this.direccion = place.formatted_address;
      });
    },
  }
};
</script>

<style lang="scss" scoped>
.register {
  padding: 2rem;
}

.title {
  text-align: center;
}

.form {
  margin: 3rem auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 50%;
  min-width: 350px;
  max-width: 100%;
  background: rgb(255, 255, 255);
  border-radius: 5px;
  padding: 40px;
  box-shadow: 0 4px 10px 4px rgba(0, 0, 0, 0.3);
}

.form-label {
  margin-top: 2rem;
  color: rgb(0, 0, 0);
  margin-bottom: 0.5rem;

  &:first-of-type {
    margin-top: 0rem;
  }
}

.form-input {
  padding: 10px 15px;
  background: none;
  background-image: none;
  border: 1px solid white;
  color: rgb(0, 0, 0);
  box-shadow: 0 4px 10px 4px rgba(0, 0, 0, 0.3);

  &:focus {
    outline: 0;
    border-color: #000000;
  }
}

.form-submit {
  background: royalblue;

  color: white;

  padding: 2rem 1;
  cursor: pointer;

  &:hover {
    background: gray;
  }
}

.error {
  margin: 1rem 0 0;
  color: #ff4a96;
}
</style>