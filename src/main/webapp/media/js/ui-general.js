var UIGeneral = function () {
	
    var handleDynamicPagination = function(total,now,url) {
        $('#dynamic_pager_demo2').bootpag({
            total: total/5,
            page: now,
            maxVisible: 10,
            firstLastUse: true,
            first: '首页',
            last: '尾页',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
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