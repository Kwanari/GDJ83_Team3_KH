const myPet = document.getElementById("myPet");
const petParent = document.getElementById("petParent");
const modalContent = document.getElementById("modalContent");
const sendBtn = document.getElementById("sendBtn");
const chatContent = document.getElementById("chatContent");
let div = document.createElement("div");
let petChatBtn;
/* =====================챗봇에 관련된 변수===================== */
// 내 키
const apiKey = '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';
// post로 보낼시 json 형식으로 나타날 주소 
const apiEndpoint = 'https://api.openai.com/v1/chat/completions';

// 캐릭터의 처음 위치
let x = 500;
let y = 400;
let currentPositionX = 450;
let currentPositionY = 350;


document.addEventListener("keydown", (e)=>{
    if(e.key==='ArrowLeft') {
        x = x-5;
        myPet.style.left = x+"px";
        currentPositionX = myPet.style.left;
    } else if(e.key==='ArrowRight') {
        x = x+5;
        myPet.style.left = x+"px";
        currentPositionX = myPet.style.left;
    } else if(e.key==='ArrowUp') {
        y = y-5;
        myPet.style.top = y+"px";
        currentPositionY = myPet.style.top;
    } else if(e.key==='ArrowDown') {
        y = y+5;
        myPet.style.top = y+"px";
        currentPositionY = myPet.style.top
    }
})


//input 창에 쓴대로 한줄씩 추가되는 태그 생성
function chatting() {
    let h1 = document.createElement("h1");
    h1.append(chatContent.value);
    div.append(h1);
    modalContent.append(div);
    
}

// pet 이미지를 누를 때 이벤트
myPet.addEventListener("click", ()=>{

    // 캐릭터 누르면 버튼 생성
    petChatBtn = document.createElement("button");
    petChatBtn.setAttribute("type", "button");
    petChatBtn.classList.add('btn', 'btn-outline-warning');
    petChatBtn.setAttribute("id", "petChat");
    petChatBtn.innerHTML = "대화하기";
    petChatBtn.setAttribute("data-bs-toggle", "modal");
    petChatBtn.setAttribute("data-bs-target", "#commentModal");
    petParent.append(petChatBtn);

    // 버튼이 다마고치 옆에 위치
    petChatBtn.style.left = currentPositionX;
    petChatBtn.style.top = currentPositionY;

    petChatBtn.addEventListener("click",()=>{
        chatContent.value="";
        div.remove();
        div.innerHTML = "";
    })
})

function addMessage(sender, message){
    //div 태그 안에 클래스 추가
    let h3 = document.createElement("h3");
    h3.append(chatContent.value);
    h3.className='message';
    h3.textContent = `${sender}: ${message}`;
    div.append(h3);
    modalContent.prepend(div);
}

async function fetchAIResponse(prompt) {
    const requestOptions = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${apiKey}`
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",  // 사용할 AI 모델
            messages: [
                {   
                    // AI 튜닝
                    role: "system",
                    content: [
                      {
                        type: "text",
                        text: "you are my pet.\nyou should be kind and happy all the time and give me joy.\nyou don't need to be my assistant.\nyou just need to be my pet.\nyou don't have to ask me anything, just be fun. basically, you are a bird."
                      }
                    ]
                },
                {
                    role: "user", // 메시지 역할을 user로 설정
                    content: prompt // 사용자가 입력한 메시지
                }
        ],
            temperature: 0.8, // 모델의 출력 다양성
            max_tokens: 1024, // 응답받을 메시지 최대 토큰(단어) 수 설정
            top_p: 1, // 토큰 샘플링 확률을 설정
            frequency_penalty: 0.5, // 일반적으로 나오지 않는 단어를 억제하는 정도
            presence_penalty: 0.5, // 동일한 단어나 구문이 반복되는 것을 억제하는 정도
            stop: ["Human"], // 생성된 텍스트에서 종료 구문을 설정
        }),
    };

    try {
        const response = await fetch(apiEndpoint, requestOptions);
        const data = await response.json();
        console.log(data);
        const aiResponse = data.choices[0].message.content;
        return aiResponse;
    } catch (error) {
		console.error('OpenAI API 호출 중 오류 발생:', error);
        return 'OpenAI API 호출 중 오류 발생';
    }
}

sendBtn.addEventListener("click", async () => {
    
    const message = chatContent.value.trim();

    if(message.length === 0) return;

    addMessage("나", message);
    chatContent.value="";

    const petResponse = await fetchAIResponse(message);
    addMessage("마이펫", petResponse);


})

chatContent.addEventListener("keyup", (e)=>{
    if(e.key === 'Enter') {
       sendBtn.click();
    }
    
})



