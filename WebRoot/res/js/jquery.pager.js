/*
* jQuery pager plugin
* Version 1.0 (12/22/2008)
* @requires jQuery v1.2.6 or later
*
* Example at: http://jonpauldavies.github.com/JQuery/Pager/PagerDemo.html
*
* Copyright (c) 2008-2009 Jon Paul Davies
* Dual licensed under the MIT and GPL licenses:
* http://www.opensource.org/licenses/mit-license.php
* http://www.gnu.org/licenses/gpl.html
* 
* Read the related blog post and contact the author at http://www.j-dee.com/2008/12/22/jquery-pager-plugin/
*
* This version is far from perfect and doesn't manage it's own state, therefore contributions are more than welcome!
*
* Usage: .pager({ pagenumber: 1, pagecount: 15, buttonClickCallback: PagerClickTest });
*
* Where pagenumber is the visible page number
*       pagecount is the total number of pages to display
*       buttonClickCallback is the method to fire when a pager button is clicked.
*
* buttonClickCallback signiture is PagerClickTest = function(pageclickednumber) 
* Where pageclickednumber is the number of the page clicked in the control.
*
* The included Pager.CSS file is a dependancy but can obviously tweaked to your wishes
* Tested in IE6 IE7 Firefox & Safari. Any browser strangeness, please report.
*/
(function($) {

    $.fn.pager = function(options) {

        var opts = $.extend({}, $.fn.pager.defaults, options);

        return this.each(function() {
        	if(options.pageGo == undefined)
        	{
        		options.pageGo = $.fn.pager.defaults.pageGo;
        	}
            $(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), parseInt(options.pagesize),parseInt(options.pagerecordSize),options.pageNoName,options.pageSizeName, options.buttonClickCallback,options.pageNo,options.pageGo));
            
        });
    };

    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount, pagesize,pagerecordSize,pageNoName,pageSizeName,buttonClickCallback,pageNoId,pageGo) {

        // setup $pager to hold render
        var $pager = $('<ul class="pages"></ul>');

        // add in the previous and next buttons
        //$pager.append(renderButton('共'+pagerecordSize+'条记录,第'+pagenumber+'/'+pagecount+'页', pagenumber, pagecount, buttonClickCallback));
        $pager.append($('<li class="pgInfo">共'+pagerecordSize+'条记录,第'+pagenumber+'/'+pagecount+'页</li>').unbind('click'));
        
        $pager.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback,pageNoId)).append(renderButton('上一页', pagenumber, pagecount, buttonClickCallback,pageNoId));

        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = 5;

        if (pagenumber > 2) {
            startPoint = pagenumber - 2;
            endPoint = pagenumber + 2;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - 2;
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li class="">' + (page) + '</li>');
            /*if(page == pagenumber){
            	currentButton.addClass('pgCurrent');
            	currentButton.click(function() { buttonClickCallback(this.firstChild.data); });
            }else
            	currentButton.addClass('pageNumber');*/
            page == pagenumber ? currentButton.addClass('pgCurrent') : currentButton.click(function() { buttonClickCallback(this.firstChild.data,pageNoId); });
            page != pagenumber ? currentButton.addClass('pageNumber') : currentButton.click();
            currentButton.appendTo($pager);
        }
       //$('<li class="page-number">第<input type="text" size="2" id="pageNo" name="'+pageNoName+'" value="' + pagenumber + '"/>页 每页显示<input type="text" size="2" id="pageSize" name="'+pageSizeName+'" value="' + pagesize + '"/>页</li>').appendTo($pager);

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('下一页', pagenumber, pagecount, buttonClickCallback,pageNoId)).append(renderButton('末页', pagenumber, pagecount, buttonClickCallback,pageNoId));
        
        var $tmp = renderButton('GO', pagenumber, pagecount, buttonClickCallback,pageNoId);
        $tmp.click(function(){
        	var cnt = pagecount;
        	var callBack = buttonClickCallback;
        	var pageId = pageNoId;
        	var goInput = $('#'+pageGo).val();
        	if(goInput == ''||isNaN(goInput))
        	{
        		jAlert('页码输入错误!');
        		return;
        	}
        	
        	if(parseInt(goInput)> cnt)
        	{
        		jAlert('页码超出最大页数!');
        		return;
        	}
        	callBack(goInput,pageId);
        	
        });
        $pager.append(renderInput(buttonClickCallback,pageNoId,pageGo)).append($tmp);

        return $pager;
    }
    
    function renderInput(buttonClickCallback,pageNoId,pageGo)
    {
    	 var s =$('<div class=""> <input id='+pageGo+' size="2" style="height:14px;"/></div>');
    	 return s;
    }
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback,pageNoId) {

        var $Button = $('<li class="">' + buttonLabel + '</li>');

        var destPage = 1;
        
        // work out destination page for required button type
        switch (buttonLabel) {
            case "首页":
                destPage = 1;
                break;
            case "上一页":
                destPage = pagenumber - 1;
                break;
            case "下一页":
                destPage = pagenumber + 1;
                break;
            case "末页":
                destPage = pagecount;
                break;
            case "GO":
            	return $Button;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "首页" || buttonLabel == "上一页") {
            pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage,pageNoId); });
            pagenumber > 1 ? $Button.addClass('pgNext') : $Button.click();
        }else {
        	
            pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage,pageNoId); });
            pagenumber < pagecount ? $Button.addClass('pgNext') : $Button.click();
        }

        return $Button;
    }

    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.pager.defaults = {
        pagenumber: 1,
        pagecount: 1,
        pageNoName:'qry.pageNo',
        pageNo:'#pageNo',
		pageSizeName:'qry.pageSize',
		pageGo:'pageGo'
    };

})(jQuery);





