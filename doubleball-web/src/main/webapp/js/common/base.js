/**
 * 创建日期: 14-7-11  下午4:40
 * @author: sjin QQ: 928990049
 */


/**
 *
 *  验证框架 错误提示 方式
 *
 **/
//设置默认的 验证样式，提示错误信息方式等
$.validator.setDefaults({
    submitHandler: function(from) {  },
    /*showErrors: function(map, list) {
     $(".error").remove();
     this.currentElements.removeClass("ui-state-highlight");
     $.each(list, function(index, error) {
     //$(error.element).addClass("ui-state-highlight");
     $(error.element).parent("div").append("<em class='error'>"+error.message+"</em>");
     //showErrorTxt(error.message , $(error.element));
     });
     },*/
    errorElement: "em",
    errorPlacement: function(error, element) {
        if(element.parent("div").children("em")){
            element.parent("div").children("em").remove();
        }

        error.appendTo( element.parent("div") );
    },
    success: function(label) {
        label.removeClass("error").addClass("success");
    }
});