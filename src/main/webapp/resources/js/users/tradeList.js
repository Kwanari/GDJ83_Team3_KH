const category_amount = document.getElementById("category-amount");
const category_type = document.getElementById("category-type");
const list = document.getElementById("list");

category_type.addEventListener("change", ()=>{

    if(category_type.value == 0){
        return;
    }
   fetch("/users/tradeList/select?transaction_type=" + category_type.value, {
        method:"GET"
   })
   .then((res)=>{return res.text()})
   .then((res)=>{
        list.innerHTML=res;
   })
})