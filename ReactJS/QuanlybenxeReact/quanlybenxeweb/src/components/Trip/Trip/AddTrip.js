import { useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddTrip = () => {
    const fields = [{
        label: "Tên chuyến xe",
        type: "text",
        field: "name"
    }, {
        label: "Thời gian khởi hành dự kiến",
        type: "text",
        field: "departureTime"
    }, {
        label: "Thời gian đến dự kiến",
        type: "text",
        field: "arrivalTime"
    }, {
        label: "Giá vé",
        type: "text",
        field: "ticketPrice"
    }, {
        label: "Trạng thái",
        type: "text",
        field: "status"
    }];

    const [trip, setTrip] = useState([]);
    const [bus, setBus] = useState([]);
    const [route, setRoute] = useState([]);
    const [company, setCompany] = useState([]);
    const nav = useNavigate();

    const change = (e, field) => {
        setTrip(current => {
            return { ...current, [field]: e.target.value }
        })
    }

    const addTrip = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in trip)
            if (key !== 'confirm')
                form.append(key, trip[key]);
        for (let pair of form.entries()) {
            console.log(`${pair[0]}: ${pair[1]}`);
        }
        try {
            let res = await APIs.post(endpoints['trips'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `${cookie.load('token')}`
                }

            });
            if (res.status === 201)
                nav("/trips");
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadBuses = async () => {
        try {
            let url = `${endpoints['buses']}?`;
            const res = await APIs.get(url);
            setBus(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadRoute = async () => {
        try {
            let url = `${endpoints['routes']}?`;
            const res = await APIs.get(url);
            setRoute(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    const loadCompany = async () => {
        try {
            let url = `${endpoints['companies']}?`;
            const res = await APIs.get(url);
            setCompany(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadBuses();
        loadRoute();
        loadCompany();
    }, []);
    return (
        <Container>
            <h1 className="text-center text-info mt-1">ĐĂNG KÝ CHUYẾN XE</h1>
            <Form onSubmit={addTrip}>
                {fields.map(f => <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                    <Form.Label>{f.label}</Form.Label>
                    <Form.Control onChange={e => change(e, f.field)} value={trip[f.field]} type={f.type} placeholder={f.label} />
                </Form.Group>)}
                <Form.Group key="busID" className="mb-3" controlId="busID">
                    <Form.Label>Xe khách</Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'busID')} value={trip['busID']} placeholder="Chọn xe khách">
                        <option value="">Chọn xe khách</option>
                        {bus.map(bus => (
                            <option key={bus.busID} value={bus.busID}>{bus.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group key="routeID" className="mb-3" controlId="routeID">
                    <Form.Label>Tuyến đường </Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'routeID')} value={trip['routeID']} placeholder="Chọn tuyến đường">
                        <option value="">Chọn tuyến đường</option>
                        {route.map(route => (
                            <option key={route.routeID} value={route.routeID}>{route.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group key="routeID" className="mb-3" controlId="routeID">
                    <Form.Label>Nhà xe</Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'companyID')} value={trip['companyID']} placeholder="Chọn nhà xe">
                        <option value="">Chọn nhà xe</option>
                        {company.map(company => (
                            <option key={company.companyID} value={company.companyID}>{company.companyName}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">Đăng ký</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default AddTrip;