function deleteBus(url, busID) {
    if (confirm("Bạn có chắc chắn muốn xóa xe khách này không?")) {
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa xe khách thành công!");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa xe khách.");
            }
        }).catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi kết nối đến server.");
        });
    }
}



function deleteCategory(url, categoryID) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status === 204)
            location.reload();
        else
            alert("ERROR");
    });
}

function drawChartRevenue(ctx, labels, data, title="Doanh thu") {
    let colors = [];
    for (let i = 0; i < data.length; i++)
        colors.push(`rgba(${parseInt(Math.random()*255)}, 
        ${parseInt(Math.random()*255)}, 
        ${parseInt(Math.random()*255)}, 0.7)`);
    
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: title,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: colors
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}



