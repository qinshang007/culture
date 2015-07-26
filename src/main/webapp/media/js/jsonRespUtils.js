/**
* Json数据解析类:用于解析从服务端返回的JSON数据信息,包括三个字段：
  result:响应结果，boolean型
  message：返回的消息
  userData：返回的用户数据
*/
function JsonRespUtils(jsonDoc){
	if (jsonDoc==null || jsonDoc=="") {
	    alert("非法的相应数据!");
	    return;
	}
	this.jsonObj = eval('('+jsonDoc+')');
}

JsonRespUtils.prototype={
    //响应结果是否成功
    isSuccessfully:function(){
	    return this.jsonObj.result;
    },
    //得到返回的信息
    getMessage:function(){
    	return this.jsonObj.message;
    },
    //得到返回的用户数据
    getCustomData:function(){
    	return this.jsonObj.customData;
    }
}