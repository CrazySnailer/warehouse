/**
 * 操作JS的函数
 **/
var insertHtml = 
['<tr class="newtr">                                                                                                                   ',
'	<td class="center">!{productSku}</td>                                                                                              ',
'	<td class="center"><input type="hidden" name="productId" value="!{productCode}"/>!{productCode}</td>                               ',
'	<td class="center">!{procudctName}</td>                                                                                            ',
'	<td class="center">!{brandName}/!{modelNo}</td>                                                                                    ',
'	<td class="center">                                                                                                                ',
'	  <input type="text" name="qty_!{productId}" class="formTextSS" style="float:none;" onblur="edit(this,this.value)" value="!{qty}"/>',
'	</td>                                                                                                                              ',
'	<td class="center"><a href="#none" onclick="delRow(this)" class="ico ico-delete" title="删除"/></td>                               ',
'</tr>                                                                                                                               '].join("");
function addARow(append_table,data){
	var newRow = $.tpl(prod_tpl, data);
	append_table.append(newRow);
}
function delRow(drow){
	$(drow).parents(".newt").remove();
}