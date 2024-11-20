<template>
  <div class="login">
    <h1 class="title">Login</h1>
    <v-form @submit.prevent="login" class="form">

      <v-text-field
        v-model="email"
        label="Email"
        outlined
        required
        type="email"
        placeholder="Email"
      ></v-text-field>

      <v-text-field
        v-model="password"
        label="Password"
        outlined
        required
        type="password"
        placeholder="Password"
      ></v-text-field>

      <p v-if="error" class="error">
        Has introducido mal el email o la contraseña.
      </p>

      <v-btn class="form-submit" type="submit" color="primary">
        Login
      </v-btn>
      
    </v-form>
  </div>
</template>


<script>
export default {
  data: () => ({
    email: "",
    password: "",
    error: false,
  }),
  methods: {
    login() {
      console.log(this.email);
      console.log(this.password);
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
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