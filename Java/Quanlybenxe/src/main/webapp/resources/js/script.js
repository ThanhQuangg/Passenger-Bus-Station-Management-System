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
    if (confirm("Bạn có chắc chắn muốn xóa loại xe này không?")) {
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa loại xe thành công!");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa loại xe.");
            }
        }).catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi kết nối đến server.");
        });
    }
}

function deleteCompany(url, companyID) {
    if (confirm("Bạn có chắc chắn muốn xóa nhà xe này không?")) {
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa nhà xe thành công!");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa nhà xe.");
            }
        }).catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi kết nối đến server.");
        });
    }
}

function deleteRoute(url, routeID) {
    if (confirm("Bạn có chắc chắn muốn xóa tuyến đường này không?")) {
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa tuyến đường thành công!");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa tuyến đường.");
            }
        }).catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi kết nối đến server.");
        });
    }
}

function deleteTrip(url, tripID) {
    if (confirm("Bạn có chắc chắn muốn xóa chuyến xe này không?")) {
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa chuyến xe thành công!");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa chuyến xe.");
            }
        }).catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi kết nối đến server.");
        });
    }
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



