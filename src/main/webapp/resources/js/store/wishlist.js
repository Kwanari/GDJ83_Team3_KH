const delbtn = document.getElementsByClassName("delbtn");
const wishdiv = document.getElementById("wishdiv");
const checkdiv = document.getElementById("checkdiv");
const chAll = document.getElementById("chAll")
const ch = document.getElementsByClassName("ch")
const delAll = document.getElementById("delAll")
const purchasebtn = document.getElementById("purchasebtn")
const arr = document.getElementById("arr")
const frm = document.getElementById("frm")

let wishnum = 0;
let itemnum = 0;

purchasebtn.addEventListener("click",()=>{
    let ar = [];
    
    for(check of ch){
        itemnum = check.getAttribute("data-inum")
        if(check.checked){
            ar.push(itemnum)
        } else {
            for(i=0;i<ar.length;i++){
                if(ar[i]===itemnum){
                    ar.splice(i,1)
                }
            }
        }
    }

    arr.value = ar

    frm.submit()
    
})


//체크박스 전체 체크 되면 전체선택 박스 체크기능
checkdiv.addEventListener("click", (e)=>{
    let flag = false;

    if(e.target.classList.contains("ch")){
        for(check of ch){
            console.log(check.checked)

            if(!check.checked){
                flag = false;
                break;
            } else {
                flag = true;
            }
        }
    }

    chAll.checked = flag
})

//전체선택
chAll.addEventListener("click", ()=>{
    for(check of ch){
        check.checked = chAll.checked
    }
})

//선택삭제
delAll.addEventListener("click", ()=>{
    for(check of ch){
        if(check.checked){
            wishnum = check.getAttribute("data-wnum")

            delwish(wishnum)
        }
    }
})

//각 아이템별 삭제 기능
checkdiv.addEventListener("click", (e)=>{    
        let flag = false;
        for(del of delbtn){
            if(e.target.classList.contains("delbtn")){
                let check = confirm("장바구니에서 삭제하시겠습니까?")

                if(!check){
                    return
                }

                wishnum = e.target.getAttribute("data-wnum")
                flag = true;
                break
            } else if (e.target.classList.contains("far")){
                let check = confirm("장바구니에서 삭제하시겠습니까?")

                if(!check){
                    return
                }

                wishnum = e.target.closest('a').getAttribute("data-wnum")
                flag = true;
                break
            }
        }

        if(flag){
            delwish(wishnum)
        }

})

function delwish(wishnum){
    fetch("deleteWishList?wishlist_num="+wishnum,{
        method:"get"
    }).then(r=>r.text())
    .then(r=>{
        r = r.trim()

        if(r>0){
            
            let delid = document.getElementById(wishnum)

            delid.parentNode.parentNode.remove()

        }

    })
}



