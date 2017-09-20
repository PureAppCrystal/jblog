<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript">
</script>
<script>
	var FormValidator = {

		$inputTextID : null,
		$buttonCheckID : null,
		$imgCheck : null,
		init : function() {
			this.$inputTextID = $("#blog-id");
			this.$buttonCheckID = $("#btn-checkid");
			this.$imgCheck = $("#img-checkid");
			
			$("#join-form").submit(this.onJoinFormSubmit.bind(this));
			this.$buttonCheckID.click(this.onCheckButtonIdClicked.bind(this)); // [중복체크]
			this.$inputTextID.change(this.onInputTextIdChanged.bind(this));	   // 텍스트 필드
		},
		onJoinFormSubmit : function() {
			
			// 이름
			var $inputTextName = $("#name");
			if ($inputTextName.val() === '') {
				alert("이름은 필수 항목입니다.");
				$inputTextName.focus();
				return false;
			}
			
			// id
			if (this.$inputTextID.val() === '') {
				alert("아이디는 필수 항목입니다.");
				this.$inputTextID.focus();
				return false;
			}
			
			// password
			var $inputTextPassword = $("#password");
			if ($inputTextPassword.val() === '') {
				alert("비밀번호는 필수 항목입니다.");
				$inputTextPassword.focus();
				return false;
			}
			
			// agree-prov
			var $inputCheckAgree = $("#agree-prov");
			if ($inputCheckAgree.is(":checked") == false) {
				alert("약관 동의 해주세요. ");
				return false;
			}
			
			return true;
		},
		onCheckButtonIdClicked : function (){
			var id = this.$inputTextID.val();
			if( id == "" ) {
				alert("아이디를 입력해주세요.");
				this.$inputTextID.focus();
				return;
			}
		},
		
		onCheckIdAjaxError : function(xhr, status, e) {
			console.error(status + ":" + e);
		},
		
		onCheckIdAjaxSuccess : function(response) {
			if(response.result != "success") {
				console.log(response.message);
				return;
			}
			
			if(response.data == true ) {
				alert("이미 사용하고 있는 ID 입니다.");
				this.$inputTextID.val("").focus();
				return;
			}
			
			this.$imgCheck.show();
			this.$buttonCheckID.hide();
		},
		
		
		onInputTextIdChanged : function() {
			this.$imgCheck.hide();
			this.$buttonCheckID.show();
		},
		
		onCheckButtonIdClicked : function() {
			var id = this.$inputTextID.val();
			if( id == "" ) {
				return;
			}
			
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/api/user/checkid?id=" + id,
				type : "get",
				dataType : "json",
				data :"",
				success : this.onCheckIdAjaxSuccess.bind(this),
				error : this.onCheckIdAjaxError
				
			});
		}
	}
	
	$(function() {
		FormValidator.init();
	});
</script>


<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/">메인으로 가기</a></li>
		</ul>
		<form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password">

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
