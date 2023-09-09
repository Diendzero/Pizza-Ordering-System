$('#findBtn').click(function () {
	$.ajax({
		type: 'GET',
		url: '/order/select',
		data: {
			'search': $('#search').val()
		},
		success: function (data) {
			$('#ordersTable').html(data)
		},
		error: function (err) {
			console.log(err)
			alert('Error!No such order！')
		}
	})
})

$('#updateSubmitBtn').click(function () {
	var id = $('#updateOrderNumber').val()
	var paymentStatus = $('#updatePaymentStatus').val()
	var deliveryStatus = $('#updateDeliveryStatus').val()

	if (paymentStatus.length == 0){
		alert("Payment Status can not be empty")
	}else if (deliveryStatus.length == 0){
         alert("Delivery Status can not be empty")
	}
	}else {
		$.ajax({
			type: 'POST',
			url: '/order/update',
			data: {
				'id': id,
				'paymentStatus': paymentStatus,
				'deliveryStatus': deliveryStatus,
			},
			success: function (data) {
				$('#modal-form-update').modal('hide')
				document.getElementById("updateForm").reset()
				$('#ordersTable').html(data)
			},
			error: function (err) {
				console.log(err)
				alert('Error! Please try again！')
			}
		})
	}
})



