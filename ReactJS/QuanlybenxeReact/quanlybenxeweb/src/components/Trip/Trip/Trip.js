import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Container, Row, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../../configs/APIs";
import styles from "./Trip.scss";
import './Trip.scss';
const Trip = () => {
    const [trip, setTrip] = useState([]);

    const loadTrip = async () => {
        try {
            let url = `${endpoints['trips']}?`;

            const res = await APIs.get(url);
            setTrip(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const nav = useNavigate();

    const formatDate = (dateString) => {
        const options = {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: false
        };
        const date = new Date(dateString);
        const formattedDate = date.toLocaleDateString('vi-VN', options);

        const parts = formattedDate.split(' ');
        const time = parts[parts.length - 2];
        const day = parts[0];
        const month = parts[2];
        const year = parts[parts.length - 1];

        return `lúc ${time} ngày ${day} ${month} năm ${year}`;
    };

    const searchTrip = (e) => {
        e.preventDefault();
        nav(`/trips?kw=${kw}`);
    };

    const [kw, setKw] = useState("");
    const [searchParams] = useSearchParams();


    useEffect(() => {
        loadTrip();
    }, [searchParams]);

    return (
        <Container>
            <Form inline onSubmit={searchTrip}>
                <Row>
                    <Col xs="auto">
                        <Form.Control type="text" value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm chuyến xe..." className="mr-sm-2" />
                    </Col>
                    <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                    </Col>
                    <Col xs="auto">
                        <Button href="/add-trip">Thêm chuyến xe</Button>
                    </Col>
                </Row>
            </Form>
            <Row className="card-container">
                {trip.map(t => (
                    <Col md={3} xs={12} className='p-2' key={t.tripID}>
                        <Card className={styles.card}>
                            <Card.Body className={styles["card-body"]}>
                                <Card.Title className={styles["card-title"]}>{t.name}</Card.Title>
                                <Card.Text className={styles["card-text"]}>
                                    Tuyến đường: {t.routeID.name}<br />
                                    Xe khách: {t.busID.name}<br />
                                    Thời gian dự kiến khởi hành: {formatDate(t.departureTime)} <br />
                                    Thời gian đến dự kiến: {formatDate(t.arrivalTime)} <br />
                                    Bến xe phụ trách: {t.companyID.companyName} <br />
                                </Card.Text>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/trips/${t.tripID}`)}>Xem chi tiết</Button>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/update-trip/${t.tripID}`)}>Sửa</Button>
                                <Button variant="danger" className="m-1" onClick={() => nav(`/buses/${t.tripID}`)}>Xóa</Button>
                            </Card.Body>
                        </Card>
                    </Col>

                ))}
            </Row>
        </Container>
    );
};

export default Trip;
