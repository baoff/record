var assetTagNumStr;
var assetTagNum = new Array();
var assetName = new Array();

function load(){
	auto("0");
}

function auto(obj){
	$("#txtInput"+obj).autocomplete({
		 minLength: 0,
		 source:function(requeset,response){
			  $.ajax({  
                  url : "orderinfo.ak?method=select_asset_Ajax_action",  
                  type : "post",  
                  dataType : "json",  
                  data : {"str":$("#txtInput"+obj).val()},  
                 success: function( data ) {  
                       response( $.map( data, function( item ) {  
                             return {  
                            	 label: item.assetTagNum,  
                            	 assetNum: item.assetNum,
                            	 assetName: item.assetName,
                            	 assetType: item.assetType,
                            	 num : item.num,
                            	 manufacturer: item.manufacturer,
                            	 library: item.library,
                            	 carryNum: item.carryNum,
                            	 feederNum: item.feederNum,
                            	 mobileFactoryNo: item.mobileFactoryNo
                             }  
                       }));  
                 }  
            });
		 },
		 focus: function( event, ui ) {  
             $(this).val( ui.item.label );
             $("#assetNum"+obj).val(ui.item.assetNum);
             $("#assetName"+obj).val(ui.item.assetName);
             $("#assetType"+obj).val(ui.item.assetType);
             $("#num"+obj).val(ui.item.num);
             $("#manufacturer"+obj).val(ui.item.manufacturer);
             $("#library"+obj).val(ui.item.library);
             $("#carryNum"+obj).val(ui.item.carryNum);
             $("#feederNum"+obj).val(ui.item.feederNum);
             $("#mobileFactoryNo"+obj).val(ui.item.mobileFactoryNo);
               return false;  
             },  
         select: function( event, ui ) {
        	 $(this).value=ui.item.lable;
        	 $("#assetNum"+obj).val(ui.item.assetNum);
             $("#assetName"+obj).val(ui.item.assetName);
             $("#assetType"+obj).val(ui.item.assetType);
             $("#num"+obj).val(ui.item.num);
             $("#manufacturer"+obj).val(ui.item.manufacturer);
             $("#library"+obj).val(ui.item.library);
             $("#carryNum"+obj).val(ui.item.carryNum);
             $("#feederNum"+obj).val(ui.item.feederNum);
             $("#mobileFactoryNo"+obj).val(ui.item.mobileFactoryNo);
             return false;  
         }  
	});
}

//添加行
function addTable(){
	var tab=document.getElementById("viewTabs"); //获得表格
	var colsNum=tab.rows.item(0).cells.length; //表格的列数
	var rownum=document.getElementById("viewTabs").rows.length;//表格当前的行数 
	tab.insertRow(rownum);
	for(var i=0;i<colsNum; i++){
		tab.rows[rownum].insertCell(i);//插入列
		if(i==0){
			tab.rows[rownum].cells[i].innerHTML=
						'<input id="txtInput'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].assetTagNum" placeholder="输入资产标签号"/>';
		}else if(i==1){
			tab.rows[rownum].cells[i].innerHTML='<input id="assetNum'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].assetNum" readonly="true"/>';
		}else if(i==2){
			tab.rows[rownum].cells[i].innerHTML='<input id="assetName'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].assetName" readonly="true"/>';
		}else if(i==3){
			tab.rows[rownum].cells[i].innerHTML='<input id="assetType'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].assetType" readonly="true"/>';
		}else if(i==4){
			tab.rows[rownum].cells[i].innerHTML='<input id="num'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].num" readonly="true"/>';
		}else if(i==5){
			tab.rows[rownum].cells[i].innerHTML='<input id="manufacturer'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].manufacturer" readonly="true"/>';
		}else if(i==6){
			tab.rows[rownum].cells[i].innerHTML='<input id="library" type="text" name="details['+(rownum-1)+'].library" readonly="true"/>';
		}else if(i==7){
			tab.rows[rownum].cells[i].innerHTML='<input id="carryNum'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].carryNum" readonly="true"/>';
		}else if(i==8){
			tab.rows[rownum].cells[i].innerHTML='<input id="feederNum'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].feederNum" readonly="true"/>';
		}else if(i==9){
			tab.rows[rownum].cells[i].innerHTML='<input id="mobileFactoryNo'+(rownum-1)+'" type="text" name="details['+(rownum-1)+'].mobileFactoryNo" readonly="true"/>';
		}else if(i==10){
			tab.rows[rownum].cells[i].innerHTML='<div class="add-btn" id="J_addline" onclick="addTable();">添加</div>';
		}else{
			tab.rows[rownum].cells[i].innerHTML='<div class="remove-btn J_removeline" onclick="delRow(this)">删除</div>';
		}
	}
	auto(""+(rownum-1));
}
function deleteRow(r)
{
var i=r.parentNode.parentNode.rowIndex;
document.getElementById('myTable').deleteRow(i);
}

//删除行
function delRow(obj){
	var rownum =obj.parentNode.parentNode.rowIndex;
	if(rownum ==1){
		return ;
	}
	var Row=obj.parentNode;
	var Row=obj.parentNode; //tr
	while(Row.tagName.toLowerCase()!="tr"){
		Row=Row.parentNode;
	}
	Row.parentNode.removeChild(Row); //删除行
}
