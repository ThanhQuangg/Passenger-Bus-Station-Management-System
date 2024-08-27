import { useEffect, useState } from "react";
import { Col, Container, Image, Row, Button } from "react-bootstrap";
import { useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import MySpinner from "../../Commons/MySpinner";
import cookie from "react-cookies";


const TripDetail = () => {
    const [trip, setTrip] = useState(null);
    const { tripID } = useParams();

    const loadTrip = async () => {
        try {
            let res = await APIs.get(endpoints['trip_detail'](tripID));
            setTrip(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadTrip();
    }, [tripID]);

    const formatCurrency = (amount) => {
        return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    }

    const formatDate = (dateString) => {
        const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
        const date = new Date(dateString);
        return date.toLocaleDateString('vi-VN', options);
    }

    const order = (id, name, price) => {

        let cart = cookie.load("cart") || null;
        if (cart === null)
            cart = {};

        if (id in cart) {
            // đã có trong giỏ
            cart[id]["quantity"]++;
        } else {
            // chưa có
            cart[id] = {
                "id": id,
                "name": name,
                "price": price,
                "quantity": 1
            };
        }

        cookie.save('cart', cart);
        console.info(cart);
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">CHI TIẾT CHUYẾN XE</h1>
            {trip === null ? <MySpinner /> : <>
                <Row>
                    <Col md={5} xs={6}>
                        <Image src={trip.busID.avatar} rounded fluid />
                    </Col>
                    <Col md={7} xs={6}>
                        <h1>{trip.name}</h1>
                        <h4 className="text-danger"> Giá vé xe: {formatCurrency(trip.ticketPrice)}</h4>
                        <h3>Thời gian dự kiến khởi hành: {formatDate(trip.departureTime)}</h3>
                        <h3>Thời gian dự kiến đến nơi: {formatDate(trip.arrivalTime)}</h3>
                        <h3>Khoảng cách: {trip.routeID.distance} km  </h3>
                        {/* <h3>Thời gian di chuyển dự kiến: {trip.routeID.estimatedDuration}</h3> */}
                        <h3>Loại xe: {trip.busID.categoryID.name}</h3>
                        <h3> Biển số xe: {trip.busID.plateNumber}</h3>
                        <h3>Mô tả chi tiết: {trip.routeID.description}</h3>
                        <Button variant="primary" className="m-1" onClick={() => order(trip.tripID, trip.name, trip.ticketPrice)}>Đặt vé xe ngay</Button>
                    </Col>
                </Row>
            </>}
        </Container>
    )
}

export default TripDetail;