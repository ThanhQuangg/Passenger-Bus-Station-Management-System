import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row, Form } from "react-bootstrap";
import { useNavigate, useSearchParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import './Category.scss';
import cookie from "react-cookies";

const Category = () => {
    const [cate, setCate] = useState([]);
    const [kw, setKw] = useState("");
    const [searchParams] = useSearchParams();
    const loadCates = async () => {
        try {
            let url = `${endpoints['categories']}?`;

            let kw = searchParams.get("kw");
            if (kw) {
                url = `${url}&kw=${kw}`;
            }

            const res = await APIs.get(url);
            setCate(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    const deleteCate = async (categoryID) => {
        if (window.confirm("Bạn có chắc chắn muốn xóa loại xe này không?")) {
            try {
                let url = `${endpoints['categories']}${categoryID}`;

                const res = await APIs.delete(url, {
                    headers: {
                        'Authorization': `${cookie.load('token')}`
                    }
                });

                if (res.status === 204) {
                    const updateCate = await APIs.get(endpoints['categories'], {
                        headers: {
                            'Authorization': `${cookie.load('token')}`
                        }
                    });
                    setCate(updateCate.data);
                }
            } catch (ex) {
                console.error(ex);
            }
        }
    }

    const searchCategory = (e) => {
        e.preventDefault();
        nav(`/categories?kw=${kw}`);
    };

    const nav = useNavigate();
    useEffect(() => {
        loadCates();
    }, []);
    return (
        <Container>
            <Form inline onSubmit={searchCategory}>
                <Row>
                    <Col xs="auto">
                        <Form.Control type="text" value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm loại..." className="mr-sm-2" />
                    </Col>
                    <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                    </Col>
                    <Col xs="auto">
                        <Button href="/add-cate">Thêm loại xe</Button>
                    </Col>
                </Row>
            </Form>
            <Row className="card-container" >
                {cate.map(c => (
                    <Col md={3} xs={12} className='p-2' key={c.categoryID}>
                        <Card>
                            <Card.Body>
                                <Card.Title>{c.name}</Card.Title>
                                <Card.Text>
                                    Mô tả: {c.description} <br />
                                </Card.Text>
                                <Button variant="primary" className="m-1" onClick={() => nav(`/update-cate/${c.categoryID}`)}>Sửa</Button>
                                <Button variant="danger" className="m-1" onClick={() => deleteCate(c.categoryID)}>Xóa</Button>

                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container >
    );
}
export default Category;