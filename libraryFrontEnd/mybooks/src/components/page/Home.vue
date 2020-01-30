<template>
  <div>
    <Header></Header>
    <AllBooks></AllBooks>
  </div>
</template>

<script>
import Header from "../common/Header"
import AllBooks from "../common/Allbooks"
export default {
  name: "Home01",
  data() {
    return {
     bookList :[]
    };
  },
  components:{"Header":Header,AllBooks},

};
</script>

<style >

</style>