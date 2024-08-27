import { useEffect, useState } from "react";
import { Col, Container, Image, Row, Button } from "react-bootstrap";
import { useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import MySpinner from "../../Commons/MySpinner";

const BusDetail = () => {
    const [bus, setBus] = useState(null);
    const {busID} = useParams();

    const loadBuses = async () => {
        try {
            let res = await APIs.get(endpoints['bus_detail'](busID));
            setBus(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadBuses();
    }, [busID]);

    

    return (
        <Container>
            <h1 className="text-center text-info mt-5 mb-5 fs-lg">CHI TIẾT XE KHÁCH</h1>
            {bus === null ? <MySpinner /> : <>
                <Row>
                    <Col md={5} xs={6}>
                        <Image src={bus.avatar} rounded fluid />
                    </Col>
                    <Col md={7} xs={6}>
                        <h1>{bus.name}</h1>    
                        <h3>Nhà xe sở hữu: {bus.companyID.companyName}</h3>
                        <h3> Biển số xe: {bus.plateNumber}</h3>
                        <h3>Sức chứa của xe: {bus.capacity}</h3>
                        <h3>Loại xe: {bus.categoryID.name}</h3>
                    </Col>
                </Row>
            </>}
        </Container>
    )
}

export default BusDetail;