import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import { useNavigate, useSearchParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import './Home.scss';

const Home = () => {

    const [buses, setBuses] = useState([]);
    const [trips, setTrips] = useState([]);
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
    const loadTrips = async () => {
        try {
            let url = `${endpoints['trips']}?`;
            let q = searchParams.get("q");
            if (q) {
                url = `${url}&kw=${q}`;
            }

            const res = await APIs.get(url);
            setTrips(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadBuses();
    }, [searchParams]);

    useEffect(() => {
        loadTrips();
    }, [searchParams]);


    return (
        <>


            <Container>
                <Row className="card-container">
                    <h1>Thông tin chuyến xe</h1>
                    {trips.map(t => (
                        <Col md={3} xs={12} className='p-2' key={t.tripID}>
                            <Card>
                                <Card.Img variant="top" src={t.busID.avatar} />
                                <Card.Body>
                                    <Card.Title>{t.name}</Card.Title>
                                    <Card.Text>
                                        Giá vé: {t.ticketPrice} VNĐ<br />
                                        Tuyến đường: {t.routeID.name}<br />
                                        Tên xe: {t.busID.name}<br />
                                        Biển số xe: {t.busID.plateNumber}
                                    </Card.Text>
                                    <Button variant="primary" className="m-1" onClick={() => nav(`/trips/${t.tripID}`)}>Xem chi tiết</Button>
                                    <Button variant="danger" className="m-1" onClick={() => nav(`/trips/${t.tripID}`)}>Đặt vé</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container>

            <Container>
                <Row className="card-container">
                    <h1>Thông tin xe khách</h1>
                    {buses.map(b => (
                        <Col md={3} xs={12} className='p-2' key={b.busID}>
                            <Card>
                                <Card.Img variant="top" src={b.avatar} />
                                <Card.Body>
                                    <Card.Title>{b.name}</Card.Title>
                                    <Card.Text>
                                        Sức chứa: {b.capacity} <br />
                                        Loại xe: {b.categoryID.name}<br />
                                        Tên xe: {b.name}<br />
                                        Biển số xe: {b.plateNumber}
                                    </Card.Text>
                                    <Button variant="primary" className="m-1" onClick={() => nav(`/buses/${b.busID}`)}>Xem chi tiết</Button>
                                    <Button variant="danger" className="m-1" onClick={() => nav(`/buses/${b.busID}`)}>Đặt vé</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container>

            {/* <br />
            <Container>
                <Row className="card-container">
                    <h1>Thông tin xe khách</h1>
                    {buses.map(b => (
                        <Col md={3} xs={12} className='p-2' key={b.busID}>
                            <Card>
                                <Card.Img variant="top" src={b.avatar} />
                                <Card.Body>
                                    <Card.Title>{b.name}</Card.Title>
                                    <Card.Text>
                                        Sức chứa: {b.capacity} <br />
                                        Loại xe: {b.categoryID.name}<br />
                                        Tên xe: {b.name}<br />
                                        Biển số xe: {b.plateNumber}
                                    </Card.Text>
                                    <Button variant="primary" className="m-1" onClick={() => nav(`/buses/${b.busID}`)}>Xem chi tiết</Button>
                                    <Button variant="danger" className="m-1" onClick={() => nav(`/buses/${b.busID}`)}>Đặt vé</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </Container> */}

        </>
    );
}

export default Home;