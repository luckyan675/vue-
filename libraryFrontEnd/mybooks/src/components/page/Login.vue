<template>
  <div>
    <h2 style="text-align: center;font-family: 'Adobe 楷体 Std R';color: palevioletred; padding-top:0.5rem;">图 书 馆</h2>
    <div id="content">
      <div id="myCarousel" class="carousel slide">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div id="imgs">
                <div class="item active">
                    <img src="../../../static/img/03.jpg" alt="第一张" />
                </div>
                <div class="item">
                    <img src="../../../static/img/02.jpg" alt="第二张" />
                </div>
                <div class="item">
                    <img src="../../../static/img/04.jpg" alt="第三张" />
                </div>>
            </div>
        </div>
        <a class="carousel-control left" href="#" v-on:click="left_method" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#" v-on:click="right_method" data-slide="next">&rsaquo;</a>
      </div>
      <div class="panel panel-default" id="login">
        <div class="panel-heading" style="background-color: #fff">
          <h3 class="panel-title">请登录</h3>
        </div>
        <div class="panel-body">
          <div class="form-group">
            <label for="id">用户名</label>
            <input type="text" class="form-control" id="id" v-model="username" placeholder="请输入用户名" />
          </div>
          <div class="form-group">
            <label for="passwd">密码</label>
            <input type="password" class="form-control" id="passwd" v-model="passwd" placeholder="请输入密码" />
          </div>
          <div class="checkbox text-left">
            <label>
              <input type="checkbox" id="remember" />记住密码
            </label>
            <a style="margin-left: 100px" href="#">忘记密码?</a>
          </div>

          <p style="text-align: right;color: red;position: absolute" id="info"></p>
          <br />
          <el-button  v-on:click="login" >登陆</el-button>
          <el-button  v-on:click="reset" >重置</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import {login} from "../../interfaces/interfaces"

    export default {
        name: "Login",
        data() {
            return {
                img_index:0,
                username:"",
                passwd:""
            };
        },
        methods: {
            left_method(){
                if(this.img_index === 2) this.img_index=-1;
                this.img_index++;
                const imgs_box = document.getElementById("imgs");
                imgs_box.style.left = -(this.img_index * 8) + "rem";
                
                // 更新小点点
                const spots = document.querySelectorAll(".carousel-indicators li");

                spots.forEach((item, index)=>{
                    item.classList.remove("active")
                    if(index === this.img_index){
                        item.classList.add("active")
                    }
                })
            },
            right_method(){
                if(this.img_index === 0)  this.img_index = 3;
                this.img_index--;
                const imgs_box = document.getElementById("imgs");
                imgs_box.style.left = -(this.img_index * 8) + "rem";

                 // 更新小点点
                const spots = document.querySelectorAll(".carousel-indicators li");

                spots.forEach((item, index)=>{
                    item.classList.remove("active")
                    if(index === this.img_index){
                        item.classList.add("active")
                    }
                })
            },
            // 登录方法
            login(){
                login(this.username,this.passwd)
                .then((response)=>{
                    
                    if(response.data.stateCode == "1"){
                        window.localStorage.setItem("userName",response.data.userName)
                        window.localStorage.setItem("userId",response.data.userId)
                        window.localStorage.setItem("isAdmin",response.data.isAdmin)
                        this.$router.push("/home")
                    }
                })
            },
            reset(){
                this.username = "";
                this.passwd = "";
            }
        },
        mounted() {
            this.timer = setInterval(()=>{
                if(this.img_index === 2)this.img_index=-1;
                this.img_index++;
                const imgs_box = document.getElementById("imgs");
                imgs_box.style.left = -(this.img_index * 8) + "rem";
                
                // 更新小点点
                const spots = document.querySelectorAll(".carousel-indicators li");

                spots.forEach((item, index)=>{
                    item.classList.remove("active")
                    if(index === this.img_index){
                        item.classList.add("active")
                    }
                })
            },3000)
        },
        destroyed() {
            clearInterval(this.timer)
        }
    };
    
</script>

<style >
#content {
    display: flex;
    padding:0.5rem 1rem;
    justify-content: center;
    align-items: center;
}
#content #myCarousel {
    position: relative;
    width: 8rem;
    height: 3.7rem;
    margin-right: 1rem;
}

#content #myCarousel .carousel-control{
    position: absolute;
    bottom: 0;
    width: 1.1rem;
    height: 100%;
    text-decoration: none;
    display: flex;
    align-items: center;
    justify-content: space-around;
    font-size: 0.32rem;
}
#content #myCarousel .carousel-control.left{
    left: 0;
    background-image: linear-gradient(to left,rgba(0,0,0,.0001) 0,rgba(0,0,0,.3) 100%);
}
#content #myCarousel .carousel-control.left:hover{
    color: #fff;
    background-image: linear-gradient(to left,rgba(0,0,0,.0001) 0,rgba(0,0,0,0.6) 100%);
}
#content #myCarousel .carousel-control.right{
    right: 0;
    background-image: linear-gradient(to right,rgba(0,0,0,.0001) 0,rgba(0,0,0,.3) 100%);
}
#content #myCarousel .carousel-control.right:hover{
    color: #fff;
    background-image: linear-gradient(to right,rgba(0,0,0,.0001) 0,rgba(0,0,0,0.6) 100%);
}
#content #myCarousel .carousel-indicators{
    position: absolute;
    display: flex;
    bottom: 0.2rem;
    left: calc(50% - 0.25rem);
    right: auto;
    z-index: 999;
}
#content #myCarousel .carousel-indicators li{
    width: 0.1rem;
    height: 0.1rem;
    margin-right: 0.1rem;
    list-style: none;
    border-radius: 50%;
    border:1px solid #fff;
    background-color: transparent;
}
#content #myCarousel .carousel-indicators li.active{
    background-color: #fff;
}
#content #myCarousel .carousel-inner{
    position: relative;
    width: 100%;
    overflow: hidden;
    
}
#content #myCarousel .carousel-inner #imgs{
    position: relative;
    width: 24rem;
    height: 100%;
    display: flex;
    left: 0;
    transition: 0.5S left ease-in-out;
}
#content #myCarousel .carousel-inner .item{
    flex-shrink: 0;
    /* background-color: hotpink; */
}
#content #myCarousel .carousel-inner .item img{
    width: 8rem;
    height: 3.7rem;
    /* height: 99% */
}
#content #login {
    width:2.5rem;
    display: flex;
    flex-direction: column;
    align-items: center;
}
#content #login .panel-heading,#content #login .panel-body{
    border: 1px solid #ccc;
    padding: 0.1rem 0.15rem;
    width:100%;
}
#content #login .panel-body button {
    padding: 12px 10px;
    margin-right: 0.2rem;
}

#content #login .panel-body .form-group{
    display: flex;
    line-height: 0.1rem;
    height: 0.4rem;
    align-items: center;
    border-bottom: 1px solid #ccc;
}
#content #login .panel-body .form-group label{
    width: 0.6rem;
    text-align: left;
}
#content #login .panel-body .form-group input{
    width: 1.9rem;
    height: 0.3rem;
    border: none;
}
#content #login .panel-body .form-group input:focus{
    outline:none;
}
</style>