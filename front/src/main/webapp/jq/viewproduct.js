$(function(){
    // alert(window.location.search);
    let queryString = location.search.substring(1); //0번 인덱스는 '?' 이니까 1번째 인덱스부터 가지고 오도록
    //queryString : 사용자가 입력 데이터를 전달하는 방법중의 하나로, url 주소에 미리 협의된 데이터를 파라미터를 통해 넘기는 것을 말한다.

    $.ajax({
        url:'/back/viewproduct',
        method:'get',
        // data: 'prod_no='+'C0001', //요청전달 데이터
        data : queryString,
        success:function(jsonObj){
            if(jsonObj.status == 1){
                // console.log(jsonObj);
                let prod_no = jsonObj.p.prodNo;
                let prod_name = jsonObj.p.prodName;
                let prod_price = jsonObj.p.prodPrice;
                let prod_mfd = jsonObj.p.prodMfd;
                let prod_info = jsonObj.p.prodInfo;
                $('div.viewproduct>img').attr('src','../images/'+ prod_no+'.jpg');
                //:
                $('div.viewproduct ul>li>span.prod_no').html(prod_no);
                $('div.viewproduct ul>li>span.prod_name').html(prod_name);
                $('div.viewproduct ul>li>span.prod_price').html(prod_price);
                $('div.viewproduct ul>li>span.prod_mfd').html(prod_mfd);
                $('div.viewproduct ul>li>span.prod_info').html(prod_info);
            }else {
                alert(jsonObj.msg);
            }
        },
        error: function(){
            alert('오류:'+jqXHR.status);
        
        }
    });
});