//以下購物車漂浮用 

    $(document).ready(function(){
      $('.shoppingCart').affix({offset: {top: 35} }); 
    });
  //以下購物車內數量加減用  
    $('.btn-minuse').on('click', function(){
      $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) - 1)
      })

      $('.btn-pluss').on('click', function(){            
        $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) + 1)
      })

    

    //以下table連結到order_details.html用    
    $('.order>tbody>tr>td').click(function() {
        window.location = 'order_details.html';
    });


     //結帳連結到shoppingCart.html用
//       $('.checkout').click(function() {
//        window.location = 'shoppingCart.html';
//    });