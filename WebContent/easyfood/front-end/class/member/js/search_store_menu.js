function $id(id){
                 return document.getElementById(id);}
           
            function addCount1(){addCount(1);}
            function addCount2(){addCount(2);}
            function addCount3(){addCount(3);}
            function addCount4(){addCount(4);}
            function addCount5(){addCount(5);}
            function addCount6(){addCount(6);}
            function lessCount1(){lessCount(1);}
            function lessCount2(){lessCount(2);}
            function lessCount3(){lessCount(3);}
            function lessCount4(){lessCount(4);}
            function lessCount5(){lessCount(5);}
            function lessCount6(){lessCount(6);}

            function addCount(i){
                var count=parseInt( $id("count"+i).value);
                if(count==10){
                  $id("count"+i).value=10;
                }else{
                  $id("count"+i).value=count+1; 
                }               
              }

            function lessCount(i){
               var count=parseInt( $id("count"+i).value);
               if(count==1){
                  $id("count"+i).value=1;
                }else{
                  $id("count"+i).value=count-1; 
                }               
              }