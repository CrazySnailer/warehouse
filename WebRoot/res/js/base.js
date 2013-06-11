function defaultErrorPlacement(error, element) {
	var messagePosition = element.metadata().messagePosition;
	if("undefined" != typeof messagePosition && messagePosition != "") {
		var $messagePosition = $(messagePosition);
		if ($messagePosition.size() > 0) {
			error.insertAfter($messagePosition).hide().show();
		} else {
			error.insertAfter(element).hide().show();
		}
	} else {
		error.insertAfter(element).hide().show();
	}
}
function defaultSubmitHandler(form) {
	$(form).find(":submit").attr("disabled", true);
	form.submit();
}

function defaultBeforeSubmit(){
	$(".formButtonSubmit").each(function(){
		$(this).attr("disabled", true);
	});
}

function enableAllSubmit(){
	$(".formButtonSubmit").each(function(){
		$(this).attr("disabled", false);
	});
}

function defaultErrorHandler(xhr, ajaxOptions, thrownError){
	alert('xhr请求失败,thrownError:'+thrownError);
}

sys_constant = {
	base: "/wh"
}

$.launchPage = function(pageURL){
    document.location.href=pageURL;
}
function getRandom(){return Math.floor(Math.random()*100+1)}
function clearSelectContent(arr){
	$.each(arr,function(index, value){
		$('#'+value).val('');
	});
}
$(document).ready( function() {
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $idsCheck = $("#listTable input[name='ids']");// ID复选框
		var $batchButton = $("#batchButton");// 批量按钮
		var $pageNumber = $("#pageNo");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $("#listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序方式
		var $order = $("#order");// 排序字段
		
		// 全选
		$allCheck.click( function() {
			if ($(this).attr("checked") == true) {
				$idsCheck.attr("checked", true);
			} else {
				$idsCheck.attr("checked", false);
			}
		});
		
		// 查找
		$searchButton.click( function() {
			$('#querytip').show();
			$pageNumber.val("1");
			$listForm.submit();
		});
		
		// 批量删除
		$batchButton.click( function() {
			var data = $(this).metadata();
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			if($idsCheckedCheck.size()==0){
				alert(data.tipA);
				return false;
			}else{
				if(confirm(data.tipB)){
					$listForm.attr('action',data.url);
					$('#querytip').text('数据提交中，请稍候...');
					$('#querytip').show();
					$listForm.submit();
				}
			}
		});	
		
		// 页码跳转
		$.gotoPage = function(pageNo,pageSize) {
			$pageNumber.val(pageNo);
			$('#pageSize').val(pageSize);
			$('#querytip').show();
			$listForm.submit();
		}
	}
	
	//tab效果
	var $tabs = $(".tabs_div");
	if ($tabs.size() > 0) {
		$.each($tabs,function(i, item){
					$($tabs).find(".tab_content").hide();
					$($tabs).find("ul.tabs li:first").addClass("active").show();
				
					var firstTab = $($tabs).find(".tab_content:first");
					if($.trim(firstTab.html())==''){
						var data = firstTab.metadata();
						firstTab.html('<iframe src="'+data.url+'" style="width:100%;height:'+data.height+'px;" frameborder="0" marginwidth="0" marginheight="0"></iframe>');
					}
					firstTab.show();	
					
					$($tabs).find("ul.tabs li").click(function() {
							$($tabs).find("ul.tabs li").removeClass("active");
							$(this).addClass("active");
							$($tabs).find(".tab_content").hide();
							var activeTab = $(this).find("a").attr("href");
							var selectTab = $($tabs).find(activeTab)
							if($.trim(selectTab.html())==''){
								var data = selectTab.metadata();
								selectTab.html('<iframe src="'+data.url+'" style="width:100%;height:'+data.height+'px;" frameborder="0" marginwidth="0" marginheight="0"></iframe>');
							}
							selectTab.show();							
							return false;
					});
		});
	}
	
	/** ajax tab 选项页效果*/
	var $ajaxtab = $(".ajaxtabdiv");
	if ($ajaxtab.size() > 0) {
		$.each($ajaxtab,function(i, tab){
					$(tab).find(".tab_content").hide();
					$(tab).find("ul.ajaxtabs li:first").find("a").addClass("current").show();
				
					var firstTab = $(tab).find(".tab_content:first");
					firstTab.show();	
					
					$(tab).find("ul.ajaxtabs li > a").click(function() {
					
							var activeTab = $(this).attr("href");
							var firstword = activeTab.substr(0,1);
							if(firstword=='#'){
								$(tab).find("ul.ajaxtabs li > a").removeClass("current");
								$(this).addClass("current");
								$(tab).find(".tab_content").hide();
								var selectTab = $(tab).find(activeTab);				
								selectTab.show();							
								return false;
							}else{
								return true;
							}
					});
		});
	}
	
	// 提示效果
	/*$("input[title], label[title]").qtip({
		content: {
			text: true
		},
		style: {
			name: "cream",
			width: {
				max: 500
			}
		}
	});*/
	
	/* ---------- Validate ---------- */		
	var $validateForm = $("#validateForm");
	if ($validateForm.size() > 0) {
		$validateForm.validate({
			errorClass: "validateError",
			ignore: ".ignoreValidate",
			errorElement: "div",
			errorPlacement: function(error, element) {
				var messagePosition = element.metadata().messagePosition;
				if("undefined" != typeof messagePosition && messagePosition != "") {
					var $messagePosition = $('#'+messagePosition);
					if ($messagePosition.size() > 0) {
						error.insertAfter($messagePosition).hide().show();
					} else {
						error.insertAfter(element).hide().show();
					}
				} else {
					error.insertAfter(element).hide().show();
				}
			},
			submitHandler: function(form) {
				$('#savetip').show();
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});
	}
	
	/* ---------------listTable-----------------------------*/
	var $listTable = $(".listTable");
	if ($listTable.size() > 0) {
		$.each($listTable,function(i, item){
			$(item).colorTable();
		});
	}
});
