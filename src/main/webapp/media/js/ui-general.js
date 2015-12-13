var UIGeneral = function () {
	
    var handleDynamicPagination = function(total,now,url) {
        $('#dynamic_pager_demo2').bootpag({
            total: total,
            page: now,
            maxVisible: 6 
        }).on('page', function(event, num){
        	location.href = url+num;
        });
    }	

    return {
        //main function to initiate the module
        init: function (total, now,url) {
            handleDynamicPagination(total,now,url);
        }

    };

}();