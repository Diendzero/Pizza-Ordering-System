$('#addPizzaBtn').click(function() {
	$('#modal-form-add').modal('show');
})

$('#findBtn').click(function () {
	$.ajax({
		type: 'GET',
		url: '/pizza/select',
		data: {
			'search': $('#search').val()
		},
		success: function (data) {
			$('#pizzaTable').html(data)
		},
		error: function (err) {
			console.log(err)
			alert('Error!No such pizza！')
		}
	})
})

$('#addSubmitBtn').click(function () {
	var name = $("#addPizzaName").val()
	var price = $("#addPizzaPrice").val()
	var description = $("#addPizzaDescription").val()
	var category = $("#addPizzaCategory").val()
	var status = $("#addPizzaStatus").val()
	var base = $("#addPizzaBase").val()
	var size = $("#addPizzaSize").val()

	if(name.length == 0){
		alert("The name cannot be empty")
	}else if (price.length == 0){
		alert("The price cannot be empty")
	}else if (description.length == 0){
		alert("The description cannot be empty")
	}else if (category.length == 0){
		alert("The category cannot be empty")
	}else if (status.length == 0){
		alert("The status cannot be empty")
	}else if (base.length == 0){
		alert("The base cannot be empty")
	}else if (size.length == 0){
		alert("The size cannot be empty")
	}else {
		$.ajax({
			type: 'POST',
			url: '/pizza/insert',
			data: {
				// 'id': id, 
				'name': name, 
				'price': price,
				'description':description,
				'category':category,
				'status':status,
				'base':base,
				'size':size
			},
			success: function (data) {
				$('#modal-form-add').modal('hide')
				document.getElementById("addForm").reset()
				$('#pizzaTable').html(data)
			},
			error: function (err) {
				console.log(err)
				alert("Error! Please try again！")
			}
		})
	}
})

$('#updateSubmitBtn').click(function () {
	var id = $('#updatePizzaId').val()
	var name = $('#updatePizzaName').val()
	var price = $('#updatePizzaPrice').val()
	var description = $('#updatePizzaDescription').val()
	var category = $('#updatePizzaCategory').val()
	var status = $('#updatePizzaStatus').val()
	var base = $('#updatePizzaBase').val()
	var size = $('#updatePizzaSize').val()
	

	if (id.length == 0){
		alert("The id cannot be empty")
	}else 
	if(name.length == 0){
		alert("The name cannot be empty")
	}else if (price.length == 0){
		alert("The price cannot be empty")
	}else if (description.length == 0){
		alert("The description cannot be empty")
	}else if (category.length == 0){
		alert("The category cannot be empty")
	}else if (status.length == 0){
		alert("The status cannot be empty")
	}else if (base.length == 0){
		alert("The base cannot be empty")
	}else if (size.length == 0){
		alert("The size cannot be empty")
	}else {
		$.ajax({
			type: 'POST',
			url: '/pizza/update',
			data: {
					'id': id, 
					'name': name, 
					'price': price,
					'description':description,
					'category':category,
					'status':status,
					'base':base,
					'size':size
			},
			success: function (data) {
				$('#modal-form-update').modal('hide')
				document.getElementById("updateForm").reset()
				$('#pizzaTable').html(data)
			},
			error: function (err) {
				console.log(err)
				alert('Error! Please try again！')
			}
		})
	}
})