import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Container, Row, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../../configs/APIs";
import styles from "./Bus.scss";
import './Bus.scss';
import cookie from "react-cookies";
const Bus = () => {
    const [buses, setBuses] = useState([]);
    const [kw, setKw] = useState("");
    const [searchParams] = useSearchParams();
    const nav = useNavigate();

    const loadBuses = async () => {
        try {
            let url = `${endpoints['buses']}?`;

            let cateID = searchParams.get('cateID');
            if (cateID) {
                url = `${url}&cateID=${cateID}`;
            }

            let kw = searchParams.get("kw");
            if (kw) {
                url = `${url}&kw=${kw}`;
            }

            const res = await APIs.get(url);
            setBuses(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }
    
    const deleteBus = async (busID) => {
        if (window.confirm("Bạn có chắc chắn muốn xóa xe này không?")) {
            try {
                let url = `${endpoints['buses']}${busID}`;

                const res = await APIs.delete(url, {
                    headers: {
                        'Authorization': `${cookie.load('token')}`
                    }
                });

                if (res.status === 204) {
                    const updatedBuses = await APIs.get(endpoints['buses'], {
                        headers: {
                            'Authorization': `${cookie.load('token')}`
                        }
                    });
                    setBuses(updatedBuses.data);
                }
            } catch (ex) {
                console.error(ex);
            }
        }
    }


    const searchBuses = (e) => {
        e.preventDefault();
        nav(`/buses?kw=${kw}`);
    };

    useEffect(() => {
        loadBuses();
    }, [searchParams]);

    return (
        <Container>
            <Form inline onSubmit={searchBuses}>
                <Row>
                    <Col xs="auto">
                        <Form.Control type="text" value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm xe khách..." className="mr-sm-2" />
                    </Col>
                    <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                    </Col>
                    <Col xs="auto">
                        <Button href="/add-bus">Thêm xe khách</Button>
                    </Col>
                </Row>
            </Form>

            <Row className="card-container" >
                {buses.map(b => (
                    <Col md={3} xs={12} className='p-2' key={b.busID}>
                        <Card className={styles.card}>
                            <Card.Img variant="top" src={b.avatar} />
                            <Card.Body className={styles["card-body"]}>
                                <Card.Title className={styles["card-title"]}>{b.name}</Card.Title>
                                <Card.Text className={styles["card-text"]}>
                                    Sức chứa: {b.capacity} <br />
                                    Loại xe: {b.categoryID.name}<br />
                                    Tên xe: {b.name}<br />
                                    Biển số xe: {b.plateNumber}
                                </Card.Text>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/add-bus/${b.busID}`)}>Sửa</Button>
                                <Button variant="danger" className="m-1" onClick={() => deleteBus(b.busID)}>Xóa</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default Bus;
