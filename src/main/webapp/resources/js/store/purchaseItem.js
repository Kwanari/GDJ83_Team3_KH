const kakaobtn = document.getElementById("kakaobtn")
const kakaodelbtn = document.getElementById("kakaodelbtn")

let uuid = ""; 

kakaodelbtn.addEventListener("click",()=>{
    kakaoDel()
})

async function kakaoDel() {
    uuid = "6d8667f6-2fc6-4955-8e94-c829a9c42f46";

    const del = await fetch(`https://api.portone.io/payments/payment-`+uuid+`/cancel`,{ 
        method: 'post',
        headers: {Authorization: `PortOne i68oGkSudVRHtsQtbxZitbS7DPq99kDGH6xS2tz5l9W7w8ppV6xKcAioepMEyyYiW2Ae0mUGZ0NgUguK`, headers: {'Content-Type': 'application/json'}},
        body: '{"reason":"그냥"}'
    },)
    if (!del.ok)
        throw new Error(`del:`+ await del.json());
    const d = await del.json();

    console.log(d)
}

kakaobtn.addEventListener("click",()=>{
    kakaoRequestPayment()
})

async function kakaoRequestPayment() {

    uuid = crypto.randomUUID()
    let amount = 64900

    const response = await PortOne.requestPayment({
        // Store ID 설정
        storeId: "store-0db2908e-d8b1-4658-a473-043843f02a63",
        // 채널 키 설정
        channelKey: "channel-key-a30ed3c2-b963-42d9-a899-949483621dc4",
        paymentId: `payment-`+uuid,
        orderName: "나이키 와플 트레이너 2 SD",
        totalAmount: amount,
        currency: "CURRENCY_KRW",
        payMethod: "EASY_PAY",
    });
    console.log(response.paymentId)
    console.log(response.code)

    if (response.code != null) {
        // 오류 발생
        return alert(response.message);
    }

    
    const notified = await fetch("/store/purchaseComplete", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        // paymentId와 주문 정보를 서버에 전달합니다
        body: JSON.stringify({
          payment_id: "payment-"+uuid,
          item_name: "나이키 와플 트레이너 2 SD",
          totalAmount: amount,
          // 주문 정보...
        }),
      });

    console.log(notified)

    // 포트원 결제내역 단건조회 API 호출
    const paymentResponse = await fetch(`https://api.portone.io/payments/payment-`+uuid,{ 
        headers: { Authorization: `PortOne i68oGkSudVRHtsQtbxZitbS7DPq99kDGH6xS2tz5l9W7w8ppV6xKcAioepMEyyYiW2Ae0mUGZ0NgUguK`} },)
    if (!paymentResponse.ok)
        throw new Error(`paymentResponse:`+ await paymentResponse.json());
    const payment = await paymentResponse.json();

    // 조회내역의 금액과 요청시의 금액 비교

    if(payment.amount.total == amount){
        switch (payment.status) {
            case "PAID": {
                console.log("결제 성공")
                break;
            }
            default : {
                console.log("결제 실패")
                break;
            }
        }
    }

}
