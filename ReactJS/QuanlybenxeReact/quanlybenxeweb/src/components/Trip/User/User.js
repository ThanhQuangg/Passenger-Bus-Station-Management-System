import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import APIs, { endpoints } from "../../../configs/APIs";
import styles from "./User.scss";
import './User.scss';
const User = () => {
    const [user, setUser] = useState([]);

    const loadUser = async () => {
        try {
            let url = `${endpoints['users']}?`;

            const res = await APIs.get(url);
            setUser(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const nav = useNavigate();

    useEffect(() => {
        loadUser();
    }, []);

    return (
        <Container>
            <Row className="card-container">
                {user.map(u => (
                    <Col md={3} xs={12} className='p-2' key={u.userID}>
                        <Card className={styles.card}>
                            <Card.Img variant="top" src={u.avatar} />
                            <Card.Body className={styles["card-body"]}>
                                <Card.Title className={styles["card-title"]}>{u.username}</Card.Title>
                                <Card.Text className={styles["card-text"]}>
                                    Role: {u.userRole}<br />
                                    Email: {u.email}<br />
                                    Số điện thoại: {u.phoneNumber} <br />
                                    Họ: {u.firstName} <br />
                                    Tên: {u.lastName} <br />
                                </Card.Text>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/buses/${u.userID}`)}>Sửa</Button>
                                <Button variant="danger" className="m-1" onClick={() => nav(`/buses/${u.userID}`)}>Xóa</Button>
                            </Card.Body>
                        </Card>
                    </Col>

                ))}
            </Row>
        </Container>
    );
};

export default User;
