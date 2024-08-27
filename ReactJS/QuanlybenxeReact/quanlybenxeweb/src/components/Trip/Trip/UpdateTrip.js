import { useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const UpdateTrip = () => {
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

    const [trip, setTrip] = useState({});
    const [bus, setBus] = useState([]);
    const [route, setRoute] = useState([]);
    const [company, setCompany] = useState([]);
    const nav = useNavigate();
    const { tripID } = useParams();

    useEffect(() => {
        const loadTrip = async () => {
            try {
                let res = await APIs.get(`${endpoints['trips']}${tripID}`);
                setTrip(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };

        const loadBuses = async () => {
            try {
                let url = `${endpoints['buses']}?`;
                const res = await APIs.get(url);
                setBus(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };

        const loadRoute = async () => {
            try {
                let url = `${endpoints['routes']}?`;
                const res = await APIs.get(url);
                setRoute(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };

        const loadCompany = async () => {
            try {
                let url = `${endpoints['companies']}?`;
                const res = await APIs.get(url);
                setCompany(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };

        loadTrip();
        loadBuses();
        loadRoute();
        loadCompany();
    }, [tripID]);

    const change = (e, field) => {
        setTrip(current => ({
            ...current,
            [field]: e.target.value
        }));
    };

    const updateTrip = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in trip) {
            form.append(key, trip[key]);
        }

        try {
            let res = await APIs.put(`${endpoints['trips']}${tripID}`, form, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `${cookie.load('token')}`
                }
            });
            if (res.status === 200) {
                nav("/trips");
            }
        } catch (ex) {
            console.error(ex);
        }
    };

    return (
        <Container>
            <h1 className="text-center text-info mt-1">CẬP NHẬT CHUYẾN XE</h1>
            <Form onSubmit={updateTrip}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={trip[f.field] || ''}
                            type={f.type}
                            placeholder={f.label}
                        />
                    </Form.Group>
                ))}
                <Form.Group key="busID" className="mb-3" controlId="busID">
                    <Form.Label>Xe khách</Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'busID')} value={trip['busID'] || ''}>
                        <option value="">Chọn xe khách</option>
                        {bus.map(b => (
                            <option key={b.busID} value={b.busID}>{b.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group key="routeID" className="mb-3" controlId="routeID">
                    <Form.Label>Tuyến đường</Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'routeID')} value={trip['routeID'] || ''}>
                        <option value="">Chọn tuyến đường</option>
                        {route.map(r => (
                            <option key={r.routeID} value={r.routeID}>{r.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group key="companyID" className="mb-3" controlId="companyID">
                    <Form.Label>Nhà xe</Form.Label>
                    <Form.Control as="select" onChange={e => change(e, 'companyID')} value={trip['companyID'] || ''}>
                        <option value="">Chọn nhà xe</option>
                        {company.map(c => (
                            <option key={c.companyID} value={c.companyID}>{c.companyName}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit">CẬP NHẬT</Button>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default UpdateTrip;
