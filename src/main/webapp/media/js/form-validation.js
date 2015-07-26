var FormValidation = function () {


    return {
        //main function to initiate the module
        init: function (myform,rule,suc_callback,failed_callback) {

            // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var error1 = $('.alert-error', myform);
            var success1 = $('.alert-success', myform);
           

            myform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: rule,

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
                    failed_callback();
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change dony by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                    .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                },

                submitHandler: function (form) {
                    error1.hide();
                    suc_callback();
                }
            });
            
        }

    };

}();