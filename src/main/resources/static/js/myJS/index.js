
$('#addUserBtn').click(function() {
	$('#modal-form-add').modal('show');
})

$('#findBtn').click(function () {
	$.ajax({
		type: 'GET',
		url: '/user/select',
		data: {
			'search': $('#search').val()
		},
		success: function (data) {
			$('#userTable').html(data)
		},
		error: function (err) {
			console.log(err)
			alert('操作失败，请刷新重新尝试！')
		}
	})
})

$('#addSubmitBtn').click(function () {
	var username = $('#addUserName').val()
	var nickname = $('#addNickname').val()
	var password = $("#addPassword").val()

	if (username.length == 0){
		alert("用户名不能为空")
	}else if(nickname.length == 0){
		alert("昵称不能为空")
	}else if (password.length == 0){
		alert("密码不能为空")
	}else {
		$.ajax({
			type: 'POST',
			url: '/user/insert',
			data: {
				'username': username,
				'nickname': nickname,
				'password': password
			},
			success: function (data) {
				$('#modal-form-add').modal('hide')
				document.getElementById("addForm").reset()
				$('#userTable').html(data)
			},
			error: function (err) {
				console.log(err)
				alert("操作失败，请刷新重新尝试！")
			}
		})
	}
})

$('#updateSubmitBtn').click(function () {
	var id = $('#updateUserId').val()
	var username = $('#updateUsername').val()
	var nickname = $('#updateNickname').val()
	var password = $("#updatePassword").val()

	if (username.length ==0) {
		alert('用户名不能为空')
	}else if (nickname.length == 0) {
		alert('昵称不能为空')
	}else if (password.length == 0) {
		alert('密码不能为空')
	}else {
		$.ajax({
			type: 'POST',
			url: '/user/update',
			data: {
				'id': id,
				'username': username,
				'nickname': nickname,
				'password': password
			},
			success: function (data) {
				$('#modal-form-update').modal('hide')
				document.getElementById("updateForm").reset()
				$('#userTable').html(data)
			},
			error: function (err) {
				console.log(err)
				alert('操作失败，请刷新重新尝试！')
			}
		})
	}
})