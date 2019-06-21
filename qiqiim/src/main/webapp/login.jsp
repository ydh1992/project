<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title></title>
  <link rel="stylesheet" href="layui/css/layui.css"> 
</head>
<body>
  <div style="margin: 30px;">
    <fieldset class="layui-elem-field index-button" style="margin-top: 30px;">
	  <legend>登录</legend>
	  <div>
	    <form class="layui-form loginform" action="login"  method="post">
	    		<div class="layui-form-item">
				    <label class="layui-form-label">uuID</label>
				    <div class="layui-input-block">
				      <input type="text" name="uuid" lay-verify="title" autocomplete="off" class="layui-input laccount">
				    </div>
				 </div>
				 <div class="layui-form-item">
				    <label class="layui-form-label">unionid</label>
				    <div class="layui-input-block">
				      <input type="text" name="unionid" lay-verify="required"  autocomplete="off" class="layui-input lpwd">
				    </div>
				 </div>
				 <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit="" lay-filter="login">立即登录</button>
				    </div>
				  </div>
			<input type="hidden" name="ownerOrCompany" value="0">
	    </form>
	  </div>
	</fieldset>
  </div>
<script src="layui/layui.js"></script>  
<script>
layui.use(['form'], function(){
  var form = layui.form
  ,$ = layui.jquery
  ,layer = layui.layer;
  
 
  //自定义验证规则
  form.verify({
	  account: function(value){
      if(value.length < 1){
        return '请输入账号';
      }
    }
    ,password: [/(.+){6,12}$/, '密码必须6到12位']
    ,'userInfo.name': function(value){
    	 if(value.length < 1){
    	        return '请输入姓名';
    	  }
    }
  });
  
  form.on('button(loginbtn)', function(data){
	    layer.alert(JSON.stringify(data.field), {
	      title: '最终的提交信息'
	    })
	    return false;
  });
	  
  $(".showform").on("click",function(){
		 var btntext = $(this).text();
		 var forms = $(".layui-elem-field");
		 if(btntext=='去登录'){
			 forms.eq(0).show();
			 forms.eq(1).hide();
		 }else{
			 forms.eq(1).show();
			 forms.eq(0).hide();
		 }  
  }) 

  
});
</script>
</body>
</html>