<template>
  <div class="register">
    <h1 class="title">Sign Up</h1>
    <form action class="form" @submit.prevent="register">
      
      <label class="form-label" for="#nombre">Nombre:</label>
      <input v-model="nombre" class="form-input" type="nombre" id="nombre" required placeholder="Nombre" />
      
      <label class="form-label" for="#direccion">Direccion:</label>
      <input v-model="direccion" class="form-input" type="direccion" id="nombre" required placeholder="Nombre" />
      
      <label class="form-label" for="#email">Email:</label>
      <input v-model="email" class="form-input" type="email" id="email" required placeholder="Email" />

      <label class="form-label" for="#email">Telefono:</label>
      <input v-model="email" class="form-input" type="tel" id="email" required placeholder="Email" />
      
      <label class="form-label" for="#password">Contraseña:</label>
      <input v-model="password" class="form-input" type="password" id="password" placeholder="Contraseña" />
      
      <label class="form-label" for="#password-repeat">Repite la contraeña:</label>
      <input v-model="passwordRepeat" class="form-input" type="password" id="password-repeat" placeholder="Contraseña" />
      
      <v-btn class="form-submit" type="submit" value="Sign Up" href="/"/>
    </form>
  </div>
</template>

<script>
export default {
  data: () => ({
    nombre: "",
    direccion: "",
    email: "",
    password: "",
    passwordRepeat: "",
  }),
  methods: {
    register() {
      console.log(this.nombre);
      console.log(this.email);
      console.log(this.password);
      console.log(this.passwordRepeat);
    },
  },
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
  width: 20%;
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
  border: none;
  color: white;
  margin-top: 3rem;
  padding: 1rem 0;
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