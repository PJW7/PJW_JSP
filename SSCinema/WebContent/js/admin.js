function tChange(type) {
	$.ajaxSetup({
		ContentType:"application/x-www-form-urlencoded; charset=UTF-8",
		type: "post"
	});
	
	$.ajax({
		url: "/SSCinema/admin_get_room_list.do" ,
		dataType: "text" ,
		data: "tnum="+type ,
		success:function(data){
			//console.log(data);
			$('#rList').empty();
			if(data){
				$('#rList').append("<option value=''>::목록::</option>");
				$('#rList').append(data);
			}else{ 
				$('#rList').append("<option value=''>::목록 없음::</option>");
			}
		},
		error: function(){
			alert('상영관 목록을 불러올 수 없습니다.');
		}
	});
}