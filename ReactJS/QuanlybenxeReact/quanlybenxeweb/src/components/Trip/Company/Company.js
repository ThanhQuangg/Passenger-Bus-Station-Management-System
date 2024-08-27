import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Container, Row, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../../configs/APIs";
import styles from "./Company.scss";
import './Company.scss';
import cookie from "react-cookies";
const Company = () => {
    const [company, setCompany] = useState([]);

    const loadCompanies = async () => {
        try {
            let url = `${endpoints['companies']}?`;

            let kw = searchParams.get("kw");
            if (kw) {
                url = `${url}&kw=${kw}`;
            }

            const res = await APIs.get(url);
            setCompany(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const deleteCompany = async (companyID) => {
        if (window.confirm("Bạn có chắc chắn muốn xóa nhà xe này không?")) {
            try {
                let url = `${endpoints['companies']}${companyID}`;

                const res = await APIs.delete(url, {
                    headers: {
                        'Authorization': `${cookie.load('token')}`
                    }
                });

                if (res.status === 204) {
                    const updateCompany = await APIs.get(endpoints['companies'], {
                        headers: {
                            'Authorization': `${cookie.load('token')}`
                        }
                    });
                    setCompany(updateCompany.data);
                }
            } catch (ex) {
                console.error(ex);
            }
        }
    }

    const nav = useNavigate();

    const searchCompany = (e) => {
        e.preventDefault();
        nav(`/companies?kw=${kw}`);
    };

    const [kw, setKw] = useState("");
    const [searchParams] = useSearchParams();


    useEffect(() => {
        loadCompanies();
    }, [searchParams]);

    return (
        <Container>
             <Form inline onSubmit={searchCompany}>
                <Row>
                    <Col xs="auto">
                        <Form.Control type="text" value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm nhà xe..." className="mr-sm-2" />
                    </Col>
                    <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                    </Col>
                    <Col xs="auto">
                        <Button href="/add-company">Thêm nhà xe</Button>
                    </Col>
                </Row>
            </Form>
            <Row className="card-container" >
                {company.map(c => (
                    <Col md={3} xs={12} className='p-2' key={c.companyID}>
                        <Card className={styles.card}>
                            <Card.Img variant="top" src={c.avatar} />
                            <Card.Body className={styles["card-body"]}>
                                <Card.Title className={styles["card-title"]}>{c.companyName}</Card.Title>
                                <Card.Text className={styles["card-text"]}>
                                    Địa chỉ: {c.address} <br />
                                    Email: {c.email}<br />
                                    Số điện thoại: {c.phoneNumber}<br />
                                </Card.Text>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/update-company/${c.companyID}`)}>Sửa</Button>
                                <Button variant="danger" className="m-1" onClick={() => deleteCompany(c.companyID)}>Xóa</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default Company;
