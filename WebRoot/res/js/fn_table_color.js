$.fn.colorTable=function(params){
   //默认参数
   var options={
       hoverColor:'#D1EEEE',
       ovenColor:'#FbFbFb'
    };
    //用传递过来的参数 覆盖 默认参数
    $.extend(options, params);
    
    /*this.each(function(){
        $(this).find('tr').each(function(){
        	$(this).find('td').each(function(){
        		if($(this).text()=='0.00'||$(this).text()=='0.0'||$(this).text()=='0'){
        			$(this).html("<span style='color:#ccc'>"+$(this).text()+"</span>");
        		}
        	});
        });
    });*/
    
    this.each(function(){
        $(this).find('tr').each(function(i,item){
        	if((i%2)==0){
        		 $(this).find('td').css('backgroundColor', options.ovenColor );
        	}
        	
            //获取初始的背景颜色值
            this.origColor = $(this).find('td').css('backgroundColor');
            
            $(this).hover(function(){
                $(this).find('td').css('backgroundColor', options.hoverColor );
            },function(){
                $(this).find('td').css('backgroundColor', this.origColor );
            });
            
            /*$(this).click(function(){
            	$(this).find('td').css('backgroundColor', 'red' );
            });*/
        });
    });
    
};