import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Container, Row, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../../configs/APIs";
import styles from "./Path.scss";
import './Path.scss';
import cookie from "react-cookies";
const Path = () => {
    const [route, setRoute] = useState([]);

    const loadRoute = async () => {
        try {
            let url = `${endpoints['routes']}?`;

            let kw = searchParams.get("kw");
            if (kw) {
                url = `${url}&kw=${kw}`;
            }

            const res = await APIs.get(url);
            setRoute(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const deleteRoute = async (routeID) => {
        if (window.confirm("Bạn có chắc chắn muốn xóa tuyến đường này không?")) {
            try {
                let url = `${endpoints['routes']}${routeID}`;

                const res = await APIs.delete(url, {
                    headers: {
                        'Authorization': `${cookie.load('token')}`
                    }
                });

                if (res.status === 204) {
                    const updateRoute = await APIs.get(endpoints['routes'], {
                        headers: {
                            'Authorization': `${cookie.load('token')}`
                        }
                    });
                    setRoute(updateRoute.data);
                }
            } catch (ex) {
                console.error(ex);
            }
        }
    }

    const nav = useNavigate();

    const serchPath = (e) => {
        e.preventDefault();
        nav(`/routes?kw=${kw}`);
    };

    const [kw, setKw] = useState("");
    const [searchParams] = useSearchParams();

    useEffect(() => {
        loadRoute();
    }, [searchParams]);



    const formatDuration = (duration) => {
        if (!duration) {
            return "Không xác định";
        }
        const [hours, minutes] = duration.split(':');
        return `${parseInt(hours, 10)} giờ ${parseInt(minutes, 10)} phút`;
    };

    return (
        <Container>
            <Form inline onSubmit={serchPath}>
                <Row>
                    <Col xs="auto">
                        <Form.Control type="text" value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm tuyến đường..." className="mr-sm-2" />
                    </Col>
                    <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                    </Col>
                    <Col xs="auto">
                        <Button href="/add-path">Thêm tuyến đường</Button>
                    </Col>
                </Row>
            </Form>
            <Row className="card-container">

                {route.map(r => {
                    const formattedTicketPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(r.ticketPrice);
                    return (
                        <Col md={3} xs={12} className='p-2' key={r.routeID}>
                            <Card className={styles.card}>
                                <Card.Body className={styles["card-body"]}>
                                    <Card.Title className={styles["card-title"]}>{r.name}</Card.Title>
                                    <Card.Text className={styles["card-text"]}>
                                        Địa điểm xuất phát: {r.startLocation} <br />
                                        Địa điểm đến: {r.endLocation}<br />
                                        Khoảng cách: {r.distance} km<br />
                                        Thời gian di chuyển dự kiến: {formatDuration(r.estimatedDuration)} <br />
                                        Mô tả: {r.description} <br />
                                        Giá vé: {formattedTicketPrice} <br />
                                    </Card.Text>
                                    <Button variant="primary" className="m-1" onClick={() =>  nav(`/update-route/${r.routeID}`)}>Sửa</Button>
                                    <Button variant="danger" className="m-1" onClick={() => deleteRoute(r.routeID)}>Xóa</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    );
                })}
            </Row>
        </Container>
    );
};

export default Path;
