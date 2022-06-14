$(function() {
    $.ajax({
        url:'http://localhost:8888/back/productlist',
        //type 쓰지 않으면 기본 GET 방식
        success: function(jsonObj){
            //jsonObj은 배열형태, 반복처리하면서
              //div.td 객체를 DOM트리에서 찾아 복사 붙여넣기 (응답받은 여러 상품에 적용해야하기 때문에. 하나의 상품만 X)
              //붙여넣기 한 div.td객체의 하위 객체 중 img 객체의 src 속성값을 상품번호.jpg alt 속성값을 상품명으로 설정
              
              //div.td 객체 찾기
              let $tdObj = $('div.td');
              
              $(jsonObj).each(function(index,item){
                console.log(item.prod_no+":"+item.prod_name+":"+item.prod_price);
                let $copyObj = $tdObj.clone(); //DOM 트리에서 td객체 복제
                let $imgObj = $copyObj.find("img"); // td 객체 복제하여 img 속성 찾기
                $imgObj.attr("src", "../images/" + item.prod_no + ".jpg");
                alert($imgObj.attr("src"));
                $imgObj.attr("alt", item.prod_name);
                $copyObj.find("li.prod_name").html(item.prod_name);

                // 반복수행하면서 만들어 낸 복제본을 table 마지막 자식으로 추가(붙여넣기)
                $('div.table').append($copyObj); //table객체 찾아 붙여넣기
                //append - last child로 추가 

            });
            $tdObj.hide();
        },
        error: function(jqXHR){
            alert('오류:' + jqXHR.status); 
            //JSON 문자열 문제 : 200번 에러코드
        }

    });
});