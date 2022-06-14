$(function() {
    $.ajax({
        url:'http://localhost:8888/back/productlist',
        //type 쓰지 않으면 기본 GET 방식
        success: function(jsonObj){
            //jsonObj은 배열형태, 반복처리하면서
              //div.td 객체를 DOM트리에서 찾아 복사 붙여넣기 (응답받은 여러 상품에 적용해야하기 때문에. 하나의 상품만 X)
              //붙여넣기 한 div.td객체의 하위 객체 중 img 객체의 href 속성값을 상품번호.jpg alt 속성값을 상품명으로 설정
              $(jsonObj).each(function(index,item){
                console.log(item.prod_no+":"+item.prod_name+":"+item.prod_price);
              });
        },
        error: function(jqXHR){
            alert('오류:' + jqXHR.status); 
            //JSON 문자열 문제 : 200번 에러코드
        }

    });
});