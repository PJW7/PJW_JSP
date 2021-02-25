$(function(){
	// 여러 ajax에서 동일하게 사용되는 속성 설정
	$.ajaxSetup({
		ContentType:"application/x-www-form-urlencoded; charset=UTF-8",
		type: "post"
	});
	
	$("#id").parent().css("padding-right","5px");
	
	$("#pwdConfirm").keyup(function(){
		if($("#pwd").val() == $("#pwdConfirm").val()){
			$("#pwd_tab tr:last-child").css("display","table-row");
			$("#PWDwarning").css("display","block");
			$("#PWDwarning").css("color","green");
			$("#PWDwarning").html("비밀번호가 일치합니다.");
		}else if($("#pwdConfirm").val() == ""){
			$("#PWDwarning").html("");
		}else{
			$("#PWDwarning").css("display","block");
			$("#PWDwarning").css("color","red");
			$("#PWDwarning").html("비밀번호가 일치하지 않습니다.");
		}
	});
	
	$("#idCheck").click(function(){
		$.ajax({
			url: "/SSCinema/idCheck.do",
			dataType: "text",
			data: "id="+$("#id").val(),
			success: function(data){
				$("#IDwarning").html(data);
			},
			error: function(){
				$("#IDwarning").html("중복 확인 실패");
			}
		});
	});//$("#id").click() END
	
});
function qtyClick() {
	var price = document.getElementById('price').value*1;
	var qty = document.getElementById('qty').value*1;
	var total = price*qty;
	var total2 = price*qty;
	total = total.toLocaleString('ko-KR', {style: 'currency', currency:'KRW'})+"원";
	document.getElementById("total_price").innerHTML = total;
	console.log(total2.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
}
