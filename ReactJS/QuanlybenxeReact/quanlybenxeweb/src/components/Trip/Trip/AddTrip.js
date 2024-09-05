import { useRef, useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddTrip = () => {
    const fields = [{
        label: "Tên chuyến xe",
        type: "text",
        field: "name"
    }, {
        label: "Thời gian khởi hành",
        type: "datetime-local",
        field: "departureTime"
    }, {
        label: "Thời gian đến",
        type: "datetime-local",
        field: "arrivalTime"
    }, {
        label: "Giá vé",
        type: "text",
        field: "ticketPrice "
    }, {
        label: "Status",
        type: "text",
        field: "status"
    }];

    const [trip, setTrip] = useState({});
    const [routes, setRoutes] = useState([]);
    const [buses, setBuses] = useState([]);
    const [companies, setCompanies] = useState([]);
    const [errors, setErrors] = useState({});
    const { tripID } = useParams();
    const nav = useNavigate();

    useEffect(() => {
        const loadTrip = async () => {
            if (tripID) {
                const res = await APIs.get(`${endpoints['trips']}${tripID}`);
                setTrip(res.data);
            }
        };

        loadTrip();
        loadRoutes();
        loadBuses();
        loadCompanies();
    }, [tripID]);

    const change = (e, field) => {
        setTrip(current => ({ ...current, [field]: e.target.value }));
    };

    const addOrUpdateTrip = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in trip) {
            form.append(key, trip[key]);
        }

        try {
            let res;
            if (tripID) {
                res = await APIs.post(`${endpoints['trips']}${tripID}`, form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            } else {
                res = await APIs.post(endpoints['trips'], form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            }
            if (res.status === 201 || res.status === 200) {
                nav("/trips");
            }
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadRoutes = async () => {
        try {
            const res = await APIs.get(endpoints['routes']);
            setRoutes(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadBuses = async () => {
        try {
            const res = await APIs.get(endpoints['buses']);
            setBuses(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadCompanies = async () => {
        try {
            const res = await APIs.get(endpoints['companies']);
            setCompanies(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    return (
        <Container>
            <h1 className="text-center text-info mt-1">
                {tripID ? "CẬP NHẬT CHUYẾN XE" : "THÊM CHUYẾN XE"}
            </h1>
            <Form onSubmit={addOrUpdateTrip}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={trip[f.field] || ""}
                            type={f.type}
                            placeholder={f.label}
                            isInvalid={!!errors[f.field]}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors[f.field]}
                        </Form.Control.Feedback>
                    </Form.Group>
                ))}
                <Form.Group key="routeID" className="mb-3" controlId="routeID">
                    <Form.Label>Tuyến đường</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={e => change(e, 'routeID')}
                        value={trip['routeID'] || ""}
                        placeholder="Chọn tuyến đường"
                        isInvalid={!!errors.routeID}
                    >
                        <option value="">Chọn tuyến đường</option>
                        {routes.map(route => (
                            <option key={route.routeID} value={route.routeID}>{route.name}</option>
                        ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        {errors.routeID}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group key="busID" className="mb-3" controlId="busID">
                    <Form.Label>Xe khách</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={e => change(e, 'busID')}
                        value={trip['busID'] || ""}
                        placeholder="Chọn xe khách"
                        isInvalid={!!errors.busID}
                    >
                        <option value="">Chọn xe khách</option>
                        {buses.map(bus => (
                            <option key={bus.busID} value={bus.busID}>{bus.name}</option>
                        ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        {errors.busID}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group key="companyID" className="mb-3" controlId="companyID">
                    <Form.Label>Công ty</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={e => change(e, 'companyID')}
                        value={trip['companyID'] || ""}
                        placeholder="Chọn công ty"
                        isInvalid={!!errors.companyID}
                    >
                        <option value="">Chọn công ty</option>
                        {companies.map(company => (
                            <option key={company.companyID} value={company.companyID}>{company.companyName}</option>
                        ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        {errors.companyID}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">
                        {tripID ? "Cập nhật" : "Thêm"}
                    </Button>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default AddTrip;
