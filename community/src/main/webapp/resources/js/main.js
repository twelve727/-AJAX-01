console.log("main.js loaded.");



// 회원 정보 조회 비동기 통신 ( AJAX )
document.getElementById("select1").addEventListener("click", function() {

    const input = document.getElementById("in1");
    const div = document.getElementById("result");
    
// AJAX 코드 작성 시작 (jQuery 방식) -> jQuery 라이브러리가 추가되어 있는지 확인

$.ajax({
    url : "member/selectOne",
    data : {"memberEmail" : input.value},
    type : "POST",
    dataType : "JSON", 
    // dataType : 응답 데이터 형식 지정
    // -> JSON으로 지정 시 자동으로 JS 객체로 변환


    // 성공 시 받을것, 멤버
    success : function(member) {
		console.log(member);
    },

    // 실패 시 받을것, 요청 상태 에러
    error : function(request, status, error) {

    }
})


});


