
var allowtype =  ["JPG","GIF","PNG","BMP","JPEG"];

function valiFile(id){
	var picone = $("#"+id).val();   
	if(picone=='')
	{
		alert("��ͼ����Ϊ�գ�")
		return false;
	}
	var fileext1 = getFiletype(picone);   
	if ($.inArray(fileext1,allowtype) == -1)
	{
	    alert("ͼƬ����ֻ����jpg,png,jpeg,gif,bmp");
	    return false;
	}
	return true;
}

function getFiletype(filename)
{
    var extStart  = filename.lastIndexOf(".")+1;
     return filename.substring(extStart,filename.length).toUpperCase();
}
