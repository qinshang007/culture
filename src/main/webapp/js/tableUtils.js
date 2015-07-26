		//生成uuid
	   function uuid() {
		    var s = [];
		    var hexDigits = "0123456789abcdef";
		    for (var i = 0; i < 36; i++) {
		        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		    }
		    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
		    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
		    s[8] = s[13] = s[18] = s[23] = "-";
		 
		    var uuid = s.join("");
		    return uuid;
		}


		//删除<tr/>
	    var deltr =function(id)
	    {
	        $("tr[id='"+id+"']").remove();//删除当前行
	    }
	    
	    //增加<tr/>
	    var addtr = function(tabId,name)
	    {
	    	var id = uuid();
	    	$("#"+tabId).append("<tr id="+id+">"
	    			+"<td><input type='text' style='width:100px' name='"+name+"' value=''/></td>"
	    			+"<td><a href='#' onclick=\"deltr('"+id+"');return false\">Delete</a></td>"
	    			+"</tr>");
	    }
	    
		var getTableData = function(tableId){
			var tableData=new Array();
            $("#"+tableId+" tr").each(function(trindex,tritem){
                tableData[trindex]=new Array();
                $(tritem).find("td").each(function(tdindex,tditem){
                    tableData[trindex][tdindex]=$(tditem).find('input').val();
                });
            });
            return tableData;
		}
		

